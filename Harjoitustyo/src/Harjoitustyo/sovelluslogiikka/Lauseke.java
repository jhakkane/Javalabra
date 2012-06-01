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
 * tai toisista Lausekkeista koostuvaa laskutoimitusta. Tätä luokkaa ei voi 
 * toteuttaa vain yhdellä operandilla, ja sen yrittäminen aiheuttaa virhetilanteen. 
 * Yksittäinen luku luodaan jollain Luku-rajapinnan toteuttavalla luokalla.
*
* @author jhakkane
*/
public class Lauseke implements Laskettava {
    private final Murtoluku POISTETTU = new Murtoluku();
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
    private boolean negatiivisia=false;
    
    private int operandinMaksimiSuuruus=20;
    private int nimittajanMaksimiSuuruus=operandinMaksimiSuuruus; //väliaikaisesti
   
    /**
     * Luo Lausekkeen suoraan Laskettava- ja Op-listoista. Tämä konstruktori
     * on tällä hetkellä vain ja ainoastaan testikäyttöä varten. Normaalisti
     * Lauseke luodaan antamalla PeliTilanne konstruktorille, joka luo Lausekkeen
     * asetusten perusteella.
     * @param operandit
     * @param operaattorit 
     */
    public Lauseke(Laskettava[] operandit, Op[] operaattorit) {
        this.operandit=operandit;
        this.operaattorit=operaattorit;
    }
    
    /**Luo Lausekkeen PeliTilanteen asetusten perusteella. On virhetilanne yrittää
     * luoda lauseketta, jossa on vain yksi operandi. Sitä tarkoitusta varten on olemassa
     * Murtoluku.
     * 
     * @param tilanne
     * @throws Exception 
     */
    public Lauseke(PeliTilanne tilanne) throws Exception {

        //PeliTilanteen muuttujia ei saa muuttaa, koska muuten pelin asetuksetkin muuttuvat.
        opLkm=tilanne.getOpLkm();
        sulkuja=tilanne.isSulkuja();
        plus=tilanne.isPlus();
        miinus=tilanne.isMiinus();
        kerto=tilanne.isKerto();
        jako=tilanne.isJako();
        operandinMaksimiSuuruus=tilanne.getOperandMax();
        murtolukuOperandi=tilanne.isMurtolukuja();
        negatiivisia=tilanne.isNegatiivisia();
        
        if (opLkm < 2) {
            throw new Exception("Operandien määrän pitää olla vähintään 2");
        }
        
        if (opLkm == 2) {
            sulkuja=false;
        }
        
        //yhtenäinen sulkujen sisällä oleva kokonaisuus
        //tai laskutoimitus jossa ei ole sulkuja ollenkaan
        if (sulkuja == false) {
            
            luoSulutonLauseke(tilanne);

        //vähintään 3 operandia
        } else {
            
            luoSulullinenLauseke(tilanne);
        }
        
        
        /*Tässä kohtaa muutellaan laskutoimitus sopivaksi siten, että siellä ei ole
         * nollalla jakoa ja että jakolaskuista tulee sopivia osamääriä.
        */
        poistaNollallaJako(tilanne);
       
    }
    
    private void luoSulullinenLauseke(PeliTilanne tilanne) {
        //jaetaan luvut kahteen lausekkeeseen tai yhteen lausekkeeseen ja yhteen satunnaislukuun

        this.operandit = new Laskettava[2];
        this.operaattorit = new Op[1];

        Random r = new Random();
        int vali = r.nextInt(opLkm-1)+1;

        int vanhaOpLkm = tilanne.getOpLkm();

        tilanne.setOpLkm(vali);
        if (vali == 1) {
            operandit[0]=satunnaisluku(true,murtolukuOperandi);                        
        } else {
            try {
                operandit[0]=new Lauseke(tilanne);
            } catch (Exception ex) {
                Logger.getLogger(Lauseke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        vali=opLkm-vali;
        tilanne.setOpLkm(vali);
        if (vali == 1) {
            operandit[1]=satunnaisluku(true,murtolukuOperandi);                        
        } else {
            try {
                operandit[1]=new Lauseke(tilanne);
            } catch (Exception ex) {
                Logger.getLogger(Lauseke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        tilanne.setOpLkm(vanhaOpLkm);

        arvoOperaattorit(tilanne);
    }
    
    private void luoSulutonLauseke(PeliTilanne tilanne) {
        this.operandit = new Laskettava[opLkm];
        this.operaattorit = new Op[opLkm-1];

        arvoOperaattorit(tilanne);
        arvoOperanditSuluttomaanLausekkeeseen(tilanne);  
    }
    
    private void arvoOperanditSuluttomaanLausekkeeseen(PeliTilanne tilanne) {
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
    
    private void arvoOperaattorit(PeliTilanne tilanne) {
        ArrayList<Op>sallitutOperaattorit = new ArrayList<Op>();
        if (plus) { sallitutOperaattorit.add(Op.PLUS); }
        if (miinus) { sallitutOperaattorit.add(Op.MIN); }
        if (kerto) { sallitutOperaattorit.add(Op.MUL); }
        if (jako) { sallitutOperaattorit.add(Op.DIV); }
        
        
        for (int i = 0; i < operaattorit.length; i++) {
            Random r = new Random();
            int opnro = r.nextInt(sallitutOperaattorit.size());

            operaattorit[i]=sallitutOperaattorit.get(opnro);
        }
    }
    
    /**
     * Palauttaa jonkin sellaisen operaattorin, joka on tässä laskutoimituksessa sallittu.
     * @param tilanne
     * @return 
     */
    private Op sopivaOperaattori(PeliTilanne tilanne) {
        Op sopiva=Op.PLUS;
        if (tilanne.isJako()) { sopiva = Op.DIV; }
        if (tilanne.isKerto()) { sopiva = Op.MUL; }
        if (tilanne.isMiinus()) { sopiva = Op.MIN; }
        if (tilanne.isPlus()) { sopiva = Op.PLUS; }
        
        return sopiva;
    }
    
    private void poistaNollallaJako(PeliTilanne tilanne) {
        
        for (int i = 1; i < operandit.length; i++) {
            if (operandit[i].lukuarvo().onNolla() && operaattorit[i-1]==Op.DIV) {
                //Huomaa, että pelkästään jakolaskuja sisältävässä laskutoimituksessa
                //ei voida joutua tähän, sillä siihen ei luoda yhtään 0-operandia.
                operaattorit[i-1]=sopivaOperaattori(tilanne);
            }     
        }
    }
    
    
    /**
     * Luo Luku-tyyppisen muuttujan annettujen parametrien ja 
     * tilanne-olion mukaisesti.
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
                osoittaja=(int)Math.round(Math.random()*operandinMaksimiSuuruus);           
            } else {
                nimittaja=1;
                osoittaja=(int)Math.round(Math.random()*operandinMaksimiSuuruus);                   
            }

        } else {
            if (murtoluku) {
                //(nimittaja*operandinMaksimiSuuruus)/nimittaja = operandinMaksimiSuuruus
                nimittaja=(int)Math.round(Math.random()*(nimittajanMaksimiSuuruus-1)+1);
                osoittaja=(int)Math.round(Math.random()*(operandinMaksimiSuuruus-1)+1);  
            } else {
                //(nimittaja*operandinMaksimiSuuruus)/nimittaja = operandinMaksimiSuuruus
                nimittaja=1;
                osoittaja=(int)Math.round(Math.random()*nimittaja*(operandinMaksimiSuuruus-1)+1);                        
            }
        }

        int negaatio=1;
        if (negatiivisia) {
            negaatio = (int)Math.round(Math.random()*2-1);   
        }
               
        osoittaja=osoittaja*negaatio;
        luku = new Murtoluku(osoittaja,nimittaja);   
        return luku;
    }

    /**
     * Palauttaa tekstinä kaikki operandit. Käyttö pääasiassa testitarkoituksiin
     * ja voidaan poistaa myöhemmin.
     * @return 
     */
    public String optext() {
        String teksti="";
        for (Laskettava laskettava : operandit) {
            teksti=teksti+laskettava+", ";
        }
     
        return teksti;
    }
    
    /**
     * Palauttaa Luku-tyyppisen muuttujan, jonka arvo on sama kuin tämän
     * kysymyksen ratkaisu.
     * 
     * Toiminta perustuu siihen, että ensin lasketaan kaikki kerto- ja jakolaskut
     * ja kerrottavan tai jakajan paikalle laitetaan tulo tai osamäärä.
     * Jaettavan tai kerrottavan paikalle laitetaan erikoismerkki 0/0.
     * Sitten lasketaan plus- ja vähennyslaskut jäljelle jääneillä operandeilla.
     * Seuraavaksi korvataan erikoismerkit nollilla.
     * Lopuksi lasketaan luvut yhteen.
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

        
        lukuarvonLaskentaKertoJaJako(operanditTemp, operaattoritTemp, tulos);
        lukuarvonLaskentaPlusJaMiinus(operanditTemp, operaattoritTemp, tulos);
        
        lukuarvonLaskentaMuutaErikoismerkitNolliksi(operanditTemp);

        //lasketaan jäljellä olevat yhteen
        tulos.asetaLuvuksi(0,1);
        for (int i = 0; i < operaattorit.length; i++) {
            tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i+1).lukuarvo());

            operanditTemp.get(i).setArvo(POISTETTU);
            operanditTemp.get(i+1).setArvo(tulos);
            operaattoritTemp.set(i, Op.PLUS);
        }

        //pitäisi olla vain 1 luku jäljellä, taulukon viimeiesessä solussa
        return operanditTemp.get(operanditTemp.size()-1).lukuarvo();

    }
    
    private void lukuarvonLaskentaMuutaErikoismerkitNolliksi(ArrayList<Murtoluku> operanditTemp) {
        for (Murtoluku murtoluku : operanditTemp) {
            if (murtoluku.onErikoisMerkki()) {
                murtoluku.setArvo(NOLLA);
            }
        }
        
    }
    
    private void lukuarvonLaskentaPlusJaMiinus(ArrayList<Murtoluku> operanditTemp,
        ArrayList<Op> operaattoritTemp, Murtoluku tulos) {
        //plus- ja vähennyslaskut
        for (int i = 0; i < operaattoritTemp.size(); i++) {
            
            //jos _TÄMÄ_ merkki on erikoismerkki, muutetaan se nollaksi
            if (operanditTemp.get(i).onErikoisMerkki()) {
                operanditTemp.get(i).setArvo(NOLLA);
            }
            
            //Siirretään lukua ja operaatiota yksi askel eteenpäin
            //jos _SEURAAVA_ merkki on erikoismerkki
            if (operanditTemp.get(i+1).onErikoisMerkki()) {

                operanditTemp.get(i+1).setArvo(operanditTemp.get(i));
                operanditTemp.get(i).setArvo(POISTETTU);

                operaattoritTemp.set(i+1, operaattoritTemp.get(i));        
                operaattoritTemp.set(i, Op.PLUS);
                continue;
            }
            
            
            if (operaattoritTemp.get(i) == Op.PLUS) {
                
                tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(POISTETTU);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            } else if (operaattoritTemp.get(i) == Op.MIN) {
                
                tulos = operanditTemp.get(i).lukuarvo().vahennys(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(POISTETTU);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            }
        }
    }
    
    private void lukuarvonLaskentaKertoJaJako(ArrayList<Murtoluku> operanditTemp,
        ArrayList<Op> operaattoritTemp, Murtoluku tulos) {

        for (int i = 0; i < operaattoritTemp.size(); i++) {

            if (operaattoritTemp.get(i)==Op.MUL) {
                tulos = operanditTemp.get(i).lukuarvo().tulo(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(POISTETTU);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            } else if (operaattoritTemp.get(i)==Op.DIV) {
                tulos = operanditTemp.get(i).lukuarvo().jaettuna(operanditTemp.get(i+1).lukuarvo());

                operanditTemp.get(i).setArvo(POISTETTU);
                operanditTemp.get(i+1).setArvo(tulos);
                operaattoritTemp.set(i, Op.PLUS);
            }
        }
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

    /**
     * Tällä metodilla Lauseke erottaa operandeistaan, onko kyse Lausekkeesta
     * vai Murtoluvusta.
     * @return 
     */
    @Override
    public boolean onLauseke() {
        return true;
    }
    
}