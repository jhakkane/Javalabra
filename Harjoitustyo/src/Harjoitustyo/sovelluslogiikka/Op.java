/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

/**
 *
 * @author jhakkane
 */
public enum Op {
    PLUS("+"), MIN("-"), DIV("/"), MUL("*");
    
    private String txt;
    
    private Op(String txt) {
        this.txt=txt;
    }

    @Override
    public String toString() {
        return this.txt;
    }
}
