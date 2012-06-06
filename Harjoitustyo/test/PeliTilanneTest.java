/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import org.junit.*;
import static org.junit.Assert.*;

/**Testaa PeliTilanne-luokan toimintaa.
 *
 * @author JH
 */
public class PeliTilanneTest {
 
    private PeliTilanne tilanne;
    
    public PeliTilanneTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        tilanne = new PeliTilanne();
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
    public void PeliTilanneSetjaGet() {
        tilanne.setOpLkm(4);
        tilanne.setSulkuja(false);
        assertTrue(tilanne.getOpLkm()==4);
        assertTrue(tilanne.isSulkuja()==false);
    }

    @Test
    public void PeliTilanneSetjaGet2() {
        tilanne.setOpLkm(4);
        tilanne.setSulkuja(false);
        assertFalse(tilanne.getOpLkm()==3);
        assertFalse(tilanne.isSulkuja()==true);
    }

    @Test
    public void onnistuukoAsetustenVaihtoPeliTilanteenKautta() throws Exception {
        String uudetAsetukset="TestiNimi\n3\ntrue\ntrue\ntrue\n"
                + "true\ntrue\ntrue\ntrue\n20\nfalse\n0\n0\n0\n0";
        tilanne.asetaAsetukset(uudetAsetukset);
        assertTrue(tilanne.getOpLkm()==3);
    }
    
    @Test
    public void ainaVahintaanYksiOperaatioSallittu() {
        tilanne.setJako(false);
        tilanne.setKerto(false);
        tilanne.setMiinus(false);
        tilanne.setPlus(false);
        
        assertTrue(tilanne.isPlus());
    }
    
    @Test
    public void oikeitaVastauksiaSuhteellisestiToimii() {
        for (int i = 0; i < 9; i++) {
            tilanne.vastattu(true);
            
        }
        tilanne.vastattu(false);
        
        assertTrue(tilanne.oikeitaVastauksiaSuhteellisesti()==90);
    }
}
