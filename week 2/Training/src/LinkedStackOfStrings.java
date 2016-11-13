/**
 * Created by t.simonov on 11.11.16.
 */
public class LinkedStackOfStrings {
    private Node first = null;

    private class Node
    {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public String pop()
    {
        String item = first.item;
        first = first.next;
        return item;
    }
}
