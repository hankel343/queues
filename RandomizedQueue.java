import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int capacity = 1, N = 0;

    private class ListIterator<Item> implements Iterator<Item> {

        private int current = 0;

        ListIterator() {
            StdRandom.shuffle(items, 0, N); // Put items in a random order
        }

        public boolean hasNext() {
            return (current != capacity && items[current] != null);
        }

        public Item next() {
            Item temp = (Item) items[current++];
            return temp;
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = items[i];
        }

        items = copy;
    }

    private void Swap(int index) {
        Item temp = items[index];
        items[index] = items[N - 1];
        items[N - 1] = temp;
    }

    //construct an empty randomized queue
    public RandomizedQueue() {
        items
                = (Item[]) new Object[1]; //Need to instantiate as an array of Objects and then cast down to items
    }

    public boolean isEmpty() {
        return (N == 0);
    }

    // return the number of items in the queue
    public int size() {
        return N;
    }

    // add an item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (N == capacity) {
            resize(capacity * 2);
            capacity *= 2;
        }
        items[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        Swap(StdRandom.uniform(N));
        Item temp = items[--N];
        if (N > 0 && N == capacity / 4) {
            resize(capacity / 2);
            capacity /= 2;
        }

        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        return items[StdRandom.uniform(N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        while (q.size() < 5) {
            q.enqueue(StdRandom.uniform(10));
        }

        Iterator<Integer> it = q.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next());
        }
    }
}
