/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Laskutoimituksia;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**Testaa Laskutoimituksia-luokan toimintaa.
 *
 * @author JH
 */
public class LaskutoimituksiaTest {
    
    public LaskutoimituksiaTest() {
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
    public void alkulukuhajotelmaTest() {
        ArrayList<Integer>oikeat = new ArrayList<Integer>();
        oikeat.add(2);
        oikeat.add(5);
        oikeat.add(3);
        assertTrue(Laskutoimituksia.Alkulukuhajotelma(30).containsAll(oikeat));
    }
}
