/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Kysymys;
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
        k = new Kysymys(1,false);
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
        Kysymys uk = new Kysymys(4,false);
        assertTrue(uk.operandienMaara()==4);
    }
    
    @Test
    public void kysymysRatkaiseJaSettoimivat() {
        k.setArvo(5);
        assertTrue(k.ratkaise()==5);
    }
    
}
