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
        boolean[] expectedRez = new boolean[]{true, true, true, true, false, false, false};
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

    @Test
    public void testGetAverageChainSize() {
        // Arrange
        System.out.println("getAverageChainSize");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        MapKTU<String, Integer> map = new MapKTU<>();

        for (int i = 0; i < keys.length; ++i) {
            if (map.maxChainSize < 2) {
                assertEquals(map.maxChainSize, map.getAverageChainSize());
                map.put(keys[i], values[i]);
            }
            map.put(keys[i], values[i]);
            assertTrue("Should be less", map.getAverageChainSize() <= map.maxChainSize);
        }
    }

    @Test
    public void testGetEmptyElementCount() {
        // Arrange
        System.out.println("getEmptyElementCount");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        MapKTU<String, Integer> map = new MapKTU<>();

        int baseElementCount = map.getEmptyElementCount();
        int i = 0;
        while (map.getMaxChainSize() <= 1 && i < keys.length - 1) {
            assertEquals(map.getEmptyElementCount(), baseElementCount - i);
            map.put(keys[i], values[i]);
            i++;
        }
    }
}
