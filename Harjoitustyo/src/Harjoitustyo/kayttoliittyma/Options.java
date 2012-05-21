/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Asetukset;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author JH
 */
public class Options implements ActionListener {
    JFrame frame2;
    Asetukset asetukset;
    JRadioButton paalla;
    JRadioButton pois;
    JTextField opLkm;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int uusiOpLkm=2;
        
        if (paalla.isSelected()) {
            asetukset.setSulkuja(true);
        } else {
            asetukset.setSulkuja(false);
        }
        
        try {
            uusiOpLkm = Integer.parseInt(opLkm.getText());
        } catch (Exception o) {
            uusiOpLkm=asetukset.getOpLkm();
        }
        
        asetukset.setOpLkm(uusiOpLkm);
        
    }
    
    public Options(Asetukset asetukset) {
        frame2 = new JFrame("Asetukset");
        this.asetukset=asetukset;
        
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
        
        JLabel teksti1 = new JLabel("Käytetäänkö sulkuja?");
        JButton lopeta = new JButton("OK");
        lopeta.addActionListener(this);        

        JLabel teksti2 = new JLabel("Kuinka monta lukua laskutoimituksessa?");
        opLkm = new JTextField();
        
        frame2.add(teksti1);
        frame2.add(paalla);
        frame2.add(pois);
        frame2.add(teksti2);
        frame2.add(opLkm);
        frame2.add(lopeta);
    }
}
