package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections;

import static com.shpp.p2p.cs.ekondratiuk.assignment17.Constants.*;

import java.util.*;

/**
 * Class of custom arrayList
 */
public class CustomArrayList<G> implements Iterable<G> {
    /**
     * Inner array for processing data
     */
    private Object[] array;

    /**
     * Actual arraylist's elements amount
     */
    private int size = 0;

    /**
     * Arraylist's current capacity
     */
    private int thisListCapacity;

    /**
     * Constructor without parameters
     * Makes an array of default capacity
     */
    public CustomArrayList() {
        this(DEFAULT_CAPACITY_VALUE);
        array = new Object[thisListCapacity];
    }

    /**
     * Makes an array of specified capacity
     *
     * @param capacity The capacity for initiate an inner array size
     */
    public CustomArrayList(int capacity) {
        thisListCapacity = capacity;
        array = new Object[capacity];
    }

    /**
     * Makes an array of default capacity with specified collection
     *
     * @param customArrayList Collection as initial data of the arraylist
     */
    public CustomArrayList(Collection<? extends G> customArrayList) {
        this(customArrayList.size());
        addAll(customArrayList);
    }

    /**
     * Adds one object to the current arraylist
     */
    public void add(G value) {
        if (size + 1 > thisListCapacity) {
            /* Extend current arraylist's capacity */
            rearrangeArrayListSpace(thisListCapacity / 2);
        }
        array[size++] = value;
    }

    /**
     * Adds a collection to the current arraylist
     *
     * @return true if the operation is successful
     */
    public boolean addAll(Collection<? extends G> collectionToAdd) {
        if (collectionToAdd.isEmpty()) {
            throw new NullPointerException();
        }
        int sizeOfCollectionToAdd = collectionToAdd.size();
        Object[] arrayToAdd = collectionToAdd.toArray();
        /* Extend current arraylist's capacity with specified value */
        if (thisListCapacity < sizeOfCollectionToAdd + thisListCapacity) {
            rearrangeArrayListSpace(sizeOfCollectionToAdd);
        }
        System.arraycopy(arrayToAdd, 0, array, size, sizeOfCollectionToAdd);
        size += sizeOfCollectionToAdd;
        return true;
    }

    /**
     * Adds an object to the current arraylist in the specified place
     */
    public void add(int index, G value) {
        checkIfInBounds(index);
        if (index == size) {
            add(value);
        } else {
            /* Check if capacity rearrange is needed */
            if (size + 1 > thisListCapacity) {
                rearrangeArrayListSpace(thisListCapacity / 2);
            }
            Object[] temp = new Object[size - index];
            System.arraycopy(array, index, temp, 0, size - index);
            array[index] = value;
            System.arraycopy(temp, 0, array, index + 1, temp.length);
            size++;
        }
    }

    /**
     * Adds a collection to the current arraylist in the specified place
     *
     * @return true if the operation is successful
     */
    public boolean addAll(int index, Collection<? extends G> collectionToAdd) {
        checkIfInBounds(index);
        if (collectionToAdd.isEmpty()) {
            throw new NullPointerException();
        }
        int sizeOfCollectionToAdd = collectionToAdd.size();
        /* Save data before the specified place */
        Object[] firstPart = collectionToAdd.toArray();
        rearrangeArrayListSpace(sizeOfCollectionToAdd);
        /* Save data after the specified place */
        Object[] secondPart = new Object[size - index];
        System.arraycopy(
                array, index, secondPart, 0, size - index
        );
        System.arraycopy(firstPart, 0, array, index, sizeOfCollectionToAdd);
        System.arraycopy(
                secondPart, 0, array, index +
                        sizeOfCollectionToAdd, secondPart.length
        );
        size += sizeOfCollectionToAdd;
        return true;
    }

    /**
     * Checks if the current arraylist contains the specified object
     *
     * @return true if it does.
     */
    public boolean contains(G value) {
        return this.indexOf(value) != -1;
    }

    /**
     * Gets the the specified object index in the current arraylist
     *
     * @return -1 if there is no such an object
     */
    public int indexOf(G value) {
        /* Check if null was specified as the object to search */
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Gets an object by the specified index.
     */
    @SuppressWarnings("unchecked")
    public G get(int index) {
        checkIfInBounds(index);
        return (G) array[index];
    }

    /**
     * Sets the specified object value by the specified index.
     *
     * @return the object that was on the specified place
     */
    public G set(int index, G value) {
        checkIfInBounds(index);
        G removed = get(index);
        array[index] = value;
        return removed;
    }

    /**
     * Removes the object with the specified index.
     *
     * @return the object that was on the specified place
     */
    public G remove(int index) {
        G removed = get(index);
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        array[size] = null;
        size--;
        return removed;
    }

    /**
     * Removes the specified object value
     *
     * @return true if the operation is successful
     */
    public boolean remove(G value) {
        int index = indexOf(value);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified index is in the allowed range
     *
     * @param index int of index
     * @throws IndexOutOfBoundsException if index is not allowed
     */
    private void checkIfInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks if the current arraylist has any elements
     *
     * @return true if it does
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the current's arraylist elements number
     */
    public int size() {
        return size;
    }

    /**
     * Clears the current arraylist from all its objects
     */
    public void clear() {
        if (size > 0) {
            size = 0;
            array = new Object[DEFAULT_CAPACITY_VALUE];
            thisListCapacity = DEFAULT_CAPACITY_VALUE;
        }
    }

    /**
     * Extends the current arraylist capacity with the specified value
     *
     * @param spaceSize The value to extend
     */
    private void rearrangeArrayListSpace(int spaceSize) {
        Object[] tempArray = new Object[thisListCapacity];
        System.arraycopy(array, 0, tempArray, 0, size);
        thisListCapacity += spaceSize;
        array = new Object[thisListCapacity];
        System.arraycopy(tempArray, 0, array, 0, tempArray.length);
    }

    /**
     * Gets string representation of the current arraylist
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

    /**
     * Iterator for public iteration using
     */
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
