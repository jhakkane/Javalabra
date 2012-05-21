/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jhakkane
 */
public class UusiPeliKuuntelija implements ActionListener {

    Sovelluslogiikka logiikka;
    
    public UusiPeliKuuntelija(Sovelluslogiikka logiikka) {
        this.logiikka=logiikka;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Kayttoliittyma kl = new Kayttoliittyma(logiikka);
    }
    
    
}
