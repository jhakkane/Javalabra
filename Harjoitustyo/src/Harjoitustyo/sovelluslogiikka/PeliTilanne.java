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
    private boolean negatiivisia=false;
    private boolean tasopeli=false;
    private boolean potenssi=false;
    private int oikeitaVastauksiaTallaKierroksella;
    private int kierros=0;
    private int vaihe=2;
    private Kysymys kysymys=null;

    public Kysymys getKysymys() {
        return kysymys;
    }

    public void setKysymys(Kysymys kysymys) {
        this.kysymys = kysymys;
    }
    
    public int getOperandMax() {
        return operandMax;
    }

    public int getVaihe() {
        return vaihe;
    }

    public void setVaihe(int vaihe) {
        this.vaihe = vaihe;
    }

    public void setOperandMax(int operandMax) {
        this.operandMax = operandMax;
    }
    
    public boolean isJako() {
        return jako;
    }

    public void setTasopeli(boolean tasopeli) {
        this.tasopeli = tasopeli;
    }

    public boolean isTasopeli() {
        return tasopeli;
    }

    public boolean isKerto() {
        return kerto;
    }

    public void setMurtolukuja(boolean murtolukuja) {
        this.murtolukuja = murtolukuja;
    }

    public void setKierros(int kierros) {
        this.kierros = kierros;
    }

    public boolean isPotenssi() {
        return potenssi;
    }

    public void setPotenssi(boolean potenssi) {
        this.potenssi = potenssi;
    }

    public int getKierros() {
        return kierros;
    }

    public void setOikeitaVastauksiaTallaKierroksella(int oikeitaVastauksiaTallaKierroksella) {
        this.oikeitaVastauksiaTallaKierroksella = oikeitaVastauksiaTallaKierroksella;
    }

    public int getOikeitaVastauksiaTallaKierroksella() {
        return oikeitaVastauksiaTallaKierroksella;
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

    /**
     * Setterinä toimimisen lisäksi tarkistaa, että vähintään yksi operaatio
     * on aina sallittu. Jos pelaaja yrittää ottaa kaikki operaatiot pois käytöstä,
     * sallitaan pluslasku.
     * @param plus 
     */
    public void setPlus(boolean plus) {
        this.plus = plus;
        yksiOperaatioSallitaanAina();
    }

    /**
     * Setterinä toimimisen lisäksi tarkistaa, että vähintään yksi operaatio
     * on aina sallittu. Jos pelaaja yrittää ottaa kaikki operaatiot pois käytöstä,
     * sallitaan pluslasku.
     * @param miinus 
     */
    public void setMiinus(boolean miinus) {
        this.miinus = miinus;
        yksiOperaatioSallitaanAina();
    }

    /**
     * Setterinä toimimisen lisäksi tarkistaa, että vähintään yksi operaatio
     * on aina sallittu. Jos pelaaja yrittää ottaa kaikki operaatiot pois käytöstä,
     * sallitaan pluslasku.
     * @param kerto 
     */
    public void setKerto(boolean kerto) {
        this.kerto = kerto;
        yksiOperaatioSallitaanAina();
    }

    /**
     * Setterinä toimimisen lisäksi tarkistaa, että vähintään yksi operaatio
     * on aina sallittu. Jos pelaaja yrittää ottaa kaikki operaatiot pois käytöstä,
     * sallitaan pluslasku.
     * @param jako 
     */
    public void setJako(boolean jako) {
        this.jako = jako;
        yksiOperaatioSallitaanAina();
    }

    /**
     * Tarkistaa, että vähintään yksi operaatio
     * on aina sallittu. Jos pelaaja yrittää ottaa kaikki operaatiot pois käytöstä,
     * sallitaan pluslasku.
     */
    private void yksiOperaatioSallitaanAina() {
        if ((isJako() == false) && (isKerto() == false) &&
                (isMiinus() == false) && (isPlus() == false)) {
            setPlus(true);
        }
    }

    public int getOpLkm() {
        return opLkm;
    }

    public void setNegatiivisia(boolean negatiivisia) {
        this.negatiivisia = negatiivisia;
    }

    public boolean isNegatiivisia() {
        return negatiivisia;
    }
    
    
    /**
     * Pitää kirjaa kaikista vastauksista ja oikeista vastauksista.
     * @param oikeaVastaus 
     */
    public void vastattu(boolean oikeaVastaus) {
        if (oikeaVastaus) {
            oikeitaVastauksia++;
        }
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
            murtolukuja = Boolean.parseBoolean(asetuksetTaulukko[7]);   
            negatiivisia = Boolean.parseBoolean(asetuksetTaulukko[8]);   
            operandMax = Integer.parseInt(asetuksetTaulukko[9]); 
            tasopeli = Boolean.parseBoolean(asetuksetTaulukko[10]); 
            oikeitaVastauksiaTallaKierroksella = Integer.parseInt(asetuksetTaulukko[11]); 
            kierros = Integer.parseInt(asetuksetTaulukko[12]); 
            vaihe = Integer.parseInt(asetuksetTaulukko[13]); 
            vastattuja = Integer.parseInt(asetuksetTaulukko[14]); 
            
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
        teksti=teksti+murtolukuja+"\n";    
        teksti=teksti+negatiivisia+"\n";   
        teksti=teksti+operandMax+"\n";    
        teksti=teksti+tasopeli+"\n";
        teksti=teksti+oikeitaVastauksiaTallaKierroksella+"\n";
        teksti=teksti+kierros+"\n";
        teksti=teksti+vaihe+"\n";        
        teksti=teksti+vastattuja+"\n";  
        
        return teksti;
    }
    
    
}
