/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;

/**Sisältää kaikki pelin asetukset, mm. pelaajan nimen ja käytetäänkö
 * laskuissa sulkuja tai murtolukuja.
 *
 * @author JH
 */
public class PeliTilanne {

    private String nimi="Anonyymi";
    private int vastattuja=0;
    private int oikeitaVastauksia=0;
    private int opLkm=2;
    private int operandMax=20;
    private boolean sulkuja=false;
    private boolean plus=true;
    private boolean miinus=false;
    private boolean kerto=false;
    private boolean jako=false;
    private boolean murtolukuja=false;
    
    public int getOperandMax() {
        return operandMax;
    }

    public void setOperandMax(int operandMax) {
        this.operandMax = operandMax;
    }
    
    public boolean isJako() {
        return jako;
    }

    public boolean isKerto() {
        return kerto;
    }

    public void setMurtolukuja(boolean murtolukuja) {
        this.murtolukuja = murtolukuja;
    }

    public boolean isMurtolukuja() {
        return murtolukuja;
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

    public void yksiOperaatioSallitaanAina() {
        if ((isJako() == false) && (isKerto() == false) &&
                (isMiinus() == false) && (isPlus() == false)) {
            setPlus(true);
        }
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
        
        if (opLkm < 2) {
            opLkm=2;
        }
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
            operandMax = Integer.parseInt(asetuksetTaulukko[7]); 
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
        teksti=teksti+operandMax+"\n";    
        
        return teksti;
    }
    
    
}
