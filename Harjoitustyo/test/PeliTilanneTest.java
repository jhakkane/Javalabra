/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import org.junit.*;
import static org.junit.Assert.*;

/**
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
        String uudetAsetukset="TestiNimi\n3\ntrue";
        tilanne.asetaAsetukset(uudetAsetukset);
        assertTrue(tilanne.getOpLkm()==3);
    }
}
