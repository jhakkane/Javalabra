/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/** Valikko, jossa pelaaja säätää pelin asetukset ennen peliä.
 * Tämä luokka perii luokan Options, ja on suurelta osin samankaltainen.
 *
 * @author JH
 */
public class OptionsAlku extends Options implements Runnable {

    JLabel tekstiA1;
    JLabel tekstiA2;
    JTextField nimiField;
    JCheckBox tasopeli;
    Sovelluslogiikka logiikka;
    
    ArrayList<Component> ainaNakyvatKomponentit;

    @Override
    public void lopetaActionPerformed(ActionEvent e) {
        super.lopetaActionPerformed(e);
        
        tilanne.setNimi(nimiField.getText());
        tilanne.setTasopeli(tasopeli.isSelected());
        
        Kayttoliittyma kl = new Kayttoliittyma(logiikka);
        SwingUtilities.invokeLater(kl);
        frame.dispose();
    }

    /**
     * Mikäli pelaaja valitsee "tasopelin", piilotetaan turhat asetukset. Tasopelin idea
     * on, että peli itse säätää asetukset pelaajan suoritumisen mukaan.
     * @param e 
     */
    public void tasopeliActionPerformed(ActionEvent e) {
        Component[] komponentit = frame.getContentPane().getComponents();
        for (Component component : komponentit) {
            if (tasopeli.isSelected()) {
                component.setVisible(false);
            } else {
                component.setVisible(true);  
            }
        
            for (Component ainaNakyva : ainaNakyvatKomponentit) {
                if (component == ainaNakyva || component == lopeta) {
                    component.setVisible(true);
                }
            }   
        }
    }
    
    public OptionsAlku(Sovelluslogiikka logiikka) {
        super(logiikka.getTilanne());
        this.logiikka=logiikka;
    }

    @Override
    protected void luoKomponentit(Container container) {
        ainaNakyvatKomponentit = new ArrayList<Component>();
        
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        luoNimiTekstiJaKentta(container);        
        luoTasopeliTekstiJaCheckBox(container);
        
        lisaaAinaNakyvatKomponentit();

        super.luoKomponentit(container);
        
    }

    private void luoNimiTekstiJaKentta(Container container) {
        tekstiA1 = new JLabel("Mikä on nimesi?");
        nimiField = new JTextField();
        container.add(tekstiA1);
        container.add(nimiField);
    }

    private void luoTasopeliTekstiJaCheckBox(Container container) {
        tekstiA2 = new JLabel("Haluatko, että peli säätää asetukset itse "
                + "pelin aikana?");
        tasopeli = new JCheckBox();
        tasopeli.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tasopeliActionPerformed(evt);
            }
        });        
        container.add(tekstiA2);
        container.add(tasopeli);
    }
    
    /**
     * Lisää ainaNakyvatKomponentit-taulukkoon ne komponentit, joiden on
     * tarkoitus näkyä koko ajan, silloinkin kun pelaaja on valinnut tasopelin.
     */
    private void lisaaAinaNakyvatKomponentit() {
        ainaNakyvatKomponentit.add(tekstiA1);
        ainaNakyvatKomponentit.add(nimiField);
        ainaNakyvatKomponentit.add(tekstiA2);
        ainaNakyvatKomponentit.add(tasopeli); 
    }
}
