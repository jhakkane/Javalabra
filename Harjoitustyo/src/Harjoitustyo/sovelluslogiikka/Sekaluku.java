/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;

/**Luku, jolla on kokonaisosa osoittajan ja nimittäjän lisäksi.
 * Toteuttaa rajapinnan Luku ja sitä kautta on Laskettava.
 *
 * @author JH
 */
public class Sekaluku implements Luku {
    
    public int kokonaisosa;
    public int osoittaja;
    public int nimittaja;
    
    /**
     * Laskee itse sekalukumuodon osoittajan ja nimittäjän perusteella.
     * @param o
     * @param n 
     */
    public Sekaluku(int o, int n) {
        nimittaja=n;
        if (nimittaja == 0) { nimittaja = 1; }
        
        kokonaisosa = (int)Math.floor(o/nimittaja);
        
        osoittaja = o-kokonaisosa*nimittaja;
        
    }
    
    /**
     * Luo sekaluvun parametrien mukaan, mutta myös siistiin sen siten, että
     * osoittaja on aina pienempi kuin nimittäjä.
     * @param o
     * @param n
     * @param k 
     */
    public Sekaluku(int o, int n, int k) {
        nimittaja=n;
        if (nimittaja == 0) { nimittaja = 1; }
        
        kokonaisosa = (int)Math.floor(o/nimittaja)+k;
        
        osoittaja=o-(int)Math.floor(o/nimittaja)*nimittaja;

    }

    @Override
    public boolean kokonaisluku() {
        if (osoittaja==0) {
            return true;
        }
        return false;
    }

    @Override
    public Sekaluku sekalukuna() {
        return this;
    }

    public int getKokonaisosa() {
        return kokonaisosa;
    }

    public int getNimittaja() {
        return nimittaja;
    }

    public int getOsoittaja() {
        return osoittaja;
    }

    @Override
    public boolean samaLuku(Luku l) {
        Murtoluku tamaMurtolukuna = murtolukuna();
        return tamaMurtolukuna.samaLuku(l);
    }

    @Override
    public String toString() {
        return kokonaisosa+" "+osoittaja+"/"+nimittaja;
    }
    
    @Override
    public Murtoluku murtolukuna() {
        return new Murtoluku(kokonaisosa*nimittaja+osoittaja,nimittaja);
    }

    @Override
    public Luku summa(Luku l) {
        Murtoluku murtolukuna = this.murtolukuna().summa(l);
        
        return murtolukuna.sekalukuna();
    }

    @Override
    public Luku jaettuna(Luku l) {
        Murtoluku murtolukuna = this.murtolukuna().jaettuna(l);
        
        return murtolukuna.sekalukuna();
    }
    
    @Override
    public Luku tulo(Luku l) {
        Murtoluku murtolukuna = this.murtolukuna().tulo(l);
        
        return murtolukuna.sekalukuna();
    }    

    @Override
    public Luku vahennys(Luku l) {
        Murtoluku murtolukuna = this.murtolukuna().vahennys(l);
        
        return murtolukuna.sekalukuna();
    }    

    @Override
    public boolean onLauseke() {
        return false;
    }
    
    @Override
    public Murtoluku lukuarvo() {
        return this.murtolukuna();
    }
    
    @Override
    public void setArvo(Murtoluku m) {
        nimittaja=m.getNimittaja();
        if (nimittaja == 0) { nimittaja = 1; }
        
        kokonaisosa = (int)Math.floor(m.getOsoittaja()/nimittaja);
        
        osoittaja = m.getOsoittaja()-kokonaisosa*nimittaja;
    }
    
    @Override
    public boolean onNolla() {
        if (osoittaja==0 && kokonaisosa==0) {
            return true;
        }
        return false;
    }
}
