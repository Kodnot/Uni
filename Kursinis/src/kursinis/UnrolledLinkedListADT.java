package kursinis;

/**
 *
 * @author audri
 */
public interface UnrolledLinkedListADT<T> extends Iterable<T> {

    // Query Operations
    int size();

    int getNodeCount();

    boolean isEmpty();

    boolean contains(Object element);

    Object[] toArray();

    void printElements();

    /**
     * Returns an array of the provided type with all the elements of this
     * unrolled list. Could be done like List<T> does it, by passing an array of
     * type T, but I don't see the point.
     *
     * @param type The type of which the returned array should be (eg. int,
     * String etc)
     * @return
     */
    <T> T[] toArray(Class<T> type);

    // Modification Operations
    void add(T element);

    boolean remove(Object element);

    // Skipped bulk modifications because they are boring and not required for the demo
    // The performance of these methods should not differ drastically from a normal linked list
    // (Same diff as observed with individual contains() or add() etc. calls)
    void clear();

    // Positional Access Operations
    T get(int index);

    T set(int index, T element);

    void add(int index, T element);

    T removeAt(int index);

    // Search Operations
    int indexOf(Object o);

    int lastIndexOf(Object o);

    // List iterators and view operations skipped
}
