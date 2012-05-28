/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author jhakkane
 */
public class Kysymys {
    
    private Kysymys[] operandit;
    private Op[] operaattorit;
    private double arvo;

    private boolean sulkuja;
    private boolean plus;
    private boolean miinus;
    private boolean kerto;
    private boolean jako;
    
    //laskutoimituksia on 2. lajia
    //joko ne koostuvat muista laskutoimituksista tai edustavat yksittäistä arvoa
    //Kysymys voi myös satunnaisgeneroida itsensa
    
    
    public Kysymys(PeliTilanne tilanne) {   

        if (tilanne.getOpLkm()<= 1) {
            Random r = new Random();
            this.arvo=(int)satunnaisluku();
            return;
        }
        
        if (tilanne.getOpLkm() == 2) {
            sulkuja=false;
        }
        
        if (sulkuja == false) {
            this.operandit = new Kysymys[tilanne.getOpLkm()];
            this.operaattorit = new Op[tilanne.getOpLkm()-1];
            
            for (int i = 0; i < tilanne.getOpLkm(); i++) {
                operandit[i] = new Kysymys((int)satunnaisluku());
            }
            
            for (int i = 0; i < operaattorit.length; i++) {
                Random r = new Random();
                int opnro = r.nextInt(2);

                if (opnro == 0) {
                    operaattorit[i]=Op.PLUS;               
                } else if(opnro == 1) {               
                    operaattorit[i]=Op.MUL;                
                }
            }
        } else {

            //vähintään 3 operandia
            this.operandit = new Kysymys[2];
            this.operaattorit = new Op[1];
            
            Random r = new Random();
            int vali = r.nextInt(tilanne.getOpLkm()-1)+1;

            PeliTilanne asetukset = tilanne;
            asetukset.setOpLkm(vali);
            asetukset.setSulkuja(true);
            operandit[0]=new Kysymys(asetukset);
            
            asetukset.setOpLkm(tilanne.getOpLkm()-vali);
            operandit[1]=new Kysymys(asetukset);
            
            int opnro = r.nextInt(2);

            if (opnro == 0) {
                operaattorit[0]=Op.PLUS;               
            } else if(opnro == 1) {               
                operaattorit[0]=Op.MUL;                
            }
        }
        
        arvo = this.ratkaise();
    }
    
    public Kysymys(double operandit[], Op operaattorit[]) {        
        
        this.operandit = new Kysymys[operandit.length];

        for (int i = 0; i < operandit.length; i++) {
            Kysymys osaToimitus = new Kysymys(operandit[0]);
            this.operandit[i]=osaToimitus;
        }
        
        this.operaattorit=operaattorit;

        arvo = ratkaise();
    }

    public Kysymys(double arvo) {
        this.arvo=arvo;
    }
    
    private double satunnaisluku() {
        return Math.random()*20;
    }
    
    public double ratkaise() { 
        
        //onko tämä yksittäinen arvo vai koostuuko osista?
        if (operandit==null) {
            return this.arvo;
        } else {
            
            ArrayList<Kysymys> operanditTemp = new ArrayList<Kysymys>();
            ArrayList<Op> operaattoritTemp = new ArrayList<Op>();
            for (int i = 0; i < operandit.length; i++) {
                operanditTemp.add(new Kysymys(operandit[i].ratkaise()));                
            }
            operaattoritTemp.addAll(Arrays.asList(operaattorit));

  
            //ensin kerto- ja jakolaskut
            for (int i = 0; i < operaattorit.length; i++) {
                double tulos=0;
                if (operaattorit[i]==Op.MUL) {
                    tulos = operanditTemp.get(i).ratkaise()*operanditTemp.get(i+1).ratkaise();                    

                    operanditTemp.get(i).setArvo(0);
                    operanditTemp.get(i+1).setArvo(tulos);
                    operaattoritTemp.set(i, Op.PLUS);
                }
            }
                  
            
            //plus- ja vähennyslaskut
            for (int i = 0; i < operaattorit.length; i++) {
                double tulos=0;
                if (operaattorit[i] == Op.PLUS) {
                    tulos = operanditTemp.get(i).ratkaise()+operanditTemp.get(i+1).ratkaise();                  
                    
                    operanditTemp.get(i).setArvo(0);
                    operanditTemp.get(i+1).setArvo(tulos);
                    operaattoritTemp.set(i, Op.PLUS);
                }    
            }        

            //lasketaan jäljellä olevat yhteen
            for (int i = 0; i < operaattorit.length; i++) {
                double tulos=0;
                tulos = operanditTemp.get(i).ratkaise()+operanditTemp.get(i+1).ratkaise();                  
                    
                operanditTemp.get(i).setArvo(0);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);            
            }                 
            
            //pitäisi olla vain 1 luku jäljellä
            return operanditTemp.get(operanditTemp.size()-1).ratkaise();
            
        }
        
    }


    public void setArvo(double arvo) {
        this.arvo = arvo;
    }
    
    public int operandienMaara() {
        if (operandit == null) {
            return 1;
        }
        return operandit.length;
    }
    
    @Override
    public String toString() {
        String teksti="";
        
        //pelkkä arvo
        if (operandit==null) {
            return ""+arvo;
        }
        
        //koostuu muista toimituksista
        for (int i = 0; i < operandit.length; i++) {
            if (operandit[i].operandienMaara() == 1) {
                teksti=teksti+operandit[i].toString();                
            } else {
                teksti=teksti+"("+operandit[i].toString()+")";
            }
            
            if (i < operaattorit.length) {
                teksti=teksti+" "+operaattorit[i].toString()+" ";
            }
        }
        
        return teksti;
    }


    
    
    
}
