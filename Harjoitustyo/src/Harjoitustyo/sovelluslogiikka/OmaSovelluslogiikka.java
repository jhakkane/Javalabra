/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

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
    public String etene() {
        if (vaihe == 2) {
            
        }
        
    }
    
    
    private String generoi(int opLkm) {        
        
        for (int i = 0; i < opLkm; i++) {
            int x = (int)(Math.random()*20);
            
        }
        
        System.out.println(x);
        String kysymys="Miten paljon on "+x+" + "+x+"?";
        return kysymys;
    }
}
