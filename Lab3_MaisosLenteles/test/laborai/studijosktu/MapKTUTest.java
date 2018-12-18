package laborai.studijosktu;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kodnot
 */
public class MapKTUTest {

    public MapKTUTest() {
    }

    @Test
    public void testContainsValue() {
        // Arrange
        System.out.println("containsValue");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] testValues = new Integer[]{1, 3, 6, 7, 12, 19, 0};
        boolean[] expectedRez = new boolean[]{false, true, true, true, false, false, false};
        MapKTU<String, Integer> map = new MapKTU<>();

        for (int i = 0; i < keys.length; ++i) {
            map.put(keys[i], values[i]);
        }

        // Act, Assert
        for (int i = 0; i < testValues.length; ++i) {
            boolean rez = map.containsValue(testValues[i]);
            assertEquals(expectedRez[i], rez);
        }
    }
}
