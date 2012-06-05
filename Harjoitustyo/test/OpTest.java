/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Op;
import org.junit.*;
import static org.junit.Assert.*;

/**Testaa Op-enumin toimintaa.
 *
 * @author JH
 */
public class OpTest {
    
    public OpTest() {
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
    public void OpMerkitOikein() {
        Op op = Op.DIV;
        assertTrue(op.toString()=="/");
    
        op = Op.MIN;
        assertTrue(op.toString()=="-");

        op = Op.PLUS;
        assertTrue(op.toString()=="+");
        
        op = Op.MUL;
        assertTrue(op.toString()=="*");
    }
}
