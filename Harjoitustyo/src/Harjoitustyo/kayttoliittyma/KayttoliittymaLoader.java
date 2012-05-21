/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.OmaSovelluslogiikka;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author jhakkane
 */
public class KayttoliittymaLoader implements Runnable {
    private JFrame frame;
    private Sovelluslogiikka logiikka;
    
    public KayttoliittymaLoader(Sovelluslogiikka logiikka) {
        this.logiikka = logiikka;
    }

    public KayttoliittymaLoader() {
        logiikka = new OmaSovelluslogiikka();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Aritmetiikkaharjoituksia");
        
        frame.setPreferredSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
        luoKomponentit(frame.getContentPane());
        
        frame.pack(); 
        frame.setVisible(true);
    }
    
    
    public void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(3,1);
        container.setLayout(layout);
        
        JButton uusiPeli = new JButton("Uusi peli");
        JButton lataaPeli = new JButton("Lataa peli");
        JButton lopeta = new JButton("Lopeta");
        

        uusiPeli.addActionListener(new UusiPeliKuuntelija(logiikka));
        lopeta.addActionListener(new LopetaKuuntelija());
        
        container.add(uusiPeli);
        container.add(lataaPeli);
        container.add(lopeta);
        
        
    }   
}
