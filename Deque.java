import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        private Item data;
        private Node next;
        private Node prev;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.data;
            current = current.next;
            return item;
        }
    }

    //construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.data = item;
        newNode.next = first;
        if (size >= 1) first.prev = newNode;
        newNode.prev = null;
        first = newNode;
        size++;

        if (size == 1) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.data = item;
        newNode.next = null;
        size++;

        if (isEmpty()) {
            last = newNode;
            first = newNode;
            newNode.prev = null;
        }
        else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item temp = first.data;

        if (first.next == null) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }

        size--;
        return temp;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item temp = last.data;

        last = last.prev;
        if (size > 1) {
            last.next = null;
        }
        else {
            first = null;
        }

        size--;
        return temp;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> D = new Deque<Integer>();
        D.addFirst(1);
        D.addFirst(2);
        D.removeLast();
    }
}
