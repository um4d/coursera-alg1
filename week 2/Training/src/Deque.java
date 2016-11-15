import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by t.simonov on 10.11.16.
 */
public class Deque<Item> implements Iterable<Item>{

    public Deque() {

    }
    public boolean isEmpty() {

    }
    public int size() {

    }
    public void addFirst(Item item) {

    }
    public void addLast(Item item) {

    }
    public Item removeFirst() {

    }
    public Item removeLast() {

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
