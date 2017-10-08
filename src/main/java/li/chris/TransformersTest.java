package li.chris;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static li.chris.Transformers.battleTransformers;
import static org.junit.Assert.assertEquals;

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
        assertEquals("0 battles\n" +
                "No winners\n", outputStream.toString());
    }

    @Test
    public void testExample() {
        List<Transformers.Transformer> transformers = new ArrayList<>();
        transformers.add(new Transformers.Transformer("Soundwave", "D", new int[]{8, 9, 2, 6, 7, 5, 6, 10}));
        transformers.add(new Transformers.Transformer("Bluestreak", "A", new int[]{6, 6, 7, 9, 5, 2, 9, 7}));
        transformers.add(new Transformers.Transformer("Hubcap", "A", new int[]{4, 4, 4, 4, 4, 4, 4, 4}));
        battleTransformers(transformers);
        assertEquals("1 battle\n" +
                "Winning team (Decepticons): Soundwave\n" +
                "Survivors from the losing team (Autobots): Hubcap\n", outputStream.toString());
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

}
