/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Toteuttaa itse ohjelman toiminnan käyttöliittymää lukuunottamatta.
 * Pääasiassa hoitaa pelaajan vastauksen tarkistamisen ja oikean vastauksen
 * näyttämisen.
 *
 * @author jhakkane
 */
public class Sovelluslogiikka {
    
    //Vaihe 1 = Käyttäjälle näkyy kysymys
    //Vaihe 2 = Käyttäjälle näkyy vastaus
    private int vaihe = 2;
    private Lauseke kysymys;
    private PeliTilanne tilanne;
    
    public Sovelluslogiikka() {
        tilanne=new PeliTilanne();
    }
    
    /**
     * Edistää pelitilannetta joko
     * a) Kutsumulla metodia, joka luo käyttäjälle uuden kysymyksen ja 
     * palauttaa sen
     * 
     * tai
     * 
     * b) Kutsumalla metodia, joka tarkistaa käyttäjän antaman vastauksen
     * ja palauttaa kommentin vastauksesta.
     * 
     * Metodi palauttaa edelleen näiden metodien paluuarvon.
     * 
     * @param vastaus
     * @return 
     */
    public String etene(String vastaus) {
        
        if (vaihe == 2) {
            return generoi();
        } else {
            String output = tarkista(vastaus);
            return output;
        }
    }
    
    /**
     * Tarkistaa käyttäjän antaman vastauksen ja palauttaa vastauksesta riippuen
     * joko "oikein", "väärin" tai "ei vastausta" -paluuarvon. Mikäli vastaus on
     * oikein tai väärin, pelitilanne etenee.
     * 
     * @param vastaus
     * @return 
     */
    private String tarkista(String vastaus) {
        
        if (vastaus.isEmpty()) {
            //huom. vaihe ei muutu, käyttäjä yrittää uudelleen
            return "Et antanut vastausta! Tässä kysymys uudelleen: \n\n"
                    +kysymys;
        }

        tilanne.vastattu();
        
        vaihe = 2;
        
        Murtoluku oikeaVastaus = kysymys.lukuarvo(); 
        
        Murtoluku pelaajanVastaus = parsePelaajanVastaus(vastaus);       
        
        if (pelaajanVastaus.samaLuku(oikeaVastaus)) {
            tilanne.oikeinVastattu();
            return "Oikein! Vastaus on juuri "+oikeaVastaus;
        } else {
            return "Väärin! Oikea vastaus on "+oikeaVastaus;
        }
    }
    
    private Murtoluku parsePelaajanVastaus(String vastaus) {
        //pelaajan vastaus on muotoa x y/z
        String vastauksenOsat[] = vastaus.split("[ /]");
        int vastausKokonaisluku= 0;
        int vastausOsoittaja = 0;
        int vastausNimittaja = 1;    
        
        //pelaaja vastasi sekaluvulla
        if (vastauksenOsat.length==3) {
            vastausKokonaisluku= Integer.parseInt(vastauksenOsat[0]);
            vastausNimittaja = Integer.parseInt(vastauksenOsat[2]);            
            vastausOsoittaja = Integer.parseInt(vastauksenOsat[1])
                    +vastausKokonaisluku*vastausNimittaja;
       
        } else if (vastauksenOsat.length==2) {
            //pelaaja vastasi murtoluvulla
            vastausOsoittaja = Integer.parseInt(vastauksenOsat[0]);
            vastausNimittaja = Integer.parseInt(vastauksenOsat[1]);
                     
        } else if (vastauksenOsat.length==1) {
            //pelaaja vastasi kokonaisluvulla
            vastausKokonaisluku= Integer.parseInt(vastauksenOsat[0]);
            vastausNimittaja = 1;
            vastausOsoittaja = vastausKokonaisluku;
        }    
        
        return new Murtoluku(vastausOsoittaja, vastausNimittaja);    
    }
    
    /**
     * Tätä metodia täytyy kutsua, kun halutaan saada tietoja vallitsevasta
     * pelitilanteesta.
     * @return 
     */   
    public PeliTilanne getTilanne() {
        return tilanne;
    }

    public Lauseke getKysymys() {
        return kysymys;
    }
    
    /**
     * Tuottaa uuden Lauseke-olion, asettaa sen kysymys-muuttujan
     * arvoksi ja palauttaa kyseisen olion toStringin.
     * @return 
     */
    private String generoi() {        
        try {
            kysymys = new Lauseke(tilanne);
        } catch (Exception ex) {
            Logger.getLogger(Sovelluslogiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String kysymysString=kysymys.toString();
        
        vaihe = 1;
        return kysymysString;
    }
}
