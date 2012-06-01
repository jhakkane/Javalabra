/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.kayttoliittyma;

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
    
    public Kayttoliittyma(Sovelluslogiikka logiikka) {
        this.logiikka = logiikka;        

        frame = new JFrame("Aritmetiikkaharjoituksia");
        frame.setJMenuBar(menubar());
        
        frame.setPreferredSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
        luoKomponentit(frame.getContentPane());
        
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
        LataaKuuntelija laKuuntelija = new LataaKuuntelija(frame, logiikka.getTilanne());
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
    
        JTextArea suhdeluku = new JTextArea();
        suhdeluku.setEditable(false);
        
        JTextArea kysymys = new JTextArea(5,20);
        kysymys.setEditable(false);
        kysymys.setLineWrap(true);
        kysymys.setWrapStyleWord(true);
        
        JButton tarkistaJaGeneroiNappula = new JButton();
        
        JTextField tekstiKentta = new JTextField();
        
        KysymyksenGenerointiKuuntelija kgKuuntelija = 
                new KysymyksenGenerointiKuuntelija(logiikka,kysymys,tekstiKentta,
                suhdeluku);
        tarkistaJaGeneroiNappula.addActionListener(kgKuuntelija);
        
        container.add(suhdeluku);
        container.add(kysymys);
        container.add(tekstiKentta);
        container.add(tarkistaJaGeneroiNappula);
        
        kysymys.setText("Tervetuloa aritmetiikan pariin. Paina alla olevaa"
                + " nappulaa generoidaksesi kysymyksen.\n"
                + "Anna vastaus joko murtolukuna ('x/y'), kokonaislukuna ('x')\n"
                + "tai sekalukuna ('x y/z').");
    }
    
}
