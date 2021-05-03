package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customQueue;

/**
 * A separate node of the custom queue
 * @param <G>
 */
public class Node<G> {
    /**
     * link to the next node in this queue
     */
    Node<G> next;

    /**
     * The current node's value
     */
    G value;

    public Node() {
    }

    /**
     * Gets the next node in this queue
     */
    public Node<G> getNext() {
        return next;
    }

    /**
     * Sets the next node in this queue
     */
    public void setNext(Node<G> next) {
        this.next = next;
    }

    /**
     * Gets the current node's value
     */
    public G getValue() {
        return value;
    }

    /**
     * Sets the current node's value
     */
    public void setValue(G value) {
        this.value = value;
    }
}
