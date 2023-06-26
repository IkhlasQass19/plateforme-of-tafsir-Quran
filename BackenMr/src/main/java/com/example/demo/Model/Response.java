package com.example.demo.Model;

import java.util.List;

public class Response {
    List<Tafassir> taffassir;
    long numFound;
    double TempExecution;

    public List<Tafassir> getTaffassir() {
        return taffassir;
    }

    public void setTaffassir(List<Tafassir> taffassir) {
        this.taffassir = taffassir;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound =  numFound;
    }

    public double getTempExecution() {
        return TempExecution;
    }

    public void setTempExecution(double tempExecution) {
        TempExecution = tempExecution;
    }
}
