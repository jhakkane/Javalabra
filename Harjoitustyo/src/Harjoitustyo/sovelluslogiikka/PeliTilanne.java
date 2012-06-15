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

    private String nimi;
    private int vastattuja;
    private int oikeitaVastauksia;
    private int opLkm;
    private int operandMax;
    private boolean sulkuja;
    private boolean plus;
    private boolean miinus;
    private boolean kerto;
    private boolean jako;
    private boolean murtolukuja;
    private boolean negatiivisia;
    private boolean tasopeli;
    private boolean potenssi;
    private int oikeitaVastauksiaTallaKierroksella;
    private int kierros;
    private int vaihe;
    private Kysymys kysymys;;

    /**
     * Luo PeliTilanne-muuttujan ja alustaa sen arvot oletusasetuksiksi.
     */
    public PeliTilanne() {
        nimi="Anonyymi";
        vastattuja=0;
        oikeitaVastauksia=0;
        opLkm = Luokkakirjasto.PELITILANNE_OLETUS_OPERANDIEN_LKM;
        operandMax = Luokkakirjasto.PELITILANNE_OLETUS_OPERANDIN_KOKO;
        sulkuja=false;
        plus=true;
        miinus=false;
        kerto=false;
        jako=false;
        murtolukuja=false;
        negatiivisia=false;
        tasopeli=false;
        potenssi=false;
        oikeitaVastauksiaTallaKierroksella=0;
        kierros=0;
        vaihe=2;
    }
    
    /**
     * Palauttaa tämänhetkisen Kysymyksen, jota pelaajalta kysytään.
     * @return 
     */
    public Kysymys getKysymys() {
        return kysymys;
    }

    /**
     * Asettaa kysymyksen, jota pelaajalta kysytään.
     * @param kysymys 
     */
    public void setKysymys(Kysymys kysymys) {
        this.kysymys = kysymys;
    }
    
    /**
     * Palauttaa suurimman mahdollisen laskuissa esiintyvän operandin koon.
     * @return 
     */
    public int getOperandMax() {
        return operandMax;
    }

    /**
     * Palauttaa pelin vaiheen. 
     * 1 = Käyttäjälle näkyy kysymys
     * 2 = Käyttäjälle näkyy vastaus
     * @return 
     */
    public int getVaihe() {
        return vaihe;
    }

    /**
     * Asettaa pelin vaiheen.
     * 1 = Käyttäjälle näkyy kysymys
     * 2 = Käyttäjälle näkyy vastaus
     * @param vaihe 
     */
    public void setVaihe(int vaihe) {
        this.vaihe = vaihe;
    }

    /**
     * Asettaa suurimman mahdollisen laskuissa esiintyvän operandin koon.
     * @return 
     */
    public void setOperandMax(int operandMax) {
        this.operandMax = operandMax;
    }
    
    /**
     * Kertoo sallitaanko laskuissa jakolaskuja.
     * @return 
     */
    public boolean isJako() {
        return jako;
    }
    
    /**
     * Asettaa pelin joko tasopelitilaan tai pois siitä.
     * Tasopeli tarkoittaa, että pelaaja ei voi säätää asetuksia itse,
     * vaan että peli itse muuttaa asetuksia pelaajan etenemisen mukaan.
     * @param tasopeli 
     */
    public void setTasopeli(boolean tasopeli) {
        this.tasopeli = tasopeli;
    }

    /**
     * Kertoo, onko peli tasopelissä vai ei.
     * Tasopeli tarkoittaa, että pelaaja ei voi säätää asetuksia itse,
     * vaan että peli itse muuttaa asetuksia pelaajan etenemisen mukaan.
     * @param tasopeli 
     */
    public boolean isTasopeli() {
        return tasopeli;
    }

    /**
     * Kertoo sallitaanko laskuissa kertolaskuja.
     * @return 
     */
    public boolean isKerto() {
        return kerto;
    }

    /**
     * Kertoo sallitaanko laskuissa murtolukuja.
     * @param murtolukuja 
     */
    public void setMurtolukuja(boolean murtolukuja) {
        this.murtolukuja = murtolukuja;
    }

    /**
     * Asettaa menossa olevan kierroksen tasopelissä. Tällä asetuksella
     * on merkitystä vain tasopelissä. Asetukset riippuvat kierroksesta.
     * @param kierros 
     */
    public void setKierros(int kierros) {
        this.kierros = kierros;
    }

    /**
     * Kertoo sallitaanko laskuissa potenssilaskuja.
     * @return 
     */
    public boolean isPotenssi() {
        return potenssi;
    }

    /**
     * Asettaa potenssilaskut päälle tai pois laskuista.
     * @param potenssi 
     */
    public void setPotenssi(boolean potenssi) {
        this.potenssi = potenssi;
    }

    /**
     * Kertoo menossa olevan kierroksen tasopelissä. Tällä asetuksella
     * on merkitystä vain tasopelissä. Asetukset riippuvat kierroksesta.
     * @return 
     */
    public int getKierros() {
        return kierros;
    }

    /**
     * Asettaa muuttujan, joka kertoo kuinka monta oikeaa vastausta tällä kierroksella
     * on annettu. Tällä asetuksella on merkitystä vain tasopelissä.
     * @param oikeitaVastauksiaTallaKierroksella 
     */
    public void setOikeitaVastauksiaTallaKierroksella(int oikeitaVastauksiaTallaKierroksella) {
        this.oikeitaVastauksiaTallaKierroksella = oikeitaVastauksiaTallaKierroksella;
    }

    /**
     * Palauttaa muuttujan, joka kertoo kuinka monta oikeaa vastausta tällä kierroksella
     * on annettu. Tällä asetuksella on merkitystä vain tasopelissä.
     * @return 
     */
    public int getOikeitaVastauksiaTallaKierroksella() {
        return oikeitaVastauksiaTallaKierroksella;
    }

    /**
     * Kertoo sallitaanko laskuissa murtolukuja.
     * @return 
     */
    public boolean isMurtolukuja() {
        return murtolukuja;
    }

    /**
     * Kertoo sallitaanko laskuissa vähennyslaskuja.
     * @return 
     */
    public boolean isMiinus() {
        return miinus;
    }

    /**
     * Kertoo sallitaanko laskuissa yhteenlaskuja.
     * @return 
     */
    public boolean isPlus() {
        return plus;
    }

    /**
     * Asettaa yhteenlaskut päälle tai pois laskuista.
     * @param plus 
     */
    public void setPlus(boolean plus) {
        this.plus = plus;
    }

    /**
     * Asettaa vähennyslaksut päälle tai pois laskuista.
     * @param miinus 
     */
    public void setMiinus(boolean miinus) {
        this.miinus = miinus;
    }

    /**
     * Asettaa kertolaskut päälle tai pois laskuista.
     * @param kerto 
     */
    public void setKerto(boolean kerto) {
        this.kerto = kerto;
    }

    /**
     * Asettaa jakolaskut päälle tai pois laskuista.
     * @param jako 
     */
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
    
    
    /**
     * Palauttaa operandien lukumäärän laskuissa.
     * @return 
     */
    public int getOpLkm() {
        return opLkm;
    }

    /**
     * Asettaa negatiiviset luvut päälle tai pois laskuista.
     */
    public void setNegatiivisia(boolean negatiivisia) {
        this.negatiivisia = negatiivisia;
    }

    /**
     * Kertoo, sallitaanko laskuissa negatiivisia lukuja.
     * @return 
     */
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

    /**
     * Kertoo kuinka monta kysymystä pelaajalta on kysytty tähän mennessä.
     * @return 
     */
    public int getKysyttyja() {
        return vastattuja;
    }

    /**
     * Kertoo kuinka monta oikeaa vastausta pelaaja on antanut tähän mennessä.
     */
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
    
    /**
     * Asettaa operandien lukumäärän laskutoimituksissa. Jos yritetään antaa
     * alle 2 operandia, muuttaa operandien lukumäärä kahdeksi.
     * @param opLkm 
     */
    public void setOpLkm(int opLkm) {
        this.opLkm = opLkm;
        
        if (opLkm < 2) {
            opLkm=2;
        }
    }

    /**
     * Kertoo sallitaanko laskutoimituksissa sulkuja. Huom. negatiiviset luvut
     * ja murtolukujen potenssilaskut merkitään suluilla riippumatta tästä
     * asetuksesta.
     * @return 
     */
    public boolean isSulkuja() {
        return sulkuja;
    }

    /**
     * Palauttaa pelaajan nimen.
     * @return 
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Asettaa pelaajan nimen.
     * @param nimi 
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    /**
     * Asettaa asetuksen, joka määrää sallitaanko sulkuja
     * laskutoimituksissa vai ei.
     * @param sulkuja 
     */
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
            oikeitaVastauksia=Integer.parseInt(asetuksetTaulukko[16]);
            
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
        teksti=teksti+oikeitaVastauksia+"\n";
        
        return teksti;
    }
    
    
}
