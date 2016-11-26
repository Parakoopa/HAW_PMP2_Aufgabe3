package kalender.marco;

import kalender.marco.DauerImpl;
import kalender.interfaces.Dauer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andi on 24.11.16.
 */
public class DauerImplTest {


    private int minuten = 22300;
    private Dauer zerodauer = new DauerImpl(0);
    private Dauer dauer = new DauerImpl(22300);


    @Before
    public void setUp() {

    }

    @Test
    public void anteileMustAddUp() {
        assertEquals("Die Anteile müssen zusammen wieder die Dauer ergeben",
                minuten,
                dauer.anteilWochen()+
                dauer.anteilTage()+
                dauer.anteilStunden()+
                dauer.anteilMinuten()
        );
    }

    @Test
    public void inStunden() {
        assertEquals(0, zerodauer.inStunden());
        assertEquals(this.minuten/60, dauer.inStunden());
    }

    @Test
    public void inTagen() {
        assertEquals(0, zerodauer.inTagen());
        assertEquals(minuten/60/24, dauer.inTagen());
    }

    @Test
    public void inWochen() {
        assertEquals(0, zerodauer.inWochen());
        assertEquals(minuten/60/24/7, dauer.inWochen());
    }

    @Test
    public void anteilMinuten() {
        assertEquals(40, dauer.anteilMinuten());
    }

    @Test
    public void anteilStunden() {
        assertEquals(660, dauer.anteilStunden());
    }

    @Test
    public void anteilTage() {
        assertEquals(1440, dauer.anteilTage());
    }

    @Test
    public void anteilWochen() {
        assertEquals(20160, dauer.anteilWochen());
    }

}