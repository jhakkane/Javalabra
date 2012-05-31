/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Lauseke;
import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**Testaa Lauseke-luokan toimintaa.
 *
 * @author JH
 */
public class LausekeTest {
    
    private Lauseke k;
    
    public LausekeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        try {
            k = new Lauseke(new PeliTilanne());
        } catch (Exception ex) {
            Logger.getLogger(LausekeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void kysymyksenLuontiOnnistuu() {
        PeliTilanne ot = new PeliTilanne();
        ot.setOpLkm(14);
        
        Lauseke uk;
        try {
            uk = new Lauseke(ot);
            assertTrue(uk.operandienMaara()==ot.getOpLkm());
        } catch (Exception ex) {
            Logger.getLogger(LausekeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
