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

/**
 *
 * @author JH
 */
public class OptionsAlku implements ActionListener {
    JFrame frame2;
    PeliTilanne asetukset;
    JRadioButton paalla;
    JRadioButton pois;
    JTextField opLkmField;
    JTextField nimiField;
    
    Sovelluslogiikka logiikka;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int uusiOpLkm=2;
        
        asetukset.setNimi(nimiField.getText());
        
        if (paalla.isSelected()) {
            asetukset.setSulkuja(true);
        } else {
            asetukset.setSulkuja(false);
        }
        
        try {
            uusiOpLkm = Integer.parseInt(opLkmField.getText());
        } catch (Exception o) {
            uusiOpLkm=asetukset.getOpLkm();
        }
        
        asetukset.setOpLkm(uusiOpLkm);
        
        Kayttoliittyma kl = new Kayttoliittyma(logiikka);
        frame2.dispose();
    }
    
    public OptionsAlku(Sovelluslogiikka logiikka) {
        frame2 = new JFrame("Asetukset");
        
        this.logiikka=logiikka;
        asetukset=logiikka.getTilanne();
        
        frame2.setPreferredSize(new Dimension(400,600));
        frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        luoKomponentit(frame2.getContentPane());
        
        frame2.pack();
        frame2.setVisible(true);
    }
    
    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        paalla = new JRadioButton("Päällä");
        pois = new JRadioButton("Pois");

        ButtonGroup sulkuja = new ButtonGroup();
        sulkuja.add(paalla);
        sulkuja.add(pois);

        JLabel teksti1 = new JLabel("Mikä on nimesi?");
        nimiField = new JTextField();
        
        JLabel teksti2 = new JLabel("Käytetäänkö sulkuja?");  

        JLabel teksti3 = new JLabel("Kuinka monta lukua laskutoimituksessa?");
        opLkmField = new JTextField();
        
        JButton lopeta = new JButton("Valmis");
        lopeta.addActionListener(this);      
        
        frame2.add(teksti1);
        frame2.add(nimiField);
        frame2.add(teksti2);
        frame2.add(paalla);
        frame2.add(pois);
        frame2.add(teksti3);
        frame2.add(opLkmField);
        frame2.add(lopeta);
    }
}
