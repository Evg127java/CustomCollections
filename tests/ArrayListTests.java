package com.shpp.p2p.cs.ekondratiuk.assignment17.tests;

import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.CustomArrayList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ArrayListTests {

    /* Adds one element in the empty list. Checks if the added element is th same */
    @Test
    public void execute01() {
        CustomArrayList<Integer> actual = new CustomArrayList<>();
        actual.add(1);
        int test = actual.get(0);
        Assert.assertEquals(1, test);
    }

    /* Checks the list's size after adding 100 elements to it */
    @Test
    public void execute02() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        for (int i = 0; i < 100; i++) {
            actual.add("test");
        }
        Assert.assertEquals(100, actual.size());
    }

    /* Check list content correctness after adding a collection to the empty list */
    @Test
    public void execute03() {
        Integer[] array = {1, 2, 3};
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3));
        Assert.assertArrayEquals(array, actual.toCastedArray());
    }

    /* Checks adding an empty collection. NullPointerException expected */
    @Test(expected = NullPointerException.class)
    public void execute04() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        actual.addAll(Collections.emptyList());
    }

    /* Check list content correctness after adding a collection to the not empty list */
    @Test
    public void execute05() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3));
        Integer[] array = {1, 2, 3};
        actual.addAll(Arrays.asList(array));
        Integer[] expected = {1, 2, 3, 1, 2, 3};
        Assert.assertArrayEquals(expected, actual.toCastedArray());
    }

    /* Checks if adding collection to the list returns true if success */
    @Test
    public void execute06() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3));
        Integer[] array = {1, 2, 3};
        Assert.assertTrue(actual.addAll(Arrays.asList(array)));
    }

    /* Checks adding 1 mln String elements to the empty list */
    @Test
    public void execute07() {
        CustomArrayList<String> test = new CustomArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            test.add("a");
        }
        Assert.assertEquals(1000000, test.size());
    }

    /* Checks adding double values to the list and clear from them */
    @Test
    public void execute08() {
        CustomArrayList<Double> test = new CustomArrayList<>();
        int initialSize = test.size();
        for (double i = 0; i < 100; i++) {
            test.add(i);
        }
        test.clear();
        Assert.assertEquals(initialSize, test.size());
    }

    /* Checks adding an element to the list by index(to the first index of the current list) */
    @Test
    public void execute09() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        int inserted = 10;
        actual.add(0, inserted);
        int gotten = actual.get(0);
        Assert.assertEquals(inserted, gotten);
    }

    /* Checks adding an element to the list by index(to last index in th current list) */
    @Test
    public void execute10() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        int inserted = 10;
        actual.add(actual.size() - 1, inserted);
        int gotten = actual.get(actual.size() - 2);
        Assert.assertEquals(inserted, gotten);
    }

    /* Checks adding an element by index more than elements amount in the list */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute11() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        int inserted = 10;
        int index = actual.size();
        actual.add(index, inserted);
    }

    /* Checks adding an element by index less than elements amount in the list */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute12() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        int inserted = 10;
        int index = -5;
        actual.add(index, inserted);
    }

    /* Checks adding an element to the list by an allowed index */
    @Test
    public void execute13() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        int insertedValue = 10;
        int index = 2;
        actual.add(index, insertedValue);
        int gottenValue = actual.get(index);
        Assert.assertEquals(insertedValue, gottenValue);
    }

    /* Check adding a collection to the list by an allowed index */
    @Test
    public void execute14() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        Integer[] array = {1, 2, 3};
        actual.addAll(2, Arrays.asList(array));
        Integer[] expected = {1, 2, 1, 2, 3, 3, 4};
        Assert.assertArrayEquals(expected, actual.toCastedArray());

    }

    /* Checks if the current list with element is not empty */
    @Test
    public void execute15() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        Integer[] array = {1, 2, 3};
        actual.addAll(2, Arrays.asList(array));
        Assert.assertFalse(actual.isEmpty());

    }

    /* Checks if the current list without elements is empty */
    @Test
    public void execute16() {
        CustomArrayList<Integer> actual = new CustomArrayList<>();
        Assert.assertTrue(actual.isEmpty());

    }

    /* Checks if success adding of a collection by a correct index to list returns true */
    @Test
    public void execute17() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(1, 2, 3, 4));
        Integer[] array = {1, 2, 3};
        Assert.assertTrue(actual.addAll(2, Arrays.asList(array)));

    }

    /* Checks adding a collection to the list by a not allowed index */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute18() {
        CustomArrayList<Integer> actual = new CustomArrayList<>(Arrays.asList(0, 2, 3, 56));
        Integer[] array = {1, 0, 3};
        int index = -1;
        actual.addAll(index, Arrays.asList(array));
    }

    /* Checks if the list contains a not existing value. Returns false */
    @Test
    public void execute19() {
        CustomArrayList<Double> actual = new CustomArrayList<>(Arrays.asList(0.2, 2.45, 3.44, 56.0));
        double value = 0.7;
        Assert.assertFalse(actual.contains(value));
    }

    /* Checks if the list contains an existing value. Returns true */
    @Test
    public void execute20() {
        CustomArrayList<Double> actual = new CustomArrayList<>(Arrays.asList(0.2, 2.45, 3.44, 56.0));
        double value = 3.44;
        Assert.assertTrue(actual.contains(value));
    }

    /* Checks of getting existed specified value's index */
    @Test
    public void execute21() {
        CustomArrayList<Double> actual = new CustomArrayList<>(Arrays.asList(0.2, 2.45, 3.44, 56.0));
        double value = 3.44;
        int index = 2;
        Assert.assertEquals(index, actual.indexOf(value));
    }

    /* Checks of getting not existed specified value's index. Must return -1 */
    @Test
    public void execute22() {
        CustomArrayList<Double> actual = new CustomArrayList<>(Arrays.asList(0.2, 2.45, 3.44, 56.0));
        double value = 6;
        actual.indexOf(value);
        Assert.assertEquals(-1, actual.indexOf(value));
    }

    /* Checks of getting an element by not correct index. Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute23() {
        CustomArrayList<Double> actual = new CustomArrayList<>(Arrays.asList(0.2, 2.45, 3.44, 56.0));
        int index = actual.size() + 1;
        actual.get(index);
    }

    /* Checks of getting an element by correct index. Must return the corresponding value */
    @Test
    public void execute24() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        int index = 2;
        String gotten = actual.get(index);
        Assert.assertEquals("c", gotten);
    }

    /* Checks getting the first element from the empty list. Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute25() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        int index = 0;
        String gotten = actual.get(index);
        Assert.assertNull(gotten);
    }

    /* Checks of setting an element by zero index for the empty list. Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute26() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        int index = 0;
        actual.set(index, "d");
    }

    /* Checks of setting an element by correct index */
    @Test
    public void execute27() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        int index = 3;
        String symbolToSet = "c";
        actual.set(index, symbolToSet);
        String gotten = actual.get(index);
        Assert.assertEquals(symbolToSet, gotten);
    }

    /* Checks if successful setting of an element by the correct index returns a replaced element */
    @Test
    public void execute28() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        int index = 3;
        String symbolToSet = "c";
        actual.set(index, symbolToSet);
        String gotten = actual.get(index);
        Assert.assertEquals(gotten, actual.set(index, symbolToSet));
    }

    /* Checks of removing element from the list by the correct index */
    @Test
    public void execute29() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        int index = 3;
        String gotten = actual.get(index);
        String removed = actual.remove(index);
        Assert.assertEquals(gotten, removed);
    }

    /* Checks of removing element from the list by not a correct index. Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute30() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        int index = -1;
        actual.get(index);
        actual.remove(index);
    }

    /* Checks removing an element from the empty list. Must throw an exception */
    @Test(expected = IndexOutOfBoundsException.class)
    public void execute31() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        int index = 0;
        actual.get(index);
        actual.remove(index);
    }

    /* Checks the list's size after removing some elements from it */
    @Test
    public void execute32() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        int initSize = actual.size();
        int counter = 0;
        actual.remove(actual.size() - 1);
        counter++;
        actual.remove(actual.size() - 1);
        counter++;
        int finalSize = actual.size();
        Assert.assertEquals(initSize - counter, finalSize);
    }

    /* Checks removing of an existing value from the list */
    @Test
    public void execute33() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        actual.remove("b");
        String[] expected = {"a", "c", "d", "e"};
        Assert.assertArrayEquals(expected, actual.toCastedArray());
    }

    /* Checks if successful removing of an element from the list returns true */
    @Test
    public void execute34() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        Assert.assertTrue(actual.remove("b"));
    }

    /* Checks if not successful removing of an element from the list returns false */
    @Test
    public void execute35() {
        CustomArrayList<String> actual = new CustomArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        Assert.assertFalse(actual.remove("f"));
    }

    /* Checks multiply adding and removing elements of the list. Adding and removing by one in turn. */
    @Test
    public void execute36() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        CustomArrayList<String> init = new CustomArrayList<>();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
            actual.remove("test");
        }
        Assert.assertArrayEquals(init.toCastedArray(), actual.toCastedArray());
    }

    /* Checks multiply adding and removing elements of the list. Adding by one and removing by one in turn */
    @Test
    public void execute37() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        int initSize = actual.size();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
        }
        actual.clear();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
        }
        for (int i = 0; i < 1000; i++) {
            actual.remove("test");
        }
        int finalSize = actual.size();
        Assert.assertEquals(initSize, finalSize);
    }

    /* Checks constructor with a collection of Strings as an argument */
    @Test
    public void execute38() {
        CustomArrayList<String> test = new CustomArrayList<>(Arrays.asList("1", "2", "3", "3", "4", "5"));
        String[] expected = {"1", "2", "3", "3", "4", "5"};
        Assert.assertArrayEquals(expected, test.toCastedArray());
    }

    /* Checks constructor with a collection of Integer as an argument */
    @Test
    public void execute39() {
        CustomArrayList<Integer> test = new CustomArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 5));
        Integer[] expected = {1, 2, 3, 3, 4};
        Assert.assertNotEquals(expected, test.toCastedArray());
    }

    /* Checks constructor with a collection of Integer as an argument */
    @Test
    public void execute40() {
        CustomArrayList<Integer> test = new CustomArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 5, 123, 0, 88));
        Integer[] expected = {1, 2, 3, 3, 4, 5, 123, 0, 88};
        Assert.assertArrayEquals(expected, test.toCastedArray());
    }
}