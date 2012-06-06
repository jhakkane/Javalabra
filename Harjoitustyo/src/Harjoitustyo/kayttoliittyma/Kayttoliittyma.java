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
    JTextArea infolaatikko;
    
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
    
    /**
     * Luo käyttöliittymälle ylhäällä näkyvän valikon, jonka kautta voidaan
     * ainakin ladata, tallettaa ja lopettaa peli.
     * @return 
     */
    public JMenuBar menubar() {
        //menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);   
        
        lisaaMenuItemOptions(menu); 
        lisaaMenuItemTallenna(menu);
        lisaaMenuItemLataa(menu);
        lisaaMenuItemLopeta(menu);
        
        menuBar.setVisible(true);
        
        return menuBar;
    }

    private void lisaaMenuItemLataa(JMenu menu) {
        JMenuItem lataa = new JMenuItem("Lataa");
        LataaKuuntelija laKuuntelija = new LataaKuuntelija(frame, logiikka,
                kysymysKentta, infolaatikko);
        lataa.addActionListener(laKuuntelija);
        menu.add(lataa);
    }

    private void lisaaMenuItemTallenna(JMenu menu) {
        JMenuItem tallenna = new JMenuItem("Tallenna");
        TallennaKuuntelija taKuuntelija = new TallennaKuuntelija(frame, logiikka.getTilanne());
        tallenna.addActionListener(taKuuntelija);
        menu.add(tallenna);
    }

    private void lisaaMenuItemOptions(JMenu menu) {
        JMenuItem options = new JMenuItem("Asetukset");
        OptionsKuuntelija opKuuntelija = new OptionsKuuntelija(frame, logiikka.getTilanne());
        options.addActionListener(opKuuntelija);
        menu.add(options);
    }

    private void lisaaMenuItemLopeta(JMenu menu) {
        JMenuItem lopeta = new JMenuItem("Sulje");
        LopetaKuuntelija loKuuntelija = new LopetaKuuntelija();
        lopeta.addActionListener(loKuuntelija);
        menu.add(lopeta);
    }
    
    /**
     * Luodaan ja asetetaan käyttöliittymän komponentit käyttöliittymään.
     * @param container 
     */
    public void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(4,1);
        container.setLayout(layout);
        
        luoInfolaatikko(container);
        luoKysymysKentta(container);
        JTextField tekstiKentta = luoTekstiKentta(container);
        luoTarkistaJaGeneroiNappula(container, tekstiKentta);
        
    }

    private void luoTarkistaJaGeneroiNappula(Container container, JTextField tekstiKentta) {
        JButton tarkistaJaGeneroiNappula = new JButton();
        container.add(tarkistaJaGeneroiNappula);
        KysymyksenGenerointiKuuntelija kgKuuntelija = 
                new KysymyksenGenerointiKuuntelija(logiikka,kysymysKentta,tekstiKentta,
                infolaatikko);
        tarkistaJaGeneroiNappula.addActionListener(kgKuuntelija);
    }

    private JTextField luoTekstiKentta(Container container) {
        JTextField tekstiKentta = new JTextField();
        container.add(tekstiKentta);
        return tekstiKentta;
    }

    private void luoKysymysKentta(Container container) {
        kysymysKentta = new JTextArea(5,20);
        kysymysKentta.setEditable(false);
        kysymysKentta.setLineWrap(true);
        kysymysKentta.setWrapStyleWord(true);
        kysymysKentta.setText(Luokkakirjasto.alkuTervehdys);
        container.add(kysymysKentta);
    }

    private void luoInfolaatikko(Container container) {
        infolaatikko = new JTextArea();
        infolaatikko.setEditable(false);
        container.add(infolaatikko);
    }
    
}
