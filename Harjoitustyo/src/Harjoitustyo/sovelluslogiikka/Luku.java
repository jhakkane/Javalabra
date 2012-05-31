/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;

/** Laskettava-luokan perivä rajapinta, joka kuvaa kaikki yksittäisiä lukuja tarkoittavat
 * luokat. Murtoluvut ja sekaluvut kuuluvat esimerkiksi Lukuihin, mutta
 * kokonaiset lausekkeet eivät.
 *
 * @author JH
 */
public interface Luku extends Laskettava {
    public Murtoluku murtolukuna();
    public Sekaluku sekalukuna();
    public boolean samaLuku(Luku l);
    public boolean kokonaisluku();
    public boolean onNolla();
    
    public Luku summa(Luku l);
    public Luku vahennys(Luku l);
    public Luku tulo(Luku l);
    public Luku jaettuna(Luku l);
    
    public void setArvo(Murtoluku m);
}
