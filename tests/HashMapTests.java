package com.shpp.p2p.cs.ekondratiuk.assignment17.tests;

import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.CustomArrayList;
import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customHashMap.CustomHashMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;


public class HashMapTests {

    /* Adds one element in the empty CustomHashMap. Checks if the added element key is the same */
    @Test
    public void execute01() {
        CustomHashMap<Integer, String> actual = new CustomHashMap<>();
        actual.put(1, "test");
        int test = actual.keySet().get(0);
        Assert.assertEquals(1, test);
    }

    /* Checks the CustomHashMap's size after adding 100 elements to it */
    @Test
    public void execute02() {
        CustomHashMap<Integer, Integer> actual = new CustomHashMap<>();
        for (int i = 0; i < 100; i++) {
            actual.put(i, i + 2);
        }
        Assert.assertEquals(100, actual.size());
    }

    /* Adds one element in the empty CustomHashMap. Checks for Exception if the index is wrong */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute03() {
        CustomHashMap<Integer, String> actual = new CustomHashMap<>();
        actual.put(1, "test");
        int test = actual.keySet().get(1);
        Assert.assertEquals(1, test);
    }

    /* Checks adding 10 times the same String element to the empty CustomHashMap */
    @Test
    public void execute04() {
        CustomHashMap<String, Integer> test = new CustomHashMap<>();
        for (int i = 0; i < 10; i++) {
            test.put("a", 10);
        }
        Assert.assertEquals(1, test.size());
    }

    /* Checks adding 10 times different String=>String items to the empty CustomHashMap */
    @Test
    public void execute05() {
        CustomHashMap<String, String> test = new CustomHashMap<>();
        for (int i = 0; i < 10; i++) {
            test.put("a" + i, "b");
        }
        Assert.assertEquals(10, test.size());
    }

    /* Checks adding double values to the CustomHashMap and clear from them */
    @Test
    public void execute06() {
        CustomHashMap<Double, String> test = new CustomHashMap<>();
        int initialSize = test.size();
        for (double i = 0; i < 100; i++) {
            test.put(Math.random() * i, "test");
        }
        test.clear();
        Assert.assertEquals(initialSize, test.size());
    }

    /* Checks if the current CustomHashMap without elements is empty */
    @Test
    public void execute07() {
        CustomHashMap<Integer, Integer> actual = new CustomHashMap<>();
        Assert.assertTrue(actual.isEmpty());
    }

    /* Checks if the CustomHashMap contains a not existing key. Returns false */
    @Test
    public void execute08() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", 77);
        actual.put("fest", 4);
        Assert.assertFalse(actual.containsKey("nest"));
    }

    /* Checks if the CustomHashMap contains a not existing value. Returns false */
    @Test
    public void execute09() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", 77);
        actual.put("fest", 4);
        Assert.assertFalse(actual.containsValue(24));
    }

    /* Checks if the CustomHashMap contains an existing key. Returns true */
    @Test
    public void execute10() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", 77);
        actual.put("fest", 4);
        Assert.assertTrue(actual.containsKey("best"));
    }

    /* Checks if the CustomHashMap contains an existing value. Returns true */
    @Test
    public void execute11() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", 77);
        actual.put("fest", 4);
        Assert.assertTrue(actual.containsValue(3));
    }

    /* Checks if the CustomHashMap contains an existing value as null. Returns true */
    @Test
    public void execute12() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", null);
        actual.put("fest", 4);
        Assert.assertTrue(actual.containsValue(null));
    }

    /* Checks if the CustomHashMap contains an existing key as null. Returns true */
    @Test
    public void execute13() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put(null, 100);
        actual.put("fest", 4);
        Assert.assertTrue(actual.containsKey(null));
    }

    /* Checks if the CustomHashMap doesn't contain value as null. Returns false */
    @Test
    public void execute14() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", 44);
        actual.put("fest", 4);
        Assert.assertFalse(actual.containsValue(null));
    }

    /* Checks if the CustomHashMap doesn't contain key as null. Returns false */
    @Test
    public void execute15() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("pest", 100);
        actual.put("fest", 4);
        Assert.assertFalse(actual.containsKey(null));
    }

    /* Checks getting the item key from the empty hashMap. Null expected */
    @Test
    public void execute16() {
        CustomHashMap<Integer, String> actual = new CustomHashMap<>();
        String real = actual.get(0);
        Assert.assertNull(real);
    }

    /* Checks for adding and removing many items */
    @Test
    public void execute17() {
        CustomHashMap<Integer, String> actual = new CustomHashMap<>();
        for (int i = 0; i < 50; i++) {
            actual.put(i, "test" + 1);
        }
        for (int i = 0; i < 25; i++) {
            if (actual.containsKey(i)) {
                actual.remove(i);
            }
        }
        Assert.assertEquals(25, actual.size());
    }

    /* Checks removing of not an existing key. Throws the NoSuchElementException */
    @Test(expected = NoSuchElementException.class)
    public void execute18() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", 77);
        actual.put("fest", 4);
        actual.remove("guest");
    }

    /* Checks iteration of values */
    @Test
    public void execute19() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        actual.put("test", 1);
        actual.put("zest", 3);
        actual.put("best", 77);
        actual.put("fest", 4);
        CustomArrayList<Integer> test = new CustomArrayList<>(Arrays.asList(1, 77, 3, 4));
        boolean flag = true;
        for (int val : actual.valueSet()) {
            if (!test.contains(val)) {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    /* Checks big amount entries processing */
    @Test
    public void execute20() {
        CustomHashMap<String, Integer> actual = new CustomHashMap<>();
        int size = 1000000;
        for (int i = 0; i < size; i++) {
            actual.put("test" + i, i);
        }
        actual.remove("test0");
        actual.put("test0", 0);
        Assert.assertEquals(size, actual.size());
    }

}
