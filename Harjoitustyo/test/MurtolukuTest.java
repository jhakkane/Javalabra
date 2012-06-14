/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Murtoluku;
import org.junit.*;
import static org.junit.Assert.*;

/**Testaa Murtoluku-luokan toimintaa.
 *
 * @author JH
 */
public class MurtolukuTest {
    
    Murtoluku l1;
    Murtoluku l2;
    Murtoluku l3;
    Murtoluku l4;
    Murtoluku l5;
    
    public MurtolukuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        l1 = new Murtoluku(3,6);
        l2 = new Murtoluku(0,3);
        l3 = new Murtoluku(1,3);
        l4 = new Murtoluku(15,7);
        l5 = new Murtoluku(8,2);
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
    public void summaToimii() {
        assertTrue(l1.summa(l2).samaLuku(new Murtoluku(7,14)));
        assertTrue(l1.summa(l3).samaLuku(new Murtoluku(10,12)));        
    }

    @Test
    public void vahennysToimii() {
        assertTrue(l1.vahennys(l2).samaLuku(new Murtoluku(6,12)));
        assertTrue(l1.vahennys(l3).samaLuku(new Murtoluku(6,36)));        
    }

    @Test
    public void jakoToimii() {
        assertTrue(l1.jaettuna(l3).samaLuku(new Murtoluku(9,6)));        
    }

    @Test
    public void tuloToimii() {
        assertTrue(l1.tulo(l2).samaLuku(new Murtoluku(0,6)));        
        assertTrue(l1.tulo(l3).samaLuku(new Murtoluku(3,18)));     
    }
    
    @Test
    public void eksponenttiToimii() {

        assertTrue(l1.korotaPotenssiin(1).samaLuku(new Murtoluku(3,6)));     
        assertTrue(l2.korotaPotenssiin(1).samaLuku(new Murtoluku(0,3)));
        assertTrue(l3.korotaPotenssiin(1).samaLuku(new Murtoluku(1,3)));
        
        assertTrue(l1.korotaPotenssiin(3).samaLuku(new Murtoluku(1,8)));     
        
        assertTrue(l2.korotaPotenssiin(2).samaLuku(new Murtoluku(0,1))); 
        
        assertTrue(l3.korotaPotenssiin(2).samaLuku(new Murtoluku(1,9)));     
        assertTrue(l3.korotaPotenssiin(3).samaLuku(new Murtoluku(1,27)));    
    }
    
    @Test
    public void samaLukuToimii() {
        assertTrue(l1.samaLuku(new Murtoluku(12,24)));
        assertFalse(l1.samaLuku(new Murtoluku(11,24)));
    }
    
    @Test
    public void toStringToimii() {
        assertTrue(l1.toString().equals("3/6"));
    }
    
    @Test
    public void kokonaislukuToimii() {
        assertFalse(l1.onKokonaisluku());
        assertTrue(l2.onKokonaisluku());
        assertFalse(l3.onKokonaisluku());
    }
    
    @Test
    public void sekalukunaToimii() {
        int[] sekalukuna = l4.sekalukuna();
        assertTrue(sekalukuna[0]==2);
        assertTrue(sekalukuna[1]==1);
        assertTrue(sekalukuna[2]==7);
    }
    
    @Test
    public void sekalukuTarkistusToimii() {
        assertTrue(l4.onSekaluku());
    }
    
    @Test
    public void onKokonaislukuToimii() {
        assertFalse(l1.onKokonaisluku());
        assertTrue(l2.onKokonaisluku());
        assertFalse(l3.onKokonaisluku());
        assertFalse(l4.onKokonaisluku());
        assertTrue(l5.onKokonaisluku());
    }
    
    @Test
    public void onSekaluku() {
        assertFalse(l1.onSekaluku());
        assertFalse(l2.onSekaluku());
        assertFalse(l3.onSekaluku());
        assertTrue(l4.onSekaluku());
        assertFalse(l5.onSekaluku());
    }
    
    @Test
    public void onMurtolukuToimii() {
        assertTrue(l1.onMurtoluku());
        assertFalse(l2.onMurtoluku());
        assertTrue(l3.onMurtoluku());
        assertFalse(l4.onMurtoluku());
        assertFalse(l5.onMurtoluku());
    }
    
    @Test
    public void toStringitToimivatOikein() {
        assertTrue(l1.toString().equals("3/6"));
        assertTrue(l2.toString().equals("0"));
        assertTrue(l3.toString().equals("1/3"));
        assertTrue(l4.toString().equals("2 1/7"));
        assertTrue(l5.toString().equals("4"));
    }
}
