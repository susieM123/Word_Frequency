/* Author: Susie Mueller
 * Purpose: Lab 6 Part 2
 * Date: 10/31/23
 * File: HashMap.java
 */

import java.util.ArrayList;

// The primary purpose of the HashMap class is to build a Hash Table.

public class HashMap<K, V> implements MapSet<K, V> {

    // Initialize fields for HashMap.
    private int size; 
    private Node<K,V>[] array; 
    private double maxLoadFactor; 
    private int capacity;
 
    // Need an inner Node class to make up the HashMap. Uses methods from KeyValuePairs class. 
    private static class Node<K, V> extends KeyValuePair<K, V> {

        // Initializes Node fields. 
        Node<K, V> next;

        // Constructor for Node. 
        public Node(K key, V value) {
            super(key, value); 
        }
    }

    // Default constructor.
    @SuppressWarnings("unchecked")
    public HashMap() {
        this.array = new Node[16]; 
        this.maxLoadFactor = 0.75;
    }

    // Constructor inputs capacity. 
    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.size = 0; 
        this.array = new Node[capacity]; 
        this.maxLoadFactor = 0.75;
    }

    // Contructor inputs capacity and loadFactor.
    public HashMap(int capacity, double loadFactor) {
        this.capacity = capacity; 
        this.maxLoadFactor = loadFactor; 
    }

    // Returns the length of the array handling this map. 
    private int capacity() {
        return array.length; 
    }

    // Returns the index of the underlying array in which the given key should be stored. 
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity());
    }

    // Returns the size.
    public int size() {
        return size; 
    }
    
    // Resets fields to default values.
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0; 
        array = new Node[16];
    }
    
    /* Associates the specified value with the specified key in this map. 
    If the map previously contained a mapping for the key, the old value is replaced. 
    Does nothing if value is null. 
    Returns the previous value associated with key, or null if there was no mapping for key. */
    public V put(K key, V value) {
        if (value == null) {
            return null;
        }

        int index = hash(key);
        Node<K, V> current = array[index];

        // Check if the key already exists in the chain
        while (current != null) {
            if (current.getKey().equals(key)) {
                V oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            }
            current = current.next;
        }

        // Key doesn't exist, add a new node to the chain
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = array[index];
        array[index] = newNode;
        size++;

        // Check if resizing is needed
        if ((double) size / capacity() > maxLoadFactor) {
            resize();
        }

        return null;
    }   
    
    // Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = array[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.next;
        }

        return null;
    }

    // Returns true if this map contains a mapping for the specified key to a value.
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    // Removes the specified key (and its associated value) from this mapping and returns the value it was mapped to.
    public V remove(K key) {
        int index = hash(key);
        Node<K, V> current = array[index];
        Node<K, V> previous = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (previous == null) {
                    array[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.getValue();
            }
            previous = current;
            current = current.next;
        }

        return null;
    }
    
    // Returns an ArrayList of all the keys in the map in no particular order.
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (Node<K, V> node : array) {
            Node<K, V> current = node;
            while (current != null) {
                keys.add(current.getKey());
                current = current.next;
            }
        }
        return keys;
    }
    
    // Returns an ArrayList of all the values in the map in the same order returned by keySet(). 
    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (Node<K, V> node : array) {
            Node<K, V> current = node;
            while (current != null) {
                values.add(current.getValue());
                current = current.next;
            }
        }
        return values;
    }
    
    // Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet().
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();
        for (Node<K, V> node : array) {
            Node<K, V> current = node;
            while (current != null) {
                entries.add(new KeyValuePair<>(current.getKey(), current.getValue()));
                current = current.next;
            }
        }
        return entries;
    }
    
    // Returns a String that represents the HashMap.
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (Node<K, V> node : array) {
            Node<K, V> current = node;
            while (current != null) {
                result.append("(").append(current.getKey()).append(", ").append(current.getValue()).append("), ");
                current = current.next;
            }
        }
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }
        result.append("]");
        return result.toString();
    }
    
    // Returns the size of the biggest bucket (the number of items in the bucket with the most items)
    public int maxDepth() {
        int maxDepth = 0;
        for (Node<K, V> node : array) {
            int depth = 0;
            Node<K, V> current = node;
            while (current != null) {
                depth++;
                current = current.next;
            }
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }

    // Resizes the HashTable. 
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity() * 2;
        Node<K, V>[] newArray = new Node[newCapacity];

        for (Node<K, V> node : array) {
            Node<K, V> current = node;
            while (current != null) {
                int newIndex = Math.abs(current.getKey().hashCode() % newCapacity);
                Node<K, V> temp = current.next;
                current.next = newArray[newIndex];
                newArray[newIndex] = current;
                current = temp;
            }
        }

        array = newArray;
    }
}
