/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * @author JH
 */
public class Asetukset {

    private int opLkm;
    private boolean sulkuja;
    
    //lisätään kaikki asetukset muuttujat-taulukkoon
    //ne myös ladataan tässä järjestyksessä

    public int getOpLkm() {
        return opLkm;
    }

    public void setOpLkm(int opLkm) {
        this.opLkm = opLkm;
    }

    public boolean isSulkuja() {
        return sulkuja;
    }

    public void setSulkuja(boolean sulkuja) {
        this.sulkuja = sulkuja;
    }

    public void asetaAsetukset(String asetukset) {
        //asetusten järjestys näkyy ylempää
        
        String[] asetuksetTaulukko = asetukset.split("\n");
        
        opLkm = Integer.parseInt(asetuksetTaulukko[0]);
        sulkuja = Boolean.parseBoolean(asetuksetTaulukko[1]);
        
    }
    
    @Override
    public String toString() {
        String teksti="";
        teksti=teksti+opLkm+"\n";
        teksti=teksti+sulkuja+"\n";
        
        return teksti;
    }
    
    
}
