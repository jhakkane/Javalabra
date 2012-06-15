/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

/**
 * Sisältää kaikki pelissä esiintyvät, käyttäjälle näkyvät tekstit sekä
 * erilaisia vakioita.
 *
 * @author JH
 */
public class Luokkakirjasto {
    
    /**
     * Murtoluku, joka sisältää arvon 0/0. Tämä on erikoismerkki,
     * jota käytetään Lausekkeen lukuarvon laskemisessa.
     */
    public static final Murtoluku POISTETTU = new Murtoluku();
    /**
     * Vakio, joka sisältää murtoluvun 0.
     */
    public static final Murtoluku NOLLA = new Murtoluku(0, 1);
    
    public static final int OIKEITA_VASTAUKSIA_JOTTA_KIERROS_VAIHTUU_TASOPELISSA = 10;
    public static final int NEG_LUKUJEN_OSUUS_OPERANDEISTA_PROSENTTEINA_KUN_NEG_LUVUT_PAALLA = 50;
    
    /**
     * Suurin mahdollinen eksponentti pelissä.
     */
    public static final int PELITILANNE_EKSPONENTTI_MAX=2;
    public static final int PELITILANNE_OLETUS_OPERANDIEN_LKM = 2;
    public static final int PELITILANNE_OLETUS_OPERANDIN_KOKO = 20;
    
    /**
     * Pelaajalle näkyvä alkutervehdys.
     */
    public static final String alkuTervehdys = "Tervetuloa aritmetiikan pariin. Paina alla olevaa"
                + " nappulaa generoidaksesi kysymyksen.\n\n"
                + "Anna vastaus joko murtolukuna ('x/y'), kokonaislukuna ('x')\n"
                + "tai sekalukuna ('x y/z').";
    
    /**
     * Teksti, joka näkyy pelaajalle koko ajan, ja kertoo
     * tilanteen pelissä.
     * @param tilanne
     * @return 
     */
    public static String suhdelukuKentanTeksti(PeliTilanne tilanne) {
        String teksti = ("Nimesi: "+tilanne.getNimi()+"\n\n"
                + "Oikeiden vastausten ("+tilanne.getOikeitaVastauksia()+") "+
                "osuus kaikista vastauksista ("+tilanne.getKysyttyja()+
                "): "+tilanne.oikeitaVastauksiaSuhteellisesti()+"%");
        return teksti;
    }
    
    /**
     * Teksti, joka näkyy pelaajalle kun pelaaja ei antanut
     * kunnollista vastausta Kysymykseen.
     * @param tilanne
     * @return 
     */
    public static String kysymyksenVastausPelaajaEiAntanutVastausta(PeliTilanne tilanne) {
        return "Et antanut kunnollista vastausta! Tässä kysymys uudelleen: \n\n"
                    +tilanne.getKysymys();
    }
    
    /**
     * Teksti, joka näkyy pelaajalle, kun hän vastasi Kysymykseen väärin.
     * @param kysymys
     * @param oikeaVastaus
     * @return 
     */
    public static String kysymyksenVastausVaaraVastaus(Kysymys kysymys, Murtoluku oikeaVastaus) {
        String teksti = "Väärin! Oikea vastaus olisi: "+
                kysymys.getLauseke()+" = "+oikeaVastaus+".";
        return teksti;
    }
    
    /**
     * Teksti, joka näkyy pelaajalle tasopelin aikana, kun hän vastasi
     * oikein kysymykseen ja kun kierros vaihtuu.
     * @param vastaus
     * @return 
     */
    public static String kysymyksenVastausOikeaVastausJaKierrosVaihtuu(String vastaus) {
        return "Oikein! Vastaus on juuri "+vastaus
                        + ". Sinulla menee pelissä niin hyvin, että "
                        + "voimme nyt nostaa vaikeustasoa.";
    }

    /**
     * Teksti, joka näkyy pelaajalle kun hän vastasi kysymykseen oikein
     * ja kun kierros ei vaihdu. Sillä, onko tasopeli päällä vai ei, ei ole
     * väliä.
     * @param vastaus
     * @return 
     */
    public static String kysymyksenVastausOikeaVastausKierrosEiVaihdu(String vastaus) {
        return "Oikein! Vastaus on juuri "+vastaus+".";    
    }
    
    /**
     * Teksti, joka näkyy pelaajalle, kun hän yrittää muuttaa asetuksia siten,
     * että mahdolliset ratkaisut Kysymykseen ovat liian suuria. Teksti
     * ilmoittaa, että joitain asetuksia muutettiin automaattisesti.
     * @return 
     */
    public static String joitainAsetuksiaMuutettiinKoskaMuutenLiianIsojaLukuja() {
        return "Nämä asetukset olisivat tuottaneet liian "
                    + "isoja lukuja. Joitain asetuksia muutetiin.";
    }

    /**
     * Teksti, joka ilmoittaa että pelaajan antamat asetukset ovat virheellisiä
     * ja että asetuksia muutettiin automaattisesti. Esim operandien määrä
     * -52 olisi virhe.
     * @return 
     */
    public static String asetuksetOvatVirheellisia() {
        return "Antamasi asetukset ovat virheellisiä. Joitain asetuksia on muutettu.";
    }
    
    /**
     * Teksti, joka näkyy pelaajalle, kun hän antoi väärän muotoisen vastauksen.
     * Esim. murtolukuvastaus on väärän muotoinen jos on olemassa kokonaislukuvastaus.
     * @param tilanne
     * @return 
     */
    public static String kysymykseenAnnettuVaaranMuotoinenVastaus(PeliTilanne tilanne) {
        return "Annoit vastauksesi väärän muotoisena. Jos kysymykseen on kokonaislukuvastaus, "
                + "anna vastaus siinä muodossa. Jos se ei ole mahdollista, anna sekalukuvastaus. "
                + "Jos tämäkään ei ole mahdollista, anna vastaus murtolukuna. Kysymys uudestaan:\n\n"+
                tilanne.getKysymys();
                   
    }
}
