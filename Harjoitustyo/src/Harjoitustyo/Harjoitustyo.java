/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo;

import Harjoitustyo.kayttoliittyma.Kayttoliittyma;
import Harjoitustyo.kayttoliittyma.KayttoliittymaLoader;
import Harjoitustyo.sovelluslogiikka.OmaSovelluslogiikka;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import javax.swing.SwingUtilities;

/**
 *
 * @author jhakkane
 */
public class Harjoitustyo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        KayttoliittymaLoader kl = new KayttoliittymaLoader();
        SwingUtilities.invokeLater(kl);

    }
    
    
    /**
     * Tämä on vain testimetodi javadocin toiminnan selvittämistä varten.
     * 
     * @param x 
     */
    
    public static void testiMetodi(int x) {
        System.out.println("Testi: " + x);
    }
    
}
