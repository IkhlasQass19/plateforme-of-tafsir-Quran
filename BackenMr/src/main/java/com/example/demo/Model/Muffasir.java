package com.example.demo.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Muffasir {
    String nom;
    int NTafssir;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNTafssir() {
        return NTafssir;
    }

    public void setNTafssir(int NTafssir) {
        this.NTafssir = NTafssir;
    }
}
