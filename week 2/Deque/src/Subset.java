/**
 * Created by t.simonov on 18.11.16.
 */
import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        while (StdIn.hasNextLine()) {
            String string = StdIn.readString();
            queue.enqueue(string);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
