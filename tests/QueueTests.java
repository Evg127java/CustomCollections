package com.shpp.p2p.cs.ekondratiuk.assignment17.tests;

import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.customQueue.CustomQueue;
import org.junit.Assert;
import org.junit.Test;

public class QueueTests {

    /* Testing of add and peek an element to the stack*/
    @Test
    public void execute01() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.add(1);
        int test = actual.peek();
        Assert.assertEquals(1, test);
    }

    /* Checks size of the stack after adding an element */
    @Test
    public void execute02() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.add(1);
        Assert.assertEquals(1, actual.size());
    }

    /* Checks if the method returns false if offering null to the queue */
    @Test
    public void execute03() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        Assert.assertFalse(actual.offer(null));
    }

    /* Checks if the method returns false if adding null to the queue */
    @Test(expected = NullPointerException.class)
    public void execute04() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        Assert.assertFalse(actual.add(null));
    }

    /* Checks if the method returns true adding not null to the queue */
    @Test
    public void execute05() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        Assert.assertTrue(actual.add(1));
    }

    /* Checks if the method returns true offering not null to the queue */
    @Test
    public void execute06() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        Assert.assertTrue(actual.offer(1));
    }

    /* Checks adding many elements to the queue */
    @Test
    public void execute07() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        for (int i = 0; i < 100; i++) {
            actual.add(i);
        }
        Assert.assertEquals(100, actual.size());
    }

    /* Checking the queue after clearing */
    @Test
    public void execute08() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.clear();
        Integer[] t = new Integer[]{};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checking to get the first element of the empty queue
     * Must throw an exception */
    @Test(expected = NullPointerException.class)
    public void execute09() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        int test = actual.element();
        System.out.println(test);
    }

    /* Checking to peek the first element of the empty queue
     * Must return null */
    @Test
    public void execute10() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        Assert.assertNull(actual.peek());
    }

    /* Checking to poll the first element of the empty queue
     * Must return null */
    @Test
    public void execute11() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        Assert.assertNull(actual.poll());
    }

    /* Checks of polling from the not empty queue */
    @Test
    public void execute12() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        int test = 2;
        actual.add(test);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        int gotten = actual.poll();
        Assert.assertEquals(test, gotten);
    }

    /* Checks the queue content after polling from the not empty queue */
    @Test
    public void execute13() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.poll();
        Integer[] t = new Integer[]{4, 5, 7};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks the queue content after after to the queue */
    @Test
    public void execute14() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.add(10);
        Integer[] t = new Integer[]{2, 4, 5, 7, 10};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks the queue content after after to the queue */
    @Test
    public void execute15() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.offer(2);
        actual.offer(4);
        actual.offer(5);
        actual.offer(7);
        actual.offer(10);
        Integer[] t = new Integer[]{2, 4, 5, 7, 10};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks the queue content after taking the first element from the queue */
    @Test
    public void execute16() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.element();
        Integer[] t = new Integer[]{2, 4, 5, 7};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks the queue content after removing the first element from the queue */
    @Test
    public void execute17() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.remove();
        Integer[] t = new Integer[]{4, 5, 7};
        Assert.assertArrayEquals(t, actual.toCastedArray());
    }

    /* Checks removing the first element from the empty queue
     * Must throw an exception*/
    @Test(expected = NullPointerException.class)
    public void execute18() {
        CustomQueue<Integer> actual = new CustomQueue<>();
        int test = actual.remove();
        System.out.println(test);
    }

    /* Checks offering 100 String elements to the queue */
    @Test
    public void execute19() {
        CustomQueue<String> actual = new CustomQueue<>();
        for (int i = 0; i < 100; i++) {
            actual.offer("test");
        }
        Assert.assertEquals(100, actual.size());
    }

    /* Checks offering 1mln String elements to the queue */
    @Test
    public void execute20() {
        CustomQueue<String> test = new CustomQueue<>();
        for (int i = 0; i < 1000000; i++) {
            test.offer("a");
        }
        Assert.assertEquals(1000000, test.size());
    }

    /* Checks adding many Double type's elements to the queue */
    @Test
    public void execute21() {
        CustomQueue<Double> test = new CustomQueue<>();
        int initialSize = test.size();
        for (double i = 0; i < 100; i++) {
            test.add(i);
        }
        test.clear();
        Assert.assertEquals(initialSize, test.size());
    }

    /* Checks multiply adding taking and removing elements from the queue. */
    @Test
    public void execute22() {
        CustomQueue<String> actual = new CustomQueue<>();
        CustomQueue<String> init = new CustomQueue<>();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
        }
        actual.clear();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
        }
        for (int i = 0; i < 1000; i++) {
            actual.element();
        }
        for (int i = 0; i < 1000; i++) {
            actual.remove();
        }
        Assert.assertArrayEquals(init.toCastedArray(), actual.toCastedArray());
    }

    /* Checks multiply adding and removing elements from the queue one by one. */
    @Test
    public void execute23() {
        CustomQueue<String> actual = new CustomQueue<>();
        CustomQueue<String> init = new CustomQueue<>();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
            actual.remove();
            actual.offer("test");
            actual.remove();
        }
        Assert.assertArrayEquals(init.toCastedArray(), actual.toCastedArray());
    }

    /* Checks if the isEmpty() method returns false if the queue with elements */
    @Test
    public void execute24() {
        CustomQueue<Double> actual = new CustomQueue<>();
        actual.add(0.2);
        actual.add(2.45);
        actual.add(3.44);
        actual.add(56.0);
        Assert.assertFalse(actual.isEmpty());
    }

    /* Checks if the method returns true if the queue without elements */
    @Test
    public void execute25() {
        CustomQueue<Double> actual = new CustomQueue<>();
        Assert.assertTrue(actual.isEmpty());

    }
}