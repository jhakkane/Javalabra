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

/**Testaa Lauseke-luokan toimintaa. Monessa testissä on periaatteessa mahdollista,
 * että testi epäonnistuu "vahingossa", sillä Lauseke satunnaisgeneroidaan, mutta
 * tämän todennäköisyys on hyvin pieni.
 *
 * @author JH
 */
public class LausekeTest {
    
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
        Murtoluku[] oper = new Murtoluku[3];
        oper[0] = new Murtoluku(6,3);
        oper[1] = new Murtoluku(12,4);
        oper[2] = new Murtoluku(1,2);

        Op[] ops = new Op[2];
        ops[0]=Op.MUL;
        ops[1]=Op.PLUS;

        l = new Lauseke(oper,ops);   
        
        assertTrue(l.lukuarvo().samaLuku(new Murtoluku(13,2)));
    }
    
    @Test
    public void lausekkeenRatkaisuOnOikein2() {
        Murtoluku[] oper = new Murtoluku[3];
        oper[0] = new Murtoluku(24,2);
        oper[1] = new Murtoluku(6,1);
        oper[2] = new Murtoluku(1,2);

        Op[] ops = new Op[2];
        ops[0]=Op.MIN;
        ops[1]=Op.DIV;

        l = new Lauseke(oper,ops);   
        
        assertTrue(l.lukuarvo().samaLuku(new Murtoluku(0,2)));
    }

    /**
     * Laske -1/2 + 12/3 / 8 * 10/2 + (-3/3) + 1 = 0
     */
    @Test
    public void lausekkeenRatkaisuOnOikein3() {
        Murtoluku[] oper = new Murtoluku[6];
        
        oper[0] = new Murtoluku(-1,2);
        oper[1] = new Murtoluku(12,3);
        oper[2] = new Murtoluku(8,1);
        oper[3] = new Murtoluku(10,2);
        oper[4] = new Murtoluku(-3,3);
        oper[5] = new Murtoluku(1,1);
        
        Op[] ops = new Op[5];
        ops[0]=Op.PLUS;
        ops[1]=Op.DIV;
        ops[2]=Op.MUL;
        ops[3]=Op.PLUS;
        ops[4]=Op.PLUS;

        l = new Lauseke(oper,ops);   
        
        assertTrue(l.lukuarvo().samaLuku(new Murtoluku(4,2)));
    }


    
    @Test
    public void lausekkeenRatkaisuOnOikein4() {
        Murtoluku[] oper = new Murtoluku[3];
        oper[0] = new Murtoluku(10,3);
        oper[1] = new Murtoluku(10,2);
        oper[2] = new Murtoluku(10,1);

        Op[] ops = new Op[2];
        ops[0]=Op.MUL;
        ops[1]=Op.MUL;

        l = new Lauseke(oper,ops);   
        
        assertTrue(l.lukuarvo().samaLuku(new Murtoluku(500,3)));
    }
    
    @Test
    public void lausekkeenRatkaisuOnOikein5() {
        Murtoluku[] oper = new Murtoluku[3];
        oper[0] = new Murtoluku(10,3);
        oper[1] = new Murtoluku(10,2);
        oper[2] = new Murtoluku(10,1);

        Op[] ops = new Op[2];
        ops[0]=Op.DIV;
        ops[1]=Op.DIV;

        l = new Lauseke(oper,ops);   
        
        assertTrue(l.lukuarvo().samaLuku(new Murtoluku(2,30)));
    }
    
    @Test
    public void oikeaMaaraOperaattoreita() throws Exception {
        PeliTilanne tilanne = new PeliTilanne();
        tilanne.setOpLkm(5);
        
        l = new Lauseke(tilanne);
        assertTrue(l.operaattoriTaulukko().length==4);
    }
    
    @Test
    public void oikeaMaaraOperandeja() throws Exception {
        PeliTilanne tilanne = new PeliTilanne();
        tilanne.setOpLkm(5);
        
        l = new Lauseke(tilanne);
        assertTrue(l.operandiTaulukko().length==5);
    }

    /**
     * Tutkii tuottaako murtolukuasetus todella murtolukuja. Teoriassa on
     * mahdollista, että sattumalta kaikki luvut ovat kokonaislukuja, mutta
     * se on hyvin epätodennäköistä.
     * @throws Exception 
     */
    @Test
    public void murtolukuasetusTuottaaMurtolukuja() throws Exception {
        PeliTilanne tilanne = new PeliTilanne();
        tilanne.setOpLkm(10);
        tilanne.setMurtolukuja(true);
        
        l = new Lauseke(tilanne);
        
        boolean lausekkeessaMurtoluku = false;
        for (int i = 0; i < l.operandiTaulukko().length; i++) {
            if (!l.operandiTaulukko()[i].lukuarvo().kokonaisluku()) {
                lausekkeessaMurtoluku = true;
            }
        }
        
        assertTrue(lausekkeessaMurtoluku);
        
    }
    
    /**
     * Jos sulut ovat käytössä, ohjelma jakaa lausekkeen aina kahteen osaan.
     * @throws Exception 
     */
    @Test
    public void oikeaMaaraOperandejaSulkulausekkeessa() throws Exception {
        PeliTilanne tilanne = new PeliTilanne();
        tilanne.setSulkuja(true);
        tilanne.setOpLkm(5);
        
        l = new Lauseke(tilanne);
        assertTrue(l.operandiTaulukko().length==2);
    }
    
    /**
     * Satunnaisgeneroi Lausekkeen asetuksilla, joilla hyvin todennäköisesti
     * tulisi nollia Lausekkeeseen, mikäli sitä ei tarkistettaisi ja estettäisi.
     * Varmistaa, että nollia ei tosiaankaan ole. Periaatteessa on mahdollista
     * että näin kävisi myös sattumalta.
     * @throws Exception 
     */
    @Test
    public void vainJakolaskujaSisaltavaLausekeEiSisallaNollia() throws Exception {
        PeliTilanne tilanne = new PeliTilanne();
        tilanne.setJako(true);
        tilanne.setPlus(false);
        tilanne.setOpLkm(10);
        tilanne.setOperandMax(1);
        
        l = new Lauseke(tilanne);      

        boolean lausekkeessaNolla = false;
        for (int i = 0; i < l.operandiTaulukko().length; i++) {
            if (l.operandiTaulukko()[i].lukuarvo().onNolla()) {
                lausekkeessaNolla = true;
            }
        }
        
        assertFalse(lausekkeessaNolla);
    }
    
    @Test
    public void operaattorienMaaraOnOikea() throws Exception {
        PeliTilanne tilanne = new PeliTilanne();
        tilanne.setOpLkm(8);
        l = new Lauseke(tilanne);
        assertTrue(l.operaattoriTaulukko().length==7);
    }
    
    @Test
    public void negaatioAsetuksetTuottavatNegatiivisiaLukuja() throws Exception {
        PeliTilanne tilanne = new PeliTilanne();
        tilanne.setOpLkm(30);
        tilanne.setNegatiivisia(true);
        l = new Lauseke(tilanne);   
        
        boolean lausekkeessaNegatiivinen = false;
        for (int i = 0; i < l.operandiTaulukko().length; i++) {
            if (l.operandiTaulukko()[i].lukuarvo().negatiivinen()) {
                lausekkeessaNegatiivinen = true;
            }
        }
        
        assertTrue(lausekkeessaNegatiivinen);
    }
}
