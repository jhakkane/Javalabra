/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/** Valikko, jossa pelaaja säätää pelin asetukset ennen peliä.
 * Tämä luokka perii luokan Options, ja on suurelta osin samankaltainen.
 *
 * @author JH
 */
public class OptionsAlku extends Options implements ActionListener, Runnable {

    JTextField nimiField;
    Sovelluslogiikka logiikka;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        
        tilanne.setNimi(nimiField.getText());
        
        Kayttoliittyma kl = new Kayttoliittyma(logiikka);
        SwingUtilities.invokeLater(kl);
        frame2.dispose();
    }
    
    public OptionsAlku(Sovelluslogiikka logiikka) {
        super(logiikka.getTilanne());
        this.logiikka=logiikka;
    }
    

    @Override
    protected void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        JLabel teksti1 = new JLabel("Mikä on nimesi?");
        nimiField = new JTextField();
        
        container.add(teksti1);
        container.add(nimiField);
        
        super.luoKomponentit(container);
        
    }
}
