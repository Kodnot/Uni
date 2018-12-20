/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3liuberskis;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kodnot
 */
public class MapKTUOATest {

    public MapKTUOATest() {
    }

    @Test
    public void testPutContains() {
        // Arrange
        System.out.println("put and contains");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        MapKTUOA<String, Integer> map = new MapKTUOA<>();

        // Act, Assert
        for (int i = 0; i < keys.length; ++i) {
            assertEquals(false, map.contains(keys[i]));
            map.put(keys[i], values[i]);
            assertEquals(true, map.contains(keys[i]));
        }
    }

    @Test
    public void testPutGet() {
        // Arrange
        System.out.println("put and get");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        MapKTUOA<String, Integer> map = new MapKTUOA<>();

        // Act, Assert
        for (int i = 0; i < keys.length; ++i) {
            assertEquals(null, map.get(keys[i]));
            map.put(keys[i], values[i]);
            assertEquals(values[i], map.get(keys[i]));
        }
    }

    @Test
    public void testRemove() {
        // Arrange
        System.out.println("remove");
        String[] keys = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        MapKTUOA<String, Integer> map = new MapKTUOA<>();

        // Act, Assert
        for (int i = 0; i < keys.length; ++i) {
            map.put(keys[i], values[i]);
            assertEquals(values[i], map.get(keys[i]));
            Integer value = map.remove(keys[i]);
            assertEquals(values[i], value);
            assertEquals(null, map.get(keys[i]));
        }
    }
}
