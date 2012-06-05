/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Luokkakirjasto;
import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author JH
 */
public class LuokkakirjastoTest {
    
    public LuokkakirjastoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
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
    public void suhdelukuKenttaToimii() {
        PeliTilanne tilanne = new PeliTilanne();
        String teksti = Luokkakirjasto.suhdelukuKentanTeksti(tilanne);
        assertTrue(teksti.equals(
                "Nimesi: "+tilanne.getNimi()+"\n\n"
                + "Oikeiden vastausten "
                + "osuus kaikista vastauksista: "+tilanne.oikeitaVastauksiaSuhteellisesti()+"%"));
    }
}
