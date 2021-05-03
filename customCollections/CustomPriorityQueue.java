package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections;

import static com.shpp.p2p.cs.ekondratiuk.assignment17.Constants.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom priority queue class. Max priority is the minimal value in the queue
 * Based on the min heap
 */
public class CustomPriorityQueue<G extends Comparable<G>> implements Iterable<G> {
    /**
     * An array for data processing in the queue.
     */
    public Object[] array;
    /**
     * Priority queue size.
     */
    private int size;
    /**
     * The size of the inner array of the priority queue.
     */
    private int priorityQueueCapacity;

    private Comparator<G> comparator;

    /**
     * Constructor for a case without arguments.
     * Makes a queue with the default capacity value.
     */
    public CustomPriorityQueue() {
        this(DEFAULT_CAPACITY_VALUE);
        this.comparator = Comparator.naturalOrder();
    }

    /**
     * Constructor for a case with a capacity argument.
     * Makes a queue with the specified capacity value.
     * Comparator value is null by default.
     * In this case using a the natural way of sorting
     */
    public CustomPriorityQueue(int capacity) {
        this.priorityQueueCapacity = capacity;
        array = new Object[capacity];
    }

    /**
     * Constructor for a case with a capacity argument.
     * Makes a queue with the specified capacity value.
     * Comparator value is null by default.
     * In this case using a the natural way of sorting
     */
    public CustomPriorityQueue(Comparator<G> comparator) {
        this.priorityQueueCapacity = DEFAULT_CAPACITY_VALUE;
        array = new Object[priorityQueueCapacity];
        this.comparator = comparator;
    }

    /**
     * Adds the specified value to the priority queue.
     *
     * @param value The item to add.
     * @return True if the operation is successful.
     * @throws NullPointerException if the operation failed
     */
    public boolean add(G value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (((size + 1)) >= priorityQueueCapacity) {
            rearrangeSpace(size / 2);
        }
        array[size++] = value;
        sortAfterAdd();
        return true;
    }

    /**
     * Corrects binary heap after adding a new value to the queue.
     * The first value in the queue has the highest priority after sorting
     */
    @SuppressWarnings("unchecked")
    private void sortAfterAdd() {
        /* Set the last item in the queue as a current */
        int currentIndex = size - 1;
        int parentIndex = (currentIndex - 1) / 2;
        G current = (G) array[currentIndex];
        G parent = (G) array[(currentIndex - 1) / 2];

        /* Process all the items from the array starting from the last item */
        while (currentIndex > 0 && (comparator.compare(current, parent) < 0)) {

            /* Swap the parent with its child if the child is more than its parent */
            G temp = (G) array[currentIndex];
            array[currentIndex] = array[parentIndex];
            array[parentIndex] = temp;
            currentIndex = parentIndex;
            current = (G) array[currentIndex];
            parentIndex = (currentIndex - 1) / 2;
            parent = (G) array[parentIndex];
        }
    }

    /**
     * Gets the head of the queue without removing it
     *
     * @return The head of the queue
     * Null if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public G peek() {
        return size == 0 ? null : (G) array[0];
    }

    /**
     * Gets the head of the queue with removing it
     *
     * @return The head of this queue
     * Null if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public G poll() {
        if (size == 0) {
            return null;
        }
        G value = (G) array[0];
        /* Swap the first and the last items in the queue */
        array[0] = array[size - 1];
        array[size-- - 1] = null;
        sortAfterPoll();
        return value;
    }

    /**
     * Corrects binary heap after deleting the item from the queue.
     * Starts from the top of the binary heap
     * (The binary heap top is the first item in the inner array of the queue)
     */
    @SuppressWarnings("unchecked")
    private void sortAfterPoll() {
        int indexOfLeftChild;
        int indexOfRightChild;
        int indexOfSmallestValue;
        int indexOfParent = 0;

        /* Process all the items in the binary heap */
        while (indexOfParent < size) {
            indexOfLeftChild = 2 * indexOfParent + 1;
            indexOfRightChild = 2 * indexOfParent + 2;
            indexOfSmallestValue = getIndexOfSmallest(indexOfLeftChild, indexOfRightChild, indexOfParent);

            /* If the top of the heap */
            if (indexOfSmallestValue == indexOfParent) {
                break;
            }

            /* Swap the smallest item with its parent if it exists */
            G temp = (G) array[indexOfParent];
            array[indexOfParent] = array[indexOfSmallestValue];
            array[indexOfSmallestValue] = temp;

            /* Set from where to begin the nex loop */
            indexOfParent = indexOfSmallestValue;
        }
    }

    /**
     * Gets the smallest item from the parent and its children
     *
     * @param indexOfLeftChild  Left child's index in the array
     * @param indexOfRightChild Right child's index in the array
     * @param indexOfParent     Parent's index in the array
     * @return The smallest item from the passed
     */
    @SuppressWarnings("unchecked")
    private int getIndexOfSmallest(int indexOfLeftChild, int indexOfRightChild, int indexOfParent) {
        G leftChild = indexOfLeftChild < array.length ? (G) array[indexOfLeftChild] : null;
        G rightChild = indexOfRightChild < array.length ? (G) array[indexOfRightChild] : null;
        G smallestValue = (G) array[indexOfParent];
        /* The initial smallest item is the parent */
        int indexOfSmallestValue = indexOfParent;

        /* If the parent has only right child */
        if (leftChild == null && rightChild != null && comparator.compare(rightChild, smallestValue) < 0) {
            indexOfSmallestValue = indexOfRightChild;
        }
        /* If the parent has only left child */
        if (rightChild == null && leftChild != null && comparator.compare(leftChild, smallestValue) < 0) {
            indexOfSmallestValue = indexOfLeftChild;
        }
        /* If the parent has both children */
        if (leftChild != null && rightChild != null) {
            /* Check if the left child is the smallest */
            if (comparator.compare(leftChild, smallestValue) < 0) {
                smallestValue = (G) array[indexOfLeftChild];
                indexOfSmallestValue = indexOfLeftChild;
            }
            /* Check if the right child is the smallest */
            if (comparator.compare(rightChild, smallestValue) < 0) {
                indexOfSmallestValue = indexOfRightChild;
            }
        }
        return indexOfSmallestValue;
    }

    /**
     * Gets elements amount in the queue.
     *
     * @return The amount of the queue's elements
     **/
    public int size() {
        return size;
    }

    /**
     * Checks if the current queue size is empty
     * Used for public calls
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Extends the current list's space by capacity increasing with the specified value
     *
     * @param additionSpaceSize The value to extend
     */
    private void rearrangeSpace(int additionSpaceSize) {
        Object[] tempArray = new Object[priorityQueueCapacity];
        System.arraycopy(array, 0, tempArray, 0, size);
        priorityQueueCapacity += additionSpaceSize;
        array = new Object[priorityQueueCapacity];
        System.arraycopy(tempArray, 0, array, 0, tempArray.length);
    }

    /**
     * Clears the current queue from all its objects
     */
    public void clear() {
        if (size > 0) {
            size = 0;
            array = new Object[DEFAULT_CAPACITY_VALUE];
            priorityQueueCapacity = DEFAULT_CAPACITY_VALUE;
        }
    }

    /**
     * Gets string representation of the the inner queue's array's content.
     *
     * @return Inner queue's array as a string.
     */
    @Override
    public String toString() {
        Object[] temp = new Object[size];
        System.arraycopy(array, 0, temp, 0, size);
        return Arrays.toString(temp);
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
                return (G) array[index++];
            }
        };
    }
}
