package laborai.studijosktu;

import java.util.Set;
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
    public void testPutIfAbsent() {
        // Arrange
        System.out.println("putIfAbsent");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] testValues = new String[]{"one", "three", "a", "d", "five", "seven"};
        String[] expectedRez = new String[]{"one", "three", null, null, "five", "seven"};
        MapKTU<String, String> map = new MapKTU<>();
        
        for (int i = 0; i < keys.length; ++i) {
            map.put(keys[i], keys[i]);
        }

        // Act, Assert
        for (int i = 0; i < testValues.length; ++i) {
            String rez = map.putIfAbsent(testValues[i], "random");
            assertEquals(true, map.contains(testValues[i]));
            assertEquals(expectedRez[i], rez);
        }
    }
    
    @Test
    public void testReplaceAll() {
        // Arrange
        System.out.println("replaceAll");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 1, 3, 1, 5, 1, 7, 1, 9};
        Integer[] expectedRez = new Integer[]{0, 0, 3, 0, 5, 0, 7, 0, 9};
        MapKTU<String, Integer> map = new MapKTU<>();
        
        for (int i = 0; i < keys.length; ++i) {
            map.put(keys[i], values[i]);
        }

        // Act
        map.replaceAll(1, 0);

        // Assert
        for (int i = 0; i < keys.length; ++i) {
            assertEquals(expectedRez[i], map.get(keys[i]));
        }
    }
    
    @Test
    public void testKeySet() {
        // Arrange
        System.out.println("keySet");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        MapKTU<String, Integer> map = new MapKTU<>();
        
        for (int i = 0; i < keys.length; ++i) {
            map.put(keys[i], values[i]);
        }

        // Act
        Set<String> keySet = map.keySet();

        // Assert
        assertEquals(keys.length, keySet.size());
        for (String key : keys) {
            assertTrue(keySet.contains(key));
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
                assertEquals(map.maxChainSize, map.getAverageChainSize(), 0.001);
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
