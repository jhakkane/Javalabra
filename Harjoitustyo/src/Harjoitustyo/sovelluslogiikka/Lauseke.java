/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Laskettava-rajapinnan toteuttava luokka, joka kuvaa useammasta Luvusta
 * koostuvaa laskutoimitusta. Tätä luokkaa ei voi toteuttaa vain yhdellä
 * operandilla, ja sen yrittäminen aiheuttaa virhetilanteen. Yksittäinen
 * luku luodaan jollain Luku-rajapinnan toteuttavalla luokalla.
*
* @author jhakkane
*/
public class Lauseke implements Laskettava {
    private final Murtoluku NOLLA = new Murtoluku(0,1);
    
    private Laskettava[] operandit; //sisältää siis sekä Lukuja että Lausekkeita
    private Op[] operaattorit;

    private int opLkm;
    private boolean sulkuja=false;
    private boolean plus=true;
    private boolean miinus=false;
    private boolean kerto=false;
    private boolean jako=false;
    
    private boolean murtolukuOperandi=false;
    
    private int operandinMaksimiSuuruus=20;
    private int nimittajanMaksimiSuuruus=operandinMaksimiSuuruus; //väliaikaisesti
    
    //laskutoimituksia on 2. lajia
    //joko ne koostuvat muista laskutoimituksista tai edustavat yksittäistä arvoa
    //Kysymys voi myös satunnaisgeneroida itsensa
    
    
    public Lauseke(PeliTilanne tilanne) throws Exception {

        opLkm=tilanne.getOpLkm();
        sulkuja=tilanne.isSulkuja();
        plus=tilanne.isPlus();
        miinus=tilanne.isMiinus();
        kerto=tilanne.isKerto();
        jako=tilanne.isJako();
        operandinMaksimiSuuruus=tilanne.getOperandMax();
        murtolukuOperandi=tilanne.isMurtolukuja();
        
        if (opLkm < 2) {
            throw new Exception("Operandien määrän pitää olla vähintään 2");
        }
        
        if (opLkm == 2) {
            sulkuja=false;
        }
        
        //yhtenäinen sulkujen sisällä oleva kokonaisuus
        //tai laskutoimitus jossa ei ole sulkuja ollenkaan
        if (sulkuja == false) {
            this.operandit = new Laskettava[opLkm];
            this.operaattorit = new Op[opLkm-1];
            

            arvoOperaattorit(tilanne);
            arvoOperandit(tilanne);


        //vähintään 3 operandia
        } else {
            
            //jaetaan luvut kahteen lausekkeeseen

            this.operandit = new Laskettava[2];
            this.operaattorit = new Op[1];
            
            Random r = new Random();
            int vali = r.nextInt(opLkm-1)+1;

            int vanhaOpLkm = tilanne.getOpLkm();
            
            tilanne.setOpLkm(vali);
            if (vali == 1) {
                operandit[0]=satunnaisluku(true,murtolukuOperandi);                        
            } else {

                operandit[0]=new Lauseke(tilanne);                
            }

            vali=opLkm-vali;
            tilanne.setOpLkm(vali);
            if (vali == 1) {
                operandit[1]=satunnaisluku(true,murtolukuOperandi);                        
            } else {
                operandit[1]=new Lauseke(tilanne);                
            }
            
            tilanne.setOpLkm(vanhaOpLkm);
            
            arvoOperaattorit(tilanne);
        }
        
        
        /*Tässä kohtaa muutellaan laskutoimitus sopivaksi siten, että siellä ei ole
         * nollalla jakoa ja että jakolaskuista tulee sopivia osamääriä.
        */
        poistaNollallaJako(tilanne);
       
    }
    
    public void arvoOperandit(PeliTilanne tilanne) {
        boolean saaOllaNolla;
        
        for (int i = 0; i < opLkm; i++) {
        
            //ei saa olla nolla, jos on vain jakolaskuja
            if (!plus && !miinus && !kerto && jako) {
                saaOllaNolla=false;   
            } else {
                saaOllaNolla=true;        
            }

            operandit[i] = satunnaisluku(saaOllaNolla,murtolukuOperandi);
        }
    }
    
    public void arvoOperaattorit(PeliTilanne tilanne) {
        for (int i = 0; i < operaattorit.length; i++) {
            Random r = new Random();
            int opnro = r.nextInt(4);

            if (opnro == 0) {
                operaattorit[i]=Op.PLUS;
            } else if(opnro == 1) {
                operaattorit[i]=Op.MUL;
            } else if(opnro == 2) {
                operaattorit[i]=Op.DIV;
            } else if(opnro == 3) {
                operaattorit[i]=Op.MIN;
            }
        }

        //muutetaan sopimattomat operaattorit ensimmäiseksi sopivaksi (joita on ainakin 1)
        Op sopiva = sopivaOperaattori(tilanne);
        
        for (int i = 0; i < operaattorit.length; i++) {
            if ((operaattorit[i]==Op.DIV && tilanne.isJako()==false) ||
                   (operaattorit[i]==Op.MUL && tilanne.isKerto()==false) ||
                    (operaattorit[i]==Op.MIN && tilanne.isMiinus()==false) ||
                    (operaattorit[i]==Op.PLUS && tilanne.isPlus()==false)) {
                operaattorit[i]=sopiva;
            }
        }
    }
    
    /**
     * Palauttaa jonkin sellaisen operaattorin, joka on tässä laskutoimituksessa sallittu.
     * @param tilanne
     * @return 
     */
    public Op sopivaOperaattori(PeliTilanne tilanne) {
        Op sopiva=Op.PLUS;
        if (tilanne.isJako()) { sopiva = Op.DIV; }
        if (tilanne.isKerto()) { sopiva = Op.MUL; }
        if (tilanne.isMiinus()) { sopiva = Op.MIN; }
        if (tilanne.isPlus()) { sopiva = Op.PLUS; }
        
        return sopiva;
    }
    
    public void poistaNollallaJako(PeliTilanne tilanne) {
        
        for (int i = 1; i < operandit.length; i++) {
            if (operandit[i].lukuarvo().onNolla() && operaattorit[i-1]==Op.DIV) {
                //Huomaa, että pelkästään jakolaskuja sisältävässä laskutoimituksessa
                //ei voida joutua tähän, sillä siihen ei luoda yhtään 0-operandia.
                operaattorit[i-1]=sopivaOperaattori(tilanne);
            }
            
        }
    }
    
    
    /**
     * Luo Luku-tyyppisen muuttujan annettujen parametrien mukaisesti.
     * @param saaOllaNolla
     * @param murtoluku
     * @return 
     */
    private Murtoluku satunnaisluku(boolean saaOllaNolla, boolean murtoluku) {
        int osoittaja=0;
        int nimittaja=1;
        Murtoluku luku;
        
        if (saaOllaNolla) {
            if (murtoluku) {            
                //(nimittaja*operandinMaksimiSuuruus)/nimittaja = operandinMaksimiSuuruus
                nimittaja=(int)Math.round(Math.random()*(nimittajanMaksimiSuuruus-1)+1);
                osoittaja=(int)Math.round(Math.random()*nimittaja*operandinMaksimiSuuruus);
                           
                luku = new Murtoluku(osoittaja,nimittaja);      
            } else {
                nimittaja=1;
                osoittaja=(int)Math.round(Math.random()*operandinMaksimiSuuruus);
                           
                luku = new Murtoluku(osoittaja,nimittaja);    
            }

        } else {
            if (murtoluku) {
                //(nimittaja*operandinMaksimiSuuruus)/nimittaja = operandinMaksimiSuuruus
                nimittaja=(int)Math.round(Math.random()*(nimittajanMaksimiSuuruus-1)+1);
                osoittaja=(int)Math.round(Math.random()*nimittaja*(operandinMaksimiSuuruus-1)+1);
 
                luku = new Murtoluku(osoittaja,nimittaja);                
            } else {
                //(nimittaja*operandinMaksimiSuuruus)/nimittaja = operandinMaksimiSuuruus
                nimittaja=1;
                osoittaja=(int)Math.round(Math.random()*nimittaja*(operandinMaksimiSuuruus-1)+1);
                           
                luku = new Murtoluku(osoittaja,nimittaja);   
            }
        }

        return luku;
    }
    
    /**
     * Palauttaa Luku-tyyppisen muuttujan, jonka arvo on sama kuin tämän
     * kysymyksen ratkaisu.
     * @return 
     */
    @Override
    public Murtoluku lukuarvo() {
        
        Murtoluku tulos = new Murtoluku(0,1);
        
        ArrayList<Murtoluku> operanditTemp = new ArrayList<Murtoluku>();
        ArrayList<Op> operaattoritTemp = new ArrayList<Op>();
        for (int i = 0; i < operandit.length; i++) {
            //välttämätöntä luoda uusi Kysymys ja Luku, sillä muuten
            //olemassa olevat oliot menevät sekaisin
            int tempOsoittaja = operandit[i].lukuarvo().getOsoittaja();
            int tempNimittaja = operandit[i].lukuarvo().getNimittaja();
            operanditTemp.add(new Murtoluku(tempOsoittaja,tempNimittaja));
        }
        operaattoritTemp.addAll(Arrays.asList(operaattorit));


        //ensin kerto- ja jakolaskut
        for (int i = 0; i < operaattorit.length; i++) {

            if (operaattorit[i]==Op.MUL) {
                tulos = operanditTemp.get(i).lukuarvo().tulo(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(NOLLA);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            } else if (operaattorit[i]==Op.DIV) {
                tulos = operanditTemp.get(i).lukuarvo().jaettuna(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(NOLLA);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            }
        }


        //plus- ja vähennyslaskut
        for (int i = 0; i < operaattorit.length; i++) {
            if (operaattorit[i] == Op.PLUS) {
                tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(NOLLA);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            } else if (operaattorit[i]==Op.MIN) {
                tulos = operanditTemp.get(i).lukuarvo().vahennys(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(NOLLA);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            }
        }

        //lasketaan jäljellä olevat yhteen
        tulos.asetaLuvuksi(0,1);
        for (int i = 0; i < operaattorit.length; i++) {
            tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i+1).lukuarvo());

            operanditTemp.get(i).setArvo(NOLLA);
            operanditTemp.get(i+1).setArvo(tulos);
            operaattoritTemp.set(i, Op.PLUS);
        }

        //pitäisi olla vain 1 luku jäljellä, taulukon viimeiesessä solussa
        return operanditTemp.get(operanditTemp.size()-1).lukuarvo();

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
        
       
        for (int i = 0; i < operandit.length; i++) {
            if (operandit[i].onLauseke() == false) {
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

    @Override
    public boolean onLauseke() {
        return true;
    }
    
}