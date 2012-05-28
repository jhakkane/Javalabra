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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author JH
 */
public class TallennaKuuntelija implements ActionListener {
    private JFrame frame;
    private PeliTilanne asetukset;
    
    public TallennaKuuntelija(JFrame frame, PeliTilanne asetukset) {
        this.frame=frame;
        this.asetukset=asetukset;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser valitsin = new JFileChooser();
        int ok = valitsin.showSaveDialog(frame);
        
        if (ok==JFileChooser.APPROVE_OPTION) {
            File file = valitsin.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(file);
                
                writer.write(asetukset.toString());
                writer.close();
                
            } catch (IOException ex) {
                Logger.getLogger(TallennaKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            }
                     
        }
    }   
}
