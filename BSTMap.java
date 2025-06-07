/* Author: Susie Mueller
 * Purpose: Lab 6 Part 1
 * Date: 10/30/23
 * File: BSTMap.java
 */


import java.util.ArrayList;
import java.util.Comparator;

// The purpose of this class is to build a Binary Search Tree.

public class BSTMap<K,V> implements MapSet<K,V> {

    // Need an inner Node class to make up the BST. Uses methods from KeyValuePairs class. 
    private static class Node<K, V> extends KeyValuePair<K, V> {

        // Initializes Node fields. 
        Node<K, V> left; 
        Node<K, V> right; 

        // Node constructor. 
        public Node(K key, V value) {
            super(key, value); // Calls constructor of parent class. 
            left = null; 
            right = null; 
        }
    }

    // Initializes BTSMap fields.
    private Node<K,V> root;
    private int size; 
    private Comparator<K> comparator;


    // Constructor sets comparator to null.
    public BSTMap() {
        this(null);
    }

    @SuppressWarnings("unchecked")
    // Constructor creates comparator.
    public BSTMap(Comparator<K> comparator) {
        this.root = null; 
        this.size = 0;
        if (comparator != null) {
            this.comparator = comparator;
        } else { // Creates a new Comparator<K>
            this.comparator = (o1, o2) -> ((Comparable<K>) o1).compareTo(o2);
        }
    }


    // Returns the size.
    public int size() {
        return size; 
    }


    // Resets fields to default values. 
    public void clear() {
        root = null; 
        size = 0; 
    }


    // Associates the specified value with the specified key in this map. 
    // If the map previously contained a mapping for the key, the old value is replaced.
    public V put(K key, V value) {
        if (value == null) { 
            return null; 
        }
        if (root == null) { 
            root = new Node<>(key, value); // Creates a new root with given key and value.
            size++; // Increases size of tree. 
            return null;
        }
        return put(key, value, root); // Calls recursive helper method to insert the key and value.
    }


    // Recursive helper function for put method.
    private V put(K key, V value, Node<K, V> cur) {
        int comparison = comparator.compare(key, cur.getKey()); // Compares given key with current Node's key.
        
        // If key is less than current Node's key, traverse the left subtree.
        if (comparison < 0) { 
            if (cur.left == null) { // If there is no left child...
                cur.left = new Node<>(key, value); // Create a new Node with given key and value.
                size++;
                return null;
            } else {
                return put(key, value, cur.left); // Recursively traverses left subtree.
            }
        
        // If the key is greater than the current node's key, traverse the right subtree.
        } else if (comparison > 0) { 
            if (cur.right == null) { // If there is no right child...
                cur.right = new Node<>(key, value); // Create a new Node with given key and value. 
                size++;
                return null;
            } else {
                return put(key, value, cur.right); // Recursively traverses right subtree.
            }
        
        // If the key is equal to the current node's key, update the value and return the old value.
        } else {
            V oldValue = cur.getValue();
            cur.setValue(value);
            return oldValue;
        }
    }


    // Returns the value to which the specified key is mapped.
    public V get(K key) {
        return get(key, root);
    }


    // Recrusive helper function for get method.
    private V get(K key, Node<K, V> cur) {
        if (cur == null) {
            return null; // Key is not in tree.
        }
        int comparison = comparator.compare(key, cur.getKey());

        // If the key is smaller, search in the left subtree.
        if (comparison < 0) {
            return get(key, cur.left); 

        // If the key is larger, search in the right subtree. 
        } else if (comparison > 0) {
            return get(key, cur.right); 
        
        // Key found and returns its value. 
        } else {
            return cur.getValue(); 
        }
    }


    // Returns true if this map contains a mapping for the specified key to a value. 
    public boolean containsKey(K key) {
        return get(key) != null;
    }


    // Returns an ArrayList of all the keys in the map in sorted order from least to greatest.
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>(); // Creates arrayList.
        keySet(root, keys); // Calls helper function.
        return keys; // Returns arrayList of keys.
    }


    // Recursive helper function for keySet method.
    private void keySet(Node<K, V> cur, ArrayList<K> output) {
        if (cur == null) {
            return;
        }
        keySet(cur.left, output); // Recursively explores left subtree.
        output.add(cur.getKey()); // Adds current node's key to output list.
        keySet(cur.right, output); // Recursively explores right subtree.
    }


    // Returns an ArrayList of all the values in the map in the same order returned by keySet().
    public ArrayList<V> values() {
        ArrayList<K> keys = keySet(); // Gets the keys in sorted order.
        ArrayList<V> values = new ArrayList<>(); // Creates arrayList called values.
        for (K key : keys) {
            values.add(get(key)); // Retrieves values for each key in sorted order.
        }
        return values; // Returns list of values.
    }


    // Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet().
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<K> keys = keySet(); // Gets the keys in sorted order.
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>(); // Creates new arrayList.
        for (K key : keys) {
            entries.add(new KeyValuePair<>(key, get(key))); // Creates KeyValuePairs in sorted order.
        }
        return entries; // Returns list of KeyValuePairs.
    }


    // Returns a String that represents the BSTMap accurately depicting the levels.
    public String toString() {
        if (root == null) {
            return "Empty BSTMap";
        }
        StringBuilder builder = new StringBuilder();
        toString(root, builder, 0); // Calls helper method.
        return builder.toString();
    }


    // Helper function for toString method. 
    private void toString(Node<K, V> cur, StringBuilder builder, int level) {
        if (cur != null) {
            toString(cur.right, builder, level + 1);
            for (int i = 0; i < level; i++) {
                builder.append("  "); // Add indentation for the current level.
            }
            builder.append(cur.toString()).append("\n"); // Append the current node.
            toString(cur.left, builder, level + 1);
        }
    }


    // Returns the length of a maximal root to leaf path. 
    public int maxDepth() {
        return maxDepth(root);
    }


    // Recursive helper function for maxDepth method. 
    private int maxDepth(Node<K, V> cur) {
        if (cur == null) {
            return 0;
        }
        int leftDepth = maxDepth(cur.left);
        int rightDepth = maxDepth(cur.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


    // Removes the given key from the structure and returns whatever value is currently associated with it.
    public V remove(K key) {
        Node<K, V> parent = null;
        Node<K, V> toDelete = root;
    
        // Find the Node to remove.
        while (toDelete != null) {
            int comparison = comparator.compare(key, toDelete.getKey());
            if (comparison == 0) {
                // Key found, call handleReplacement.
                V removedValue = toDelete.getValue();
                handleReplacement(toDelete, parent);
                size--;
                return removedValue;
            }
            parent = toDelete;
            if (comparison < 0) {
                toDelete = toDelete.left;
            } else {
                toDelete = toDelete.right;
            }
        }
    
        return null; // Key not found.
    }


    // Recursive helper function for the remove method. 
    private void handleReplacement(Node<K,V> toDelete, Node<K,V> toDeleteParent) {
        Node<K, V> replacement;

        if (toDelete.left == null) { // If there is no left child.
            replacement = toDelete.right;

        } else if (toDelete.right == null) { // If there is no right child.
            replacement = toDelete.left;

        } else { // We'll have to find it.

            Node<K, V> replacementParent = toDelete;
            replacement = toDelete.right; // Sets replacement to be the next largest Node.

            while (replacement.left != null) { 
                replacementParent = replacement;
                replacement = replacement.left;
            }
    
            // Recursively call handleReplacement on this Node.
            handleReplacement(replacement, replacementParent);
            replacement.left = toDelete.left;
            replacement.right = toDelete.right;
        }

        // Update toDeleteParent's child (whichever one is currently toDelete) to be replacement.
        if (toDeleteParent == null) {
            root = replacement;
        } else if (toDelete == toDeleteParent.left) {
            toDeleteParent.left = replacement;
        } else {
            toDeleteParent.right = replacement;
        }

        size--; // Decreases size by one.
    }


    public static void main(String[] args) {
        // this will sort the strings lexicographically (dictionary-order)
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);
        System.out.println(words);
    
        // this will sort the strings in reverse lexicographic order
        BSTMap<String, Integer> wordsReverse = new BSTMap<>(new Comparator<String>() {
    
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
    
        });
        wordsReverse.put("ten", 10);
        wordsReverse.put("five", 5);
        wordsReverse.put("three", 3);
        System.out.println(wordsReverse);
    }
}