package com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customHashMap;

import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.CustomArrayList;

import java.util.NoSuchElementException;

import static com.shpp.p2p.cs.ekondratiuk.assignment17.Constants.*;

/**
 * Custom HashMap class.
 */
public class CustomHashMap<K, V> {
    private int size;
    private int buckets;
    private MapNode<K, V>[] map;

    /**
     * Constructor without parameters
     * Uses default capacity value
     */
    public CustomHashMap() {
        this(DEFAULT_HASHMAP_CAPACITY_VALUE);
    }

    /**
     * Constructor for the case with capacity value's parameter
     */
    @SuppressWarnings("unchecked")
    public CustomHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        map = new MapNode[capacity];
    }

    /**
     * Adds th item as an entry to this hashMap
     *
     * @param key   Key of the item
     * @param value Item's value
     */
    public void put(K key, V value) {
        /* Rehash the map if the free space's size is critical */
        if (buckets > map.length * HASHMAP_OVERLOAD_VALUE) {
            enlargeMapSize();
        }
        int index;
        int hash = hash(key);
        MapNode<K, V> mapNodeToPut = new MapNode<>(key, value, hash);
        index = getIndexInMap(mapNodeToPut.getKey());
        /* If the target map cell is empty */
        if (processEmptyMapCell(index, mapNodeToPut)) {
            return;
        }
        MapNode<K, V> currentNodeFromMap = map[index];

        /* Bucket collision processing */
        while (currentNodeFromMap.getNextMapNode() != null) {
            if (currentNodeFromMap.getHash() != hash) {
                currentNodeFromMap = currentNodeFromMap.getNextMapNode();
                continue;
            }
            if (key.equals(currentNodeFromMap.getKey())) {
                currentNodeFromMap.setValue(mapNodeToPut.getValue());
                return;
            }
        }
        processLastNodeInBucket(mapNodeToPut, currentNodeFromMap);
    }

    /**
     * Adds the node to the empty cell in the map
     *
     * @param index        Map index to put the node
     * @param mapNodeToPut Node to put
     * @return True if the empty cell was processed
     */
    private boolean processEmptyMapCell(int index, MapNode<K, V> mapNodeToPut) {
        if (map[index] == null) {
            map[index] = mapNodeToPut;
            size++;
            buckets++;
            return true;
        }
        return false;
    }

    /**
     * Processes the last(only one) node in a map cell(bucket)
     * Compares two nodes:
     * One node is formed from input data
     * The other one is from the map with the same index to put
     *
     * @param mapNodeToPut       Node which must be put in this hashMap
     * @param currentNodeFromMap Node from a calculated index of the map
     */
    private void processLastNodeInBucket(MapNode<K, V> mapNodeToPut, MapNode<K, V> currentNodeFromMap) {
        if (currentNodeFromMap.getHash() != mapNodeToPut.getHash()) {
            currentNodeFromMap.setNextMapNode(mapNodeToPut);
            size++;
            return;
        }
        if (mapNodeToPut.getKey().equals(currentNodeFromMap.getKey())) {
            currentNodeFromMap.setValue(mapNodeToPut.getValue());
            return;
        }
        currentNodeFromMap.setNextMapNode(mapNodeToPut);
        size++;
    }

    /**
     * Gets the value by the specified key
     *
     * @param key Key of the item
     * @return Value of the item by the specified key
     * Null if the item with the specified key is absent
     */
    public V get(K key) {
        int index;
        index = getIndexInMap(key);
        MapNode<K, V> current = map[index];
        while (current != null) {
            if (current.getHash() == hash(key)) {
                if (key.equals(current.getKey())) {
                    return current.getValue();
                }
            } else {
                current = current.getNextMapNode();
            }
        }
        return null;
    }

    /**
     * Checks if this hashMap has the specified key
     *
     * @param key Key to check
     * @return True if the key is in this map
     */
    public boolean containsKey(K key) {
        MapNode<K, V> mapNode = map[getIndexInMap(key)];
        if (mapNode == null) {
            return false;
        }
        do {
            /* Process cases if the specified key is null or not */
            boolean isKeyEqual = key == null ? mapNode.getKey() == null : key.equals(mapNode.getKey());
            if (isKeyEqual) {
                return true;
            }
        } while ((mapNode = mapNode.getNextMapNode()) != null);
        return false;
    }

    /**
     * Checks if this hashMap has the specified value
     *
     * @param value Value to check
     * @return True if the value is in this map
     */
    public boolean containsValue(V value) {
        for (MapNode<K, V> mapNode : map) {
            /* ignore if the current item in the map is null */
            if (mapNode == null) {
                continue;
            }
            do {
                /* Process cases if the specified value is null or not */
                boolean isValueEqual =
                        value == null ? mapNode.getValue() == null : value.equals(mapNode.getValue());
                if (isValueEqual) {
                    return true;
                }
            } while ((mapNode = mapNode.getNextMapNode()) != null);
        }
        return false;
    }

    /**
     * Removes the item by the specified index from this map
     *
     * @param key Key of the item to remove
     * @throws NoSuchElementException if this map doesn't contain the item with the specified key
     */
    public void remove(K key) {
        if (!containsKey(key)) {
            throw new NoSuchElementException();
        }
        int hash = hash(key);
        int index = key == null ? hash : getIndexInMap(key);
        MapNode<K, V> currentMapNode = map[index];
        MapNode<K, V> prevMapNode = null;
        /* Go deeper in a bucket until the null */
        while (currentMapNode != null) {
            boolean isHashEqual = currentMapNode.getHash() == hash;
            if (!isHashEqual) {
                prevMapNode = currentMapNode;
                currentMapNode = currentMapNode.getNextMapNode();
                continue;
            }
            boolean isKeyEqual =
                    key == null ? currentMapNode.getKey() == null : key.equals(currentMapNode.getKey());
            if (isKeyEqual) {
                if (prevMapNode == null) {
                    map[index] = null;
                    buckets--;
                } else {
                    prevMapNode.setNextMapNode(currentMapNode.getNextMapNode());
                }
                size--;
                return;
            }
            prevMapNode = currentMapNode;
            currentMapNode = currentMapNode.getNextMapNode();
        }
    }

    /**
     * Returns Items list of this hashMap for iteration
     */
    public CustomArrayList<MapNode<K, V>> entrySet() {
        CustomArrayList<MapNode<K, V>> entryList = new CustomArrayList<>();
        for (MapNode<K, V> mapNode : map) {
            if (mapNode == null) {
                continue;
            }
            do {
                entryList.add(mapNode);
            } while ((mapNode = mapNode.getNextMapNode()) != null);
        }
        return entryList;
    }

    /**
     * Returns Keys list of this hashMap for iteration
     */
    public CustomArrayList<K> keySet() {
        CustomArrayList<K> keyList = new CustomArrayList<>();
        for (MapNode<K, V> mapNode : map) {
            if (mapNode == null) {
                continue;
            }
            do {
                keyList.add(mapNode.getKey());
            } while ((mapNode = mapNode.getNextMapNode()) != null);
        }
        return keyList;
    }

    /**
     * Returns Values list of this hashMap for iteration
     */
    public CustomArrayList<V> valueSet() {
        CustomArrayList<V> valueList = new CustomArrayList<>();
        for (MapNode<K, V> mapNode : map) {
            if (mapNode == null) {
                continue;
            }
            do {
                valueList.add(mapNode.getValue());
            } while ((mapNode = mapNode.getNextMapNode()) != null);
        }
        return valueList;
    }


    /**
     * Rehashes the items in this map.
     * Enlarges the map capacity
     * Recalculates new hashes and map's indexes to rearrange the items in the map
     */
    @SuppressWarnings("unchecked")
    private void enlargeMapSize() {
        size = 0;
        buckets = 0;
        MapNode<K, V>[] oldMap = map;
        map = new MapNode[oldMap.length * 2];
        for (MapNode<K, V> mapNode : oldMap) {
            if (mapNode == null) {
                continue;
            }
            do {
                addMapNode(mapNode.getKey(), mapNode.getValue());
            } while ((mapNode = mapNode.getNextMapNode()) != null);

        }
    }

    /**
     * Adds an item to the inner hashMap's array.
     *
     * @param key   Item's key.
     * @param value Item's value.
     */
    private void addMapNode(K key, V value) {
        int index = getIndexInMap(key);
        MapNode<K, V> temp = new MapNode<>(key, value, hash(key));
        if (map[index] == null) {
            map[index] = temp;
            buckets++;
        } else {
            MapNode<K, V> current = map[index];
            while (current.getNextMapNode() != null) {
                current = current.getNextMapNode();
            }
            current.setNextMapNode(temp);
        }
        size++;
    }

    /**
     * Gets the map's index for the item
     *
     * @param key Item's key
     * @return Index value to put in the map
     */
    private int getIndexInMap(Object key) {
        return hash(key) & map.length - 1;
    }

    /**
     * Calculates the key's hash
     *
     * @param key Specified key
     * @return Calculated hash value
     */
    private int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Returns the string representation of this map content.
     *
     * @return string representation of this hash map.
     */
    public String toString() {
        CustomArrayList<MapNode<K, V>> list = new CustomArrayList<>();

        for (MapNode<K, V> mapNode : map) {
            if (mapNode != null) {
                do {
                    list.add(mapNode);
                } while ((mapNode = mapNode.getNextMapNode()) != null);
            }
        }
        return list.toString();
    }

    /**
     * Returns the items(key => value) number in this map.
     *
     * @return The number of items(key => value).
     */
    public int size() {
        return size;
    }

    /**
     * Removes all the items from this map.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        if (size > 0) {
            size = 0;
            buckets = 0;
            /* New empty inner array's reinitialization */
            map = new MapNode[map.length];
        }
    }

    /**
     * Checks if this hashMap has any items.
     *
     * @return True if this map contains no items.
     */
    public boolean isEmpty() {
        return size == 0;
    }

}