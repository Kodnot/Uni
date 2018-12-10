/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursinis;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kodnot
 */
public class UnrolledLinkedListTest {
    
    public UnrolledLinkedListTest() {
    }

    @Test
    public void testIsEmpty() {
        // Arrange
        System.out.println("isEmpty");
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);
        
        // Act, Assert
        assertEquals(true, instance.isEmpty());
        instance.add(2);
        assertEquals(false, instance.isEmpty());
        instance.remove(2);
        assertEquals(true, instance.isEmpty());
    }

    @Test
    public void testSize() {
        // Arrange
        System.out.println("size");
        Integer[] elements = new Integer[]{1, -3, 2, 14, null, 6};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        // Act, Assert
        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
            assertEquals(instance.size(), i + 1);
        }
    }
    
    @Test
    public void testClear() {
        // Arrange
        System.out.println("clear");
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        // Act
        instance.clear();

        // Assert
        assertEquals(0, instance.size());
    }
    
    @Test
    public void testAdd() {
        // Arrange
        System.out.println("add");
        Integer[] elements = new Integer[]{1, 3, 2, 4, 5, 6};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        // Act
        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
            instance.printElements();
        }
        Integer[] rez = instance.toArray(Integer.class);
        int nodeCount = instance.getNodeCount();

        // Assert
        assertArrayEquals(elements, rez);
        assertEquals(3, nodeCount);
    }
    
    @Test
    public void testRemove() {
        // Arrange
        System.out.println("remove");
        Integer[] elements = new Integer[]{1, 3, 2, 4, 5};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);
        Integer[] expected = new Integer[]{4, 5};
        
        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }
        instance.printElements();

        // Act
        for (int el : new int[]{3, 2, 1}) {
            instance.remove(el);
            instance.printElements();
        }
        
        Integer[] rez = instance.toArray(Integer.class);

        // Assert
        assertArrayEquals(expected, rez);
        assertEquals(instance.getNodeCount(), 1);
    }
    
    @Test
    public void testContains() {
        // Arrange
        System.out.println("contains");
        Integer[] elements = new Integer[]{1, 3, 2, 4, 5, 6};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);
        int[] testCases = new int[]{1, 4, 6, 9, 16};
        boolean[] expected = new boolean[]{true, true, true, false, false};
        
        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }
        instance.printElements();

        // Act, Assert
        for (int i = 0; i < testCases.length; ++i) {
            boolean rez = instance.contains(testCases[i]);
            assertEquals(expected[i], rez);
        }
    }
    
    @Test
    public void testToArray() {
        // Arrange
        System.out.println("toArray");
        Integer[] elements = new Integer[]{1, 3, 2, 4};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(2);

        // Act
        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }
        instance.printElements();
        
        Integer[] rez = instance.toArray(Integer.class);

        // Assert
        assertArrayEquals(elements, rez);
    }
}
