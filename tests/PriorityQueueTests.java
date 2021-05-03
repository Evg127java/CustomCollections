package com.shpp.p2p.cs.ekondratiuk.assignment17.tests;

import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.CustomArrayList;
import com.shpp.p2p.cs.ekondratiuk.assignment17.customCollections.CustomPriorityQueue;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class PriorityQueueTests {
    /* Testing of add and peek an element to the CustomPriorityQueue */
    @Test
    public void execute01() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(1);
        int test = actual.peek();
        Assert.assertEquals(1, test);
    }

    /* Checks size of the CustomPriorityQueue after adding an element */
    @Test
    public void execute02() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(1);
        Assert.assertEquals(1, actual.size());
    }

    /* Checks adding many elements to the CustomPriorityQueue */
    @Test
    public void execute03() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        for (int i = 0; i < 100; i++) {
            actual.add(i);
        }
        Assert.assertEquals(100, actual.size());
    }

    /* Checking the CustomPriorityQueue after clearing */
    @Test
    public void execute04() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.clear();
        String t = "[]";
        Assert.assertEquals(t, actual.toString());
    }

    /* Checking to peek the first element of the empty CustomPriorityQueue
     * Must return null */
    @Test
    public void execute05() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        Assert.assertNull(actual.peek());
    }

    /* Checking to poll the first element of the empty CustomPriorityQueue
     * Must return null */
    @Test
    public void execute06() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        Assert.assertNull(actual.poll());
    }

    /* Checks of polling from the not empty CustomPriorityQueue */
    @Test
    public void execute07() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        int test = 2;
        actual.add(test);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        int gotten = actual.poll();
        Assert.assertEquals(test, gotten);
    }

    /* Checks the content after polling from the not empty CustomPriorityQueue */
    @Test
    public void execute08() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.poll();
        String str = "[4, 7, 5]";
        Assert.assertEquals(str, actual.toString());
    }

    /* Checks the content after adding to the CustomPriorityQueue */
    @Test
    public void execute09() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(7);
        actual.add(10);
        String str = "[2, 4, 5, 7, 10]";
        Assert.assertEquals(str, actual.toString());
    }

    /* Checks adding 100 String elements to the CustomPriorityQueue */
    @Test
    public void execute10() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        for (int i = 0; i < 100; i++) {
            actual.add(i);
        }
        Assert.assertEquals(100, actual.size());
    }

    /* Checks adding 1mln String elements to the CustomPriorityQueue */
    @Test
    public void execute11() {
        CustomPriorityQueue<String> actual = new CustomPriorityQueue<>();
        for (int i = 0; i < 1000000; i++) {
            actual.add("a");
        }
        Assert.assertEquals(1000000, actual.size());
    }

    /* Checks adding many random Double type's elements to the CustomPriorityQueue */
    @Test
    public void execute12() {
        CustomPriorityQueue<Double> test = new CustomPriorityQueue<>();
        int initialSize = test.size();
        for (double i = 0; i < 100; i++) {
            test.add(Math.random() * i);
        }
        test.clear();
        Assert.assertEquals(initialSize, test.size());
    }

    /* Checks multiply adding taking and removing elements from the CustomPriorityQueue. */
    @Test
    public void execute13() {
        CustomPriorityQueue<String> actual = new CustomPriorityQueue<>();
        CustomPriorityQueue<String> init = new CustomPriorityQueue<>();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
        }
        actual.clear();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
        }
        for (int i = 0; i < 1000; i++) {
            actual.poll();
        }
        for (int i = 0; i < 1000; i++) {
            actual.poll();
        }
        Assert.assertEquals(init.toString(), actual.toString());
    }

    /* Checks multiply adding and removing elements from the CustomPriorityQueue one by one. */
    @Test
    public void execute14() {
        CustomPriorityQueue<String> actual = new CustomPriorityQueue<>();
        CustomPriorityQueue<String> init = new CustomPriorityQueue<>();
        for (int i = 0; i < 1000; i++) {
            actual.add("test");
            actual.poll();
            actual.add("test");
            actual.poll();
        }
        Assert.assertEquals(init.toString(), actual.toString());
    }

    /* Checks if the isEmpty() method returns false if the CustomPriorityQueue with elements */
    @Test
    public void execute15() {
        CustomPriorityQueue<Double> actual = new CustomPriorityQueue<>();
        actual.add(0.2);
        actual.add(2.45);
        actual.add(3.44);
        actual.add(56.0);
        Assert.assertFalse(actual.isEmpty());
    }

    /* Checks if the method returns true if the CustomPriorityQueue without elements */
    @Test
    public void execute16() {
        CustomPriorityQueue<Double> actual = new CustomPriorityQueue<>();
        Assert.assertTrue(actual.isEmpty());
    }

    /* Checking the CustomPriorityQueue content after polling  */
    @Test
    public void execute17() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(8);
        actual.poll();
        String t = "[4, 8, 5]";
        Assert.assertEquals(t, actual.toString());
    }

    /* Checks if the queue polls the minimal number */
    @Test
    public void execute18() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(8);
        actual.add(17);
        actual.add(22);
        actual.add(4);
        int gotten = actual.poll();
        Assert.assertEquals(2, gotten);
    }

    /* Checks if the queue peeks the minimal number */
    @Test
    public void execute19() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(8);
        actual.add(17);
        actual.add(22);
        actual.add(4);
        int gotten = actual.peek();
        Assert.assertEquals(2, gotten);
    }

    /* Checks if the queue peeks the minimal number if several numbers are minimal*/
    @Test
    public void execute20() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>();
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(2);
        actual.add(17);
        actual.add(22);
        actual.add(4);
        int gotten = actual.peek();
        Assert.assertEquals(2, gotten);
    }

    /* Checks of passing a comparator as a parameter of the priority queue
     * Gets the smallest item */
    @Test
    public void execute21() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer e1, Integer e2) {
                return e1.compareTo(e2);
            }

        });
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(2);
        actual.add(17);
        actual.add(22);
        actual.add(4);
        int gotten = actual.peek();
        Assert.assertEquals(2, gotten);
    }

    /* Checks of passing a comparator as a parameter of the priority queue
     * Gets the largest item */
    @Test
    public void execute22() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer e1, Integer e2) {
                return e2.compareTo(e1);
            }

        });
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(2);
        actual.add(17);
        actual.add(22);
        actual.add(4);
        int gotten = actual.peek();
        Assert.assertEquals(22, gotten);
    }

    /* Checks of passing a comparator as a parameter of the priority queue
     * Gets all the items taken by smallest in turn */
    @Test
    public void execute23() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer e1, Integer e2) {
                return e2.compareTo(e1);
            }

        });
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(2);
        actual.add(17);
        actual.add(22);
        actual.add(4);
        CustomArrayList<Integer> test = new CustomArrayList<>();
        while (actual.size() > 0) {
            test.add(actual.poll());
        }
        Assert.assertEquals("[22, 17, 5, 4, 4, 2, 2]", test.toString());
    }

    /* Checks of passing a comparator as a parameter of the priority queue
     * Gets all the items taken by largest in turn */
    @Test
    public void execute24() {
        CustomPriorityQueue<Integer> actual = new CustomPriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer e1, Integer e2) {
                return e1.compareTo(e2);
            }

        });
        actual.add(2);
        actual.add(4);
        actual.add(5);
        actual.add(2);
        actual.add(17);
        actual.add(22);
        actual.add(4);
        CustomArrayList<Integer> test = new CustomArrayList<>();
        while (actual.size() > 0) {
            test.add(actual.poll());
        }
        Assert.assertEquals("[2, 2, 4, 4, 5, 17, 22]", test.toString());
    }

    /* Checks of passing a comparator as a parameter of the priority queue
     * Gets all the items taken by largest in turn */
    @Test
    public void execute25() {
        CustomPriorityQueue<String> actual = new CustomPriorityQueue<>(new Comparator<String>() {

            @Override
            public int compare(String e1, String e2) {
                return e1.compareTo(e2);
            }

        });
        actual.add("test");
        actual.add("best");
        actual.add("zest");
        actual.add("quest");
        actual.add("west");
        actual.add("guest");
        actual.add("nest");
        CustomArrayList<String> test = new CustomArrayList<>();
        while (actual.size() > 0) {
            test.add(actual.poll());
        }
        Assert.assertEquals("[best, guest, nest, quest, test, west, zest]", test.toString());
    }
}
