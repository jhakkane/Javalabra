/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Harjoitustyo.sovelluslogiikka;

/**
 *Sisältää kaikki pelissä esiintyvät, käyttäjälle näkyvät tekstit.
 * Saattaa sisältää tulevaisuudessa myös erilaisia metodeja ja vakioita.
 * @author JH
 */
public class Luokkakirjasto {
    
    public static final String alkuTervehdys = "Tervetuloa aritmetiikan pariin. Paina alla olevaa"
                + " nappulaa generoidaksesi kysymyksen.\n"
                + "Anna vastaus joko murtolukuna ('x/y'), kokonaislukuna ('x')\n"
                + "tai sekalukuna ('x y/z').";
    
    public static String suhdelukuKentanTeksti(PeliTilanne tilanne) {
        String teksti = ("Nimesi: "+tilanne.getNimi()+"\n\n"
                + "Oikeiden vastausten "
                + "osuus kaikista vastauksista: "+tilanne.oikeitaVastauksiaSuhteellisesti()+"%");
        return teksti;
    }
    
    public static String kysymyksenVastausPelaajaEiAntanutVastausta(PeliTilanne tilanne) {
        return "Et antanut vastausta! Tässä kysymys uudelleen: \n\n"
                    +tilanne.getKysymys();
    }
    
    public static String kysymyksenVastausVaaraVastaus(Murtoluku oikeaVastaus) {
        return "Väärin! Oikea vastaus on "+oikeaVastaus+".";
    }
    
    public static String kysymyksenVastausOikeaVastausJaKierrosVaihtuu(Murtoluku vastaus) {
        return "Oikein! Vastaus on juuri "+vastaus
                        + ". Sinulla menee pelissä niin hyvin, että "
                        + "voimme nyt nostaa vaikeustasoa.";
    }

    public static String kysymyksenVastausOikeaVastausKierrosEiVaihdu(Murtoluku vastaus) {
        return "Oikein! Vastaus on juuri "+vastaus+".";    
    }
}
