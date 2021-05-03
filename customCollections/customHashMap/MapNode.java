package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customHashMap;

/**
 * HashMap Node's implementing
 */
public class MapNode<K, V> {
    private final K key;
    private V value;
    private final int hash;
    /**
     * Next MapNode
     */
    private MapNode<K, V> nextMapNode = null;

    /**
     * Constructor
     */
    public MapNode(K key, V value, int hash) {
        this.key = key;
        this.value = value;
        this.hash = hash;
    }

    /**
     * Gets the MapNode's key
     */
    public K getKey() {
        return key;
    }

    /**
     * Gets the MapNode's key's hash
     */
    public int getHash() {
        return hash;
    }

    /**
     * Gets the MapNode's value
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the MapNode's value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Sets the MapNode's next Node
     */
    public void setNextMapNode(MapNode<K, V> mapNode) {
        this.nextMapNode = mapNode;
    }

    /**
     * Gets the MapNode's next Node
     */
    public MapNode<K, V> getNextMapNode() {
        return nextMapNode;
    }

    /**
     * Gets the MapNode's content({key, value}) as a string.
     */
    public String toString() {
        return "{" + key + " => " + value + "}";
    }
}

