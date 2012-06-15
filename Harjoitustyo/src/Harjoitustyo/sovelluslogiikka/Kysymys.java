/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.logging.Level;
import java.util.logging.Logger;

/** Kuvaa kysymystä, joka pelaajalle näytetään. Sisältää yhden lausekkeen
 * ja tietoja kysymyksestä, kuten vastasiko pelaaja siihen oikein vai väärin.
 *
 * @author JH
 */
public class Kysymys {
    
    private Lauseke lauseke;
    private boolean oikeinVastattu;
    
    /**
     * Luo PeliTilanteen perusteella uuden Kysymyksen, joka koostuu Lausekkeesta 
     * ja muista tiedoista.
     * @param tilanne 
     */
    public Kysymys(PeliTilanne tilanne) {
        try {
            lauseke = new Lauseke(tilanne);
        } catch (Exception ex) {
            Logger.getLogger(Sovelluslogiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Palautta Kysymyksen sisältämän Lausekkeen tekstiesityksen
     * eli toString-metodin palauttaman arvon.
     * @return 
     */
    @Override
    public String toString() {
        return lauseke.toString();
    }
    
    /**
     * Palauttaa Kysymyksen muodostavan Lausekkeen ratkaisun.
     * @return 
     */
    public Murtoluku oikeaVastaus() {
        return lauseke.lukuarvo();
    }

    /**
     * Asettaa muuttujan, joka kertoo vastasiko pelaaja tähän
     * Kysymykseen oikein vai ei.
     * @param oikeinVastattu 
     */
    public void setOikeinVastattu(boolean oikeinVastattu) {
        this.oikeinVastattu = oikeinVastattu;
    }

    /**
     * Kertoo vastasiko pelaaja tähän Kysymykseen oikein vai ei.
     * @return 
     */
    public boolean isOikeinVastattu() {
        return oikeinVastattu;
    }

    /**
     * Palauttaa Kysymyksen sisältämän Lausekkeen.
     * @return 
     */
    public Lauseke getLauseke() {
        return lauseke;
    }
    
    
}
