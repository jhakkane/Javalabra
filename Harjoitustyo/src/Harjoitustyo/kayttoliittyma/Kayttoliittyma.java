/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

import Harjoitustyo.sovelluslogiikka.Luokkakirjasto;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import java.awt.*;
import javax.swing.*;
import javax.swing.WindowConstants;

/**Toteuttaa näkymän, joka hoitaa itse pelin ja näkyy pelaajalle valtaosan
 * ajasta. Näyttää kysymyksen ja tarjoaa pelaajalle mahdollisuuden vastata siihen.
 * Sisältää myös valikon.
 *
 * @author jhakkane
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Sovelluslogiikka logiikka;
    JTextArea kysymysKentta;
    JTextArea suhdeluku;
    
    public Kayttoliittyma(Sovelluslogiikka logiikka) {
        this.logiikka = logiikka;        

        frame = new JFrame("Aritmetiikkaharjoituksia");

        frame.setPreferredSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
        luoKomponentit(frame.getContentPane());
        frame.setJMenuBar(menubar());        
    }
    
    @Override
    public void run() {        
        frame.pack(); 
        frame.setVisible(true);    
    }
    
    public JMenuBar menubar() {
        //menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);        
        JMenuItem options = new JMenuItem("Asetukset");
        OptionsKuuntelija opKuuntelija = new OptionsKuuntelija(frame, logiikka.getTilanne());
        options.addActionListener(opKuuntelija);

        JMenuItem tallenna = new JMenuItem("Tallenna");
        TallennaKuuntelija taKuuntelija = new TallennaKuuntelija(frame, logiikka.getTilanne());
        tallenna.addActionListener(taKuuntelija);
        
        JMenuItem lataa = new JMenuItem("Lataa");
        LataaKuuntelija laKuuntelija = new LataaKuuntelija(frame, logiikka,
                kysymysKentta, suhdeluku);
        lataa.addActionListener(laKuuntelija);
        
        JMenuItem lopeta = new JMenuItem("Sulje");
        LopetaKuuntelija loKuuntelija = new LopetaKuuntelija();
        lopeta.addActionListener(loKuuntelija);
        
        menu.add(options); 
        menu.add(tallenna);
        menu.add(lataa);
        menu.add(lopeta);
        
        menuBar.setVisible(true);
        
        return menuBar;
    }
    
    public void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(4,1);
        container.setLayout(layout);
    
        suhdeluku = new JTextArea();
        suhdeluku.setEditable(false);
        
        kysymysKentta = new JTextArea(5,20);
        kysymysKentta.setEditable(false);
        kysymysKentta.setLineWrap(true);
        kysymysKentta.setWrapStyleWord(true);
        
        JButton tarkistaJaGeneroiNappula = new JButton();
        
        JTextField tekstiKentta = new JTextField();
        
        KysymyksenGenerointiKuuntelija kgKuuntelija = 
                new KysymyksenGenerointiKuuntelija(logiikka,kysymysKentta,tekstiKentta,
                suhdeluku);
        tarkistaJaGeneroiNappula.addActionListener(kgKuuntelija);
        
        container.add(suhdeluku);
        container.add(kysymysKentta);
        container.add(tekstiKentta);
        container.add(tarkistaJaGeneroiNappula);
        
        kysymysKentta.setText(Luokkakirjasto.alkuTervehdys);
    }
    
}
