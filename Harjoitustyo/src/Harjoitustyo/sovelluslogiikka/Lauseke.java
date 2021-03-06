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

/**
 * Laskettava-rajapinnan toteuttava luokka, joka kuvaa yhdestä tai useammasta
 * Luvusta tai toisista Lausekkeista koostuvaa laskutoimitusta. Normaalisti
 * yksittäinen Murtoluku on syytä tehdä Murtoluku-luokan avulla, mutta jos
 * Murtoluku halutaan korottaa johonkin potenssiin, on käytettävä lauseketta.
 * 
* @author jhakkane
 */
public class Lauseke implements Laskettava { 
    private Laskettava[] operandit; //sisältää siis sekä Lukuja että Lausekkeita
    private Op[] operaattorit;
    private int eksponentti;
    private int opLkm;
    private boolean sulkuja;
    private boolean plus;
    private boolean miinus;
    private boolean kerto;
    private boolean jako;
    private boolean murtolukuOperandi;
    private boolean negatiivisia;
    private boolean potenssi;
    private int operandinMaksimiSuuruus;
    private int nimittajanMaksimiSuuruus;

    /**
     * Luo Lausekkeen suoraan Laskettava- ja Op-listoista. Tämä konstruktori on
     * tällä hetkellä vain ja ainoastaan testikäyttöä varten. Normaalisti
     * Lauseke luodaan antamalla PeliTilanne konstruktorille, joka luo
     * Lausekkeen asetusten perusteella.
     *
     * @param operandit
     * @param operaattorit
     */
    public Lauseke(Laskettava[] operandit, Op[] operaattorit) {
        attribuuttienAlustusOletusarvoilla();
        
        this.operandit = operandit;
        this.operaattorit = operaattorit;
    }

    /**
     * Luo Lausekkeen suoraan eksponentista ja Laskettava- sekä Op-listoista.
     * Tämä konstruktori on tällä hetkellä vain ja ainoastaan testikäyttöä
     * varten. Normaalisti Lauseke luodaan antamalla PeliTilanne
     * konstruktorille, joka luo Lausekkeen asetusten perusteella.
     *
     * @param operandit
     * @param operaattorit
     * @param eksponentti
     */
    public Lauseke(Laskettava[] operandit, Op[] operaattorit, int eksponentti) {
        attribuuttienAlustusOletusarvoilla();
        
        this.operandit = operandit;
        this.operaattorit = operaattorit;
        this.eksponentti = eksponentti;
    }

    /**
     * Luo Lausekkeen yhden Murtoluvun ja eksponentin perusteella.
     *
     * @param luku
     * @param eksponentti
     */
    public Lauseke(Murtoluku luku, int eksponentti) {
        attribuuttienAlustusOletusarvoilla();    
    
        operandit = new Laskettava[1];
        operandit[0] = luku;
        this.eksponentti = eksponentti;
    }

    /**
     * Luo Lausekkeen PeliTilanteen asetusten perusteella. On mahdollista luoda
     * myös yhden operandin lausekkeita.
     *
     * @param tilanne
     * @throws Exception
     */
    public Lauseke(PeliTilanne tilanne) throws Exception {
        attribuuttienAlustusOletusarvoilla();
        attribuuttienAlustus(tilanne);

        if (opLkm < 2) {
            luoYhdenLuvunLauseke(tilanne);
        }

        //ei sulkuja yksittäisten operandien ympärillä: ei siis esim. (2) + (5)
        if (opLkm == 2) {
            sulkuja = false;
        }
        luoSulullinenTaiSulutonLauseke(tilanne);

        poistaNollallaJako(tilanne);

    }

    /**
     * Siirretään lukua ja operaatiota yksi askel eteenpäin
     * jos _SEURAAVA_ merkki on erikoismerkki. Jos siirrettiin,
     * palautetaan true, muuten false,
     * @param operanditTemp
     * @param i
     * @param operaattoritTemp
     * @return 
     */
    private boolean liikutaLukuaJaOperaattoriaJosTarpeen(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        if (operanditTemp.get(i + 1).onErikoisMerkki()) {
            lukuarvonLaskentaErikoismerkkiKohdattu(operanditTemp, i, operaattoritTemp);
            return true;
        }
        return false;
    }

    private void luoSulullinenTaiSulutonLauseke(PeliTilanne tilanne) {
        //yhtenäinen sulkujen sisällä oleva kokonaisuus
        //tai laskutoimitus jossa ei ole sulkuja ollenkaan
        if (sulkuja == false) {
            luoSulutonLauseke(tilanne);
        } else {
            //vähintään 3 operandia
            luoSulullinenLauseke(tilanne);
        }
    }

    private void arvoOperaattoritMaaritaSallitutOperaattorit(ArrayList<Op> sallitutOperaattorit) {
        if (plus) {
            sallitutOperaattorit.add(Op.PLUS);
        }
        if (miinus) {
            sallitutOperaattorit.add(Op.MIN);
        }
        if (kerto) {
            sallitutOperaattorit.add(Op.MUL);
        }
        if (jako) {
            sallitutOperaattorit.add(Op.DIV);
        }
    }

    private void asetaTaulukkoonMurtolukuTaiLausekeTilanteestaRiippuen(int operandienMaara, int taulukonIndeksi, PeliTilanne tilanne) {
        if (operandienMaara == 1) {
            operandit[taulukonIndeksi] = satunnaisluku(true, murtolukuOperandi);
        } else {
            try {
                operandit[taulukonIndeksi] = new Lauseke(tilanne);
            } catch (Exception ex) {
                Logger.getLogger(Lauseke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void luoYhdenLuvunLauseke(PeliTilanne tilanne) {
        this.operandit = new Laskettava[1];
        this.operaattorit = null;
        this.eksponentti = (int) (Math.round(Math.random() * Luokkakirjasto.PELITILANNE_EKSPONENTTI_MAX));
        operandit[0] = satunnaisluku(true, murtolukuOperandi);
    }

    private void attribuuttienAlustus(PeliTilanne tilanne) {
        opLkm = tilanne.getOpLkm();
        sulkuja = tilanne.isSulkuja();
        plus = tilanne.isPlus();
        miinus = tilanne.isMiinus();
        kerto = tilanne.isKerto();
        jako = tilanne.isJako();
        operandinMaksimiSuuruus = tilanne.getOperandMax();
        murtolukuOperandi = tilanne.isMurtolukuja();
        negatiivisia = tilanne.isNegatiivisia();
        potenssi = tilanne.isPotenssi();
    }

    private void attribuuttienAlustusOletusarvoilla() {
        operandit = null; //sisältää siis sekä Lukuja että Lausekkeita
        operaattorit = null;
        eksponentti = 1;
        opLkm = 2;
        sulkuja = false;
        plus = true;
        miinus = false;
        kerto = false;
        jako = false;
        murtolukuOperandi = false;
        negatiivisia = false;
        potenssi = false;
        operandinMaksimiSuuruus = 20;
        nimittajanMaksimiSuuruus = operandinMaksimiSuuruus;
    }
    
    
    /**
     * Kertoo kuvaako tämä Lauseke yhden ainoan kokonaisluvun. Näin on
     * mikäli Lauseke koostuu yhdestä kokonaislukuoperandista.
     * @return 
     */
    @Override
    public boolean onKokonaisluku() {
        if (operandit.length==1) {
            return operandit[0].onKokonaisluku();
        }
        return false;
    }

    private void lukuarvonLaskentaErikoismerkkiKohdattu(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        operanditTemp.get(i + 1).setArvo(operanditTemp.get(i));
        operanditTemp.get(i).setArvo(Luokkakirjasto.POISTETTU);
        operaattoritTemp.set(i + 1, operaattoritTemp.get(i));
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaJakoOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().jaettuna(operanditTemp.get(i + 1).lukuarvo());
        operanditTemp.get(i).setArvo(Luokkakirjasto.POISTETTU);
        operanditTemp.get(i + 1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaKertoOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().tulo(operanditTemp.get(i + 1).lukuarvo());
        operanditTemp.get(i).setArvo(Luokkakirjasto.POISTETTU);
        operanditTemp.get(i + 1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaMiinusOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().vahennys(operanditTemp.get(i + 1).lukuarvo());
        operanditTemp.get(i).setArvo(Luokkakirjasto.POISTETTU);
        operanditTemp.get(i + 1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    private void lukuarvonLaskentaPlusOperaatio(ArrayList<Murtoluku> operanditTemp, int i, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i + 1).lukuarvo());
        operanditTemp.get(i).setArvo(Luokkakirjasto.POISTETTU);
        operanditTemp.get(i + 1).setArvo(tulos);
        operaattoritTemp.set(i, Op.PLUS);
    }

    /**
     * Luo uuden, tilapäisen, kaikki operandit sisältävän taulukon lukuarvon
     * laskentaa varten. Tavallista operanditaulukkoa ei voi käyttää, sillä
     * taulukon arvoja joudutaan muuttamaan.
     * @param operanditTemp 
     */
    private void lukuarvonLaskentaTilapaisenOperandiTaulukonAlustus(ArrayList<Murtoluku> operanditTemp) {
        for (int i = 0; i < operandit.length; i++) {
            int tempOsoittaja = operandit[i].lukuarvo().getOsoittaja();
            int tempNimittaja = operandit[i].lukuarvo().getNimittaja();
            operanditTemp.add(new Murtoluku(tempOsoittaja, tempNimittaja));
        }
    }

    /**
     * Asettaa operandit-taulukkoon tasan kaksi Laskettavaa. Nämä voivat olla
     * joko kaksi lauseketta tai yksi lauseke ja yksi satunnaisluku.
     *
     * @param tilanne
     */
    private void luoSulullinenLauseke(PeliTilanne tilanne) {

        this.operandit = new Laskettava[2];
        this.operaattorit = new Op[1];

        Random r = new Random();

        int operandienMaara = r.nextInt(opLkm - 1) + 1;
        asetaOperandeihinSatunnaislukuTaiLauseke(0, operandienMaara, tilanne);

        operandienMaara = opLkm - operandienMaara;
        asetaOperandeihinSatunnaislukuTaiLauseke(1, operandienMaara, tilanne);

        arvoOperaattorit(tilanne);
    }

    /**
     * Asettaa operandien kohtaan taulukonIndeksi joko satunnaisluvun tai uuden
     * Lausekkeen. Tämä riippuu operandienMaarasta. Jos se on yksi, luodaan
     * Murtoluku, jos enemmän, luodaan Lauseke. Metodi käyttää apuna
     * tilanne-muuttujaa, jonka opLkm-arvoa muutetaan ja joka palautetaan
     * takaisin vanhaksi kun sitä ei enää tarvita.
     *
     * @param taulukonIndeksi
     * @param operandienMaara
     * @param tilanne
     * @param vanhaOpLkm
     */
    private void asetaOperandeihinSatunnaislukuTaiLauseke(int taulukonIndeksi, int operandienMaara,
            PeliTilanne tilanne) {

        int vanhaOpLkm = tilanne.getOpLkm();

        tilanne.setOpLkm(operandienMaara);

        operandienMaara = opLkm - operandienMaara;
        tilanne.setOpLkm(operandienMaara);
        asetaTaulukkoonMurtolukuTaiLausekeTilanteestaRiippuen(operandienMaara, taulukonIndeksi, tilanne);
        tilanne.setOpLkm(vanhaOpLkm);
    }

    /**
     * Asettaa operandit ja operaattorit asetusten mukaan siten, että tähän
     * Lausekkeeseen ei tule sulkuja.
     *
     * @param tilanne
     */
    private void luoSulutonLauseke(PeliTilanne tilanne) {
        this.operandit = new Laskettava[opLkm];
        this.operaattorit = new Op[opLkm - 1];

        arvoOperaattorit(tilanne);
        arvoOperanditSuluttomaanLausekkeeseen(tilanne);
    }

    /**
     * Arpoo satunnaiset Murtoluvut Lausekkeeseen, jossa ei ole sulkuja. Tämä
     * metodi pitää huolen siitä, että yksikään operandi ei ole nolla, jos
     * Lauseke sisältää vain jakolaskuja.
     *
     * @param tilanne
     */
    private void arvoOperanditSuluttomaanLausekkeeseen(PeliTilanne tilanne) {
        boolean saaOllaNolla;

        for (int i = 0; i < operandit.length; i++) {

            if (!plus && !miinus && !kerto && jako) {
                saaOllaNolla = false;
            } else {
                saaOllaNolla = true;
            }

            operandit[i] = satunnaisluku(saaOllaNolla, murtolukuOperandi);
        }
    }

    /**
     * Asettaa operaattorit-taulukkoon satunnaisesti jonkun asetusten tällä
     * hetkellä salliman operaattorin.
     *
     * @param tilanne
     */
    private void arvoOperaattorit(PeliTilanne tilanne) {
        ArrayList<Op> sallitutOperaattorit = new ArrayList<Op>();
        arvoOperaattoritMaaritaSallitutOperaattorit(sallitutOperaattorit);

        for (int i = 0; i < operaattorit.length; i++) {
            Random r = new Random();
            int opnro = r.nextInt(sallitutOperaattorit.size());

            operaattorit[i] = sallitutOperaattorit.get(opnro);
        }
    }

    private void muutaTamaMerkkiErikoismerkiksiJosTarpeen(ArrayList<Murtoluku> operanditTemp, int i) {
        //jos _TÄMÄ_ merkki on erikoismerkki, muutetaan se nollaksi
        if (operanditTemp.get(i).onErikoisMerkki()) {
            operanditTemp.get(i).setArvo(Luokkakirjasto.NOLLA);
        }
    }

    private void satunnaislukuLuoOsoittajaJaNimittajaAsetustenPerusteella(boolean saaOllaNolla, boolean murtoluku, Murtoluku murtolukuosa) {
        if (saaOllaNolla) {
            if (murtoluku) {
                asetaSatunnaisluvunOsoittajaJaNimittajaSaaOllaNollaTaiMurtoluku(murtolukuosa);
            } else {
                asetaSatunnaisluvunOsoittajaJaNimittajaEiVoiOllaNollaTaiMurtoluku(murtolukuosa);
            }
        } else {
            if (murtoluku) {
                asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_VoiOllaMurtoluku(murtolukuosa);
            } else {
                asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_EiVoiOllaMurtoluku(murtolukuosa);
            }
        }
    }

    /**
     * Palauttaa jonkin sellaisen operaattorin, joka on tässä laskutoimituksessa
     * sallittu.
     *
     * @param tilanne
     * @return
     */
    private Op sopivaOperaattori(PeliTilanne tilanne) {
        Op sopiva = Op.PLUS;
        if (tilanne.isJako()) {
            sopiva = Op.DIV;
        }
        if (tilanne.isKerto()) {
            sopiva = Op.MUL;
        }
        if (tilanne.isMiinus()) {
            sopiva = Op.MIN;
        }
        if (tilanne.isPlus()) {
            sopiva = Op.PLUS;
        }

        return sopiva;
    }

    /**
     * Käy läpi operandit ja operaattorit ja tarkistaa jaetaanko jossakin
     * kohdassa nollalla. Mikäli jaetaan, muutetaan operaattori
     * sopivaOperaattori-metodin palauttamaksi operaattoriksi.
     *
     * Huomaa, että pelkästään jakolaskuja sisältävässä laskutoimituksessa ei
     * voida joutua tilanteeseen, jossa jaetaan nollalla, sillä sellaiseen
     * laskutoimitukseen ei luoda yhtään 0-operandia.
     *
     * @param tilanne
     */
    private void poistaNollallaJako(PeliTilanne tilanne) {
        for (int i = 1; i < operandit.length; i++) {
            if (tamaOperandiYritetaanJakaaNollalla(i)) {
                operaattorit[i - 1] = sopivaOperaattori(tilanne);
            }
        }
    }

    private void suoritaPlusTaiMiinusLasku(ArrayList<Op> operaattoritTemp, int i, ArrayList<Murtoluku> operanditTemp) {
        //muussa tapauksessa suoritetaan operaatio normaalisti
        if (operaattoritTemp.get(i) == Op.PLUS) {
            lukuarvonLaskentaPlusOperaatio(operanditTemp, i, operaattoritTemp);
        } else if (operaattoritTemp.get(i) == Op.MIN) {
            lukuarvonLaskentaMiinusOperaatio(operanditTemp, i, operaattoritTemp);
        }
    }

    private boolean tamaOperandiYritetaanJakaaNollalla(int i) {
        return operandit[i].lukuarvo().onNolla() && operaattorit[i - 1] == Op.DIV;
    }

    /**
     * Luo Lauseke-tyyppisen muuttujan, joka koostuu vain yhdestä Murtoluku-tyyppisestä
     * operandista.
     *
     * @param saaOllaNolla
     * @param murtoluku
     * @return
     */
    private Lauseke satunnaisluku(boolean saaOllaNolla, boolean murtoluku) {
        Murtoluku murtolukuosa = new Murtoluku(0, 1);
        satunnaislukuLuoOsoittajaJaNimittajaAsetustenPerusteella(saaOllaNolla, murtoluku, murtolukuosa);

        if (negatiivisia) {
            muutaLukuSatunnaisestiNegatiiviseksi(murtolukuosa);
        }
        
        int exp = arvoSopivaEksponenttiMurtoluvulle(murtolukuosa);
        Lauseke satunnaisluku = new Lauseke(murtolukuosa, exp);
        
        return satunnaisluku;
    }

    /**
     * Arpoo parametrina annetun Murtoluvun perusteella sopivan eksponentin
     * tämän Lausekkeen asetusten perusteella.
     * @param luku 
     */
    private int arvoSopivaEksponenttiMurtoluvulle(Murtoluku luku) {
        if (potenssi && !luku.onNolla()) {
            return (int)(Math.round(Math.random()*Luokkakirjasto.PELITILANNE_EKSPONENTTI_MAX));
        }
        return 1;
    }

    /**
     * Muuttaa parametrina annetun luvun satunnaisesti negatiiviseksi.
     *
     * @param luku
     */
    private void muutaLukuSatunnaisestiNegatiiviseksi(Murtoluku luku) {
        int negaatio = 1;
        int negaatioRoll = 0;
        negaatioRoll = (int) (Math.random() * 100);

        if (negaatioRoll < Luokkakirjasto.NEG_LUKUJEN_OSUUS_OPERANDEISTA_PROSENTTEINA_KUN_NEG_LUVUT_PAALLA) {
            negaatio = -1;
        }

        luku.setOsoittaja(negaatio * luku.getOsoittaja());
    }

    private void asetaSatunnaisluvunOsoittajaJaNimittajaSaaOllaNollaTaiMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int) Math.round(Math.random() * operandinMaksimiSuuruus));
        luku.setNimittaja((int) Math.round(Math.random() * (nimittajanMaksimiSuuruus - 1) + 1));
    }

    private void asetaSatunnaisluvunOsoittajaJaNimittajaEiVoiOllaNollaTaiMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int) Math.round(Math.random() * operandinMaksimiSuuruus));
        luku.setNimittaja(1);
    }

    private void asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_VoiOllaMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int) Math.round(Math.random() * (operandinMaksimiSuuruus - 1) + 1));
        luku.setNimittaja((int) Math.round(Math.random() * (nimittajanMaksimiSuuruus - 1) + 1));
    }

    private void asetaSatunnaisluvunOsoittajaJaNimittaja_EiVoiOllaNolla_EiVoiOllaMurtoluku(Murtoluku luku) {
        luku.setOsoittaja((int) Math.round(Math.random() * (operandinMaksimiSuuruus - 1) + 1));
        luku.setNimittaja(1);
    }

    /**
     * Palauttaa taulukkona kaikki operandit. Käyttö pääasiassa
     * testitarkoituksiin ja voidaan poistaa myöhemmin.
     *
     * @return
     */
    public Laskettava[] operandiTaulukko() {
        Laskettava[] operanditaulukko = new Laskettava[operandit.length];
        for (int i = 0; i < operandit.length; i++) {
            operanditaulukko[i] = operandit[i];
        }
        return operanditaulukko;
    }

    /**
     * Palauttaa taulukkona kaikki operaattorit. Käyttö pääasiassa
     * testitarkoituksiin ja voidaan poistaa myöhemmin.
     *
     * @return
     */
    public Op[] operaattoriTaulukko() {
        Op[] operaattoritaulukko = new Op[operaattorit.length];
        for (int i = 0; i < operaattorit.length; i++) {
            operaattoritaulukko[i] = operaattorit[i];
        }
        return operaattoritaulukko;
    }

    /**
     * Palauttaa Luku-tyyppisen muuttujan, jonka arvo on sama kuin tämän
     * kysymyksen ratkaisu.
     *
     * Jos operanditaulukossa on vain yksi alkio, käsitellään se suoraan
     * palauttamalla tämä luku korotettuna eksponentin ilmoittamaan potenssiin.
     *
     * Muussa tapauksessa toiminta perustuu siihen, että ensin lasketaan kaikki
     * kerto- ja jakolaskut ja kerrottavan tai jakajan paikalle laitetaan tulo
     * tai osamäärä. Jaettavan tai kerrottavan paikalle laitetaan erikoismerkki
     * 0/0. Sitten lasketaan plus- ja vähennyslaskut jäljelle jääneillä
     * operandeilla. Seuraavaksi korvataan erikoismerkit nollilla. Lopuksi
     * lasketaan luvut yhteen.
     *
     * @return
     */
    @Override
    public Murtoluku lukuarvo() {

        if (operandit.length == 1) {
            return lukuarvonLaskentaVainYksioperandi();
        }

        ArrayList<Murtoluku> operanditTemp = new ArrayList<Murtoluku>();
        ArrayList<Op> operaattoritTemp = new ArrayList<Op>();
        Murtoluku lukuarvo;

        lukuarvonLaskentaTilapaisenOperandiTaulukonAlustus(operanditTemp);
        operaattoritTemp.addAll(Arrays.asList(operaattorit));

        lukuarvonLaskentaKertoJaJako(operanditTemp, operaattoritTemp);
        lukuarvonLaskentaPlusJaMiinus(operanditTemp, operaattoritTemp);

        lukuarvonLaskentaMuutaErikoismerkitNolliksi(operanditTemp);
        lukuarvonLaskentaLaskeJaljellaOlevatYhteen(operanditTemp, operaattoritTemp);

        //pitäisi olla vain 1 luku jäljellä, taulukon viimeisessä solussa
        lukuarvo = operanditTemp.get(operanditTemp.size() - 1).lukuarvo();
        lukuarvo = lukuarvo.korotaPotenssiin(eksponentti);
        return lukuarvo;
    }

    /**
     * Palauttaa operanditaulukon ainoan alkion korotettuna eksponentin
     * ilmoittamaan potenssiin.
     *
     * @return
     */
    private Murtoluku lukuarvonLaskentaVainYksioperandi() {
        Murtoluku lukuarvo = operandit[0].lukuarvo();
        lukuarvo = lukuarvo.korotaPotenssiin(eksponentti);
        return lukuarvo;
    }

    /**
     * Käy läpi tilapäisen operandilistan kaikki alkiot ja laskee ne yhteen.
     *
     * @param operanditTemp
     * @param operaattoritTemp
     */
    private void lukuarvonLaskentaLaskeJaljellaOlevatYhteen(ArrayList<Murtoluku> operanditTemp, ArrayList<Op> operaattoritTemp) {
        Murtoluku tulos;
        //lasketaan jäljellä olevat yhteen
        for (int i = 0; i < operaattorit.length; i++) {
            tulos = operanditTemp.get(i).lukuarvo().summa(operanditTemp.get(i + 1).lukuarvo());

            operanditTemp.get(i).setArvo(Luokkakirjasto.POISTETTU);
            operanditTemp.get(i + 1).setArvo(tulos);
            operaattoritTemp.set(i, Op.PLUS);
        }
    }

    /**
     * Käy läpi Lausekkeen ja tarkistaa onko operandeina erikoismerkkejä. Jos
     * on, muttaa ne nolliksi.
     *
     * @param operanditTemp
     */
    private void lukuarvonLaskentaMuutaErikoismerkitNolliksi(ArrayList<Murtoluku> operanditTemp) {
        for (Murtoluku murtoluku : operanditTemp) {
            if (murtoluku.onErikoisMerkki()) {
                murtoluku.setArvo(Luokkakirjasto.NOLLA);
            }
        }
    }

    /**
     * Hoitaa kaikki taulukossa olevat plus- ja miinuslaskut.
     * @param operanditTemp
     * @param operaattoritTemp 
     */
    private void lukuarvonLaskentaPlusJaMiinus(ArrayList<Murtoluku> operanditTemp,
            ArrayList<Op> operaattoritTemp) {
        for (int i = 0; i < operaattoritTemp.size(); i++) {
            muutaTamaMerkkiErikoismerkiksiJosTarpeen(operanditTemp, i);
            
            //Jos liikutetaan, ei yritetä suorittaa mitään laskutoimituksia vaan jatketaan eteenpäin.
            if (liikutaLukuaJaOperaattoriaJosTarpeen(operanditTemp, i, operaattoritTemp)) continue;
            suoritaPlusTaiMiinusLasku(operaattoritTemp, i, operanditTemp);
        }
    }

    /**
     * Hoitaa kaikki taulukossa olevat kerto- ja jakolaskut.
     * @param operanditTemp
     * @param operaattoritTemp 
     */
    private void lukuarvonLaskentaKertoJaJako(ArrayList<Murtoluku> operanditTemp,
            ArrayList<Op> operaattoritTemp) {

        for (int i = 0; i < operaattoritTemp.size(); i++) {
            if (operaattoritTemp.get(i) == Op.MUL) {
                lukuarvonLaskentaKertoOperaatio(operanditTemp, i, operaattoritTemp);
            } else if (operaattoritTemp.get(i) == Op.DIV) {
                lukuarvonLaskentaJakoOperaatio(operanditTemp, i, operaattoritTemp);
            }
        }
    }
    
    /**
     * Palauttaa operanditaulukon koon Lausekkeessa. Lukuja laskutoimituksessa
     * voi olla huomattavasti enemmän, sillä operandit saattavat olla itsekin
     * Lausekkeita jotka sisältävät Murtolukuja ja Lausekkeita.
     * @return 
     */
    public int operandienMaara() {
        if (operandit == null) {
            return 1;
        }
        return operandit.length;
    }

    /**
     * Palauttaa Lausekkeen tekstimuodossa. Mikäli Lausekkeen eksponentti ei ole
     * yksi ja se koostuu useammasta operandista, laitetaan Lausekkeen ympärille sulut.
     * 
     * @return 
     */
    @Override
    public String toString() {
        String teksti = "";
        
        if (operandit.length==1) {
            return toStringYksittainenLuku();
        }
        
        teksti = toStringLisaaOperanditJaOperaattorit(teksti);   
        teksti = toStringAsetaSulutjaEksponenttiJosTarpeen(teksti);
        
        return teksti;
    }

    private String toStringLisaaOperaattorit(int i, String teksti) {
        if (operaattorit != null) {
            if (i < operaattorit.length) {
                teksti = teksti + " " + operaattorit[i].toString() + " ";
            }                    
        }
        return teksti;
    }

    private String toStringLisaaOperandit(int i, String teksti) {
        if (operandit[i].onLauseke()) {
            if (operandit[i].koostuuUseastaOperandista()) {
                teksti = teksti+"("+operandit[i].toString()+")";                            
            } else {
                teksti = teksti+operandit[i].toString();                      
            }
        } else {
            if (operandit[i].lukuarvo().negatiivinen()) {
                teksti=teksti + "("+operandit[i].toString()+")";
            } else {
                teksti = teksti + operandit[i].toString();
            }
        }
        return teksti;
    }

    /**
     * Palauttaa Lausekkeen tekstimuodossa, kun se koostuu vain yhdestä
     * Murtoluvusta.
     * @return 
     */
    private String toStringYksittainenLuku() {
        String teksti=operandit[0].toString();
        if (operandit[0].lukuarvo().negatiivinen() || 
                (!onKokonaisluku() && eksponentti != 1)) {
            teksti="("+teksti+")";
        }
        if (eksponentti != 1) {
            teksti=teksti+"^"+eksponentti;
        }
        return teksti;
    }
    
    private String toStringAsetaSulutjaEksponenttiJosTarpeen(String teksti) {
        if (eksponentti != 1) {
            teksti = "("+teksti+")^"+eksponentti;
        }
                
        return teksti;
    }
    
    /**
     * Lisää toString-metodissa palautettavaan Stringiin tämän
     * Lausekkeen sisältämät operandit ja operaattorit.
     * @param teksti
     * @return 
     */
    private String toStringLisaaOperanditJaOperaattorit(String teksti) {
        for (int i = 0; i < operandit.length; i++) {
            teksti = toStringLisaaOperandit(i, teksti);
            teksti = toStringLisaaOperaattorit(i, teksti);
        }
        return teksti;
    }

    /**
     * Rajapinnan vaatima metodi joka kertoo koostuuko tämä useammasta operandista.
     * Näin on, mikäli operandilista on suurempi kuin yksi.
     * @return 
     */
    @Override
    public boolean koostuuUseastaOperandista() {
        if (operandit.length > 1) {
            return true;
        }
        return false;
    }
    
    /**
     * Tällä metodilla Lauseke erottaa operandeistaan, onko kyse Lausekkeesta
     * vai Murtoluvusta.
     *
     * @return
     */
    @Override
    public boolean onLauseke() {
        return true;
    }
}