/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma.kaynnistys;

import Harjoitustyo.kayttoliittyma.Kayttoliittyma;
import Harjoitustyo.kayttoliittyma.TallennaKuuntelija;
import Harjoitustyo.sovelluslogiikka.Asetukset;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author JH
 */
public class LataaPeliKuuntelija implements ActionListener {
    Sovelluslogiikka logiikka;
    JFrame frame;
    Scanner scan;
    
    public LataaPeliKuuntelija(Sovelluslogiikka logiikka, JFrame frame) {
        this.logiikka=logiikka;
        this.frame=frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser valitsin = new JFileChooser();
        int ok = valitsin.showOpenDialog(frame);
        
        if (ok==JFileChooser.APPROVE_OPTION) {
            File file = valitsin.getSelectedFile();
            try {
                scan = new Scanner(file);
                String sisalto="";
                while (scan.hasNextLine()) {
                    sisalto=sisalto+scan.nextLine()+"\n";
                }
                
                Kayttoliittyma kl = new Kayttoliittyma(logiikka);
                logiikka.asetaAsetuksetTekstinPerusteella(sisalto);
                frame.dispose();
                
            } catch (IOException ex) {
                Logger.getLogger(TallennaKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                     
        }
        

    }   
}
