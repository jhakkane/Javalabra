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
public class PeliTilanne {

    private String nimi;
    private int vastattuja;
    private int oikeitaVastauksia;
    private int opLkm;
    private boolean sulkuja;
    private boolean plus;
    private boolean miinus;
    private boolean kerto;
    private boolean jako;

    public PeliTilanne() {
        this.nimi="Anonyymi";
        this.vastattuja=0;
        this.oikeitaVastauksia=0;
        this.opLkm=2;
        this.sulkuja=false;
        this.plus=true;
        this.miinus=false;
        this.kerto=false;
        this.jako=false;
    }
    
    public boolean isJako() {
        return jako;
    }

    public boolean isKerto() {
        return kerto;
    }

    public boolean isMiinus() {
        return miinus;
    }

    public boolean isPlus() {
        return plus;
    }

    public void setPlus(boolean plus) {
        this.plus = plus;
    }

    public void setMiinus(boolean miinus) {
        this.miinus = miinus;
    }

    public void setKerto(boolean kerto) {
        this.kerto = kerto;
    }

    public void setJako(boolean jako) {
        this.jako = jako;
    }


    
    public int getOpLkm() {
        return opLkm;
    }

    public void oikeinVastattu() {
        oikeitaVastauksia++;
    }
    
    public void vastattu() {
        vastattuja++;
    }

    public int getKysyttyja() {
        return vastattuja;
    }

    public int getOikeitaVastauksia() {
        return oikeitaVastauksia;
    }
    
    public int oikeitaVastauksiaSuhteellisesti() {
        if (vastattuja==0) {
            return 0;
        }
        
        return (int)Math.floor((1.0*oikeitaVastauksia)/(1.0*vastattuja)*100.0);
    }
    
    public void setOpLkm(int opLkm) {
        this.opLkm = opLkm;
    }

    public boolean isSulkuja() {
        return sulkuja;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public void setSulkuja(boolean sulkuja) {
        this.sulkuja = sulkuja;
    }

    public void asetaAsetukset(String asetukset) throws Exception {
        //asetusten järjestys näkyy ylempää
        
        String[] asetuksetTaulukko = asetukset.split("\n");
        
        try {
            nimi=asetuksetTaulukko[0];
            opLkm = Integer.parseInt(asetuksetTaulukko[1]);
            sulkuja = Boolean.parseBoolean(asetuksetTaulukko[2]);            
            plus = Boolean.parseBoolean(asetuksetTaulukko[3]);    
            miinus = Boolean.parseBoolean(asetuksetTaulukko[4]);                
            kerto = Boolean.parseBoolean(asetuksetTaulukko[5]);                
            jako = Boolean.parseBoolean(asetuksetTaulukko[6]);    
        } catch (Exception e) {
            throw e;
        }

        
    }
    
    @Override
    public String toString() {
        String teksti="";
        teksti=teksti+nimi+"\n";
        teksti=teksti+opLkm+"\n";
        teksti=teksti+sulkuja+"\n";
        teksti=teksti+plus+"\n";
        teksti=teksti+miinus+"\n";
        teksti=teksti+kerto+"\n";        
        teksti=teksti+jako+"\n";    
        
        return teksti;
    }
    
    
}
