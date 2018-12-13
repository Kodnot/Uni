/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursinis;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author audri
 */
public class UnrolledLinkedList<T> implements UnrolledLinkedListADT<T> {

    private int maxElementCount;
    private Node startPos;
    private Node endPos;
    private int elementCount;

    public UnrolledLinkedList(int nodeCapacity) {
        startPos = null;
        endPos = null;
        elementCount = 0;
        maxElementCount = nodeCapacity;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public void clear() {
        elementCount = 0;
        startPos = null;
        endPos = null;
    }

    @Override
    public void add(T element) {
        // TODO: Smarter logic to choose to which node to insert?
        elementCount++;
        // Insert into empty list
        if (startPos == null) {
            startPos = new Node(element);
            endPos = startPos;
            return;
        }

        // Insert into the end node without expanding
        // TODO: Should this be <=?
        if (endPos.elementCount + 1 <= maxElementCount) {
            endPos.elements[endPos.elementCount] = element;
            endPos.elementCount++;
        } else {
            Node node = new Node();
            int j = 0;
            for (int i = endPos.elementCount / 2 + 1; i < endPos.elementCount; ++i, ++j) {
                node.elements[j] = endPos.elements[i];
            }

            node.elements[j++] = element;
            node.elementCount = j;
            endPos.elementCount = endPos.elementCount / 2 + 1;
            endPos.next = node;
            endPos = node;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > elementCount) {
            throw new IndexOutOfBoundsException("Index has to be between 0 and list size, inclusive.");
        }

        Node cur = startPos;
        int curIndex = 0;
        int valIndex = 0;
        while (cur != null) {
            if (index - curIndex <= cur.elementCount) {
                valIndex = index - curIndex;
                break;
            }
            curIndex += cur.elementCount;
            cur = cur.next;
        }

        elementCount++;
        // Insert into empty list
        if (startPos == null) {
            startPos = new Node(element);
            endPos = startPos;
            return;
        }

        if (cur.next != null && index - curIndex == maxElementCount) {
            curIndex += maxElementCount;
            valIndex = index - curIndex;
            cur = cur.next;
        }

        System.out.println("curIndex:" + curIndex + ", valIndex: " + valIndex + ", val:" + element);

        // Insert into the current node without expanding
        if (cur != null && cur.elementCount + 1 <= maxElementCount) {
            for (int i = cur.elementCount; i > valIndex; --i) {
                cur.elements[i] = cur.elements[i - 1];
            }
            cur.elements[valIndex] = element;

            cur.elementCount++;
            return;
        }

        // Insert into end (If I have a list of max size 4 [1 2 3 4], and I want to insert into the 
        // [4] position (0-based), I'll want to set the next element in a new array
        if (valIndex == cur.elementCount) {
            Node node = new Node();
            int j = 0;
            for (int i = endPos.elementCount / 2 + 1; i < endPos.elementCount; ++i, ++j) {
                node.elements[j] = endPos.elements[i];
            }

            node.elements[j++] = element;
            node.elementCount = j;
            endPos.elementCount = endPos.elementCount / 2 + 1;
            endPos.next = node;
            endPos = node;
            return;
        }

        // Insert into a position in a full node
        Node node = new Node();
        int j = 0;
        boolean valCopied = false;
        for (int i = cur.elementCount / 2; i < cur.elementCount; ++i, ++j) {
            if (i == valIndex && !valCopied) {
                node.elements[j] = element;
                i--;
                valCopied = true;
            } else {
                node.elements[j] = cur.elements[i];
            }
        }
        node.elementCount = j;
        cur.elementCount = cur.elementCount / 2;
        node.next = cur.next;
        cur.next = node;

        // If the inserted value was not copied to the new array, the cur array must be shifted
        if (!valCopied) {
            for (int i = cur.elementCount; i > valIndex; --i) {
                cur.elements[i] = cur.elements[i - 1];
            }
            cur.elements[valIndex] = element;

            cur.elementCount++;
        }

        if (cur == endPos) {
            endPos = node;
        }
    }

    @Override
    public void remove(Object element) {
        Node cur = startPos;
        boolean found = false;
        while (cur != null && !found) {
            for (int i = 0; i < cur.elementCount; ++i) {
                if (!found && cur.elements[i].equals(element)) {
                    elementCount--;
                    found = true;
                }
                if (found && i + 1 < cur.elementCount) {
                    cur.elements[i] = cur.elements[i + 1];
                }
            }
            if (found) {
                cur.elementCount--;
            } else {
                cur = cur.next;
            }
        }

        // Nothing to balance
        if (!found || cur.next == null) {
            return;
        }

        int movedDataCount = (maxElementCount + 1) / 2 - cur.elementCount;
        if (movedDataCount <= 0) {
            return;
        }

        movedDataCount = Math.min(movedDataCount, cur.next.elementCount);
        int i = cur.elementCount, j = 0;
        for (; j < movedDataCount; ++j) {
            cur.elements[i++] = cur.next.elements[j];
            if (j + movedDataCount < cur.next.elementCount) {
                cur.next.elements[j] = cur.next.elements[j + movedDataCount];
            }
        }

        // Finish shifting elements in next node
        for (; j < cur.next.elementCount; ++j) {
            if (j + movedDataCount < cur.next.elementCount) {
                cur.next.elements[j] = cur.next.elements[j + movedDataCount];
            }
        }
        cur.next.elementCount -= movedDataCount;
        cur.elementCount += movedDataCount;

        if (cur.next.elementCount < (maxElementCount + 1) / 2) {
            // Merge nodes
            for (j = 0; j < cur.next.elementCount; ++j) {
                cur.elements[i++] = cur.next.elements[j];
            }
            cur.elementCount += cur.next.elementCount;
            if (cur.next == endPos) {
                endPos = cur;
            }
            cur.next = cur.next.next;
        }
    }

    @Override
    public boolean contains(Object element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public Object[] toArray() {
        final Object[] rez = new Object[elementCount];
        int index = 0;
        Node cur = startPos;
        while (cur != null) {
            for (int i = 0; i < cur.elementCount; ++i) {
                rez[index++] = (T) cur.elements[i];
            }
            cur = cur.next;
        }
        return rez;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(Class<T> type) {
        final T[] rez = (T[]) Array.newInstance(type, elementCount);
        int index = 0;
        Node cur = startPos;
        while (cur != null) {
            for (int i = 0; i < cur.elementCount; ++i) {
                rez[index++] = (T) cur.elements[i];
            }
            cur = cur.next;
        }
        return rez;
    }

    public int getNodeCount() {
        int rez = 0;
        Node cur = startPos;
        while (cur != null) {
            rez++;
            cur = cur.next;
        }
        return rez;
    }

    public void printElements() {
        Node cur = startPos;
        System.out.print("(n=" + elementCount + ") ");
        while (cur != null) {
            for (int i = 0; i < cur.elementCount; ++i) {
                System.out.print(cur.elements[i] + " ");
            }
            System.out.print(" : ");
            cur = cur.next;
        }
        System.out.println("");
    }

    @Override
    public T get(int index) {
        Node cur = startPos;
        int curIndex = 0;
        while (cur != null) {
            if (index - curIndex < cur.elementCount) {
                return (T) cur.elements[index - curIndex];
            }
            curIndex += cur.elementCount;
            cur = cur.next;
        }

        return null;
    }

    @Override
    public T set(int index, T element) {
        Node cur = startPos;
        int curIndex = 0;
        while (cur != null) {
            if (index - curIndex < cur.elementCount) {
                T oldElement = (T) cur.elements[index - curIndex];
                cur.elements[index - curIndex] = element;
                return oldElement;
            }
            curIndex += cur.elementCount;
            cur = cur.next;
        }

        return null;
    }

    @Override
    public int indexOf(Object o) {
        Node cur = startPos;
        int index = 0;
        while (cur != null) {
            for (int i = 0; i < cur.elementCount; ++i) {
                if (cur.elements[i].equals(o)) {
                    return index;
                }
                index++;
            }
            cur = cur.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node cur = startPos;
        int index = 0;
        int rezIndex = -1;
        while (cur != null) {
            for (int i = 0; i < cur.elementCount; ++i) {
                if (cur.elements[i].equals(o)) {
                    rezIndex = index;
                }
                index++;
            }
            cur = cur.next;
        }
        return rezIndex;
    }

    protected class Node {

        protected Node next;
        protected int elementCount;
        protected Object[] elements;

        protected Node() {
            next = null;
            this.elements = new Object[maxElementCount];
            this.elementCount = 0;
        }

        protected Node(Object element) {
            this();
            elements[0] = element;
            this.elementCount = 1;
        }
    }
}
