/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jhakkane
 */
public class Sovelluslogiikka {
    
    //Vaihe 1 = Käyttäjälle näkyy kysymys
    //Vaihe 2 = Käyttäjälle näkyy vastaus
    private int vaihe = 2;
    private Kysymys kysymys;
    private PeliTilanne tilanne;
    
    public Sovelluslogiikka() {
        this.tilanne=new PeliTilanne();
        tilanne.setOpLkm(2);
        tilanne.setSulkuja(false);
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
        int oikeaVastaus=(int)kysymys.ratkaise();
        
        if (vastaus.isEmpty()) {
            //huom. vaihe ei muutu, käyttäjä yrittää uudelleen
            return "Et antanut vastausta! Tässä kysymys uudelleen: \n\n "
                    +kysymys;
        }

        tilanne.vastattu();
        
        vaihe = 2;
        if (Integer.parseInt(vastaus)==oikeaVastaus) {
            tilanne.oikeinVastattu();
            return "Oikein! Vastaus on juuri "+oikeaVastaus;
        } else {
            return "Väärin! Oikea vastaus on "+oikeaVastaus;
        }
    }
    
    /**
     * Tätä metodia täytyy kutsua, kun halutaan saada tietoja vallitsevasta
     * pelitilanteesta.
     * @return 
     */
    
    public PeliTilanne getTilanne() {
        return tilanne;
    }   
    
    /**
     * Tuottaa uuden Kysymy-solion, asettaa sen kysymys-muuttujan
     * arvoksi ja palauttaa kyseisen olion toStringin.
     * @return 
     */
    private String generoi() {        
        
        kysymys = new Kysymys(tilanne);
        
        String kysymysString=kysymys.toString();
        
        vaihe = 1;
        return kysymysString;
    }
}
