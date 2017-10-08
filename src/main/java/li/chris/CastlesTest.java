package li.chris;

import static li.chris.Castles.buildCastles;
import static org.junit.Assert.assertEquals;

/**
 * Created by Chris on 2017-10-07.
 */
public class CastlesTest {

    @org.junit.Test()
    public void testEmpty() {
        assertEquals(0, buildCastles(new int[] {}));
    }

    @org.junit.Test
    public void testOne() {
        assertEquals(1, buildCastles(new int[]{2}));
    }

    @org.junit.Test
    public void testTwo() {
        assertEquals(1, buildCastles(new int[]{2, 1}));
    }

    @org.junit.Test
    public void testAllRepeating() {
        assertEquals(1, buildCastles(new int[]{2, 2, 2}));
    }

    @org.junit.Test
    public void testPeak() {
        assertEquals(2, buildCastles(new int[]{2, 3, 2}));
    }

    @org.junit.Test
    public void testRepeatingPeak() {
        assertEquals(2, buildCastles(new int[]{2, 3, 3, 3, 2}));
    }

    @org.junit.Test
    public void testValley() {
        assertEquals(2, buildCastles(new int[]{2, 1, 2}));
    }

    @org.junit.Test
    public void testRepeatingValley() {
        assertEquals(2, buildCastles(new int[]{2, 1, 1, 1, 2}));
    }

    @org.junit.Test
    public void testTwoPeaksOneValley() {
        assertEquals(4, buildCastles(new int[]{2, 3, 2, 3, 2}));
    }

    @org.junit.Test
    public void testRepeatBeginning() {
        assertEquals(1, buildCastles(new int[]{2, 2, 2, 2, 2, 3}));
    }

    @org.junit.Test
    public void testRepeatEnd() {
        assertEquals(1, buildCastles(new int[]{1, 2, 2, 2, 2, 2}));
    }

    @org.junit.Test
    public void testRepeatBeginningValley() {
        assertEquals(2, buildCastles(new int[]{2, 2, 2, 2, 2, 1, 3}));
    }

    @org.junit.Test
    public void testAlternating() {
        assertEquals(6, buildCastles(new int[]{1, 2, 1, 2, 1, 2, 1}));
    }
}

