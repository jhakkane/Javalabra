/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;

/**Luku, jolla ei ole kokonaisosaa, vaan vain osoittaja ja nimittäjä.
 * Toteuttaa rajapinnan Luku ja sitä kautta on Laskettava.
 *
 * @author JH
 */
public class Murtoluku implements Laskettava {
    
    private int osoittaja;
    private int nimittaja;

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

    public int getOsoittaja() {
        return osoittaja;
    }

    public int getNimittaja() {
        return nimittaja;
    }
    
    public Murtoluku murtolukuna() {
        return this;
    }
    
    public double desimaalilukuna() {
        return osoittaja/nimittaja;
    }
    
    public boolean kokonaisluku() {
        double osamaara=osoittaja/nimittaja;
        if (osamaara == Math.round(osamaara)) {
            return true;
        }
        
        return false;
    }
    
    public boolean samaLuku(Murtoluku p) {
        Murtoluku l = p.murtolukuna();
        
        if (l.getOsoittaja()==0 && this.osoittaja==0) {
            return true;
        }

        if (l.getOsoittaja() != 0) {
            Murtoluku osamaara = this.jaettuna(l);
            if (osamaara.getOsoittaja()==osamaara.getNimittaja()) {
                return true;
            }            
        }
        
        return false;
    }
    
    public void asetaLuvuksi(int o, int n) {
        this.osoittaja=o;
        this.nimittaja=n;
    }
    
    //Kaikki laskutoimitukset tehdään tosiasiassa murtoluvuilla, ja mahdollisesti
    //muutetaan sitten sekaluvuiksi.
    
    public Murtoluku tulo(Murtoluku l) {
        Murtoluku kerrottava = l.murtolukuna();
        Murtoluku tulo;
        
        int uusiOsoittaja=this.osoittaja*kerrottava.getOsoittaja();
        int uusiNimittaja=this.nimittaja*kerrottava.getNimittaja();
        
        tulo = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return tulo;
    }
    
    public Murtoluku jaettuna(Murtoluku l) {
        Murtoluku jakaja = l.murtolukuna();
        Murtoluku osamaara;
        
        int uusiOsoittaja=this.osoittaja*jakaja.getNimittaja();
        int uusiNimittaja=this.nimittaja*jakaja.getOsoittaja();
        
        osamaara = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return osamaara;
    }    

    public Murtoluku summa(Murtoluku l) {
        Murtoluku summattava = l.murtolukuna();
        Murtoluku summa;
        
        int uusiOsoittaja=this.osoittaja*summattava.getNimittaja()+
                summattava.getOsoittaja()*this.nimittaja;
        int uusiNimittaja=this.nimittaja*summattava.getNimittaja();
        
        summa = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return summa;
    }   
   
    public Murtoluku vahennys(Murtoluku l) {
        Murtoluku vahennettava = l.murtolukuna();
        Murtoluku summa;
        
        int uusiOsoittaja=this.osoittaja*vahennettava.getNimittaja()-
                vahennettava.getOsoittaja()*this.nimittaja;
        int uusiNimittaja=this.nimittaja*vahennettava.getNimittaja();
        
        summa = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return summa;
    }

    /**
     * Tulostaa kokonaisluvun kokonaislukuna, murtoluku näkyy murtolukuna.
     * @return 
     */
    @Override
    public String toString() {
        String teksti="";
        if (nimittaja==1) {
            teksti=teksti+osoittaja;
        } else {
            if (sekaluku()) {
                teksti=teksti+sekalukuna()[0]+"  "+sekalukuna()[1]
                        +"/"+sekalukuna()[2];
            } else {
                teksti=teksti+osoittaja+"/"+nimittaja;   
            }
        }
        
        if (negatiivinen()) {
            teksti="("+teksti+")";
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
    
    public boolean sekaluku() {
        if (osoittaja > nimittaja && nimittaja != 1) {
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
    
    public void setNimittaja(int nimittaja) {
        this.nimittaja = nimittaja;
    }

    public void setOsoittaja(int osoittaja) {
        this.osoittaja = osoittaja;
    }

    public boolean onLauseke() {
        return false;
    }

    public Murtoluku lukuarvo() {
        return this;
    }

    public void setArvo(Murtoluku m) {
        this.nimittaja=m.getNimittaja();
        this.osoittaja=m.getOsoittaja();
    }

    public boolean onNolla() {
        if (osoittaja==0) {
            return true;
        }
        return false;
    }
    
}
