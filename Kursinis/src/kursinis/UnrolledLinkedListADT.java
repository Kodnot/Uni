/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursinis;

/**
 *
 * @author audri
 */
public interface UnrolledLinkedListADT<T> /*extends Iterable<T>*/ {

    // Query Operations
    int size();

    boolean isEmpty();

    boolean contains(Object element);

    Object[] toArray();

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

    void remove(Object element);

    // Skipped bulk modifications because they are boring and not required for the demo
    // The performance of these methods should not differ drastically from a normal linked list
    // (Same diff as observed with individual contains() or add() etc. calls)
    // TODO: Implementing a merge sort could be here could be interesting
    void clear();

    // Positional Access Operations
    T get(int index);

    T set(int index, T element);

    void add(int index, T element);

//    T remove(int index);
    
    // Search Operations
    int indexOf(Object o);

    int lastIndexOf(Object o);

    // List iterators and view operations skipped
}
