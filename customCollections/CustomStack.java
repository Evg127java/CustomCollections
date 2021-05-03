package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.shpp.p2p.cs.ekondratiuk.assignment17.Constants.*;

/**
 * Class of custom stack
 */
public class CustomStack<G> implements Iterable<G> {
    /**
     * Inner array for processing data
     */
    private Object[] array;

    /**
     * Actual stack's elements amount
     */
    private int size = 0;

    /**
     * Stack's current capacity
     */
    private int stackCapacity;

    /**
     * Constructor without parameters
     * Makes an array of default capacity
     */
    public CustomStack() {
        stackCapacity = DEFAULT_CAPACITY_VALUE;
        array = new Object[stackCapacity];
    }

    /**
     * Adds an object to the top of the stack
     *
     * @param value The object to add to the stack
     * @return the object that was added
     */
    public G push(G value) {
        /* Check if capacity space is enough */
        if (size + 1 > stackCapacity) {
            int currentStackSize = array.length;
            Object[] tempArray = new Object[currentStackSize];
            System.arraycopy(array, 0, tempArray, 0, currentStackSize);
            stackCapacity = stackCapacity + (stackCapacity / 2) + 1;
            array = new Object[stackCapacity];
            System.arraycopy(tempArray, 0, array, 0, tempArray.length);
        }
        array[size++] = value;
        return value;
    }

    /**
     * Gets the top object from the stack with removing
     */
    public G pop() {
        /* Use the custom inner method for get the last object from the current stack */
        G popped = this.peek();
        array[size] = null;
        size--;
        return popped;
    }

    /**
     * Gets the top object from the stack without removing
     *
     * @return the top object from the stack
     */
    @SuppressWarnings("unchecked")
    public G peek() {
        checkStackZeroSize();
        return (G) array[size - 1];
    }

    /**
     * Checks if the current stack contains the specified object
     *
     * @return object's index or -1 if there is no such an object
     */
    public int contains(G value) {
        checkStackZeroSize();
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return size - i;
            }
        }
        return -1;
    }

    /**
     * Checks if the current stack size is zero
     * Uses for inner calls
     *
     * @throws EmptyStackException if the current stack size is zero
     */
    private void checkStackZeroSize() {
        if (size == 0) {
            throw new EmptyStackException();
        }
    }

    /**
     * Checks if the current stack size is empty
     * Uses for public calls
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Gets the current's stack elements number
     */
    public int size() {
        return size;
    }


    /**
     * Clears the current stack from all its objects
     */
    public void clear() {
        if (size > 0) {
            size = 0;
            array = new Object[DEFAULT_CAPACITY_VALUE];
            stackCapacity = DEFAULT_CAPACITY_VALUE;
        }
    }

    /**
     * Gets string representation of the current stack
     */
    public String toString() {
        Object[] tempArray = new Object[size];
        System.arraycopy(array, 0, tempArray, 0, size);
        return Arrays.toString(tempArray);
    }

    /**
     * Gets the list of Generic type represented objects
     */
    @SuppressWarnings("unchecked")
    public G[] toCastedArray() {
        G[] tempArray = (G[]) new Object[size];
        for (int i = 0; i < size; i++) {
            tempArray[i] = (G) array[i];
        }
        return tempArray;
    }

    @Override
    public Iterator<G> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size && array[index] != null;
            }

            @Override
            @SuppressWarnings("unchecked")
            public G next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (G)array[index++];
            }
        };
    }
}
