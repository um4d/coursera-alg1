import edu.princeton.cs.algs4.StdOut;

/**
 * Created by varos on 19.11.2016.
 */
public class DequeTest {
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.removeLast();
        deque.addFirst(3);
        deque.removeFirst();
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.removeLast();
        StdOut.print(deque.size());
    }

}
