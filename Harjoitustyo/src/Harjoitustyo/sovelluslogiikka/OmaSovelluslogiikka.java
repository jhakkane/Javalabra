/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jhakkane
 */
public class OmaSovelluslogiikka implements Sovelluslogiikka {
    
    //Vaihe 1 = Käyttäjälle näkyy kysymys
    //Vaihe 2 = Käyttäjälle näkyy vastaus
    private int vaihe = 2;
    private Kysymys kysymys;
    
    @Override
    public String etene(String vastaus) {
        //vaihe vaihdetaan tarkista- ja generoi-metodeissa!
        
        if (vaihe == 2) {
            return generoi(2);
        } else {
            String output = tarkista(vastaus);
            return output;
        }
        
    }
    
    private String tarkista(String vastaus) {
        int oikeaVastaus=0;
        int[] luvut = kysymys.getOperandit();
        Op[] operaattorit = kysymys.getOperaattorit();
        
        for (int i = 0; i < operaattorit.length; i++) {
            
            oikeaVastaus=oikeaVastaus+luvut[i];
            
        }
        
        if (vastaus.isEmpty()) {
            //huom. vaihe ei muutu, käyttäjä yrittää uudelleen
            return "Et antanut vastausta! Tässä kysymys uudelleen: "
                    +kysymys.getKysymysString();
        }
        
        vaihe = 2;
        if (Integer.parseInt(vastaus)==oikeaVastaus) {
            return "Oikein! Vastaus on juuri "+oikeaVastaus;
        } else {
            return "Väärin! Oikea vastaus on "+oikeaVastaus;
        }
    }
    
    
    private String generoi(int opLkm) {        

        int luvut[] = new int[opLkm];
        Op operaattorit[] = new Op[opLkm];
        
        //luodaan luvut
        for (int i = 0; i < opLkm; i++) {         
            luvut[i]=((int)(Math.random()*20));
            
        }
        
        //luodaan operaattorit
        for (int i = 0; i < opLkm-1; i++) {
            Random r = new Random();
            int opnro = r.nextInt(4);
            
            if (opnro == 1) {
                operaattorit[i]=Op.PLUS;
            } else if(opnro == 2) {
                operaattorit[i]=Op.MIN;                
            } else if(opnro == 3) {
                operaattorit[i]=Op.DIV;                
            } else if(opnro == 4) {
                operaattorit[i]=Op.MUL;                
            }
            
            
            
        }
        

        
        //luo käyttäjälle näkyvä kysymys
        String kysymysString="Miten paljon on ";
        for (int i = 0; i < luvut.length; i++) {
            
            if (i == luvut.length-1) {
                kysymysString=kysymysString+luvut[i]+"?";
            } else {
                kysymysString=kysymysString+luvut[i];
                
                if (i < operaattorit.length) {
                    if (operaattorit[i]==Op.PLUS) {
                        kysymysString=kysymysString+"+";
                    } else if(operaattorit[i]==Op.MIN) {
                       kysymysString=kysymysString+"-";        
                    } else if(operaattorit[i]==Op.DIV) {
                       kysymysString=kysymysString+"/";              
                    } else if(operaattorit[i]==Op.MUL) {
                       kysymysString=kysymysString+"*";              
                    }
                }
                
                
            }
            
            
        }

        kysymys = new Kysymys(luvut,operaattorit, kysymysString);
        
        vaihe = 1;
        return kysymysString;
    }
}
