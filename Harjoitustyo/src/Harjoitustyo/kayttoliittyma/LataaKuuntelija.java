/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Asetukset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author jhakkane
 */
public class LataaKuuntelija implements ActionListener {
    private JFrame frame;
    private Asetukset asetukset;
    private Scanner scan;
    
    public LataaKuuntelija(JFrame frame, Asetukset asetukset) {
        this.frame=frame;
        this.asetukset=asetukset;
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
                
                asetukset.asetaAsetukset(sisalto);
                
            } catch (IOException ex) {
                Logger.getLogger(TallennaKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                     
        }
    }   
}
