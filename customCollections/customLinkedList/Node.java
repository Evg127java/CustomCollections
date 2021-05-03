package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customLinkedList;

public class Node<G> {
    private G value;
    /**
     * The next node in the queue.
     */
    private Node<G> nextNode;
    /**
     * The previous node in the queue.
     */
    private Node<G> previousNode;

    /**
     * Constructor for creating a node.
     * The new node must contain the value itself and links
     * to the next and previous nodes from it
     */
    public Node(Node<G> previous, G value, Node<G> next) {
        this.value = value;
        this.nextNode = next;
        this.previousNode = previous;
    }

    /**
     * Gets the current node's value
     * @return Node's value of Generic type
     */
    public G getValue() {
        return value;
    }

    /**
     * Sets the current node's value
     * @param value the value of Generic type
     */
    public void setValue(G value) {
        this.value = value;
    }

    /**
     * Gets the previous node of the current one
     */
    public Node<G> getPreviousNode() {
        return previousNode;
    }

    /**
     * Sets the previous node of the current one
     */
    public void setPreviousNode(Node<G> previousOne) {
        this.previousNode = previousOne;
    }

    /**
     * Gets the next node of the current one
     */
    public Node<G> getNextNode() {
        return nextNode;
    }

    /**
     * Sets the previous node of the current one
     */
    public void setNextNode(Node<G> nextOne) {
        this.nextNode = nextOne;
    }

    /**
     * Return a string representation of the contents of the specified node.
     *
     * @return A string representation of node.
     */
    public String toString() {
        return value.toString();
    }
}
