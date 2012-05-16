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
        frame.setPreferredSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(3,1);
        container.setLayout(layout);
    
        JLabel kysymys = new JLabel("Kysymys");
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
