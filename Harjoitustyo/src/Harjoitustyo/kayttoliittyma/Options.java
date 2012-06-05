/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**Valikko, jossa pelaaja voi muuttaa pelin asetuksia kesken pelin.
 *
 * @author JH
 */
public class Options implements Runnable {
    JFrame frame;
    PeliTilanne tilanne;
    JCheckBox sulut;
    JCheckBox plus;
    JCheckBox miinus;
    JCheckBox kerto;
    JCheckBox jako;
    
    JCheckBox murtolukuja;
    JCheckBox negatiivisia;
    
    JTextField opLkmField;
    JTextField kokoField;
    
    JButton lopeta;
    
    JLabel teksti1;
    JLabel teksti2;
    JLabel teksti3;
    JLabel teksti4;
    JLabel teksti5;
    JLabel teksti6;
    JLabel teksti7;
    JLabel teksti8;
    JLabel teksti9;
  
    JLabel eiAsetuksiaTeksti;
    
    /**
     * Kun painetaan valmis-nappulaa, muutetaan asetukset mikäli pelaaja
     * ei ole valinnut tasopeliä.
     * @param e 
     */
    public void lopetaActionPerformed(ActionEvent e) {
        
        asetaTilanne();
        frame.dispose();
    }
    
    public void asetaTilanne() {
        if (sulut.isSelected()) {
            tilanne.setSulkuja(true);
        } else {
            tilanne.setSulkuja(false);
        }

        if (plus.isSelected()) {
            tilanne.setPlus(true);
        } else {
            tilanne.setPlus(false);
        }

        if (miinus.isSelected()) {
            tilanne.setMiinus(true);
        } else {
            tilanne.setMiinus(false);
        }
        
        if (kerto.isSelected()) {
            tilanne.setKerto(true);
        } else {
            tilanne.setKerto(false);
        }

        if (jako.isSelected()) {
            tilanne.setJako(true);
        } else {
            tilanne.setJako(false);
        }
        
        if (murtolukuja.isSelected()) {
            tilanne.setMurtolukuja(true);
        } else {
            tilanne.setMurtolukuja(false);
        }

        if (negatiivisia.isSelected()) {
            tilanne.setNegatiivisia(true);
        } else {
            tilanne.setNegatiivisia(false);
        }
        
        int i=2;
        try {
            i = Integer.parseInt(opLkmField.getText());
        } catch (Exception o) {
            i=tilanne.getOpLkm();
        }
        tilanne.setOpLkm(i);
        
        try {
            i = Integer.parseInt(kokoField.getText());
        } catch (Exception o) {
            i=tilanne.getOperandMax();
        }
        tilanne.setOperandMax(i);
        
    }
    
    @Override
    public void run() {
        frame.pack();
        frame.setVisible(true);      
    }
    
    public Options(PeliTilanne tilanne) {
        frame = new JFrame("Asetukset");
        this.tilanne=tilanne;
        
        frame.setPreferredSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());     
    }
    
    
    protected void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        sulut = new JCheckBox();
        sulut.setSelected(tilanne.isSulkuja());
        
        plus = new JCheckBox();
        plus.setSelected(tilanne.isPlus());
        
        miinus = new JCheckBox();
        miinus.setSelected(tilanne.isMiinus());
        
        kerto = new JCheckBox();
        kerto.setSelected(tilanne.isKerto());
    
        jako = new JCheckBox();
        jako.setSelected(tilanne.isJako());
        
        murtolukuja = new JCheckBox();
        murtolukuja.setSelected(tilanne.isMurtolukuja());
        
        negatiivisia = new JCheckBox();
        negatiivisia.setSelected(tilanne.isNegatiivisia());
        
        teksti1 = new JLabel("Käytetäänkö sulkuja?");    
        teksti2 = new JLabel("Pluslaskuja?");   
        teksti3 = new JLabel("Miinuslaskuja?");   
        teksti4 = new JLabel("Kertolaskuja?");   
        teksti5 = new JLabel("Jakolaskuja?");   
        
        teksti6 = new JLabel("Saako laskuissa olla murtolukuja?");  
        teksti7 = new JLabel("Saako laskuissa olla negatiivisia lukuja?");   
        
        teksti8 = new JLabel("Kuinka monta lukua laskutoimituksessa?");
        opLkmField = new JTextField();
        opLkmField.setText(""+tilanne.getOpLkm());

        teksti9 = new JLabel("Mikä on itseisarvoltaan suurin luku, joka saa esiintyä laskutoimituksessa?");
        kokoField = new JTextField();
        kokoField.setText(""+tilanne.getOperandMax());
        
        lopeta = new JButton("Valmis");
        lopeta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lopetaActionPerformed(evt);
            }
        });
        
        this.frame.add(teksti1);
        this.frame.add(sulut);
        
        this.frame.add(teksti2);
        this.frame.add(plus);
        
        this.frame.add(teksti3);
        this.frame.add(miinus);
        
        this.frame.add(teksti4);
        this.frame.add(kerto);
        
        this.frame.add(teksti5);
        this.frame.add(jako);

        this.frame.add(teksti6);
        this.frame.add(murtolukuja);
        
        this.frame.add(teksti7);
        this.frame.add(negatiivisia);
        
        this.frame.add(teksti8);
        this.frame.add(opLkmField);

        this.frame.add(teksti9);
        this.frame.add(kokoField);
        
        this.frame.add(lopeta);
    }
}
