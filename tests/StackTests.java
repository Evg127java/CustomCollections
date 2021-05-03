package com.shpp.p2p.cs.ekondratiuk.assignment17.tests;

import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.CustomStack;
import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class StackTests {

    /* Testing of push and peek an element to the stack*/
    @Test
    public void execute01() {
        CustomStack<Integer> actual = new CustomStack<>();
        actual.push(1);
        int test = actual.peek();
        Assert.assertEquals(1, test);
    }

    /* Testing of the stack content after add some elements to it */
    @Test
    public void execute02() {
        CustomStack<Integer> actual = new CustomStack<>();
        actual.push(2);
        actual.push(4);
        actual.push(5);
        actual.push(7);
        actual.push(10);
        Integer[] t = new Integer[]{2, 4, 5, 7, 10};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Testing of empty content of the stack */
    @Test
    public void execute03() {
        CustomStack<Integer> actual = new CustomStack<>();
        Integer[] t = new Integer[]{};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks stack's content after pop an element from it */
    @Test
    public void execute04() {
        CustomStack<Integer> actual = new CustomStack<>();
        actual.push(2);
        actual.push(4);
        actual.push(5);
        actual.push(7);
        actual.pop();
        Integer[] t = new Integer[]{2, 4, 5};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks the stack content after clearing */
    @Test
    public void execute05() {
        CustomStack<Integer> actual = new CustomStack<>();
        actual.push(2);
        actual.push(4);
        actual.push(5);
        actual.push(7);
        actual.clear();
        Integer[] t = new Integer[]{};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks correctness of pushing an element to the stack */
    @Test
    public void execute06() {
        CustomStack<Integer> actual = new CustomStack<>();
        int value = 2;
        int gotten = actual.push(value);
        Assert.assertEquals(value, gotten);
    }

    /* Checks of peeking from an empty stack. Must throw an exception */
    @Test(expected = EmptyStackException.class)
    public void execute07() {
        CustomStack<Integer> actual = new CustomStack<>();
        int test = actual.peek();
        System.out.println(test);
    }

    /* Checks of popping from an empty stack. Must throw an exception */
    @Test(expected = EmptyStackException.class)
    public void execute08() {
        CustomStack<Integer> actual = new CustomStack<>();
        int test = actual.pop();
        System.out.println(test);
    }

    /* Checks of pushing many String elements to stack */
    @Test
    public void execute09() {
        CustomStack<String> actual = new CustomStack<>();
        for (int i = 0; i < 100; i++) {
            actual.push("test");
        }
        Assert.assertEquals(100, actual.size());
    }

    /* Checks of pushing many Integer elements to stack */
    @Test
    public void execute10() {
        CustomStack<Integer> test = new CustomStack<>();
        for (int i = 0; i < 1000000; i++) {
            test.push(i);
        }
        Assert.assertEquals(1000000, test.size());
    }

    /* Checks of pushing many Double elements to stack and clear from them */
    @Test
    public void execute11() {
        CustomStack<Double> test = new CustomStack<>();
        int initialSize = test.size();
        for (double i = 0; i < 100; i++) {
            test.push(i);
        }
        test.clear();
        Assert.assertEquals(initialSize, test.size());
    }

    /* Checks multiply pushing and popping elements of the stack. */
    @Test
    public void execute12() {
        CustomStack<String> actual = new CustomStack<>();
        CustomStack<String> init = new CustomStack<>();
        for (int i = 0; i < 1000; i++) {
            actual.push("test");
        }
        actual.clear();
        for (int i = 0; i < 1000; i++) {
            actual.push("test");
        }
        for (int i = 0; i < 1000; i++) {
            actual.pop();
        }
        Assert.assertArrayEquals(init.toCastedArray(), actual.toCastedArray());
    }

    /* Checks multiply pushing and popping elements of the stack.
    Pushing by one and popping by one in turn */
    @Test
    public void execute13() {
        CustomStack<String> actual = new CustomStack<>();
        CustomStack<String> init = new CustomStack<>();
        for (int i = 0; i < 1000; i++) {
            actual.push("test");
            actual.pop();
            actual.push("test");
            actual.pop();
        }
        Assert.assertArrayEquals(init.toCastedArray(), actual.toCastedArray());
    }

    /* Checks if the specified not existing value is in the stack */
    @Test
    public void execute14() {
        CustomStack<Double> actual = new CustomStack<>();
        actual.push(0.2);
        actual.push(2.45);
        actual.push(3.44);
        actual.push(56.0);
        double value = 0.7;
        Assert.assertEquals(-1, actual.contains(value));
    }

    /* Checks if the method isEmpty returns false if the stack is not empty */
    @Test
    public void execute15() {
        CustomStack<Double> actual = new CustomStack<>();
        actual.push(0.2);
        actual.push(2.45);
        actual.push(3.44);
        actual.push(56.0);
        Assert.assertFalse(actual.isEmpty());
    }

    /* Checks if the method isEmpty returns true if the stack is empty */
    @Test
    public void execute16() {
        CustomStack<Double> actual = new CustomStack<>();
        Assert.assertTrue(actual.isEmpty());
    }

    /* Checks if the method size returns zero if the stack is empty */
    @Test
    public void execute17() {
        CustomStack<Double> actual = new CustomStack<>();
        Assert.assertEquals(0, actual.size());
    }

    /* Checks if the specified existing value is in the stack */
    @Test
    public void execute18() {
        CustomStack<Double> actual = new CustomStack<>();
        actual.push(0.2);
        actual.push(2.45);
        actual.push(3.44);
        actual.push(56.0);
        double value = 3.44;
        Assert.assertEquals(2, actual.contains(value));
    }

    /* Check if the method size defines sizeof the stack correctly */
    @Test
    public void execute19() {
        CustomStack<Double> actual = new CustomStack<>();
        actual.push(0.2);
        actual.push(2.45);
        actual.push(3.44);
        actual.push(56.0);
        Assert.assertEquals(4, actual.size());
    }

    /*  Checks getting correct index in stack in contains method( for the top) */
    @Test
    public void execute20() {
        CustomStack<Double> actual = new CustomStack<>();
        actual.push(0.2);
        actual.push(2.45);
        actual.push(3.44);
        actual.push(56.0);
        double value = 56.0;
        Assert.assertEquals(1, actual.contains(value));
    }

    /*  Checks getting correct index in stack from the top in contains method(for the bottom) */
    @Test
    public void execute21() {
        CustomStack<Double> actual = new CustomStack<>();
        actual.push(0.2);
        actual.push(2.45);
        actual.push(3.44);
        actual.push(56.0);
        double value = 0.2;
        Assert.assertEquals(4, actual.contains(value));
    }

    /* Checks pushing of many elements to the stack */
    @Test
    public void execute22() {
        CustomStack<String> actual = new CustomStack<>();
        int i = 0;
        while (i < 1000000) {
            actual.push("test");
            i++;
        }
        Assert.assertEquals(1000000, actual.size());
    }
}
