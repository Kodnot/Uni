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
        maxElementCount = nodeCapacity + 1;
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
        if (endPos.elementCount + 1 < maxElementCount) {
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
    public void remove(T element) {
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

        int movedDataCount = maxElementCount / 2 - cur.elementCount;
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

        if (cur.next.elementCount < maxElementCount / 2) {
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
    public boolean contains(T element) {
        Node cur = startPos;
        while (cur != null) {
            for (int i = 0; i < cur.elementCount; ++i) {
                if (cur.elements[i].equals(element)) {
                    return true;
                }
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(Class<T> type) {
        @SuppressWarnings("unchecked")
        final T[] rez = (T[]) Array.newInstance(type, elementCount);
        int index = 0;
        Node cur = startPos;
        while (cur != null) {
            for (int i = 0; i < cur.elementCount; ++i) {
                rez[index++] = (T)cur.elements[i];
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

    protected class Node {

        protected Node next;
        protected int elementCount;
        protected Object[] elements;

        protected Node() {
            next = null;
            this.elements = new Object[maxElementCount];
            this.elementCount = 0;
        }

        protected Node(T element) {
            this();
            elements[0] = element;
            this.elementCount = 1;
        }
    }
}
