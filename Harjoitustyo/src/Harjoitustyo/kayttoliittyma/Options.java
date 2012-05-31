/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**Valikko, jossa pelaaja voi muuttaa pelin asetuksia kesken pelin.
 *
 * @author JH
 */
public class Options implements ActionListener, Runnable {
    JFrame frame2;
    PeliTilanne tilanne;
    JCheckBox sulut;
    JCheckBox plus;
    JCheckBox miinus;
    JCheckBox kerto;
    JCheckBox jako;
    
    JCheckBox murtolukuja;
    
    JTextField opLkmField;
    JTextField kokoField;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
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
        
        tilanne.yksiOperaatioSallitaanAina();
        
        
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
        frame2.pack();
        frame2.setVisible(true);      
    }
    
    public Options(PeliTilanne tilanne) {
        frame2 = new JFrame("Asetukset");
        this.tilanne=tilanne;
        
        frame2.setPreferredSize(new Dimension(400,600));
        frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        luoKomponentit(frame2.getContentPane());  
    }
    
    protected void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        sulut = new JCheckBox();
        plus = new JCheckBox();
        miinus = new JCheckBox();
        kerto = new JCheckBox();
        jako = new JCheckBox();
        
        murtolukuja = new JCheckBox();
        
        JLabel teksti1 = new JLabel("Käytetäänkö sulkuja?");    
        JLabel teksti2 = new JLabel("Pluslaskuja?");   
        JLabel teksti3 = new JLabel("Miinuslaskuja?");   
        JLabel teksti4 = new JLabel("Kertolaskuja?");   
        JLabel teksti5 = new JLabel("Jakolaskuja?");   
        
        JLabel teksti6 = new JLabel("Saako laskuissa olla murtolukuja?");  
        
        JLabel teksti7 = new JLabel("Kuinka monta lukua laskutoimituksessa?");
        opLkmField = new JTextField();

        JLabel teksti8 = new JLabel("Mikä on itseisarvoltaan suurin luku, joka saa esiintyä laskutoimituksessa?");
        kokoField = new JTextField();
        
        JButton lopeta = new JButton("Valmis");
        lopeta.addActionListener(this);    
        
        frame2.add(teksti1);
        frame2.add(sulut);
        
        frame2.add(teksti2);
        frame2.add(plus);
        
        frame2.add(teksti3);
        frame2.add(miinus);
        
        frame2.add(teksti4);
        frame2.add(kerto);
        
        frame2.add(teksti5);
        frame2.add(jako);

        frame2.add(teksti6);
        frame2.add(murtolukuja);
        
        frame2.add(teksti7);
        frame2.add(opLkmField);
        
        frame2.add(teksti8);
        frame2.add(kokoField);
        
        frame2.add(lopeta);
    }
}
