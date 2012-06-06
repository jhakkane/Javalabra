/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.*;
import org.junit.*;
import static org.junit.Assert.*;

/**Testaa Sovelluslogiikka-luokan toimintaa.
 *
 * @author jhakkane
 */
public class SovelluslogiikkaTest {
    
    private Sovelluslogiikka logiikka;
    
    public SovelluslogiikkaTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        logiikka = new Sovelluslogiikka(); 
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    

    @Test
    public void eteneTuottaaOikeanPituisenKysymyksen() {
        
        String[] osat = logiikka.etene("").split(" ");
        int lkm = logiikka.getTilanne().getOpLkm();
        
        //operandit + operaattorit (joita yksi vähemmän kuin operandeja)
        assertTrue(osat.length==(lkm*2-1));
        
    }
    
    @Test
    public void eteneEteneeOikeallaVastauksella() {
        logiikka.etene(""); //luo kysymyksen
        String ilmoitus = Luokkakirjasto.kysymyksenVastausOikeaVastausKierrosEiVaihdu(
                logiikka.getKysymys().oikeaVastaus());
        assertTrue(logiikka.etene(""+logiikka.getKysymys().oikeaVastaus()).substring(0, 15).equals(
                ilmoitus.substring(0,15)));
    }
    
    @Test
    public void eteneEteneeOikealleaMurtolukuVastauksella() {
        logiikka.getTilanne().setMurtolukuja(true);
        logiikka.getTilanne().setOpLkm(7);
        logiikka.etene(""); //luo kysymyksen
        String ilmoitus = Luokkakirjasto.kysymyksenVastausOikeaVastausKierrosEiVaihdu(
                logiikka.getKysymys().oikeaVastaus());
        assertTrue(logiikka.etene(""+logiikka.getKysymys().oikeaVastaus()).substring(0, 15).equals(
                ilmoitus.substring(0,15)));     
    }
    
    @Test
    public void eteneEteneeVaarallaVastauksella() {
        logiikka.etene(""); //luo kysymyksen
        logiikka.getTilanne().setNegatiivisia(false);
        String ilmoitus = Luokkakirjasto.kysymyksenVastausVaaraVastaus(
                logiikka.getKysymys().oikeaVastaus());
        assertTrue(logiikka.etene("-1").substring(0, 15).equals(
                ilmoitus.substring(0,15)));
    }
    
    @Test
    public void eteneEiEteneJosVastausOnTyhja() {
        logiikka.etene(""); //luo kysymyksen
        assertTrue(logiikka.etene("").substring(0, 30).equals(
                Luokkakirjasto.kysymyksenVastausPelaajaEiAntanutVastausta(logiikka.getTilanne()).substring(0,30)));
    }

    /**
     * Tarkista, että tasopeliasetuksilla kierros todella vaihtuu kun oikeita
     * vastauksia on tarpeeksi.
     */
    @Test
    public void tasopelinToiminnanTestausta() {
        logiikka.getTilanne().setTasopeli(true);
        logiikka.etene(""); //luo ensimmäisen kysymyksen
        
        int alkuperainenKierros = logiikka.getTilanne().getKierros();
        
        //Huom. vastauksia on kaksinkertainen määrä, koska kysymysten välissä tulee
        //"oikein vastattu" ilmoitus, johon pitää vastata myös.
        for (int i = 0; i <= Luokkakirjasto.OIKEITA_VASTAUKSIA_JOTTA_KIERROS_VAIHTUU_TASOPELISSA*2; i++) {
            logiikka.etene(""+logiikka.getTilanne().getKysymys().oikeaVastaus());
        }
        
        assertTrue(logiikka.getTilanne().getKierros() > alkuperainenKierros);
        
    }
    
    @Test
    public void oikeitaVastauksiaOikeaMaara() {
        logiikka.etene(""); //luo ensimmäisen kysymyksen
        for (int i = 0; i < 18; i++) {
            logiikka.etene(""+logiikka.getTilanne().getKysymys().oikeaVastaus());
        }
        
        assertTrue(logiikka.getTilanne().getOikeitaVastauksia()==9);
    }
    
}
