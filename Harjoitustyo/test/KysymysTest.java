/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Kysymys;
import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author JH
 */
public class KysymysTest {
    
    private Kysymys k;
    
    public KysymysTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        k = new Kysymys(new PeliTilanne());
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
        
        Kysymys uk = new Kysymys(ot);
        assertTrue(uk.operandienMaara()==ot.getOpLkm());
    }
}
