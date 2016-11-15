import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by t.simonov on 10.11.16.
 */
public class Deque<Item> implements Iterable<Item>{

    private Node first, last;
    private int dequeSize;


    public Deque() {

    }
    public boolean isEmpty() {
        return first == null || last == null;
    }
    public int size() {
        return dequeSize;
    }
    public void addFirst(Item item) {
        validateItem(item);
        dequeSize++;
        if (isEmpty()) {
            first = last;
        }
        Node oldFirst = first;
        first.item = item;
        first.next = oldFirst;
    }
    public void addLast(Item item) {

    }
    public Item removeFirst() {

    }
    public Item removeLast() {

    }

    private class Node {
        private Node next;
        private Node previous;
        private Item item;
    }

    private void validateItem(Item item) {
        if(item == null) {
            throw new java.lang.NullPointerException();
        }
    }



    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Item> action) {

    }

    @Override
    public Spliterator<Item> spliterator() {
        return null;
    }
}


