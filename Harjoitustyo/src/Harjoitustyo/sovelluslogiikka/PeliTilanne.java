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
    private int opLkm = Luokkakirjasto.PELITILANNE_OLETUS_OPERANDIEN_LKM;
    private int operandMax = Luokkakirjasto.PELITILANNE_OLETUS_OPERANDIN_KOKO;
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

    /**
     * Kasvattaa isolukua siten, että se kasvaa niin paljon kuin
     * yhdessä operaatiossa on mahdollista näillä asetuksilla.
     * @param isoluku
     * @return 
     */
    private long kasvataIsolukuaAsetustenMukaan(long isoluku) {
        if (isJako() || isKerto()) {
            isoluku = kasvataIsolukuaJosJakoTaiKerto(isoluku);
        } else {
            isoluku = kasvataIsolukuaJosEiJakoaTaiKertoa(isoluku);            
        }
        return isoluku;
    }

    private long kasvataIsolukuaJosEiJakoaTaiKertoa(long isoluku) {
        if (isPotenssi()) {
            isoluku = isoluku+(long)Math.pow(getOperandMax(),
                Luokkakirjasto.PELITILANNE_EKSPONENTTI_MAX);                             
        } else {
            isoluku = isoluku+getOperandMax();
        }
        return isoluku;
    }

    private long kasvataIsolukuaJosJakoTaiKerto(long isoluku) {
        if (isPotenssi()) {
            isoluku = isoluku*(long)Math.pow(getOperandMax(),
                Luokkakirjasto.PELITILANNE_EKSPONENTTI_MAX);                             
        } else {
            isoluku = isoluku*getOperandMax();
        }
        return isoluku;
    }

    /**
     * Pienentää operandien lukumäärää yhdellä jos se on yli kaksi.
     * Muutoin jaksaa operandin maksimikoon kahdella.
     */
    private void muutaAsetuksiaNiinEttaSuurinMahdollinenLukuOnPienempi() {
        //jos isoLuku on yli rajan, tehdään muutoksia asetuksiin
        if (getOpLkm() > 2) {
            setOpLkm(getOpLkm()-1);
        } else {
            setOperandMax((int)(getOperandMax()/2));
        }
    }

    /**
     * Selvittää, onko näillä asetuksilla saatava suurin mahdollinen luku
     * osoittajassa tai nimittäjässä suurempi kuin mitä int-muuttujaan mahtuun.
     * 
     * Tässä oletetaan, että suurin mahdollinen nimittäjä on pienempi tai
     * yhtäsuuri kuin suurin mahdollinen operandi.
     * @param isoluku
     * @param isoLukuAlleRajan
     * @return 
     */
    private boolean selvitaOnkoIsoinMahdollinenLukuAlleIntRajan(boolean isoLukuAlleRajan) {
        long isoluku = (long)Math.pow(getOperandMax(),
            Luokkakirjasto.PELITILANNE_EKSPONENTTI_MAX);
        
        for (int i = 0; i < getOpLkm()-1; i++) {
            isoluku = kasvataIsolukuaAsetustenMukaan(isoluku);

                if (isoluku > 2147483647) {
                    isoLukuAlleRajan = false;
                    break;
                }    
        }
        return isoLukuAlleRajan;
    }

    private int tarkistaJaMuutaOpLkm(int virhetyyppi) {
        if (getOpLkm() < 2) {
            setOpLkm(Luokkakirjasto.PELITILANNE_OLETUS_OPERANDIEN_LKM);
            virhetyyppi = 2;
        }
        return virhetyyppi;
    }

    private int tarkistaJaMuutaOperandMax(int virhetyyppi) {
        if (getOperandMax() < 1) {
            setOperandMax(Luokkakirjasto.PELITILANNE_OLETUS_OPERANDIN_KOKO);
            virhetyyppi = 2;
        }
        return virhetyyppi;
    }

    /**
     * Tarkistaa, että vähintään yksi operaatio
     * on aina sallittu. Jos pelaaja yrittää ottaa kaikki operaatiot pois käytöstä,
     * sallitaan pluslasku. Palauttaa true, jos jouduttiin tekemään muutoksia.
     */
    private boolean yksiOperaatioSallitaanAina() {
        if ((isJako() == false) && (isKerto() == false) &&
                (isMiinus() == false) && (isPlus() == false)) {
            setPlus(true);
            return true;
        }
        return false;
    }

    /**
     * Käy läpi asetukset ja katsoo onko niissä virheitä. Esim. kahta pienempi
     * operandien lukumäärä tai se, että mikään operaatio ei ole sallittu,
     * ovat virheitä. Virheet korjataan ja palautetaan oikea virhetyyppi
     * int-muuttujassa.
     * 
     * 0 = ei virhettä
     * 1 = asetukset mahdollistavat liian suuria lukuja (eivät mahdu int-muuttujaan)
     * 2 = muu virhe, kuten kahta pienempi operandien lukumäärä
     * @return 
     */
    public int tarkistaJaMuutaSopimattomatAsetukset() {
        int virhetyyppi=0;
        
        if (yksiOperaatioSallitaanAina()) {
            virhetyyppi = 2;
        }
        
        if (tarkistaMahdollistavatkoAsetuksetLiianSuuriaLukuja()) {
            virhetyyppi = 1;
        }
        
        virhetyyppi = tarkistaJaMuutaOperandMax(virhetyyppi);
        virhetyyppi = tarkistaJaMuutaOpLkm(virhetyyppi);
        
        return virhetyyppi;
    }
    
    /**
     * Laskee long-muuttujaan apuna käyttäen miten iso luku näillä
     * asetuksilla voi enintään tulla osoittajaan tai nimittäjään. Jos se on suurempi kuin
     * int-muuttujan maksimiarvo, muutetaan asetuksia niin kauan kunnes
     * long-muuttujan arvo on alle int-arvon maksimiarvo.
     * @return 
     */
    private boolean tarkistaMahdollistavatkoAsetuksetLiianSuuriaLukuja() {
        boolean muutettiinkoJotain = false;
        
        while (true) {
            boolean isoLukuAlleRajan = true;

            isoLukuAlleRajan = selvitaOnkoIsoinMahdollinenLukuAlleIntRajan(isoLukuAlleRajan);
        
            if (isoLukuAlleRajan) {
                break;
            } else {
                muutaAsetuksiaNiinEttaSuurinMahdollinenLukuOnPienempi();
                
                muutettiinkoJotain = true;
            }
        }
        
        return muutettiinkoJotain;
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
    
    /**
     * Palauttaa oikeiden vastausten osuuden kaikista vastauksista prosentteina.
     * @return 
     */
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

    
    /**
     * Asettaa asetukset parametrina annettavan String-muuttujan perusteella.
     * Muuttujan sisältävän tekstin täytyy olla samanmuotoinen kuin
     * PeliTilanteen toString-metodin palauttama teksti. Mikäli näin ei ole,
     * metodi heittää poikkeuksen.
     * @param asetukset
     * @throws Exception 
     */
    public void asetaAsetukset(String asetukset) throws Exception {
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
            potenssi = Boolean.parseBoolean(asetuksetTaulukko[15]); 
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Tekstiesitys sisältää kaikki PeliTilanteen sisältämät muuttujat
     * erotettuna toisistaan rivinvaihdoilla. Tämä metodia on tarkoitus
     * käyttää kun asetukset halutaan tallettaa tekstitiedostoon.
     * @return 
     */
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
        teksti=teksti+potenssi+"\n";
        
        return teksti;
    }
    
    
}
