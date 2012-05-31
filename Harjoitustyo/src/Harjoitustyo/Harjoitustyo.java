/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo;

import Harjoitustyo.kayttoliittyma.Kayttoliittyma;
import Harjoitustyo.kayttoliittyma.kaynnistys.KayttoliittymaLoader;
import Harjoitustyo.kayttoliittyma.kaynnistys.KayttoliittymaLoader;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import javax.swing.SwingUtilities;

/**Sisältää main-metodin, jossa käynnistetään pelin toimintaa ohjaava
 * Sovelluslogiikka-luokka ja käyttöliittymän ensimmäisen vaiheen
 * toteuttava KäyttöliittymäLoader.
 *
 * @author jhakkane
 */
public class Harjoitustyo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Sovelluslogiikka logiikka = new Sovelluslogiikka();
        KayttoliittymaLoader kl = new KayttoliittymaLoader(logiikka);
        SwingUtilities.invokeLater(kl);

    }
    
}
