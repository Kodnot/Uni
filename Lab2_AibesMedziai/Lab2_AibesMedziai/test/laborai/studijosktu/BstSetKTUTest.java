package laborai.studijosktu;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kodnot
 */
public class BstSetKTUTest {

    public BstSetKTUTest() {
    }

    /**
     * Test of addAll method, of class BstSetKTU.
     */
    @Test
    public void testAddAll() {
        System.out.println("addAll");
        BstSetKTU<Integer> c = new BstSetKTU<>();
        c.add(1);
        c.add(2);
        BstSetKTU<Integer> instance = new BstSetKTU<>();
        instance.addAll(c);

        assertArrayEquals(c.toArray(), instance.toArray());
    }

    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        BstSetKTU<Integer> c = new BstSetKTU<>();
        c.add(1);
        c.add(2);
        c.add(5);
        BstSetKTU<Integer> instance = new BstSetKTU<>();
        instance.add(2);
        instance.add(5);
        instance.add(8);

        Integer[] expected = new Integer[]{8};
        boolean expectedRez = true;
        boolean rez = instance.removeAll(c);

        assertArrayEquals(instance.toArray(), expected);
        assertEquals(rez, expectedRez);
    }

    @Test
    public void testTailSet() {
        System.out.println("tailSet");
        BstSetKTU<Integer> c = new BstSetKTU<>();
        c.add(1);
        c.add(2);
        c.add(3);
        SetADT<Integer> firstSub = c.tailSet(2, true);
        SetADT<Integer> secondSub = c.tailSet(2, false);

        Integer[] ex1 = new Integer[]{2, 3};
        Integer[] ex2 = new Integer[]{3};

        assertArrayEquals(firstSub.toArray(), ex1);
        assertArrayEquals(secondSub.toArray(), ex2);
    }

    /**
     * Test of higher method, of class BstSetKTU.
     */
    @Test
    public void testHigher() {
        System.out.println("higher");
        BstSetKTU<Integer> c = new BstSetKTU<>();
        c.add(1);
        c.add(2);
        Integer val = 3;
        Integer expected = null;
        Integer rez = c.higher(val);

        assertEquals(rez, expected);
    }

    /**
     * Test of pollLast method, of class BstSetKTU.
     */
    @Test
    public void testPollLast() {
        System.out.println("pollLast");
        BstSetKTU<Integer> c = new BstSetKTU<>();
        c.add(1);
        c.add(3);
        c.add(2);
        Integer expectedSize = 2;
        Integer expected = 3;
        Integer rez = c.pollLast();

        assertEquals(rez, expected);
        assertEquals((Integer) c.size(), expectedSize);
    }

    /**
     * Test of getHeight method, of class BstSetKTU.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        BstSetKTU<Integer> instance = new BstSetKTU<>();
        instance.add(1);
        instance.add(2);
        int expResult = 2;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }
}
