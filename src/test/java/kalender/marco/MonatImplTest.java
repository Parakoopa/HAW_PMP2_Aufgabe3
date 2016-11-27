package kalender.marco;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 26.11.16.
 */
public class MonatImplTest {

    private MonatImpl testMonat;
    private MonatImpl anderesJahr;
    private MonatImpl andererMonat;

    @Before
    public void setUp() {
        this.testMonat = new MonatImpl(2016, 1);
        this.anderesJahr = new MonatImpl(2013, 1);
        this.andererMonat = new MonatImpl(2016, 3);
    }

    @Test
    public void getMonat() {
        assertEquals(
                "Februar.16. soll 1 als Monat ausgeben (0 ist Januar)",
                1,
                this.testMonat.getMonat()
        );
        assertEquals(
                "April.16. soll 3 als Monat ausgeben (0 ist Januar)",
                3,
                this.andererMonat.getMonat()
        );
    }

    @Test
    public void getJahr() {
        assertEquals(
                "Februar.16. soll 2016 als Jahr ausgeben",
                2016,
                this.testMonat.getJahr()
        );
        assertEquals(
                "Februar.13. soll 2013 als Jahr ausgeben",
                2013,
                this.anderesJahr.getJahr()
        );
    }

    @Test
    public void getStart() {
        assertEquals(
                new DatumStub(2016, 1, 1),
                this.testMonat.getStart()
        );
        assertEquals(
                new DatumStub(2016, 1, 3),
                this.andererMonat.getStart()
        );
        assertEquals(
                new DatumStub(2013, 1, 1),
                this.anderesJahr.getStart()
        );
    }

    @Test
    public void getEnde() {
        assertEquals(
                new DatumStub(2016, 29, 1, 23, 59),
                this.testMonat.getEnde()
        );
        assertEquals(
                new DatumStub(2016, 30, 3, 23, 59),
                this.andererMonat.getEnde()
        );
        assertEquals(
                new DatumStub(2013, 28, 1, 23, 59),
                this.anderesJahr.getEnde()
        );
    }

}