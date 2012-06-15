/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Luokkakirjasto;
import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Kuuntelija, joka avaa tiedostonvalitsimen, jolla pelaaja valitsee
 * ladattavan tallennustiedoston.
 * @author jhakkane
 */
public class LataaKuuntelija implements ActionListener {
    private JFrame frame;
    private Sovelluslogiikka logiikka;
    private Scanner scan;
    private JTextArea tekstiKentta;
    private JTextArea suhdeluku;
    
    public LataaKuuntelija(JFrame frame, Sovelluslogiikka logiikka,
            JTextArea tekstiKentta, JTextArea suhdeluku) {
        this.frame=frame;
        this.logiikka = logiikka;
        this.tekstiKentta=tekstiKentta;
        this.suhdeluku=suhdeluku;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser valitsin = new JFileChooser();
        int ok = valitsin.showOpenDialog(frame);
        
        if (ok==JFileChooser.APPROVE_OPTION) {
            selvitaTallennusTiedostonSisaltoJaAsetaAsetukset(valitsin);              
        }
    }

    /**
     * Käy läpi pelaajan valitseman tiedoston, muuttaa sen tekstimuotoon ja
     * asettaa asetukset sen perusteella.
     * @param valitsin
     * @throws HeadlessException 
     */
    private void selvitaTallennusTiedostonSisaltoJaAsetaAsetukset(JFileChooser valitsin) throws HeadlessException {
        File file = valitsin.getSelectedFile();
        try {
            scan = new Scanner(file);
            String sisalto="";
            while (scan.hasNextLine()) {
                sisalto=sisalto+scan.nextLine()+"\n";
            }
            
            asetaAsetuksetJaPaivitaTilanneTekstitiedostonPerusteella(sisalto);
            
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Tiedostoa ei voi avata.");
        }
    }
    
    /**
     * Asettaa tallennettun tiedoston sisällön perusteella asetukset ja antaa
     * käyttäjälle uuden kysymyksen uusilla asetuksilla.
     * @param sisalto 
     */
    private void asetaAsetuksetJaPaivitaTilanneTekstitiedostonPerusteella(String sisalto) {
        try {
            logiikka.getTilanne().asetaAsetukset(sisalto);
            logiikka.getTilanne().setVaihe(2);
            tekstiKentta.setText(logiikka.etene("")); 
            suhdeluku.setText(Luokkakirjasto.suhdelukuKentanTeksti(logiikka.getTilanne()));
        } catch (Exception ex) {
            //virheellinen save-tiedosto
            JOptionPane.showMessageDialog(frame, "Tiedosto ei ole kelvollinen.");
        }         
    }

}
