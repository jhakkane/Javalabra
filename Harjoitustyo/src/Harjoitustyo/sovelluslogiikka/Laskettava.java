/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

/** Rajapinta, joka kuvaa kaikkia sellaisia olioita, joille voi
 * suorittaa laskutoimituksia. Olennaista Laskettavien käytölle on
 * se, ovatko ne lausekkeita vai ei.
 *
 * @author JH
 */
public interface Laskettava {
    public boolean onLauseke();
    public Murtoluku lukuarvo();
}
