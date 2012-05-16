/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * @author jhakkane
 */
public class Kysymys {
    
    private int[] luvut;
    private String kysymysString;
    private Op[] operaattorit;
    private int oikeaVastaus;

    
    //talletetaan käyttäjän antama vastaus kysymyksen yhteyteen
    private int vastaus;
    
    Kysymys(int[] luvut, Op[] operaattorit, String kysymysString) {
        this.luvut = luvut;
        this.operaattorit=operaattorit;
        this.kysymysString=kysymysString;
    }

    public String getKysymysString() {
        return kysymysString;
    }

    public Op[] getOperaattorit() {
        return operaattorit;
    }


    public int[] getOperandit() {
        return luvut;
    }


    
    
    
}
