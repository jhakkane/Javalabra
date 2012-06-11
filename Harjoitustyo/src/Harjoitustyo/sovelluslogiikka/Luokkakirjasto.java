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
    
    public static final int OIKEITA_VASTAUKSIA_JOTTA_KIERROS_VAIHTUU_TASOPELISSA = 5;
    public static final int NEG_LUKUJEN_OSUUS_OPERANDEISTA_PROSENTTEINA_KUN_NEG_LUVUT_PAALLA = 50;
    
    public static final int EKSPONENTTI_MAX=3;
    public static final int OPERANDIEN_LUKUMAARA_MAX=9;
    public static final int OPERANDIN_KOKO_MAX=1000;
    
    public static final String alkuTervehdys = "Tervetuloa aritmetiikan pariin. Paina alla olevaa"
                + " nappulaa generoidaksesi kysymyksen.\n\n"
                + "Anna vastaus joko murtolukuna ('x/y'), kokonaislukuna ('x')\n"
                + "tai sekalukuna ('x y/z').";
    
    public static String suhdelukuKentanTeksti(PeliTilanne tilanne) {
        String teksti = ("Nimesi: "+tilanne.getNimi()+"\n\n"
                + "Oikeiden vastausten "
                + "osuus kaikista vastauksista: "+tilanne.oikeitaVastauksiaSuhteellisesti()+"%");
        return teksti;
    }
    
    public static String kysymyksenVastausPelaajaEiAntanutVastausta(PeliTilanne tilanne) {
        return "Et antanut kunnollista vastausta! Tässä kysymys uudelleen: \n\n"
                    +tilanne.getKysymys();
    }
    
    public static String kysymyksenVastausVaaraVastaus(Kysymys kysymys, Murtoluku oikeaVastaus) {
        String teksti = "Väärin! Oikea vastaus olisi: "+
                kysymys.getLauseke()+" = "+oikeaVastaus+".";
        return teksti;
    }
    
    public static String kysymyksenVastausOikeaVastausJaKierrosVaihtuu(String vastaus) {
        return "Oikein! Vastaus on juuri "+vastaus
                        + ". Sinulla menee pelissä niin hyvin, että "
                        + "voimme nyt nostaa vaikeustasoa.";
    }

    public static String kysymyksenVastausOikeaVastausKierrosEiVaihdu(String vastaus) {
        return "Oikein! Vastaus on juuri "+vastaus+".";    
    }
    
    public static String joitainAsetuksiaMuutettiinKoskaMuutenLiianIsojaLukuja() {
        return "Nämä asetukset olisivat tuottaneet liian "
                    + "isoja lukuja. Joitain asetuksia muutetiin.";
    }
}
