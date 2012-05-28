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

    
    public PeliTilanne() {
        this.setOpLkm(2);
        this.setSulkuja(false);
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

    public void asetaAsetukset(String asetukset) {
        //asetusten järjestys näkyy ylempää
        
        String[] asetuksetTaulukko = asetukset.split("\n");
        
        nimi=asetuksetTaulukko[0];
        opLkm = Integer.parseInt(asetuksetTaulukko[1]);
        sulkuja = Boolean.parseBoolean(asetuksetTaulukko[2]);
        
    }
    
    @Override
    public String toString() {
        String teksti="";
        teksti=teksti+nimi+"\n";
        teksti=teksti+opLkm+"\n";
        teksti=teksti+sulkuja+"\n";
        
        return teksti;
    }
    
    
}
