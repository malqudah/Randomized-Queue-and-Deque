/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description: Uses resizing arrays to create a genericdatastructurethatallows
 *  you to add elements to the front or the end (as opposed to a regular stack
 *  or queue that only supports one or the either). Implements and uses an
 *  iterator to print all of the elements in it
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    /* @citation Copied/Adapted from: https://algs4.cs.princeton.edu/13stacks/
    ResizingArrayStack.java.htmlAccessed 02/17/2020. various blocks of code
    were taken from the file and adapted into the program; some of them copied
    directly
    */

    // array of items
    private Item[] a;
    // number of elements
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
        a = (Item[]) new Object[1];
    }


    // private helper method for no such element exceptions
    private void noElem() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // private helper method for illegal arguments
    private void illegalArg(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] copy = (Item[]) (new Object[capacity]);
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }

    // add the item
    public void enqueue(Item item) {
        illegalArg(item);
        // doubles array size if full
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        noElem();
        int ranIndex = StdRandom.uniform(n);
        Item item = a[ranIndex];
        // avoid loitering
        a[ranIndex] = a[n - 1];
        a[n - 1] = null;
        n--;
        // shrink size of array if quarter full
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        noElem();
        int ranIndex = StdRandom.uniform(n);
        Item item = a[ranIndex];
        // shrink size of array if quarter full
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        // index for array
        private int i;
        // copy array
        private final Item[] copy;

        // constructor
        public RandomizedQueueIterator() {
            i = 0;
            copy = (Item[]) (new Object[n]);
            for (int p = 0; p < n; p++) {
                copy[p] = a[p];
            }
            StdRandom.shuffle(copy);
        }

        // checks if there is a next element
        public boolean hasNext() {
            return i < n;
        }

        // returns the next element
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy[i++];
        }

        // not supported
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> testOne = new RandomizedQueue<>();
        testOne.enqueue(5);
        testOne.enqueue(7);
        testOne.enqueue(9);
        StdOut.println("Size = " + testOne.size());
        StdOut.println("Random number dequeued = " + testOne.dequeue());
        StdOut.println("Is it empty? " + testOne.isEmpty());
        StdOut.println("Random number = " + testOne.sample());
        StdOut.print("Elements are: ");
        for (int i : testOne) {
            StdOut.print(i + ", ");
        }
    }
}
