/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Asetukset;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

/**
 *
 * @author JH
 */
public class OptionsKuuntelija implements ActionListener {

    private JFrame frame;
    private Asetukset asetukset;
    
    public OptionsKuuntelija(JFrame frame, Asetukset asetukset) {
        this.frame=frame;
        this.asetukset=asetukset;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Options options = new Options(asetukset);
    }
    
    
}
