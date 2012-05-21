/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Kysymys;
import Harjoitustyo.sovelluslogiikka.OmaSovelluslogiikka;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jhakkane
 */
public class OmaSovelluslogiikkaTest {
    
    private OmaSovelluslogiikka testiOlio;
    private Kysymys k;
    
    public OmaSovelluslogiikkaTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testiOlio = new OmaSovelluslogiikka();
        k = new Kysymys(3,false);   
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
    public void eteneMetodiToimii() {
        assertTrue(testiOlio.testiString().equals("Terve"));
    }

    public void kysymysRatkaiseJaSettoimivat() {

        k.setArvo(5);
        assertTrue(k.ratkaise()==5);
    }
}
