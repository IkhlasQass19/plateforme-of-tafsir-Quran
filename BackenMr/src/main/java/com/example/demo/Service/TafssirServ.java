package com.example.demo.Service;
import com.example.demo.Model.Muffasir;
import com.example.demo.Model.Tafssir;
import com.example.demo.Repository.TafssirRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TafssirServ {
    private static TafssirRepos tafssirRepos = null;
    @Autowired
    public TafssirServ(TafssirRepos tafssirRepos ){
        this.tafssirRepos=tafssirRepos;
    }
    public static List<Muffasir> getMuffassirun(){

        List<Tafssir> a= tafssirRepos.findAll();
        ArrayList<Muffasir> mufassirun = new ArrayList<>();
        for (Tafssir tafssir : a) {
            String nom=tafssir.getNom();
            int ntafssir=tafssir.getNTafsir();
            System.out.println(nom);
            System.out.println(ntafssir);
            Muffasir m=new Muffasir();
            m.setNom(nom);
            m.setNTafssir(ntafssir);
            mufassirun.add(m);
        }
        return mufassirun;
    }
}
