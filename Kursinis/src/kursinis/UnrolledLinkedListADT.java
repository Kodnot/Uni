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
    
    boolean isEmpty();

    int size();

    void clear();

    void add(T element);

    void remove(T element);

    boolean contains(T element);

    // Grąžinamas aibės elementų masyvas.
    T[] toArray(Class<T> type);
}
