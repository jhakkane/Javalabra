/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
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
import javax.swing.JOptionPane;

/**
 *
 * @author jhakkane
 */
public class LataaKuuntelija implements ActionListener {
    private JFrame frame;
    private PeliTilanne tilanne;
    private Scanner scan;
    
    public LataaKuuntelija(JFrame frame, PeliTilanne tilanne) {
        this.frame=frame;
        this.tilanne=tilanne;
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
                
                //virheellinen save-tiedosto
                try {
                    tilanne.asetaAsetukset(sisalto);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Tiedosto ei ole kelvollinen.");
                }
                
            } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Tiedostoa ei voi avata.");
            }
            
                     
        }
    }   
}
