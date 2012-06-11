/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Luokkakirjasto;
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
    JCheckBox potenssi;
    
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
    JLabel potenssiTxt;
  
    JLabel eiAsetuksiaTeksti;
    
    /**
     * Muuttuja, joka kertoo tapahtuiko asetusten asettamisessa virheitä.
     * 0 = ei virheitä
     * 1 = asetukset mahdollistavat liian suuria lukuja
     * 2 = asetukset ovat muuten vääränlaiset, esim. kirjaimia
     */
    private int virhetyyppi = 0;
    
    /**
     * Kun painetaan valmis-nappulaa, muutetaan asetukset mikäli pelaaja
     * ei ole valinnut tasopeliä.
     * @param e 
     */
    public void lopetaActionPerformed(ActionEvent e) {
        
        asetaTilanne();
        frame.dispose();
    }
    
    /**
     * Asettaa asetukset pelaajan valintojen mukaan ja lopuksi muuttaa
     * virheelliset asetukset.
     */
    public void asetaTilanne() {
        asetaSulkuAsetukset();
        asetaPlusAsetukset();
        asetaMiinusAsetukset();
        asetaKertoAsetukset();
        asetaJakoAsetukset();
        asetaMurtolukuAsetukset();
        asetaNegatiivisuusAsetukset();
        asetaOpLkm();
        asetaMaxOpAsetukset();
        asetaPotenssiAsetukset();
        
        muutaSopimattomatAsetukset();
    }

    
    /**
     * Kutsutaan PeliTilanteen metodia, joka muuttaa sopimattomat asetukset sopiviksi.
     * Näytetään pelaajalle virheilmoitus joko kyseisen metodin paluuarvon perusteella
     * tai mikäli pelaaja antoi virheellisen syötteen tekstikenttiin.
     */
    private void muutaSopimattomatAsetukset() {
        
        virhetyyppi = tilanne.tarkistaJaMuutaSopimattomatAsetukset();
        
        if (virhetyyppi == 1) {
            JOptionPane.showMessageDialog(frame,
                    Luokkakirjasto.joitainAsetuksiaMuutettiinKoskaMuutenLiianIsojaLukuja());
        } else if (virhetyyppi == 2) {
            JOptionPane.showMessageDialog(frame,
                    Luokkakirjasto.asetuksetOvatVirheellisia());    
        }
    }
    

    
    /**
     * Käsittelee maksimioperandikentän ja yrittää asettaa asetukset
     * sen mukaisesti. Mikäli ei onnistu, asetetaan virhetyyppi sopivasti.
     */
    private void asetaMaxOpAsetukset() {
        int i = 20;
        try {
            i = Integer.parseInt(kokoField.getText());
        } catch (Exception o) {
            i=tilanne.getOperandMax();
            virhetyyppi = 2;
        }
        tilanne.setOperandMax(i);
    }

    /**
     * Käsittelee operandien lukumääräkentän ja yrittää asettaa asetukset
     * sen mukaisesti. Mikäli ei onnistu, asetetaan virhetyyppi sopivasti.
     */
    private void asetaOpLkm() {
        int i=2;
        try {
            i = Integer.parseInt(opLkmField.getText());
        } catch (Exception o) {
            i=tilanne.getOpLkm();
            virhetyyppi = 2;
        }
        tilanne.setOpLkm(i);
    }

    private void asetaNegatiivisuusAsetukset() {
        if (negatiivisia.isSelected()) {
            tilanne.setNegatiivisia(true);
        } else {
            tilanne.setNegatiivisia(false);
        }
    }

    private void asetaMurtolukuAsetukset() {
        if (murtolukuja.isSelected()) {
            tilanne.setMurtolukuja(true);
        } else {
            tilanne.setMurtolukuja(false);
        }
    }

    private void asetaJakoAsetukset() {
        if (jako.isSelected()) {
            tilanne.setJako(true);
        } else {
            tilanne.setJako(false);
        }
    }

    private void asetaKertoAsetukset() {
        if (kerto.isSelected()) {
            tilanne.setKerto(true);
        } else {
            tilanne.setKerto(false);
        }
    }

    private void asetaMiinusAsetukset() {
        if (miinus.isSelected()) {
            tilanne.setMiinus(true);
        } else {
            tilanne.setMiinus(false);
        }
    }

    private void asetaPlusAsetukset() {
        if (plus.isSelected()) {
            tilanne.setPlus(true);
        } else {
            tilanne.setPlus(false);
        }
    }

    private void asetaPotenssiAsetukset() {
        if (potenssi.isSelected()) {
            tilanne.setPotenssi(true);
        } else {
            tilanne.setPotenssi(false);
        }
    }
    
    private void asetaSulkuAsetukset() {
        if (sulut.isSelected()) {
            tilanne.setSulkuja(true);
        } else {
            tilanne.setSulkuja(false);
        }
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
        luoSulkuKysymysJaTeksti();
        luoPluslaskuKysymysJaTeksti();
        luoMiinuslaskuKysymysJaTeksti();
        luoKertolaskuKysymysJaTeksti();
        luoJakolaskuKysymysJaTeksti();
        luoMurtolukuKysymysJaTeksti();
        luoNegatiivisuusKysymysJaTeksti();     
        luoPotenssiKysymysJaTeksti();
        luoOpLkmKysymysJaTeksti();
        luoOpMaxKokoKysymysJaTeksti();
        luoLopetaNappula();  

    }

    private void luoLopetaNappula() {
        lopeta = new JButton("Valmis");
        lopeta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lopetaActionPerformed(evt);
            }
        });
        this.frame.add(lopeta);
    }

    private void luoOpMaxKokoKysymysJaTeksti() {
        teksti9 = new JLabel("Mikä on itseisarvoltaan suurin luku, joka saa esiintyä laskutoimituksessa?");
        kokoField = new JTextField();
        kokoField.setText(""+tilanne.getOperandMax());
        this.frame.add(teksti9);
        this.frame.add(kokoField);
    }

    private void luoOpLkmKysymysJaTeksti() {
        teksti8 = new JLabel("Kuinka monta lukua laskutoimituksessa?");
        opLkmField = new JTextField();
        opLkmField.setText(""+tilanne.getOpLkm());
        this.frame.add(teksti8);
        this.frame.add(opLkmField);
    }

    private void luoPotenssiKysymysJaTeksti() {
        potenssiTxt= new JLabel("Saako mukana olla potenssilaskuja?");   
        potenssi = new JCheckBox();
        potenssi.setSelected(tilanne.isPotenssi());
        this.frame.add(potenssiTxt);
        this.frame.add(potenssi);
    }
    
    private void luoNegatiivisuusKysymysJaTeksti() {
        teksti7 = new JLabel("Saako laskuissa olla negatiivisia lukuja?");   
        negatiivisia = new JCheckBox();
        negatiivisia.setSelected(tilanne.isNegatiivisia());
        this.frame.add(teksti7);
        this.frame.add(negatiivisia);
    }

    private void luoMurtolukuKysymysJaTeksti() {
        teksti6 = new JLabel("Saako laskuissa olla murtolukuja?");  
        murtolukuja = new JCheckBox();
        murtolukuja.setSelected(tilanne.isMurtolukuja());
        this.frame.add(teksti6);
        this.frame.add(murtolukuja);
    }

    private void luoJakolaskuKysymysJaTeksti() {
        teksti5 = new JLabel("Jakolaskuja?");   
        jako = new JCheckBox();
        jako.setSelected(tilanne.isJako());
        this.frame.add(teksti5);
        this.frame.add(jako);
    }

    private void luoKertolaskuKysymysJaTeksti() {
        teksti4 = new JLabel("Kertolaskuja?"); 
        kerto = new JCheckBox();
        kerto.setSelected(tilanne.isKerto());
        this.frame.add(teksti4);
        this.frame.add(kerto);
    }

    private void luoMiinuslaskuKysymysJaTeksti() {
        teksti3 = new JLabel("Miinuslaskuja?");   
        miinus = new JCheckBox();
        miinus.setSelected(tilanne.isMiinus());
        this.frame.add(teksti3);
        this.frame.add(miinus);
    }

    private void luoPluslaskuKysymysJaTeksti() {
        teksti2 = new JLabel("Pluslaskuja?");   
        plus = new JCheckBox();
        plus.setSelected(tilanne.isPlus());
        this.frame.add(teksti2);
        this.frame.add(plus);
    }

    private void luoSulkuKysymysJaTeksti() {
        teksti1 = new JLabel("Käytetäänkö sulkuja?");    
        sulut = new JCheckBox();
        sulut.setSelected(tilanne.isSulkuja());
        this.frame.add(teksti1);
        this.frame.add(sulut);
    }
}
