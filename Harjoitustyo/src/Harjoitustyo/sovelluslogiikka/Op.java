/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

/**Tämä enum kuvaa kaikki operaatiot, joita luvuille voidaan
 * tässä ohjelmassa suorittaa.
 *
 * @author jhakkane
 */
public enum Op {
    PLUS("+"), MIN("-"), DIV("/"), MUL("*"), NULL("N");
    
    private String txt;
    
    private Op(String txt) {
        this.txt=txt;
    }

    /**
     * Palauttaa tämän operaation String-muodossa. Plus-operaatio on
     * +, miinus -, jako /, kerto * ja tyhjä operaatio N.
     * @return 
     */
    @Override
    public String toString() {
        return this.txt;
    }
}
