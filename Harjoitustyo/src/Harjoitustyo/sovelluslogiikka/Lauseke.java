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
        attribuuttienAlustus(tilanne);
        
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

    private void attribuuttienAlustus(PeliTilanne tilanne) {
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
    }

    private void lukuarvonLaskentaErikoismerkkiKohdattu(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        operanditTemp.get(i+1).setArvo(operanditTemp.get(i));
        operanditTemp.get(i).setArvo(POISTETTU);
        operaattoritTemp.set(i+1, operaattoritTemp.get(i));
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaJakoOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().jaettuna(operanditTemp.get(i+1).lukuarvo());
        operanditTemp.get(i).setArvo(POISTETTU);
        operanditTemp.get(i+1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaKertoOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().tulo(operanditTemp.get(i+1).lukuarvo());
        operanditTemp.get(i).setArvo(POISTETTU);
        operanditTemp.get(i+1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaMiinusOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().vahennys(operanditTemp.get(i+1).lukuarvo());
        operanditTemp.get(i).setArvo(POISTETTU);
        operanditTemp.get(i+1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaPlusOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i+1).lukuarvo());
        operanditTemp.get(i).setArvo(POISTETTU);
        operanditTemp.get(i+1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaTilapaisenOperandiTaulukonAlustus(ArrayList<Murtoluku> operanditTemp) {
        for (int i = 0; i < operandit.length; i++) {
            //välttämätöntä luoda uusi Kysymys ja Luku, sillä muuten
            //olemassa olevat oliot menevät sekaisin
            int tempOsoittaja = operandit[i].lukuarvo().getOsoittaja();
            int tempNimittaja = operandit[i].lukuarvo().getNimittaja();
            operanditTemp.add(new Murtoluku(tempOsoittaja,tempNimittaja));
        }
    }

    
    /**
     * Asettaa operandit-taulukkoon tasan kaksi Laskettavaa. Nämä voivat olla joko
     * kaksi lauseketta tai yksi lauseke ja yksi satunnaisluku.
     * @param tilanne 
     */
    private void luoSulullinenLauseke(PeliTilanne tilanne) {

        this.operandit = new Laskettava[2];
        this.operaattorit = new Op[1];

        Random r = new Random();
        
        int operandienMaara = r.nextInt(opLkm-1)+1;
        asetaOperandeihinSatunnaislukuTaiLauseke(0, operandienMaara, tilanne);

        operandienMaara=opLkm-operandienMaara;
        asetaOperandeihinSatunnaislukuTaiLauseke(1, operandienMaara, tilanne);

        arvoOperaattorit(tilanne);
    }
    
    /**
     * Asettaa operandien kohtaan taulukonIndeksi joko satunnaisen Murtoluvun tai
     * Lausekkeen. Tämä riippuu operandienMaarasta. Jos se on yksi, luodaan Murtoluku,
     * jos enemmän, luodaan Lauseke. Metodi käyttää apuna tilanne-muuttujaa, jonka
     * opLkm-arvoa muutetaan ja joka palautetaan takaisin vanhaksi kun sitä ei enää
     * tarvita.
     * @param taulukonIndeksi
     * @param operandienMaara
     * @param tilanne
     * @param vanhaOpLkm 
     */
    private void asetaOperandeihinSatunnaislukuTaiLauseke(int taulukonIndeksi, int operandienMaara,
            PeliTilanne tilanne) {
        int vanhaOpLkm = tilanne.getOpLkm();
        
        tilanne.setOpLkm(operandienMaara);
        
        operandienMaara=opLkm-operandienMaara;
        tilanne.setOpLkm(operandienMaara);
        if (operandienMaara == 1) {
            operandit[taulukonIndeksi]=satunnaisluku(true, murtolukuOperandi);                        
        } else {
            try {
                operandit[taulukonIndeksi]=new Lauseke(tilanne);
            } catch (Exception ex) {
                Logger.getLogger(Lauseke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tilanne.setOpLkm(vanhaOpLkm);
    }
    
    /**
     * Asettaa operandit ja operaattorit asetusten mukaan siten, että
     * tähän Lausekkeeseen ei tule sulkuja.
     * @param tilanne 
     */
    private void luoSulutonLauseke(PeliTilanne tilanne) {
        this.operandit = new Laskettava[opLkm];
        this.operaattorit = new Op[opLkm-1];

        arvoOperaattorit(tilanne);
        arvoOperanditSuluttomaanLausekkeeseen(tilanne);  
    }
    
    /**
     * Arpoo satunnaiset Murtoluvut Lausekkeeseen, jossa ei ole sulkuja.
     * Tämä metodi pitää huolen siitä, että yksikään operandi ei ole nolla,
     * jos Lauseke sisältää vain jakolaskuja.
     * @param tilanne 
     */
    private void arvoOperanditSuluttomaanLausekkeeseen(PeliTilanne tilanne) {
        boolean saaOllaNolla;
        
        for (int i = 0; i < opLkm; i++) {
        
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
    
    /**
     * Käy läpi operandit ja operaattorit ja tarkistaa jaetaanko jossakin
     * kohdassa nollalla. Mikäli jaetaan, muutetaan operaattori
     * sopivaOperaattori-metodin palauttamaksi operaattoriksi.
     * 
     * Huomaa, että pelkästään jakolaskuja sisältävässä laskutoimituksessa
     * ei voida joutua tilanteeseen, jossa jaetaan nollalla, sillä 
     * sellaiseen laskutoimitukseen ei luoda yhtään 0-operandia.
     * @param tilanne 
     */
    private void poistaNollallaJako(PeliTilanne tilanne) {   
        for (int i = 1; i < operandit.length; i++) {
            if (operandit[i].lukuarvo().onNolla() && operaattorit[i-1]==Op.DIV) {
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
        Murtoluku satunnaisluku = new Murtoluku(0,1);
        
        if (saaOllaNolla) {
            if (murtoluku) {      
                asetaSatunnaisluvunOsoittajaJaNimittajaSaaOllaNollaTaiMurtoluku(satunnaisluku);
            } else {
                asetaSatunnaisluvunOsoittajaJaNimittajaEiVoiOllaNollaTaiMurtoluku(satunnaisluku);
            }

        } else {
            if (murtoluku) {
                asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_VoiOllaMurtoluku(satunnaisluku);
            } else {
                asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_EiVoiOllaMurtoluku(satunnaisluku);                   
            }
        }
        
        if (negatiivisia) {
            muutaLukuSatunnaisestiNegatiiviseksi(satunnaisluku);
        }
               
        return satunnaisluku;
    }

    /**
     * Muuttaa parametrina annetun luvun satunnaisesti negatiiviseksi.
     * @param luku 
     */
    private void muutaLukuSatunnaisestiNegatiiviseksi(Murtoluku luku) {
        int negaatio=1;
        int negaatioRoll=0;
        negaatioRoll = (int)Math.random()*100;
        
        if (negaatioRoll < Luokkakirjasto.NEG_LUKUJEN_OSUUS_OPERANDEISTA_PROSENTTEINA_KUN_NEG_LUVUT_PAALLA) {
            negaatio=-1;
        }
        
        luku.setOsoittaja(negaatio*luku.getOsoittaja());
    }
    
    private void asetaSatunnaisluvunOsoittajaJaNimittajaSaaOllaNollaTaiMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int)Math.round(Math.random()*operandinMaksimiSuuruus));
        luku.setNimittaja((int)Math.round(Math.random()*(nimittajanMaksimiSuuruus-1)+1));
    }
    
    private void asetaSatunnaisluvunOsoittajaJaNimittajaEiVoiOllaNollaTaiMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int)Math.round(Math.random()*operandinMaksimiSuuruus));
        luku.setNimittaja(1);
    }
    
    private void asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_VoiOllaMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int)Math.round(Math.random()*(operandinMaksimiSuuruus-1)+1));
        luku.setNimittaja((int)Math.round(Math.random()*(nimittajanMaksimiSuuruus-1)+1));
    }
    
    private void asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_EiVoiOllaMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int)Math.round(Math.random()*(operandinMaksimiSuuruus-1)+1));
        luku.setNimittaja(1);        
    }
    
    /**
     * Palauttaa taulukkona kaikki operandit. Käyttö pääasiassa testitarkoituksiin
     * ja voidaan poistaa myöhemmin.
     * @return 
     */
    public Laskettava[] operandiTaulukko() {
        Laskettava[] teksti = new Laskettava[operandit.length];
        for (int i = 0; i < operandit.length; i++) {
            teksti[i]=operandit[i];        
        }
        return teksti;
    }

    /**
     * Palauttaa taulukkona kaikki operaattorit. Käyttö pääasiassa testitarkoituksiin
     * ja voidaan poistaa myöhemmin.
     * @return 
     */
    public Op[] operaattoriTaulukko() {
        Op[] teksti = new Op[operaattorit.length];
        for (int i = 0; i < operaattorit.length; i++) {
            teksti[i]=operaattorit[i];        
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
        
        ArrayList<Murtoluku> operanditTemp = new ArrayList<Murtoluku>();
        ArrayList<Op> operaattoritTemp = new ArrayList<Op>();
        
        lukuarvonLaskentaTilapaisenOperandiTaulukonAlustus(operanditTemp);
        operaattoritTemp.addAll(Arrays.asList(operaattorit));

        lukuarvonLaskentaKertoJaJako(operanditTemp, operaattoritTemp);
        lukuarvonLaskentaPlusJaMiinus(operanditTemp, operaattoritTemp);
        
        lukuarvonLaskentaMuutaErikoismerkitNolliksi(operanditTemp);
        lukuarvonLaskentaLaskeJaljellaOlevatYhteen(operanditTemp, operaattoritTemp);

        //pitäisi olla vain 1 luku jäljellä, taulukon viimeiesessä solussa
        return operanditTemp.get(operanditTemp.size()-1).lukuarvo();

    }
    
    /**
     * Käy läpi tilapäisen operandilistan kaikki alkiot ja laskee ne yhteen.
     * @param operanditTemp
     * @param operaattoritTemp 
     */
    private void lukuarvonLaskentaLaskeJaljellaOlevatYhteen(ArrayList<Murtoluku> operanditTemp, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        //lasketaan jäljellä olevat yhteen
        //tulos.asetaLuvuksi(0,1); --- POISTA JOS NÄYTTÄÄ ETTÄ EI TAPAHDU KAUHEITA
        for (int i = 0; i < operaattorit.length; i++) {
            tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i+1).lukuarvo());

            operanditTemp.get(i).setArvo(POISTETTU);
            operanditTemp.get(i+1).setArvo(tulos);
            operaattoritTemp.set(i, Op.PLUS);
        }
    }
    
    /**
     * Käy läpi Lausekkeen ja tarkistaa onko operandeina erikoismerkkejä. Jos
     * on, muttaa ne nolliksi.
     * @param operanditTemp 
     */
    private void lukuarvonLaskentaMuutaErikoismerkitNolliksi(ArrayList<Murtoluku> operanditTemp) {
        for (Murtoluku murtoluku : operanditTemp) {
            if (murtoluku.onErikoisMerkki()) {
                murtoluku.setArvo(NOLLA);
            }
        }        
    }
    
    private void lukuarvonLaskentaPlusJaMiinus(ArrayList<Murtoluku> operanditTemp,
        ArrayList<Op> operaattoritTemp) {
        //plus- ja vähennyslaskut
        for (int i = 0; i < operaattoritTemp.size(); i++) {
            
            //jos _TÄMÄ_ merkki on erikoismerkki, muutetaan se nollaksi
            if (operanditTemp.get(i).onErikoisMerkki()) {
                operanditTemp.get(i).setArvo(NOLLA);
            }
            
            //Siirretään lukua ja operaatiota yksi askel eteenpäin
            //jos _SEURAAVA_ merkki on erikoismerkki
            if (operanditTemp.get(i+1).onErikoisMerkki()) {
                lukuarvonLaskentaErikoismerkkiKohdattu(operanditTemp, i, operaattoritTemp);
                continue;
            }
            
            //muussa tapauksessa suoritetaan operaatio normaalisti
            if (operaattoritTemp.get(i) == Op.PLUS) {
                lukuarvonLaskentaPlusOperaatio(operanditTemp, i, operaattoritTemp);
            } else if (operaattoritTemp.get(i) == Op.MIN) {
                lukuarvonLaskentaMiinusOperaatio(operanditTemp, i, operaattoritTemp);
            }
        }
    }
    
    private void lukuarvonLaskentaKertoJaJako(ArrayList<Murtoluku> operanditTemp,
        ArrayList<Op> operaattoritTemp) {

        for (int i = 0; i < operaattoritTemp.size(); i++) {
            if (operaattoritTemp.get(i)==Op.MUL) {
                lukuarvonLaskentaKertoOperaatio(operanditTemp, i, operaattoritTemp);
            } else if (operaattoritTemp.get(i)==Op.DIV) {
                lukuarvonLaskentaJakoOperaatio(operanditTemp, i, operaattoritTemp);
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