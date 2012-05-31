/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Collections;

/**Sisältää erilaisia laskutoimituksia, joita saatetaan tarvita
 * muissa luokissa. Ei välttämättä tarpeellinen luokka, ja voidaan
 * ehkä poistaa.
 *
 * @author JH
 */
public class Laskutoimituksia {

    public static ArrayList<Integer> Alkulukuhajotelma(int luku) {
        ArrayList<Integer>alkuluvut = new ArrayList<Integer>();
        double osamaara;
        
        for (int i = 2; i < luku; i++) {
            boolean lukuJaollinenTalla=false;
            osamaara=luku*1.0/i;
            
            if ((osamaara)==Math.round(osamaara)) {
                lukuJaollinenTalla=true;
            }
            
            if (lukuJaollinenTalla) {
                boolean alkuluku = true;

                for (Integer integer : alkuluvut) {
                    osamaara = i*1.0/integer;
                    
                    if ((osamaara)==Math.round(osamaara)) {
                        alkuluku=false;
                        continue;
                    }
                }   
            
                if (alkuluku==true) {
                    alkuluvut.add(i);
                }
            
            }
        }

        alkuluvut.add(1);
        Collections.sort(alkuluvut);
        return alkuluvut;
    }
}
