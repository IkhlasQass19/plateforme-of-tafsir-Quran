package com.example.demo;

import com.example.demo.Model.Muffasir;
import com.example.demo.Model.Response;
import com.example.demo.Model.Tafassir;
import com.example.demo.Service.SolrServ;
import com.example.demo.Service.TafssirServ;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@SpringBootApplication
@RequestMapping(path="")
public class Run {

    static String host="localhost";
    static String port="8983";

    public Run(SolrServ solrService) {
        this.solrService = solrService;
    }

    public static void main(String[] args) {

        SpringApplication.run(Run.class,args);
    }
    @GetMapping("/mufassirun")
    public List<Muffasir> getAllMuffasir(){
        return TafssirServ.getMuffassirun();
    }
    @GetMapping("/surah/{surahNo}/{tafsirId}")
    public  List<Tafassir> executeQuery(@PathVariable int surahNo, @PathVariable int tafsirId) throws SolrServerException, IOException {

        List<Tafassir> tafasirList=new ArrayList<>();
        String urlString = "http://solr:8983/solr/coreprojetmr/";
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
        solr.setParser( new XMLResponseParser());
        SolrQuery query = new SolrQuery();
        query.set("q.op","AND");
        query.set("q", "NTafsir"+":"+tafsirId+"\n Nsoura:"+surahNo);
        QueryResponse response = solr.query(query);
        System.out.println(query);
        SolrDocumentList docList = response.getResults();
        System.out.println(docList.size());
        if(docList.size()==0)
        {
            return tafasirList;
            // return "هذا المفسر لم يفسر هاته الاية";
        }

        for (SolrDocument doc : docList) {
            System.out.println(doc.getFieldValue("Text"));
            System.out.println(doc.getFieldValue("NAya"));
            String texteArabeSansLatin = (String) doc.getFieldValue("Text");
            texteArabeSansLatin =texteArabeSansLatin.replaceAll("\\p{IsLatin}", "");
            Tafassir taf=new Tafassir();
            taf.setNAya((Integer) doc.getFieldValue("NAya"));
            taf.setText(texteArabeSansLatin);
            tafasirList.add(taf);

        }
        //trier la list du taffassir 3la 7sab Aya :
        Collections.sort(tafasirList, (taf1, taf2) -> Integer.compare(taf1.getNAya(), taf2.getNAya()));
        return tafasirList;
    }
    private final SolrServ solrService;

    @GetMapping(path = "/searchTxt/{texte}")
    public Response getSearchTxt(@PathVariable  String texte) throws SolrServerException, IOException {
        Response reponse = this.solrService.getSearchTxt(texte);
        return reponse;
    }

}
