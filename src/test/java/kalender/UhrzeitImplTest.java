package kalender;

import kalender.UhrzeitImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 26.11.16.
 */
public class UhrzeitImplTest {

    UhrzeitImpl uhrzeitA;
    UhrzeitImpl uhrzeitB;

    @Before
    public void setUp() {

        uhrzeitA = new UhrzeitImpl(12,0);
        uhrzeitB = new UhrzeitImpl(14,31);
    }

    @Test
    public void compareTo() {
        assertTrue(
                "14:31 Uhr ist später als 12 Uhr",
                this.uhrzeitB.compareTo(uhrzeitA) > 0
        );
        assertTrue(
                "12 Uhr ist früher als 14:31 Uhr",
                this.uhrzeitB.compareTo(uhrzeitA) > 0
        );
        assertEquals(
                "12 Uhr und 12 Uhr sind gleich",
                0,
                this.uhrzeitA.compareTo(uhrzeitA)
        );
    }

    @Test
    public void getStunde() {
        assertEquals(
                "12 Uhr hat 12 Stunden",
                12,
                this.uhrzeitA.getStunde()
        );
        assertEquals(
                "14:31 Uhr hat 14 Stunden",
                14,
                this.uhrzeitB.getStunde()
        );
    }

    @Test
    public void getMinuten() {
        assertEquals(
                "12 Uhr hat 0 Minuten",
                0,
                this.uhrzeitA.getMinuten()
        );
        assertEquals(
                "14:31 Uhr hat 31 Minuten",
                31,
                this.uhrzeitB.getMinuten()
        );
    }

}