package kalender.marco;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 26.11.16.
 */
public class TagImplTest {

    TagImpl tagJahrTagImJahr;
    TagImpl tagJahrMonatTagImMonat;
    TagImpl tagMitTag;

    @Before
    public void setUp() {

        tagJahrTagImJahr = new TagImpl(2016, 32);
        tagJahrMonatTagImMonat = new TagImpl(2015, 2, 3);
        tagMitTag = new TagImpl(tagJahrMonatTagImMonat);

    }

    @Test
    public void getJahr() {
        assertEquals(
                "1. Februar 16. soll 2016 als Jahr ausgeben",
                2016,
                this.tagJahrTagImJahr.getJahr()
        );
        assertEquals(
                "3. März 15. soll 2015 als Jahr ausgeben",
                2015,
                this.tagJahrMonatTagImMonat.getJahr()
        );
        assertEquals(
                "3. März 15. soll 2015 als Jahr ausgeben",
                2015,
                this.tagMitTag.getJahr()
        );
    }

    @Test
    public void getMonat() {
        assertEquals(
                "1. Februar 16. soll 1 (Februar) als Monat ausgeben",
                1,
                this.tagJahrTagImJahr.getMonat()
        );
        assertEquals(
                "3. März 15. soll 2 (März) als Monat ausgeben",
                2,
                this.tagJahrMonatTagImMonat.getMonat()
        );
        assertEquals(
                "3. März 15. soll 2 (März) als Monat ausgeben",
                2,
                this.tagMitTag.getMonat()
        );
    }
    
    @Test
    public void getStart() {
        assertEquals(
                new DatumStub(2016, 1, 1, 0, 0),
                this.tagJahrTagImJahr.getStart()
        );
        assertEquals(
                new DatumStub(2015, 3, 2, 0, 0),
                this.tagJahrMonatTagImMonat.getStart()
        );
        assertEquals(
                new DatumStub(2015, 3, 2, 0, 0),
                this.tagMitTag.getStart()
        );
    }

    @Test
    public void getEnde() {
        assertEquals(
                new DatumStub(2016, 1, 1, 23, 59),
                this.tagJahrTagImJahr.getEnde()
        );
        assertEquals(
                new DatumStub(2015, 3, 2, 23, 59),
                this.tagJahrMonatTagImMonat.getEnde()
        );
        assertEquals(
                new DatumStub(2015, 3, 2, 23, 59),
                this.tagMitTag.getEnde()
        );
    }

    @Test
    public void compareTo() {
        assertTrue(
                "Der 1. Februar 16. ist größer als 3. März 15. ",
                this.tagJahrTagImJahr.compareTo(tagJahrMonatTagImMonat) > 0
        );
        assertTrue(
                "Der 3. März 15. ist kleiner als 1. Februar 16. ",
                this.tagJahrMonatTagImMonat.compareTo(tagJahrTagImJahr) < 0
        );
        assertEquals(
                "Gleiche Tage sind gleich im Vergleich.",
                0,
                this.tagJahrMonatTagImMonat.compareTo(tagMitTag)
        );
    }
    
    @Test
    public void getTagImJahr() {
        assertEquals(
                "1. Februar 16. soll 32 als Tag im Jahr ausgeben",
                32,
                this.tagJahrTagImJahr.getTagImJahr()
        );
        assertEquals(
                "3. März 15. soll 62 als Tag im Jahr ausgeben",
                62,
                this.tagJahrMonatTagImMonat.getTagImJahr()
        );
        assertEquals(
                "3. März 15. soll 62 als Tag im Jahr ausgeben",
                62,
                this.tagMitTag.getTagImJahr()
        );
    }

    @Test
    public void getTagImMonat() {
        assertEquals(
                "1. Februar 16. soll 1 als Tag im Monat ausgeben",
                1,
                this.tagJahrTagImJahr.getTagImMonat()
        );
        assertEquals(
                "3. März 15. soll 2 als Tag im Monat ausgeben",
                3,
                this.tagJahrMonatTagImMonat.getTagImMonat()
        );
        assertEquals(
                "3. März 15. soll 2 als Tag im Monat ausgeben",
                3,
                this.tagMitTag.getTagImMonat()
        );
    }

    @Test
    public void differenzInTagen() {
        assertEquals(
                "Zwischen Der 1. Feb 16. und  als 3. März 15. sind 335 Tage vergangen",
                335,
                this.tagJahrTagImJahr.differenzInTagen(tagJahrMonatTagImMonat)
        );
        assertEquals(
                "Zwischen Der 3. März 15. und  als 1. Februar 16. sind -335 Tage vergangen",
                -335,
                this.tagJahrMonatTagImMonat.differenzInTagen(tagJahrTagImJahr)
        );
        assertEquals(
                "Zwischen zwei gleichen Tagen ist kein Tag vergangen.",
                0,
                this.tagJahrMonatTagImMonat.compareTo(tagMitTag)
        );
    }

}