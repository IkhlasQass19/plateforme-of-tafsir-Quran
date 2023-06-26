package com.example.demo.Service;

import com.example.demo.Model.Response;
import com.example.demo.Model.Tafassir;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SolrServ {

    public Response getSearchTxt(String texte) throws SolrServerException, IOException {
        SolrDocumentList list = null;
        List<Tafassir> tafasirList=new ArrayList<>();
        long startTime = System.currentTimeMillis();
        String urlString = "http://solr:8983/solr/coreprojetmr/";
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
        solr.setParser( new XMLResponseParser());
        SolrQuery query = new SolrQuery();
        query.set("q.op","OR");
        String quer="MoufaNom:*"+texte +"*\n" +
                "Description:*"+texte+"*\n"+
                "Aya:*"+texte +"*\n"+
                "Text:*"+texte+"*\n"+
                "annee:*"+texte+"*\n"+
                "NTafsir:*"+texte+"*\n"+
                "Type:*"+texte+"*\n"+
                "Aya4:*"+texte+"*\n"+
                "Aya3:*"+texte+"*\n"+
                "Aya2:*"+texte+"*\n"+
                "SourahNom:*"+texte+"*\n"+
                "Nsoura:*"+texte+"*\n"+
                "NAya:*"+texte+"*\n";
        query.setQuery(quer);
        query.set("rows",50);
        QueryResponse response = solr.query(query);
        System.out.println(query);
        list = response.getResults();
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime)/ 1000.0;
        System.out.println(response.getResults().getNumFound());
        Response reponse=new Response();
        reponse.setNumFound(response.getResults().getNumFound());
        reponse.setTempExecution(responseTime);
        for (SolrDocument doc : list) {
            String texteArabeSansLatin = (String) doc.getFieldValue("Text");
            texteArabeSansLatin =texteArabeSansLatin.replaceAll("\\p{IsLatin}", "");
            Tafassir taf=new Tafassir();
            taf.setNAya((Integer) doc.getFieldValue("NAya"));
            taf.setAya((String) doc.getFieldValue("Aya"));
            taf.setAnne((Integer) doc.getFieldValue("annee"));
            taf.setNTafsir((Integer) doc.getFieldValue("NTafsir"));
            taf.setDescription((String) doc.getFieldValue("Description"));
            taf.setText(texteArabeSansLatin);
            taf.setKtab((String) doc.getFieldValue("Ktab"));
            taf.setMoufaNom((String) doc.getFieldValue("MoufaNom"));
            taf.setType((String) doc.getFieldValue("Description"));
            taf.setSourahNom((String) doc.getFieldValue("SourahNom"));
            taf.setNsoura((Integer) doc.getFieldValue("Nsoura"));
            tafasirList.add(taf);
        }
        //trier la list du taffassir 3la 7sab Aya :
        Collections.sort(tafasirList, (taf1, taf2) -> {
            int nsouraComparison = Integer.compare(taf1.getNsoura(), taf2.getNsoura());
            if (nsouraComparison != 0) {
                return nsouraComparison;
            }
            return Integer.compare(taf1.getNAya(), taf2.getNAya());
        });
        reponse.setTaffassir(tafasirList);
        return  reponse;
    }
}
