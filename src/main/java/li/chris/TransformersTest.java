package li.chris;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static li.chris.Transformers.battleTransformers;
import static org.junit.Assert.assertTrue;

/**
 * Created by Chris on 2017-10-07.
 */
public class TransformersTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testOneTransformer() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{8, 9, 2, 6, 7, 5, 6, 10}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("0 battles"));
        assertTrue(outputStream.toString().contains("No winners"));
    }

    @Test
    public void testTwoTransformers() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{8, 9, 2, 6, 7, 5, 6, 10}));
        transformers.add(new Transformers.Transformer("Bluestreak", "A", new int[]{6, 6, 7, 9, 5, 2, 9, 7}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("Winning team (Decepticons): Soundwave"));
        assertTrue(!outputStream.toString().contains("Survivors from the losing team (Autobots): Bluestreak"));
    }

    @Test
    public void testStrengthAndCourageRating() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{5, 9, 2, 6, 7, 5, 6, 10}));
        transformers.add(new Transformers.Transformer("Bluestreak", "A", new int[]{8, 1, 1, 1, 1, 9, 1, 1}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("Winning team (Autobots): Bluestreak"));
        assertTrue(!outputStream.toString().contains("Survivors from the losing team (Decepticons): Soundwave"));
    }

    @Test
    public void testSkillRating() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{2, 9, 2, 6, 7, 5, 6, 5}));
        transformers.add(new Transformers.Transformer("Bluestreak", "A", new int[]{1, 1, 1, 1, 1, 1, 1, 8}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("Winning team (Autobots): Bluestreak"));
        assertTrue(!outputStream.toString().contains("Survivors from the losing team (Decepticons): Soundwave"));
    }

    @Test
    public void testOverallRating() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{8, 8, 8, 8, 8, 8, 8, 8}));
        transformers.add(new Transformers.Transformer("Bluestreak", "A", new int[]{9, 9, 9, 9, 9, 9, 9, 9}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("Winning team (Autobots): Bluestreak"));
        assertTrue(!outputStream.toString().contains("Survivors from the losing team (Decepticons): Soundwave"));
    }

    @Test
    public void testTie() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{8, 8, 8, 8, 8, 8, 8, 8}));
        transformers.add(new Transformers.Transformer("Bluestreak", "A", new int[]{8, 8, 8, 8, 8, 8, 8, 8}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("No winners"));
    }

    @Test
    public void testExample() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{8, 9, 2, 6, 7, 5, 6, 10}));
        transformers.add(new Transformers.Transformer("Bluestreak", "A", new int[]{6, 6, 7, 9, 5, 2, 9, 7}));
        transformers.add(new Transformers.Transformer("Hubcap", "A", new int[]{4, 4, 4, 4, 4, 4, 4, 4}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("Winning team (Decepticons): Soundwave"));
        assertTrue(outputStream.toString().contains("Survivors from the losing team (Autobots): Hubcap"));
    }

    @Test
    public void testOptimusVsOptimus() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Optimus Prime", "A", new int[]{6, 6, 7, 9, 5, 2, 9, 7}));
        transformers.add(new Transformers.Transformer("Optimus Prime", "D", new int[]{9, 9, 9, 9, 5, 2, 9, 7}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("No winners"));
    }

    @Test
    public void testOptimusVsNormal() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Optimus Prime", "A", new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
        transformers.add(new Transformers.Transformer("Bumble Bee", "D", new int[]{6, 6, 7, 9, 5, 2, 9, 7}));
        battleTransformers(transformers);
        assertTrue(outputStream.toString().contains("1 battle"));
        assertTrue(outputStream.toString().contains("Winning team (Autobots): Optimus Prime"));
        assertTrue(!outputStream.toString().contains("Survivors from the losing team (Decepticons): Bumble Bee"));
    }


    @After
    public void cleanUp() {
        System.setOut(null);
    }

}
