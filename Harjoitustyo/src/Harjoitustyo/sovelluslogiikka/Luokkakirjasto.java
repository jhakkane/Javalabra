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
    
    public static final int OIKEITA_VASTAUKSIA_JOTTA_KIERROS_VAIHTUU_TASOPELISSA = 10;
    public static final int NEG_LUKUJEN_OSUUS_OPERANDEISTA_PROSENTTEINA_KUN_NEG_LUVUT_PAALLA = 50;
    
    /**
     * Suurin mahdollinen eksponentti pelissä.
     */
    public static final int PELITILANNE_EKSPONENTTI_MAX=2;
    public static final int PELITILANNE_OLETUS_OPERANDIEN_LKM = 2;
    public static final int PELITILANNE_OLETUS_OPERANDIN_KOKO = 20;
    
    public static final String alkuTervehdys = "Tervetuloa aritmetiikan pariin. Paina alla olevaa"
                + " nappulaa generoidaksesi kysymyksen.\n\n"
                + "Anna vastaus joko murtolukuna ('x/y'), kokonaislukuna ('x')\n"
                + "tai sekalukuna ('x y/z').";
    
    public static String suhdelukuKentanTeksti(PeliTilanne tilanne) {
        String teksti = ("Nimesi: "+tilanne.getNimi()+"\n\n"
                + "Oikeiden vastausten ("+tilanne.getOikeitaVastauksia()+") "+
                "osuus kaikista vastauksista ("+tilanne.getKysyttyja()+
                "): "+tilanne.oikeitaVastauksiaSuhteellisesti()+"%");
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

    public static String asetuksetOvatVirheellisia() {
        return "Antamasi asetukset ovat virheellisiä. Joitain asetuksia on muutettu.";
    }
    
    public static String kysymykseenAnnettuVaaranMuotoinenVastaus(PeliTilanne tilanne) {
        return "Annoit vastauksesi väärän muotoisena. Jos kysymykseen on kokonaislukuvastaus, "
                + "anna vastaus siinä muodossa. Jos se ei ole mahdollista, anna sekalukuvastaus. "
                + "Jos tämäkään ei ole mahdollista, anna vastaus murtolukuna. Kysymys uudestaan:\n\n"+
                tilanne.getKysymys();
                   
    }
}
