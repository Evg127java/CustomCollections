package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customLinkedList;

import java.util.*;

/**
 * Class of custom linkedList
 */
public class CustomLinkedList<G> implements Iterable<G> {
    /**
     * Actual linkedList's elements amount
     */
    private int size;

    /**
     * Pointer to the head of the List
     */
    private Node<G> head;

    /**
     * Pointer to the head of the List
     */
    private Node<G> tail;

    public CustomLinkedList() {
    }

    /**
     * Constructor for taking a collection as parameter
     * @param collection Collection of Generic type or types that extends it
     */
    public CustomLinkedList(Collection<? extends G> collection) {
        this();
        Object[] objects = collection.toArray();
        for (int i = 0; i < objects.length; i++) {
            @SuppressWarnings("unchecked") G value = (G) objects[i];
            Node<G> currentNode = new Node<>(null, value, null);
            if (i == 0) {
                head = currentNode;
                tail = head;
            } else  {
                currentNode.setPreviousNode(tail);
                tail.setNextNode(currentNode);
                tail = currentNode;
            }
            size++;
        }
    }

    /**
     * Adds the node to the head of the list
     * @param value The value ov the node
     */
    public void addFirst(G value) {
        Node<G> temp = new Node<>(null, value, null);
        if (isEmpty()) {
            tail = temp;
        } else {
            head.setPreviousNode(temp);
            temp.setNextNode(head);
        }
        head = temp;
        size++;
    }

    /**
     * Adds the node to the tail of the list
     * @param value The value ov the node
     */
    public void addLast(G value) {
        Node<G> temp = new Node<>(null, value, null);
        if (isEmpty()) {
            head = temp;
        } else {
            tail.setNextNode(temp);
            temp.setPreviousNode(tail);
        }
        tail = temp;
        size++;
    }

    /**
     * Gets the value of a node by specified index
     * @param index Index value
     * @return      The value of a specified node of the list
     */
    public G get(int index) {
        checkIfInBounds(index);
        /* Go to the specified index from the head */
        Node<G> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getValue();
    }

    /**
     * Gets the value of the node from the head of the list without removing it
     * @return Node value
     */
    public G peekFirst() {
        return isEmpty() ? null : head.getValue();
    }

    /**
     * Gets the value of the node from the tail of the list without removing it
     * @return Node value
     */
    public G peekLast() {
        return isEmpty() ? null : tail.getValue();
    }

    /**
     * Gets the value of the node from the head of the list with removing it
     * @return Node value
     */
    public G pollFirst() {
        G valueToRemove = head.getValue();
        Node<G> NextNodeAfterHead = head.getNextNode();
        if (NextNodeAfterHead == null) {
            tail = null;
        } else {
            NextNodeAfterHead.setPreviousNode(null);
        }
        head = head.getNextNode();
        size--;
        return valueToRemove;
    }

    /**
     * Gets the value of the node from the tail of the list with removing it
     * @return Node value
     */
    public G pollLast() {
        G valueToRemove = tail.getValue();
        Node<G> nodeBeforeTail = tail.getPreviousNode();
        if (head.getNextNode() == null) {
            head = null;
        } else {
            nodeBeforeTail.setNextNode(null);
        }
        tail = tail.getPreviousNode();
        size--;
        return valueToRemove;
    }

    /**
     * Sets the value of a node by specified index
     * @param index Index value
     * @param value The value to set
     * @return      The value that was removed
     */
    public G set(int index, G value) {
        checkIfInBounds(index);
        G valueToReplace;
        Node<G> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        valueToReplace = currentNode.getValue();
        currentNode.setValue(value);
        return valueToReplace;

    }

    /**
     * Gets the the specified object index in the current list
     *
     * @return -1 if there is no such an object
     */
    public int indexOf(G value) {
        Node<G> nodeByIndex = head;
        if (value == null) {
            if (nodeByIndex.getValue() == null) {
                return 0;
            }
            /* Go through the whole list from the head */
            for (int i = 1; i < size; i++) {
                nodeByIndex = nodeByIndex.getNextNode();
                if (nodeByIndex.getValue() == null) {
                    return i;
                }
            }
        } else {
            /* If th specified index is not a null */
            if (nodeByIndex.getValue().equals(value)) {
                return 0;
            }
            /* Go through the whole list from the head */
            for (int i = 1; i < size; i++) {
                nodeByIndex = nodeByIndex.getNextNode();
                if (nodeByIndex.getValue().equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Removes the node of the specified list's index.
     * @param index index of a node to remove
     * @return      Removed node's value
     */
    public G remove(int index) {
        checkIfInBounds(index);
        Node<G> currentNode = head;
        if (index == 0) {
            return pollFirst();
        }
        if (index == size - 1) {
            return pollLast();
        }
        /* Go to the index from the head */
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        Node<G> nodeBeforeIndex = currentNode.getPreviousNode();
        Node<G> nodeAfterIndex = currentNode.getNextNode();
        /* Assign the links between nodes are after and before removed */
        nodeAfterIndex.setPreviousNode(nodeBeforeIndex);
        nodeBeforeIndex.setNextNode(nodeAfterIndex);
        size--;
        return currentNode.getValue();
    }

    /**
     * Gets the current size if the inner array of the list
     */
    public int size() {
        return size;
    }

    /**
     * Clears the list.
     * Sets its head and tail to null
     * Sets the current size of the inner array to zero size
     */
    public void clear() {
        if (size > 0) {
            head = null;
            tail = null;
            size = 0;
        }
    }

    /**
     * Checks if the list contains the specified value
     * @param value The value to check
     * @return      True if contains
     */
    public boolean contains(G value) {
        return indexOf(value) != -1;
    }

    public boolean remove(G value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    /**
     * Returns an array of nodes values from the current list.
     * @return Nodes values' array
     */
    public Object[] toArray() {
        Object[] tempArray = new Object[size];
        Node<G> currentNode = head;
        /* Go through the whole list from the head */
        for (int i = 0; i < size; i++) {
            tempArray[i] = currentNode.getValue();
            currentNode = currentNode.getNextNode();
        }
        return tempArray;
    }

    /**
     * Checks if the current list is empty
     * Null value of the head means the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Checks if the specified index is in the allowed range
     * @param index int of index
     * @throws      IndexOutOfBoundsException if index is not allowed
     */
    private void checkIfInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Iterator for public iteration using
     */
    public Iterator<G> iterator() {
        return new Iterator<>() {
            private Node<G> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public G next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                G value = currentNode.getValue();
                currentNode = currentNode.getNextNode();
                return value;
            }
        };
    }
}
