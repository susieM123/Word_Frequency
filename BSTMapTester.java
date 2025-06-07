/* Author: Susie Mueller
 * Purpose: Lab 6 Part 1 Tester File
 * Date: 10/30/23
 * File: BSTMapTester.java
 */


// The purpose of this class is to test the BSTMap class.
// Notes on how to compile and run are at the bottom. 

public class BSTMapTester {

    public static void main(String[] args) {

    // Case 1: testing size() and clear()
    {
        // setup
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        bstMap.put("one", 1);
        bstMap.put("two", 2);
        bstMap.put("three", 3);

        // verify
        System.out.println(bstMap.size() + " == 3");

        // test
        assert bstMap.size() == 3 : "Error in BSTMap::size()";

        // clear the map
        bstMap.clear();

        // verify
        System.out.println(bstMap.size() + " == 0");

        // test
        assert bstMap.size() == 0 : "Error in BSTMap::clear()";
    }

        // Case 1: Testing put() and get()
    {
        // Setup
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Verify
        System.out.println(map.size() + " == 3");

        // Test
        assert map.size() == 3 : "Error in BSTMap::put() or BSTMap::size()";

        // Verify get()
        System.out.println(map.get("one") + " == 1");
        System.out.println(map.get("two") + " == 2");
        System.out.println(map.get("three") + " == 3");

        // Test get()
        assert map.get("one") == 1 : "Error in BSTMap::get()";
        assert map.get("two") == 2 : "Error in BSTMap::get()";
        assert map.get("three") == 3 : "Error in BSTMap::get()";
    }

    // Case 2: Testing remove()
    {
        // Setup
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Verify
        System.out.println(map.size() + " == 3");

        // Test
        assert map.size() == 3 : "Error in BSTMap::put() or BSTMap::size()";

        // Remove a key
        int removedValue = map.remove("two");

        // Verify removal
        System.out.println(removedValue + " == 2");
        System.out.println(map.size() + " == 2");

        // Test removal
        assert removedValue == 2 : "Error in BSTMap::remove()";
        assert map.size() == 2 : "Error in BSTMap::remove() or BSTMap::size()";
    }

    // Case 3: testing containsKey()
    {
        // setup
        BSTMap<String, Double> bstMap = new BSTMap<>();
        bstMap.put("pi", 3.14);
        bstMap.put("e", 2.71);
        bstMap.put("phi", 1.618);

        // verify
        System.out.println(bstMap.containsKey("e") + " == true");
        System.out.println(bstMap.containsKey("sqrt2") + " == false");

        // test
        assert bstMap.containsKey("e") : "Error in BSTMap::containsKey()";
        assert !bstMap.containsKey("sqrt2") : "Error in BSTMap::containsKey()";
    }

    // Case 4: Testing keySet()
    {
        // Setup
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Verify
        System.out.println(map.size() + " == 3");

        // Test
        assert map.size() == 3 : "Error in BSTMap::put() or BSTMap::size()";

        // Verify keySet()
        System.out.println(map.keySet() + " contains [one, three, two]");

        // Test keySet()
        assert map.keySet().contains("one") : "Error in BSTMap::keySet()";
        assert map.keySet().contains("two") : "Error in BSTMap::keySet()";
        assert map.keySet().contains("three") : "Error in BSTMap::keySet()";
    }

    // Case 5: testing values()
    {
        // setup
        BSTMap<Integer, String> bstMap = new BSTMap<>();
        bstMap.put(1, "one");
        bstMap.put(2, "two");
        bstMap.put(3, "three");

        // verify
        System.out.println(bstMap.values() + " == [one, three, two]");

        // test
        assert bstMap.values().toString().equals("[one, three, two]") : "Error in BSTMap::values()";
    }

    System.out.println("Done testing BSTMap!");

    }

    // case 6: testing entrySet()
    {
        // setup
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        bstMap.put("one", 1);
        bstMap.put("two", 2);
        bstMap.put("three", 3);

        // verify
        System.out.println(bstMap.entrySet() + " == [{one=1}, {three=3}, {two=2}]");

        // test
        assert bstMap.entrySet().toString().equals("[{one=1}, {three=3}, {two=2}]") : "Error in BSTMap::entrySet()";
    }

    // case 7: testing remove()
    {
        // setup
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        bstMap.put("one", 1);
        bstMap.put("two", 2);
        bstMap.put("three", 3);

        // remove an existing key
        Integer removedValue = bstMap.remove("two");

        // verify
        System.out.println(removedValue + " == 2");

        // test
        assert removedValue.equals(2) : "Error in BSTMap::remove()";

        // remove a non-existing key
        removedValue = bstMap.remove("four");

        // verify
        System.out.println(removedValue + " == null");

        // test
        assert removedValue == null : "Error in BSTMap::remove()";
    }

    // case 8: testing maxDepth()
    {
        // setup
        BSTMap<Integer, String> bstMap = new BSTMap<>();
        bstMap.put(50, "Fifty");
        bstMap.put(30, "Thirty");
        bstMap.put(70, "Seventy");
        bstMap.put(20, "Twenty");
        bstMap.put(40, "Forty");
        bstMap.put(60, "Sixty");
        bstMap.put(80, "Eighty");

        // verify
        System.out.println(bstMap.maxDepth() + " == 4");

        // test
        assert bstMap.maxDepth() == 4 : "Error in BSTMap::maxDepth()";
    }
}

/* 
 * How to Compile: javac BSTMapTester.java
 * How to Run: java BSTMapTester
 */