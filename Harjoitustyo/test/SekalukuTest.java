/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Murtoluku;
import Harjoitustyo.sovelluslogiikka.Sekaluku;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**Testaa Sekaluku-luokan toimintaa.
 *
 * @author JH
 */
public class SekalukuTest {
    
    Sekaluku s1;
    
    public SekalukuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        s1 = new Sekaluku(3,5,1);
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
    public void murtoluvuksiMuuttoToimii() {
        assertTrue(s1.murtolukuna().samaLuku(new Murtoluku(8,5)));
    }
    
    @Test
    public void kokonaislukuToimii() {
        assertFalse(s1.kokonaisluku());
        assertTrue(new Sekaluku(10,5,6).kokonaisluku());
    }
    
    @Test 
    public void testailu() {
        //6 / 2
        Sekaluku s2 = new Sekaluku(10,5,4);
        Sekaluku s3 = new Sekaluku(14,7,0);
        assertTrue(s2.samaLuku(new Sekaluku(0,0,6)));
        assertTrue(s2.jaettuna(s3).samaLuku(new Sekaluku(0,0,3)));
        assertTrue(s2.jaettuna(s3).kokonaisluku());
    }

}
