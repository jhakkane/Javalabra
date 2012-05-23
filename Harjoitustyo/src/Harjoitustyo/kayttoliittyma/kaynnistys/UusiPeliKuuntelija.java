/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma.kaynnistys;

import Harjoitustyo.kayttoliittyma.Kayttoliittyma;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author jhakkane
 */
public class UusiPeliKuuntelija implements ActionListener {

    Sovelluslogiikka logiikka;
    JFrame frame;
    
    public UusiPeliKuuntelija(Sovelluslogiikka logiikka, JFrame frame) {
        this.logiikka=logiikka;
        this.frame=frame;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Kayttoliittyma kl = new Kayttoliittyma(logiikka);
        frame.dispose();
    }
    
    
}
