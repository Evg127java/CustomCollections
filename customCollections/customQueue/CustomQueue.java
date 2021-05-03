package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The custom queue class
 */
public class CustomQueue<G> implements Iterable<G> {
    /**
     * The head of this queue
     */
    private Node<G> head;

    /**
     * The tail of this queue
     */
    private Node<G> tail;

    /**
     * Actual queue's elements amount
     */
    private int size;

    /**
     * Adds an object to the end of the queue
     *
     * @param value The object to add to the queue
     * @return true if the object was added
     */
    public boolean offer(G value) {
        if (value == null) {
            return false;
        }
        Node<G> tempNode = new Node<>();
        tempNode.setValue(value);
        if (!isEmpty()) {
            tail.setNext(tempNode);
            tail = tempNode;
        } else {
            tail = tempNode;
            head = tail;
        }
        size++;
        return true;
    }

    /**
     * Adds an object to the end of the queue
     *
     * @param value The object to add to the queue
     * @return true if the object was added
     * @throws NullPointerException if object was not added
     */
    public boolean add(G value) {
        if (!offer(value)) {
            throw new NullPointerException();
        }
        return true;
    }

    /**
     * Gets the head of the queue without removing
     *
     * @return the head of the queue or null if the queue is empty
     */
    public G peek() {
        return head == null ? null : head.value;
    }

    /**
     * Gets the head of the queue without removing
     *
     * @return the head of the queue
     * @throws NullPointerException if the queue is empty
     */
    public G element() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return peek();
    }

    /**
     * Gets the head of the queue with removing
     *
     * @return the head of the queue or null if the queue is empty
     */
    public G poll() {
        if (isEmpty()) {
            return null;
        }
        G value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    /**
     * Removes the head of the queue
     *
     * @return the head of the queue
     * @throws NullPointerException if the queue is empty
     */
    public G remove() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return poll();
    }

    /**
     * Gets the current's queue elements number
     */
    public int size() {
        return size;
    }

    /**
     * Clears the current queue from all its objects
     */
    public void clear() {
        if (size > 0) {
            head = null;
            tail = null;
            size = 0;
        }
    }

    /**
     * Checks if the current queue size is empty
     * Uses for public calls
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the list of Generic type represented objects for tests
     */
    @SuppressWarnings("unchecked")
    public G[] toCastedArray() {
        G[] array = (G[]) new Object[size];
        Node<G> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.getValue();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public Iterator<G> iterator() {
        return new Iterator<>() {
            Node<G> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public G next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                G value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }
}
