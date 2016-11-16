import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by t.simonov on 10.11.16.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int dequeSize;


    public Deque() {
        first = null;
        last = null;
        dequeSize = 0;
    }

    public boolean isEmpty() {
        return first == null || last == null;
    }

    public int size() {
        return dequeSize;
    }

    public void addFirst(Item item) {
        validateItem(item);
        ++dequeSize;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        } else oldFirst.previous = first;
    }

    public void addLast(Item item) {
        validateItem(item);
        ++dequeSize;
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else oldLast.next = last;
    }

    public Item removeFirst() {
        validateDequeIsEmpty();
        Node oldFirst = first;
        first = first.next;
        Item item = oldFirst.item;
        oldFirst.next = null;
        --dequeSize;
        return item;
    }

    public Item removeLast() {
        validateDequeIsEmpty();
        Node oldLast = last;
        last = last.previous;
        Item item = oldLast.item;
        oldLast.previous = null;
        --dequeSize;
        return item;
    }

    private class Node {
        private Node next;
        private Node previous;
        private Item item;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private void validateDequeIsEmpty() {
        if (isEmpty()) {
            throw new java.lang.NullPointerException();
        }
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {throw new UnsupportedOperationException();}
    }
}
