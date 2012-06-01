/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Lauseke;
import Harjoitustyo.sovelluslogiikka.Murtoluku;
import Harjoitustyo.sovelluslogiikka.Op;
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
    private Lauseke l;
    
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
            
            Murtoluku[] oper = new Murtoluku[3];
            oper[0] = new Murtoluku(6,3);
            oper[1] = new Murtoluku(12,4);
            oper[2] = new Murtoluku(1,2);
            
            Op[] ops = new Op[2];
            ops[0]=Op.MUL;
            ops[1]=Op.PLUS;
            
            l = new Lauseke(oper,ops);
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
    
    @Test
    public void lausekkeenRatkaisuOnOikein() {
        assertTrue(l.lukuarvo().samaLuku(new Murtoluku(13,2)));
    }
    
    
}
