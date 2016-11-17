/**
 * Created by t.simonov on 16.11.16.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

    Item[] s;
    int queueSize;
    int arraySize;

    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        queueSize = 0;
        arraySize = 1;
    }

    public boolean isEmpty() {
        return s[0] == null;
    }
    public int size() {
        return queueSize;
    }
    public void enqueue(Item item) {
        if (arraySize == s.length) {
            resize(s.length * 2);
        }
        s[arraySize] = item;
        arraySize++;
        queueSize++;
    }
    private void reduction(int capacity) {
        Item[] sCopy = s;
        s = (Item[]) new Object[capacity];
        for (int i = 0, j = 0; i < arraySize; i++) {
            if (!(s[i] == null)) {
                s[i] = sCopy[j];
                j++;
            }
        }
    }

    public Item dequeue() {
        int n = StdRandom(0, arraySize);
    }                    // remove and return a random item
    public Item sample();                     // return (but do not remove) a random item
    public Iterator<Item> iterator();         // return an independent iterator over items in random order
    public static void main(String[] args);   // unit testing
}