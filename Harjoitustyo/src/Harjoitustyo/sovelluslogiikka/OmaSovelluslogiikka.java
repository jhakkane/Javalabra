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
public class OmaSovelluslogiikka implements Sovelluslogiikka {
    
    //Vaihe 1 = Käyttäjälle näkyy kysymys
    //Vaihe 2 = Käyttäjälle näkyy vastaus
    private int vaihe = 2;
    private Kysymys kysymys;
    private Asetukset asetukset;
    
    public OmaSovelluslogiikka() {
        this.asetukset=new Asetukset();
        asetukset.setOpLkm(3);
        asetukset.setSulkuja(false);
    }
    
    @Override
    public String etene(String vastaus) {
        //vaihe vaihdetaan tarkista- ja generoi-metodeissa!
        
        if (vaihe == 2) {
            return generoi();
        } else {
            String output = tarkista(vastaus);
            return output;
        }
        
    }
    
    private String tarkista(String vastaus) {
        int oikeaVastaus=(int)kysymys.ratkaise();
        
        if (vastaus.isEmpty()) {
            //huom. vaihe ei muutu, käyttäjä yrittää uudelleen
            return "Et antanut vastausta! Tässä kysymys uudelleen: \n\n "
                    +kysymys;
        }
        
        vaihe = 2;
        if (Integer.parseInt(vastaus)==oikeaVastaus) {
            return "Oikein! Vastaus on juuri "+oikeaVastaus;
        } else {
            return "Väärin! Oikea vastaus on "+oikeaVastaus;
        }
    }

    public Asetukset getAsetukset() {
        return asetukset;
    }
    
    
    private String generoi() {        
        
        kysymys = new Kysymys(asetukset);
        
        String kysymysString=kysymys.toString();
        
        vaihe = 1;
        return kysymysString;
    }
}
