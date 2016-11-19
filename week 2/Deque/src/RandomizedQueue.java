import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
/**
 * Created by t.simonov on 16.11.16.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int queueSize;
    private int arraySize;

    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        queueSize = 0;
        arraySize = 0;
    }

    public boolean isEmpty() {
        return queueSize == 0;
    }

    public int size() {
        return queueSize;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (arraySize == s.length) {
            resize(s.length * 2);
        }
        s[arraySize++] = item;
        queueSize++;
    }

    private void resize(int capacity) {
        Item[] sCopy = s;
        s = (Item[]) new Object[capacity];
        for (int i = 0; i < arraySize; i ++) {
            s[i] = sCopy[i];
        }
    }

    private void reduction(int capacity) {
        if(capacity == 0) {
            s = (Item[]) new Object[1];
            queueSize = 0;
            arraySize = 0;
        } else {
            Item[] sCopy = s;
            s = (Item[]) new Object[capacity];
            for (int i = 0, j = 0; i < arraySize; i++) {
                if (j > queueSize) return;
                if (!(sCopy[i] == null)) {
                    s[j] = sCopy[i];
                    j++;
                }
            }
        }
        arraySize = queueSize;
    }

    public Item dequeue() {
        if (queueSize < 1) throw new java.util.NoSuchElementException();
        int n = notNullIndex();
        Item item = s[n];
        s[n] = null;
        queueSize--;
        if (queueSize < arraySize / 2) {
            reduction(queueSize);
        }
        return item;
    }

    public Item sample() {
        if (queueSize < 1) throw new java.util.NoSuchElementException();
        return s[notNullIndex()];
    }

    private int notNullIndex() {
        int n;
        while (s[n = StdRandom.uniform(0, arraySize)] == null){
            //n = StdRandom.uniform(0, arraySize);
        }
        return n;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int n;
        private int[] iteratorArray;

        private ArrayIterator() {
            iteratorArray = new int[queueSize];
            n = 0;
            for (int i = 0; i < arraySize; i++) {
                if (!(s[i] == null)) {
                    iteratorArray[i] = notNullIndex();
                }
            }
            StdRandom.shuffle(iteratorArray);
        }


        public boolean hasNext() {
            return n < iteratorArray.length;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return s[n++];
        }


        public void remove() {throw new UnsupportedOperationException();}
    }


    public static void main(String[] args) {

    }   // unit testing
}