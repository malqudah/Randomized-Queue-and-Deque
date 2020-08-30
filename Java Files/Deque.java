/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description: Uses doubly linked lists to create a generic data structure
 *  randomizedqueue, in which the element that is removed is randomly chosen.
 *  the elements are added to either the front or back with manipulation of
 *  the private node class. Implements and uses an iterator to print all items
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    /* @citation Copied/Adapted from: https://algs4.cs.princeton.edu/13stacks/
    LinkedStack.java.html Accessed 02/17/2020. various blocks of code
    were taken from the file and adapted into the program; some of them copied
    directly
    */

    // size of the deque
    private int n;
    // front of the deque
    private Node first;
    // back of the deque
    private Node last;

    // helper linked list class
    private class Node {
        // item of a given node
        private Item item;
        // pointer to the next element in deque
        private Node next;
        // pointer to previous element in deque
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = null;
        last = null;
        assert check();
    }

    // private helper method for illegal arguments
    private void illegalArg(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    // private helper method for no such element exceptions
    private void noElem() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null && last == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        illegalArg(item);
        if (n == 0) {
            first = new Node();
            first.item = item;
            last = first;
            first.prev = null;
            first.next = null;
        }
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            oldfirst.prev = first;
            first.prev = null;
        }
        n++;
        assert check();
    }

    // add the item to the back
    public void addLast(Item item) {
        illegalArg(item);
        if (n == 0) {
            addFirst(item);
            last = first;
            return;
        }
        else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.prev = oldlast;
            oldlast.next = last;
            last.next = null;
        }
        n++;
        assert check();
    }

    // remove and return the item from the front
    public Item removeFirst() {
        noElem();
        // save item to return
        Item item = first.item;
        Node oldfirst = first;
        // delete first node
        first = first.next;
        if (first != null) {
            first.prev = null;
            oldfirst.next = null;
        }
        else last = null;
        n--;
        assert check();
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        noElem();
        Item item = last.item;
        Node oldlast = last;
        last = last.prev;
        if (last != null) {
            oldlast.next = null;
            last.next = null;
        }
        else first = null;
        n--;
        assert check();
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        // sets current node
        private Node current;

        // constructor
        public DequeIterator() {
            // initializes current node to first
            current = first;
        }

        // checks if there is a next value in the deque
        public boolean hasNext() {
            return current != null;
        }

        // returns the next item in the deque
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        // not supported
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // check internal invariants
    /* @citation Copied and pastedfrom: https://algs4.cs.princeton.edu/13stacks/
    LinkedStack.java.html Accessed 02/17/2020. Not sure if should be used
    but it was available so i copied anyway
    */
    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        }
        else if (n == 1) {
            if (first == null) return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null) return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> testOne = new Deque<Integer>();
        testOne.addFirst(5);
        testOne.addLast(10);
        testOne.addFirst(11);
        testOne.addFirst(15);
        testOne.addFirst(6);
        StdOut.println("Size = " + testOne.size());
        StdOut.println("Is it empty? " + testOne.isEmpty());
        StdOut.println("First removed = " + testOne.removeFirst());
        StdOut.println("Last removed = " + testOne.removeLast());
        StdOut.print("Elements are: ");
        for (int i : testOne) {
            StdOut.print(i);
            StdOut.print(", ");
        }
    }
}
