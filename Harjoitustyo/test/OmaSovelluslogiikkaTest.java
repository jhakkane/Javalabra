/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.PeliTilanne;
import Harjoitustyo.sovelluslogiikka.Kysymys;
import Harjoitustyo.sovelluslogiikka.Sovelluslogiikka;
import Harjoitustyo.sovelluslogiikka.Op;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jhakkane
 */
public class OmaSovelluslogiikkaTest {
    
    private Sovelluslogiikka logiikka;
    
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
        logiikka = new Sovelluslogiikka(); 
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
    public void eteneTuottaaOikeanPituisenKysymyksen() {
        
        String[] osat = logiikka.etene("").split(" ");
        int lkm = logiikka.getTilanne().getOpLkm();
        
        //operandit + operaattorit (joita yksi vähemmän kuin operandeja)
        assertTrue(osat.length==(lkm*2-1));
        
    }
}