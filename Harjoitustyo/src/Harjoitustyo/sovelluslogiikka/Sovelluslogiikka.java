/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Toteuttaa itse ohjelman toiminnan käyttöliittymää lukuunottamatta.
 * Pääasiassa hoitaa pelaajan vastauksen tarkistamisen ja oikean vastauksen
 * näyttämisen.
 *
 * @author jhakkane
 */
public class Sovelluslogiikka {
    
    /**
     * Vaihe 1 = Käyttäjälle näkyy kysymys
     * Vaihe 2 = Käyttäjälle näkyy vastaus
     */
    private PeliTilanne tilanne;
    
    public Sovelluslogiikka() {
        tilanne=new PeliTilanne();
    }
    
    /**
     * Edistää pelitilannetta joko
     * a) Kutsumulla metodia, joka luo käyttäjälle uuden kysymyksen ja 
     * palauttaa sen
     * 
     * tai
     * 
     * b) Kutsumalla metodia, joka tarkistaa käyttäjän antaman vastauksen
     * ja palauttaa kommentin vastauksesta.
     * 
     * Metodi palauttaa edelleen näiden metodien paluuarvon, joka on pelaajalle
     * näkyvä teksti.
     * 
     * @param vastaus
     * @return 
     */
    public String etene(String vastaus) {
        
        if (tilanne.getVaihe() == 2) {
            return luoUusiKysymys();
        } else {
            String output = tarkistaPelaajanVastaus(vastaus);
            return output;
        }
    }
    

    /**
     * Tarkistaa käyttäjän antaman vastauksen ja palauttaa vastauksesta riippuen
     * joko "oikein", "väärin", "ei vastausta" tai "vastaus väärän muotoinen" -paluuarvon.
     * Pelitilanne etenee eli vaihe muuttuu vain silloin kun vastaus on sekä
     * oikein että oikean muotoinen.
     * 
     * @param vastaus
     * @return 
     */
    private String tarkistaPelaajanVastaus(String vastaus) {        
        try {
            Murtoluku oikeaVastaus = tilanne.getKysymys().oikeaVastaus();
            
            Murtoluku pelaajanVastaus = parsePelaajanVastaus(vastaus);
            
            if (pelaajanVastaus.samaLuku(oikeaVastaus)) {
                if (onkoKysymysVaaranMuotoinen(oikeaVastaus, vastaus)) return Luokkakirjasto.kysymykseenAnnettuVaaranMuotoinenVastaus(tilanne);
            
                tilanne.setVaihe(2);
                return pelaajaVastasiKysymykseenOikein(vastaus);
            } else {
                tilanne.setVaihe(2);
                return pelaajaVastasiKysymykseenVaarin(oikeaVastaus);
            }
        } catch (Exception e) {
            return Luokkakirjasto.kysymyksenVastausPelaajaEiAntanutVastausta(tilanne);
        }
        
    }

    private boolean onkoKysymysVaaranMuotoinen(Murtoluku oikeaVastaus, String vastaus) {
        if (oikeaVastaus.onKokonaisluku() && 
                siistiJaPuraPelaajanVastaus(vastaus).length != 1) {
            return true;
        }
        if (oikeaVastaus.onSekaluku() && 
                siistiJaPuraPelaajanVastaus(vastaus).length != 3) {
            return true;
        }
        if (oikeaVastaus.onMurtoluku() && 
                siistiJaPuraPelaajanVastaus(vastaus).length != 2) {
            return true;
        }
        return false;
    }
    
    /**
     * Päivittää PeliTilanteen ja Kysymyksen, sekä palauttaa pelaajalle näkyvän tekstin,
     * kun pelaaja vastasi kysymykseen väärin.
     * @param oikeaVastaus
     * @return 
     */
    private String pelaajaVastasiKysymykseenVaarin(Murtoluku oikeaVastaus) {
        tilanne.vastattu(false);
        tilanne.getKysymys().setOikeinVastattu(false);
        return Luokkakirjasto.kysymyksenVastausVaaraVastaus(tilanne.getKysymys(), oikeaVastaus); 
    }
    
    /**
     * Päivittää PeliTilanteen ja Kysymyksen sekä palauttaa pelaajaa
     * onnittelevan tekstin. Ottaa paremetriksi pelaajan antaman
     * syötteen sellaisenaan.
     * @param vastaus
     * @return 
     */
    private String pelaajaVastasiKysymykseenOikein(String vastaus) {
            tilanne.vastattu(true);
            tilanne.getKysymys().setOikeinVastattu(true);
            
            boolean vaihtuikoKierros = asetustenMuuttaminenTasopelissa();
            
            if (vaihtuikoKierros) {
                return Luokkakirjasto.kysymyksenVastausOikeaVastausJaKierrosVaihtuu(vastaus);
            }
            return Luokkakirjasto.kysymyksenVastausOikeaVastausKierrosEiVaihdu(vastaus);
    }
    
    /**
     * Huolehtii kierroksen vaihtumisesta tasopelissä ja palauttaa boolean-
     * muuttujan, joka kertoo vaihtuiko kierros vai ei.
     * @return 
     */
    public boolean asetustenMuuttaminenTasopelissa() {
        boolean vaihtuikoKierros = false;
        int oikeitaVastauksiaTallaKierroksella = tilanne.getOikeitaVastauksiaTallaKierroksella();
        int kierros = tilanne.getKierros();
        if (tilanne.isTasopeli()) {
            oikeitaVastauksiaTallaKierroksella++;

            if (oikeitaVastauksiaTallaKierroksella > Luokkakirjasto.OIKEITA_VASTAUKSIA_JOTTA_KIERROS_VAIHTUU_TASOPELISSA) {
                kierros++;
                oikeitaVastauksiaTallaKierroksella=0;
                vaihtuikoKierros = true;
            }

            tilanne.setOikeitaVastauksiaTallaKierroksella(oikeitaVastauksiaTallaKierroksella);
            tilanne.setKierros(kierros);
            asetaAsetuksetKierroksenMukaanTasopelissa();
        }
        
        return vaihtuikoKierros;
    }

    /**
     * Muutetaan asetuksia kierroksen perusteella. Mitä paremmin pelaaja on
     * pärjännyt, sitä vaikeammat asetukset.
     */
    private void asetaAsetuksetKierroksenMukaanTasopelissa() {
        //Kierros 0 = alkutilanne (oletusarvot PeliTilanteessa)
        int kierros = tilanne.getKierros();
        
        if (kierros == 1) {
            tilanne.setMiinus(true);
        } else if (kierros == 2) {
            tilanne.setKerto(true);
        } else if (kierros == 3) {
            tilanne.setOpLkm(3);
        } else if (kierros == 4) {
            tilanne.setOpLkm(2);
            tilanne.setMiinus(false);
            tilanne.setPlus(false);
            tilanne.setKerto(false);
            tilanne.setJako(true);
        } else if (kierros == 5) {
            tilanne.setMiinus(true);
            tilanne.setPlus(true);
            tilanne.setKerto(true);
            tilanne.setMurtolukuja(true);
        } else if (kierros == 6) {
            tilanne.setMurtolukuja(false);
            tilanne.setNegatiivisia(true);
        } else if (kierros == 7) {
            tilanne.setPotenssi(true);
        } else if (kierros == 8) {
            tilanne.setMurtolukuja(true);
        } else if (kierros == 9) {
            tilanne.setOpLkm(3);
        } else if (kierros == 10) {
            tilanne.setOpLkm(3);
        } else if (kierros == 11) {
            tilanne.setSulkuja(true);
        }
        
    }
    
    /**
     * Puhdistaa parametrina annetun String-muuttujan alussa ja lopussa olevista
     * välilyönneistä ja muuttaa useamman peräkkäisen välilyönnin yhdeksi.
     * Jakaa Stringin välilyöntien ja kauttaviivojen perusteella ja palauttaa
     * näin muodostetun String-taulukon.
     * @param vastaus
     * @return 
     */
    private String[] siistiJaPuraPelaajanVastaus(String vastaus) {
        String siistiVastaus = vastaus.trim();
        siistiVastaus.replaceAll(" +", " ");
        String[] vastauksenOsat = siistiVastaus.split("[ /]");
        
        return vastauksenOsat;
    }
    
    /**
     * Tulkitsee parametrina annetun String-muuttujan ja muuttaa sen
     * Murtoluvuksi, mikäli mahdollista. Mikäli pelaajan vastaus on väärän
     * muotoinen, heitetään poikkeus.
     * @param vastaus
     * @return 
     */
    private Murtoluku parsePelaajanVastaus(String vastaus) throws Exception {
        //pelaajan vastaus on muotoa x y/z
        String vastauksenOsat[] = siistiJaPuraPelaajanVastaus(vastaus);
        Murtoluku vastausLuku; 
        
        //pelaaja vastasi sekaluvulla
        if (vastauksenOsat.length==3) {
            vastausLuku = parsePelaajanVastausSekaluku(vastauksenOsat);    
        } else if (vastauksenOsat.length==2) {
            //pelaaja vastasi murtoluvulla
            vastausLuku = parsePelaajanVastausMurtoluku(vastauksenOsat);
        } else if (vastauksenOsat.length==1) {
            //pelaaja vastasi kokonaisluvulla
            vastausLuku = parsePelaajanVastausKokonaisluku(vastauksenOsat);
        } else {
            //pelaajan vastaus on virheellinen
            throw new Exception();
        }
        
        return vastausLuku;
    }
    
    /**
     * Käsittelee String-taulukon, joka sisältää kolme kokonaislukua
     * ja tekee niistä Murtoluvun.
     * @param vastauksenOsat
     * @return 
     */
    private Murtoluku parsePelaajanVastausSekaluku(String vastauksenOsat[]) {
        int vastausKokonaisluku= Integer.parseInt(vastauksenOsat[0]);
        int vastausNimittaja = Integer.parseInt(vastauksenOsat[2]);            
        int vastausOsoittaja = Integer.parseInt(vastauksenOsat[1]);
       
        int nimittaja = vastausNimittaja;
        int osoittaja = vastausOsoittaja+vastausKokonaisluku*vastausNimittaja;
        
        return new Murtoluku(osoittaja, nimittaja);
    }
    
    /**
     * Käsittelee String-taulukon, joka sisältää kaksi kokonaislukua
     * ja tekee siitä Murtoluvun.
     * @param vastauksenOsat
     * @return 
     */
    private Murtoluku parsePelaajanVastausMurtoluku(String vastauksenOsat[]) {
        int vastausOsoittaja = Integer.parseInt(vastauksenOsat[0]);
        int vastausNimittaja = Integer.parseInt(vastauksenOsat[1]);
       
        return new Murtoluku(vastausOsoittaja, vastausNimittaja);
    }
    
    /**
     * Käsittelee String-taulukon, joka sisältää tasan yhden kokonaislukua
     * kuvaavan numeron ja tekee siitä Murtoluvun.
     * @param vastauksenOsat
     * @return 
     */
    private Murtoluku parsePelaajanVastausKokonaisluku(String vastauksenOsat[]) {
        int vastausNimittaja = 1;
        int vastausOsoittaja = Integer.parseInt(vastauksenOsat[0]);   

        return new Murtoluku(vastausOsoittaja, vastausNimittaja);
    }
    
    /**
     * Tätä metodia täytyy kutsua, kun halutaan saada tietoja vallitsevasta
     * pelitilanteesta.
     * @return 
     */   
    public PeliTilanne getTilanne() {
        return tilanne;
    }

    public Kysymys getKysymys() {
        return tilanne.getKysymys();
    }

    /**
     * Tuottaa uuden Lauseke-olion, asettaa sen kysymys-muuttujan
     * arvoksi ja palauttaa kyseisen olion toStringin.
     * @return 
     */
    private String luoUusiKysymys() {        
        Kysymys kysymys = new Kysymys(tilanne);
        tilanne.setKysymys(kysymys);

        tilanne.setVaihe(1);
        return kysymys.toString();
    }
}
