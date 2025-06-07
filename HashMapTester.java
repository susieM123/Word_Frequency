/* Author: Susie Mueller
 * Purpose: Project 6 
 * Date: 11/11/23
 * File: HashMapTester.java
 */


// The purpose of this class is to test the HashMap class. 
// Notes on how to compile and run are at the bottom. 

public class HashMapTester {
    
    public static void main(String[] args) {

        // case 1: testing HashMap() default constructor
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();

            // verify
            System.out.println("\nTesting HashMap() default constructor.");
            System.out.println(map.size() + " == 0");

            // test
            assert map.size() == 0 : "Error in HashMap::HashMap()";
        }

        // case 2: testing put(K key, V value)
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);

            // verify
            System.out.println("\nTesting put(K key, V value).");
            System.out.println(map.size() + " == 3");

            // test
            assert map.size() == 3 : "Error in HashMap::put(K key, V value)";
        }

        // case 3: testing get(K key)
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);

            // verify
            System.out.println("\nTesting get(K key). ");
            System.out.println(map.get("one") + " == 1");
            System.out.println(map.get("two") + " == 2");
            System.out.println(map.get("three") + " == null");

            // test
            assert map.get("one") == 1 : "Error in HashMap::get(K key)";
            assert map.get("two") == 2 : "Error in HashMap::get(K key)";
            assert map.get("three") == null : "Error in HashMap::get(K key)";
        }

        // case 4: testing containsKey(K key)
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);

            // verify
            System.out.println("\nTesting containsKey(K key).");
            System.out.println(map.containsKey("one") + " == true");
            System.out.println(map.containsKey("three") + " == false");

            // test
            assert map.containsKey("one") : "Error in HashMap::containsKey(K key)";
            assert !map.containsKey("three") : "Error in HashMap::containsKey(K key)";
        }

        // case 5: testing remove(K key)
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);

            int removedValue = map.remove("one");

            // verify
            System.out.println("\nTesting remove(K key).");
            System.out.println(removedValue + " == 1");
            System.out.println(map.size() + " == 1");

            // test
            assert removedValue == 1 : "Error in HashMap::remove(K key)";
            assert map.size() == 1 : "Error in HashMap::remove(K key)";
        }

        // case 6: testing keySet()
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);

            // verify
            System.out.println("\nTesting keySet().");
            System.out.println(map.keySet().size() + " == 3");
            System.out.println(map.keySet().contains("one") + " == true");
            System.out.println(map.keySet().contains("four") + " == false");

            // test
            assert map.keySet().size() == 3 : "Error in HashMap::keySet()";
            assert map.keySet().contains("one") : "Error in HashMap::keySet()";
            assert !map.keySet().contains("four") : "Error in HashMap::keySet()";
        }

        // case 7: testing values()
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);

            // verify
            System.out.println("\nTesting values().");
            System.out.println(map.values().size() + " == 3");
            System.out.println(map.values().contains(2) + " == true");
            System.out.println(map.values().contains(4) + " == false");

            // test
            assert map.values().size() == 3 : "Error in HashMap::values()";
            assert map.values().contains(2) : "Error in HashMap::values()";
            assert !map.values().contains(4) : "Error in HashMap::values()";
        }

        // case 8: testing entrySet()
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);

            // verify
            System.out.println("\nTesting entrySet().");
            System.out.println(map.entrySet().size() + " == 3");

            // test
            assert map.entrySet().size() == 3 : "Error in HashMap::entrySet()";
        }

        // case 9: testing clear()
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            map.clear();

            // verify
            System.out.println("\nTesting clear().");
            System.out.println(map.size() + " == 0");

            // test
            assert map.size() == 0 : "Error in HashMap::clear()";
        }

        // case 10: testing maxDepth()
        {
            // setup
            HashMap<String, Integer> map = new HashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);
            map.put("four", 4);
            map.put("five", 5);
            map.put("six", 6);
            map.put("seven", 7);

            // verify
            System.out.println("\nTesting maxDepth().");
            System.out.println(map.maxDepth() + " == 2");

            // test
            assert map.maxDepth() == 2 : "Error in HashMap::maxDepth()";
        }
    }
}


/* 
 * How to compile: javac HashMapTester.java
 * How to run: java HashMapTester
 */