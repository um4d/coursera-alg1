import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by varos on 15.11.2016.
 */
public class test{
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 500; i++) {
           if (StdRandom.uniform(10) < 7) {
               rq.enqueue(StdRandom.uniform(99));
                StdOut.println(rq.size());
           }
            if (StdRandom.uniform(10) < 1) {
                rq.dequeue();
                StdOut.println(rq.size());
            }
            if (StdRandom.uniform(10) < 0) {
                rq.sample();
                StdOut.println(rq.size());
            }
            if (StdRandom.uniform(10) < 1) {
                rq.isEmpty();
                StdOut.println(rq.size());
            }
            if (StdRandom.uniform(10) < 1) {
                rq.size();
                StdOut.println(rq.size());
            }

        }
    }
}
