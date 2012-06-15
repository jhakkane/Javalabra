/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;

/**
 * Luku, jolla on osoittaja ja nimittäjä. Voi ilmaista myös kokonaislukuja
 * ja sekalukuja. Olennaista on, että kaikki Murtoluku-luokan luvut voidaan
 * esittää osoittajan ja nimittäjän avulla.
 * 
 * Toteuttaa rajapinnan Laskettava joten sitä voidaan käyttää
 * laskutoimituksissa.
 *
 * @author JH
 */
public class Murtoluku implements Laskettava {
    
    private int osoittaja;
    private int nimittaja;

    /**
     * Ainut tapa luoda murtoluku 0/0. Tarpeen vain erikoislukuna esim. Lausekkeessa,
     * tällä ei lasketa mitään.
     */
    public Murtoluku() {
        osoittaja = 0;
        nimittaja = 0;
    }
    
    /**
     * Luo uuden murtoluvun ja pitää huolen, että vain osoittaja voi olla negatiivinen.
     * Nimittäjä ei myöskään voi olla nolla.
     * @param o
     * @param n 
     */
    public Murtoluku(int o, int n) {
        if ((o < 0 && n < 0) || (n < 0 && o > 0)) {
            o=-o;
            n=-n;
        }
        
        osoittaja=o;
        nimittaja=n;
        
        if (nimittaja == 0) { nimittaja = 1; }
    }

    /**
     * Palauttaa Murtoluvun osoittajan.
     * @return 
     */
    public int getOsoittaja() {
        return osoittaja;
    }

    /**
     * Palauttaa Murtoluvun nimittäjän.
     * @return 
     */
    public int getNimittaja() {
        return nimittaja;
    }
    
    
    /**
     * Kertoo onko Murtoluku erikoismerkki eli onko sen nimittäjä nolla.
     * Tätä ominaisuutta tarvitaan ainakin Lausekkeiden ratkaisemisessa.
     * @return 
     */
    public boolean onErikoisMerkki() {
        if (nimittaja==0) {
            return true;
        }
        return false;
    }
    
    /**
     * Rajapinnan vaatima metodi joka kertoo koostuuko tämä useammasta operandista.
     * Murtoluku ei koskaan koostu useasta operandista.
     * @return 
     */
    @Override
    public boolean koostuuUseastaOperandista() {
        return false;
    }
    
    /**
     * Palauttaa murtoluvun desimaalimuotoisen (liki)arvon.
     * @return 
     */
    public double desimaalilukuna() {
        return osoittaja/nimittaja;
    }
    
    /**
     * Kertoo onko luku kokonaisluku vai ei.
     * @return 
     */
    @Override
    public boolean onKokonaisluku() {
        double osamaara=osoittaja*1.0/nimittaja;
        if (osamaara == Math.round(osamaara)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Palauttaa true, mikäli tätä lukua ei voi esittää kokonaislukuna tai
     * sekalukuna. Muutoin palauttaa false.
     * @return 
     */
    public boolean onMurtoluku() {
        if (!onKokonaisluku() && !onSekaluku()) {
            return true;
        }
        return false;
    }
    
    /**
     * Kertoo onko tämä Murtoluku sama kuin parametrina annettava Murtoluku p.
     * Samuus tarkoittaa tässä samaa etäisyyttä nollasta, joten 1/2 ja 2/4 ovat 
     * sama luku.
     * @param p
     * @return 
     */
    public boolean samaLuku(Murtoluku p) {
        
        if (p.getOsoittaja()==0 && this.osoittaja==0) {
            return true;
        }

        if (p.getOsoittaja() != 0) {
            Murtoluku osamaara = this.jaettuna(p);
            if (osamaara.getOsoittaja()==osamaara.getNimittaja()) {
                return true;
            }            
        }
        
        return false;
    }
    
    /**
     * Asettaa osoittajan ja nimittäjän parametrien mukaisesti.
     * @param o
     * @param n 
     */
    public void asetaLuvuksi(int o, int n) {
        this.osoittaja=o;
        this.nimittaja=n;
    }
    

    /**
     * Palauttaa uuden Murtoluvun, jonka arvo on tämä Murtoluku potenssiin
     * eksponentti.
     * @param luku
     * @return 
     */
    public Murtoluku korotaPotenssiin(int eksponentti) {
        if (eksponentti == 0) {
            return new Murtoluku(1,1);
        }
        if (eksponentti == 1) {
            return this;
        }
        
        Murtoluku korotettu = new Murtoluku(this.osoittaja,this.nimittaja);
        
        for (int i = 1; i < eksponentti; i++) {
            korotettu = korotettu.tulo(this);
        }
        return korotettu;
    }
    
    /**
     * Laskee tämän Murtoluvun ja parametrina annettavan Murtoluvun l tulon
     * ja antaa sen paluuarvona. Metodi ei yritä sieventää tuloa.
     * @param l
     * @return 
     */
    public Murtoluku tulo(Murtoluku kerrottava) {
        Murtoluku tulo;
        
        int uusiOsoittaja=this.osoittaja*kerrottava.getOsoittaja();
        int uusiNimittaja=this.nimittaja*kerrottava.getNimittaja();
        
        tulo = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return tulo;
    }
    
    /**
     * Laskee tämän Murtoluvun ja parametrina annettava Murtoluvun l osamäärän
     * ja antaa sen paluuarvona. Metodi ei yritä sieventää osamäärää.
     * @param l
     * @return 
     */
    public Murtoluku jaettuna(Murtoluku jakaja) {
        Murtoluku osamaara;
        
        int uusiOsoittaja=this.osoittaja*jakaja.getNimittaja();
        int uusiNimittaja=this.nimittaja*jakaja.getOsoittaja();
        
        osamaara = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return osamaara;
    }    

    /**
     * Laskee tämän Murtoluvun ja parametrina annettavan Murtoluvun l summan
     * ja antaa sen paluuarvona. Metodi ei yritä sieventää summaa.
     * @param l
     * @return 
     */
    public Murtoluku summa(Murtoluku summattava) {
        Murtoluku summa;
        
        int uusiOsoittaja=this.osoittaja*summattava.getNimittaja()+
                summattava.getOsoittaja()*this.nimittaja;
        int uusiNimittaja=this.nimittaja*summattava.getNimittaja();
        
        summa = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return summa;
    }   
   
    /**
     * Laskee tämän Murtoluvun ja parametrina annettavan Murtoluvun l erotuksen
     * ja antaa sen paluuarvona. Metodi ei yritä sieventää erotusta.
     * @param l
     * @return 
     */
    public Murtoluku vahennys(Murtoluku vahennettava) {
        Murtoluku summa;
        
        int uusiOsoittaja=this.osoittaja*vahennettava.getNimittaja()-
                vahennettava.getOsoittaja()*this.nimittaja;
        int uusiNimittaja=this.nimittaja*vahennettava.getNimittaja();
        
        summa = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return summa;
    }

    /**
     * Mikäli mahdollista, palauttaa kokonaisluvun. Jos se ei onnistu, niin
     * sekaluvun. Jos tämäkään ei ole mahdollista, niin palautetaan murtoluku.
     * @return 
     */
    @Override
    public String toString() {
        String teksti="";
        if (nimittaja==1) {
            teksti=teksti+osoittaja;
        } else {
            if (onKokonaisluku()) {
                teksti=""+sekalukuna()[0];
            }
            
            if (onSekaluku()) {        
                teksti = toStringKasitteleSekaluku();
            }
            
            if (onMurtoluku()) {
                teksti = osoittaja +"/"+ nimittaja;
            }
        }
        
        return teksti;
    }

    /**
     * Hoitaa toStringin, kun Murtoluku on sekaluku. Ottaa huomioon, että
     * pelin mukaan -3/2 = -1 -1/2, mutta ilmoittaa tällaiset luvut
     * muodossa -1 1/2.
     * @return 
     */
    private String toStringKasitteleSekaluku() {
        String teksti;
        if (sekalukuna()[0] < 0) {
            teksti=sekalukuna()[0]+" "+sekalukuna()[1]*(-1)
                +"/"+sekalukuna()[2];                            
        } else {
            teksti=sekalukuna()[0]+" "+sekalukuna()[1]
                +"/"+sekalukuna()[2];                    
        }
        return teksti;
    }

    /**
     * Palauttaa murtoluvun sekalukumuodossa int-taulukossa.
     * osat[0]=kokonaisosa
     * osat[1]=osoittaja
     * osat[2]=nimittäjä
     * @return 
     */
    public int[] sekalukuna() {
        int[] osat = new int[3];
        
        osat[0]=(int)Math.floor(osoittaja/nimittaja);
        osat[1]=osoittaja-osat[0]*nimittaja;
        osat[2]=nimittaja;
        
        return osat;
    }
    
    /**
     * Kertoo voidaanko Murtoluku ilmaista sekalukuna. Näin voidaan tehdä,
     * jos osoittaja on suurempi kuin nimittäjä ja luku ei ole kokonaisluku.
     * @return 
     */
    public boolean onSekaluku() {
        if (Math.abs(osoittaja) > Math.abs(nimittaja) && !onKokonaisluku()) {
            return true;
        }
        return false;
    }
    /**
     * Tarkistaa osoittajan perusteella, onko luku negatiivinen.
     * Nimittäjä ei voi olla negatiivinen, konstruktori pitää siitä huolen.
     * @return 
     */
    public boolean negatiivinen() {
        if (osoittaja < 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Asettaa nimittäjän parametrin mukaiseksi.
     * @param nimittaja 
     */
    public void setNimittaja(int nimittaja) {
        this.nimittaja = nimittaja;
    }

    /**
     * Asettaa osoittajan parametrin mukaiseksi.
     * @param osoittaja 
     */
    public void setOsoittaja(int osoittaja) {
        this.osoittaja = osoittaja;
    }

    /**
     * Tällä metodilla Lauseke erottaa operandeistaan, onko kyse Lausekkeesta
     * vai Murtoluvusta.
     * @return 
     */
    @Override
    public boolean onLauseke() {
        return false;
    }

    /**
     * Palauttaa Murtoluvun itsensä. Kertoo siis, mikä tämän Murtoluvun
     * arvo on. Rajapinnan vaatima metodi, jota tarvitaan Lausekkeiden
     * ratkaisussa.
     * @return 
     */
    @Override
    public Murtoluku lukuarvo() {
        return this;
    }

    /**
     * Asettaa tällä murtoluvulle saman arvon kuin parametrina annetulle Murtoluvulle m.
     * @param m 
     */
    public void setArvo(Murtoluku m) {
        this.nimittaja=m.getNimittaja();
        this.osoittaja=m.getOsoittaja();
    }

    /**
     * Kertoo onko Murtoluku nolla. Näin on, mikäli osoittaja on nolla.
     * @return 
     */
    public boolean onNolla() {
        if (osoittaja==0) {
            return true;
        }
        return false;
    }
    
}
