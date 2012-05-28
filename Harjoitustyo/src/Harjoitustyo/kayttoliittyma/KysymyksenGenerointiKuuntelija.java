/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jhakkane
 */
public class KysymyksenGenerointiKuuntelija implements ActionListener {
    
    private Sovelluslogiikka logiikka;
    private JTextArea kysymys;
    private JTextField kentta;
    private JTextArea suhdeluku;
    
    public KysymyksenGenerointiKuuntelija(Sovelluslogiikka logiikka, JTextArea kysymys,
            JTextField kentta, JTextArea suhdeluku) {
        this.logiikka=logiikka;
        this.kysymys=kysymys;
        this.kentta=kentta;
        this.suhdeluku=suhdeluku;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String kysymysString = logiikka.etene(kentta.getText());
        kentta.setText("");
        kysymys.setText(kysymysString);
        
        suhdeluku.setText("Oikeiden vastausten"
                + "osuus kaikista vastauksista: "+logiikka.getTilanne().oikeitaVastauksiaSuhteellisesti()+"%");
        
    }
    
}
