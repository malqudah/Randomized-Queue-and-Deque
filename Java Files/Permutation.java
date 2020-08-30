/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description:  Uses randomizedqueue to create an object of the strings
 *  that are in std in by enqueuing them. then using a for loop, prints
 *  k (from command line) random strings from the data structure using dequeue
 *  method.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        // creates randomized queue object
        RandomizedQueue<String> testOne = new RandomizedQueue<>();
        // while stdin is not empty, enqueue all of the strings
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            testOne.enqueue(input);
        }
        int k = Integer.parseInt(args[0]);
        // dequeue from testOne k elements
        for (int i = 0; i < k; i++) {
            StdOut.println(testOne.dequeue());
        }
    }
}
