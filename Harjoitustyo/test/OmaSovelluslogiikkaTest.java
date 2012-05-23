/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Harjoitustyo.sovelluslogiikka.Asetukset;
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
    private Asetukset asetukset;
    
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
        k = new Kysymys(1,false);   
        asetukset = new Asetukset();
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
    
    @Test
    public void onnistuukoAsetustenVaihtoAsetustenKautta() {
        String uudetAsetukset="3\ntrue";
        asetukset.asetaAsetukset(uudetAsetukset);
        assertTrue(asetukset.getOpLkm()==3);
    }
    
    @Test
    public void onnistuukoAsetustenVaihtoSovelluslogiikanKautta() {
        String uudetAsetukset="3\nfalse";       
        testiOlio.asetaAsetuksetTekstinPerusteella(uudetAsetukset);
        assertTrue(testiOlio.getAsetukset().isSulkuja()==false);
    }
    
    @Test
    public void AsetuksetSetjaGet() {
        asetukset.setOpLkm(4);
        asetukset.setSulkuja(false);
        assertTrue(asetukset.getOpLkm()==4);
        assertTrue(asetukset.isSulkuja()==false);
    }
    
}
