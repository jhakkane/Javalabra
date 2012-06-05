/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

/** Rajapinta, joka kuvaa kaikkia sellaisia olioita, joille voi
 * suorittaa laskutoimituksia. Olennaista Laskettavien käytölle on
 * se, ovatko ne Lausekkeita vai ei.
 *
 * @author JH
 */
public interface Laskettava {
    /**
     * Kertoo onko olio Lauseke vai ei. Jos ei, kyse on Murtoluvusta.
     * @return 
     */
    public boolean onLauseke();
    
    /**
     * Palauttaa olion arvon. Lausekkeen tapauksessa ratkaisee lausekkeen,
     * Murtoluvun tapauksessa palauttaa luvun sellaisenaan.
     * @return 
     */
    public Murtoluku lukuarvo();
}
