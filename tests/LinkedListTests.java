package com.shpp.p2p.cs.ekondratiuk.assignment17.tests;

import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customLinkedList.CustomLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LinkedListTests {

    /* Checks the list size after adding as first many elements to it */
    @Test
    public void execute01() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            actual.addFirst("test");
        }
        Assert.assertEquals(1000, actual.size());
    }

    /* Checks the list size after adding as last many elements to it */
    @Test
    public void execute02() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            actual.addLast("test");
        }
        Assert.assertEquals(1000, actual.size());
    }

    /* Checks multiply element adding to the beginning and to the end of the list */
    @Test
    public void execute03() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            actual.addLast("test");
            actual.addFirst("test");
        }
        Assert.assertEquals(2000, actual.size());
    }

    /* Checks multiply element adding in cycles to the beginning and to the end of the list */
    @Test
    public void execute04() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        for (int i = 0; i < 2000; i++) {
            actual.addFirst("test");
        }
        for (int i = 0; i < 1000; i++) {
            actual.addLast("test");
        }
        Assert.assertEquals(3000, actual.size());
    }

    /* Checks adding as last many elements to the list's end and clearing of them */
    @Test
    public void execute05() {
        CustomLinkedList<Double> test = new CustomLinkedList<>();
        int initialSize = test.size();
        for (double i = 0; i < 100; i++) {
            test.addLast(i);
        }
        test.clear();
        Assert.assertEquals(initialSize, test.size());
    }

    /* Checks if the method returns false if the specified value is not in the list */
    @Test
    public void execute06() {
        CustomLinkedList<Double> actual = new CustomLinkedList<>();
        actual.addLast(0.2);
        actual.addLast(2.45);
        actual.addLast(3.44);
        actual.addLast(56.0);
        double value = 0.7;
        Assert.assertFalse(actual.contains(value));
    }

    /* Checks if the method returns true if the specified value is in the list */
    @Test
    public void execute07() {
        CustomLinkedList<Double> actual = new CustomLinkedList<>();
        actual.addLast(0.2);
        actual.addLast(2.45);
        actual.addLast(3.44);
        actual.addLast(56.0);
        double value = 3.44;
        Assert.assertTrue(actual.contains(value));
    }

    /* Checks peeking of the first element from the list(without removing) */
    @Test
    public void execute08() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("test");
        actual.addLast("best");
        actual.addLast("west");
        actual.addLast("zest");
        String first = actual.peekFirst();
        Assert.assertEquals("test", first);
    }

    /* Checks peeking of the last element from the list(without removing) */
    @Test
    public void execute09() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("test");
        actual.addLast("best");
        actual.addLast("west");
        actual.addLast("zest");
        String first = actual.peekLast();
        Assert.assertEquals("zest", first);
    }

    /* Checks getting index of existing value in the list */
    @Test
    public void execute10() {
        CustomLinkedList<Double> actual = new CustomLinkedList<>();
        actual.addLast(0.2);
        actual.addLast(2.45);
        actual.addLast(3.44);
        actual.addLast(56.0);
        double value = 3.44;
        int index = 2;
        Assert.assertEquals(index, actual.indexOf(value));
    }

    /* Checks getting index of not existing value in the list.
     * Must return -1 */
    @Test
    public void execute11() {
        CustomLinkedList<Double> actual = new CustomLinkedList<>();
        actual.addLast(0.2);
        actual.addLast(2.45);
        actual.addLast(3.44);
        actual.addLast(56.0);
        double value = 6;
        actual.indexOf(value);
        Assert.assertEquals(-1, actual.indexOf(value));
    }

    /* Checks getting value from the list by not allowed index
     * Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute12() {
        CustomLinkedList<Double> actual = new CustomLinkedList<>();
        actual.addLast(0.2);
        actual.addLast(2.45);
        actual.addLast(3.44);
        actual.addLast(56.0);
        int index = actual.size() + 1;
        actual.get(index);
    }

    /* Checks getting an existing value from the list by an allowed index */
    @Test
    public void execute13() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        int index = 2;
        String gotten = actual.get(index);
        Assert.assertEquals("d", gotten);
    }

    /* Checks getting a value from the empty list by zero index
     * Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute14() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        int index = 0;
        String gotten = actual.get(index);
        Assert.assertNull(gotten);
    }

    /* Checks setting a value to the  empty list by zero index
     * Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute15() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        int index = 0;
        actual.set(index, "d");
    }

    /* Checks setting a value to the list by allowed index */
    @Test
    public void execute16() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        int index = 3;
        String symbolToSet = "c";
        actual.set(index, symbolToSet);
        String gotten = actual.get(index);
        Assert.assertEquals(symbolToSet, gotten);
    }

    /* Checks if set method returns replaced value */
    @Test
    public void execute17() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        int index = 3;
        String symbolToSet = "c";
        String gotten = actual.get(index);
        Assert.assertEquals(gotten, actual.set(index, symbolToSet));
    }

    /* Checks removing the value from the list by a specified allowed index */
    @Test
    public void execute18() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        int index = 3;
        String gotten = actual.get(index);
        String removed = actual.remove(index);
        Assert.assertEquals(gotten, removed);
    }

    /* Checks removing a value from the list by a specified not allowed index
     * Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute19() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        int index = -1;
        actual.get(index);
        actual.remove(index);
    }

    /* Checks removing a value by a specified index from the empty list
     * Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute20() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        int index = 0;
        actual.get(index);
        actual.remove(index);
    }

    /* Checks the list size after several elements removing from the list */
    @Test
    public void execute21() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        int initSize = actual.size();
        int counter = 0;
        actual.remove(actual.size() - 1);
        counter++;
        actual.remove(actual.size() - 1);
        counter++;
        int finalSize = actual.size();
        Assert.assertEquals(initSize - counter, finalSize);
    }

    /* Checks the list's content after removing some value */
    @Test
    public void execute22() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        actual.remove("b");
        String[] expected = {"a", "c", "d", "e"};
        Assert.assertArrayEquals(expected, actual.toArray());
    }

    /* Checks the method returns true after successful removing of an element of the list */
    @Test
    public void execute23() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        Assert.assertTrue(actual.remove("b"));
    }

    /* Checks the method returns false after unsuccessful removing of an element of the list */
    @Test
    public void execute24() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        actual.addLast("a");
        actual.addLast("b");
        actual.addLast("c");
        actual.addLast("d");
        actual.addLast("e");
        Assert.assertFalse(actual.remove("f"));
    }

    /* Checks multiply adding and polling from the beginning of the list in a cycle */
    @Test
    public void execute25() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        CustomLinkedList<String> init = new CustomLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            actual.addFirst("test");
            actual.pollFirst();
        }
        Assert.assertArrayEquals(init.toArray(), actual.toArray());
    }

    /* Checks multiply adding and polling from the end of the list in a cycle */
    @Test
    public void execute26() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        CustomLinkedList<String> init = new CustomLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            actual.addLast("test");
            actual.pollLast();
        }
        Assert.assertArrayEquals(init.toArray(), actual.toArray());
    }

    /* Checks multiply adding and polling from the beginning of the list in a cycle by blocks */
    @Test
    public void execute27() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        CustomLinkedList<String> init = new CustomLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            actual.addFirst("test");
        }
        actual.clear();
        for (int i = 0; i < 1000; i++) {
            actual.addFirst("test");
        }
        for (int i = 0; i < 1000; i++) {
            actual.pollFirst();
        }
        Assert.assertArrayEquals(init.toArray(), actual.toArray());
    }

    /* Checks multiply adding and polling from the beginning of the list in a cycle in turn */
    @Test
    public void execute28() {
        CustomLinkedList<String> actual = new CustomLinkedList<>();
        CustomLinkedList<String> init = new CustomLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            actual.addLast("test");
            actual.pollLast();
            actual.addFirst("test");
            actual.pollFirst();
        }
        Assert.assertArrayEquals(init.toArray(), actual.toArray());
    }

    /* Checks constructor with a collection of Integers as an argument */
    @Test
    public void execute29() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(1, 2, 3, 3, 4, 5));
        Integer[] expected = {1, 2, 3, 3, 4, 5};
        Assert.assertArrayEquals(expected, test.toArray());
    }

    /* Checks constructor with a collection of Strings as an argument */
    @Test
    public void execute30() {
        CustomLinkedList<String> test = new CustomLinkedList<>(Arrays.asList("1", "2", "3", "3", "4", "5"));
        String[] expected = {"1", "2", "3", "3", "4", "5"};
        Assert.assertArrayEquals(expected, test.toArray());
    }

    /* Checks constructor with a collection of Integer as an argument */
    @Test
    public void execute31() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(1, 2, 3, 3, 4, 5));
        Integer[] expected = {1, 2, 3, 3, 4};
        Assert.assertNotEquals(expected, test.toArray());
    }
}

