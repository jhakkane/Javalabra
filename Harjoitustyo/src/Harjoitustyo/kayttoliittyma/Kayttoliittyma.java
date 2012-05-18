/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.*;
import javax.swing.*;
import javax.swing.WindowConstants;

/**
 *
 * @author jhakkane
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Sovelluslogiikka logiikka;
    
    public Kayttoliittyma(Sovelluslogiikka logiikka) {
        this.logiikka = logiikka;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Aritmetiikkaharjoituksia");
        frame.setJMenuBar(menubar());
        
        frame.setPreferredSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public JMenuBar menubar() {
        //menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);        
        JMenuItem options = new JMenuItem("Asetukset");
        JMenuItem lopeta = new JMenuItem("Sulje");
        menu.add(options);
        menu.add(lopeta);
        
        menuBar.setVisible(true);
        
        return menuBar;
    }
    
    public void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(3,1);
        container.setLayout(layout);
    
        JTextArea kysymys = new JTextArea(5,20);
        kysymys.setEditable(false);
        kysymys.setLineWrap(true);
        kysymys.setWrapStyleWord(true);
        
        JButton tarkistaJaGeneroiNappula = new JButton();
        
        JTextField tekstiKentta = new JTextField();
        
        KysymyksenGenerointiKuuntelija kgKuuntelija = 
                new KysymyksenGenerointiKuuntelija(logiikka,kysymys,tekstiKentta);
        tarkistaJaGeneroiNappula.addActionListener(kgKuuntelija);
        
        
        container.add(kysymys);
        container.add(tekstiKentta);
        container.add(tarkistaJaGeneroiNappula);
        
        kysymys.setText("Tervetuloa aritmetiikan pariin. Paina alla olevaa"
                + " nappulaa generoidaksesi kysymyksen.");
    }
    
}
