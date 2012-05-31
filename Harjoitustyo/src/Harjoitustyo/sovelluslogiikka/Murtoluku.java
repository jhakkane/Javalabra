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
public class Murtoluku implements Luku {
    
    private int osoittaja;
    private int nimittaja;

    
    public Murtoluku(int o, int n) {
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
    
    @Override
    public Murtoluku murtolukuna() {
        return this;
    }
    
    public double desimaalilukuna() {
        return osoittaja/nimittaja;
    }
    
    @Override
    public boolean kokonaisluku() {
        if (sekalukuna().kokonaisluku()) {
            return true;
        }
        return false;
    }
    
    @Override
    public Sekaluku sekalukuna() {
        return new Sekaluku(osoittaja,nimittaja);
    }
    
    @Override
    public boolean samaLuku(Luku p) {
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
    
    @Override
    public Murtoluku tulo(Luku l) {
        Murtoluku kerrottava = l.murtolukuna();
        Murtoluku tulo;
        
        int uusiOsoittaja=this.osoittaja*kerrottava.getOsoittaja();
        int uusiNimittaja=this.nimittaja*kerrottava.getNimittaja();
        
        tulo = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return tulo;
    }
    
    @Override
    public Murtoluku jaettuna(Luku l) {
        Murtoluku jakaja = l.murtolukuna();
        Murtoluku osamaara;
        
        int uusiOsoittaja=this.osoittaja*jakaja.getNimittaja();
        int uusiNimittaja=this.nimittaja*jakaja.getOsoittaja();
        
        osamaara = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return osamaara;
    }    

    @Override
    public Murtoluku summa(Luku l) {
        Murtoluku summattava = l.murtolukuna();
        Murtoluku summa;
        
        int uusiOsoittaja=this.osoittaja*summattava.getNimittaja()+
                summattava.getOsoittaja()*this.nimittaja;
        int uusiNimittaja=this.nimittaja*summattava.getNimittaja();
        
        summa = new Murtoluku(uusiOsoittaja, uusiNimittaja);
        
        return summa;
    }   
   
    @Override
    public Murtoluku vahennys(Luku l) {
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
        if (nimittaja==1) {
            return ""+osoittaja;
        } else {
            return osoittaja+"/"+nimittaja;
        }
    }

    public void setNimittaja(int nimittaja) {
        this.nimittaja = nimittaja;
    }

    public void setOsoittaja(int osoittaja) {
        this.osoittaja = osoittaja;
    }

    @Override
    public boolean onLauseke() {
        return false;
    }

    @Override
    public Murtoluku lukuarvo() {
        return this;
    }

    @Override
    public void setArvo(Murtoluku m) {
        this.nimittaja=m.getNimittaja();
        this.osoittaja=m.getOsoittaja();
    }

    @Override
    public boolean onNolla() {
        if (osoittaja==0) {
            return true;
        }
        return false;
    }
    
}
