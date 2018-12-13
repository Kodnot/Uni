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
    public void testAdd_GenericType() {
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
    public void testAdd_int_GenericType() {
        // Arrange
        System.out.println("add at index");
        Integer[] elements = new Integer[]{1, 3, 2, 4, 5, 6};
        Integer[] expected = new Integer[]{1, 0, 2, 3, 3, 4, 5, 1, 2, 4, 5, 6};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        for (int i = 0; i < elements.length; ++i) {
            instance.add(i, elements[i]);
            instance.printElements();
        }

        // Act
        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i], i);
            instance.printElements();
        }
        Integer[] rez = instance.toArray(Integer.class);

        // Assert
        assertArrayEquals(expected, rez);

        // TODO: add a throw test
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
    public void testToArray_0args() {
        // Arrange
        System.out.println("toArray");
        Integer[] elements = new Integer[]{1, 3, 2, 4};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(2);

        // Act
        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }
        instance.printElements();

        Object[] rez = instance.toArray();

        // Assert
        assertArrayEquals(elements, rez);
    }

    @Test
    public void testToArray_Class() {
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

    @Test
    public void testGetNodeCount() {
        // Arrange
        System.out.println("getNodeCount");
        Integer[] elements = new Integer[]{1, 3, 2, 4};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);
        int expectedNodeCount = 2;

        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }
        instance.printElements();
        
        // Act
        int nodeCount = instance.getNodeCount();

        // Assert
        assertEquals(expectedNodeCount, nodeCount);
    }

    @Test
    public void testPrintElements() {
        // Arrange
        System.out.println("printElements");
        Integer[] elements = new Integer[]{1, 3, 2, 4, 6, 7};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }

        // Act, Assert
        System.out.print("Inserted elements: ");
        for (int el : elements) {
            System.out.print(el + " ");
        }
        System.out.println("\nPrintElements() results: ");
        instance.printElements();
    }

    @Test
    public void testGet() {
        // Arrange
        System.out.println("get");
        Integer[] elements = new Integer[]{1, 2, 3, 4, 5, 6};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }

        // Act, Assert
        for (int i = 0; i < elements.length; ++i) {
            assertEquals(elements[i], instance.get(i));
        }
    }

    @Test
    public void testSet() {
        // Arrange
        System.out.println("set");
        Integer[] elements = new Integer[]{1, 2, 3, 4, 5, 6};
        Integer[] newElements = new Integer[]{7, 8, 9, 10, 11, 12};

        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }

        // Act, Assert
        for (int i = 0; i < elements.length; ++i) {
            Integer oldVal = instance.set(i, newElements[i]);
            Integer newVal = instance.get(i);
            assertEquals(elements[i], oldVal);
            assertEquals(newElements[i], newVal);
        }
    }

    @Test
    public void testIndexOf() {
        // Arrange
        System.out.println("indexOf");
        Integer[] elements = new Integer[]{1, 2, 3, 4, 5, 6};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }

        // Act, Assert
        for (int i = 0; i < elements.length; ++i) {
            assertEquals(elements[i] - 1, instance.indexOf(elements[i]));
        }
    }

    @Test
    public void testLastIndexOf() {
        // Arrange
        System.out.println("lastIndexOf");
        Integer[] elements = new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        UnrolledLinkedList<Integer> instance = new UnrolledLinkedList<Integer>(3);

        for (int i = 0; i < elements.length; ++i) {
            instance.add(elements[i]);
        }

        // Act, Assert
        for (int i = 0; i < elements.length; ++i) {
            assertEquals(elements[i] * 2 - 1, instance.lastIndexOf(elements[i]));
        }
    }
}
