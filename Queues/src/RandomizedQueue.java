import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rQueue;
    private int size;

    public RandomizedQueue() { // construct an empty randomized queue
        this.rQueue = (Item[]) new Object[2];
        size = 0;
    }

    public boolean isEmpty() { // is the randomized queue empty?
        return size == 0;
    }

    public int size() {  // return the number of items on the randomized queue
        return size;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = rQueue[i];
        }
        rQueue = temp;
    }

    public void enqueue(Item item) { // add the item
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == rQueue.length) {
            resize(2 * rQueue.length);
        }
        rQueue[size++] = item;
    }

    private int randomDraw() {
        return StdRandom.uniform(size);
    }

    public Item dequeue() { // remove and return a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int removeIndex = randomDraw();
        Item returnItem = rQueue[removeIndex];
        size--;
        rQueue[removeIndex] = rQueue[size];
        rQueue[size] = null;
        if (size > 0 && size == rQueue.length / 4) {
            resize(rQueue.length / 2);
        }
        return returnItem;
    }

    public Item sample() { // return a random item (but do not remove it)
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = randomDraw();
        Item returnItem = rQueue[index];
        return returnItem;
    }

    @Override
    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int index;
        private Item [] randomArray;

        public RandomizedIterator() {
            randomArray = (Item []) new Object[size];
            for (int i = 0; i < size; i++) {
                randomArray[i] = rQueue[i];
            }
            StdRandom.shuffle(randomArray);
        }

        @Override
        public boolean hasNext() {
            return index < randomArray.length;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return randomArray[index++];
        }
    }

    public static void main(String[] args) { // unit testing (optional)
        RandomizedQueue<Integer> a = new RandomizedQueue<>();
        System.out.println(a.isEmpty());
        a.enqueue(10);
        System.out.println(a.isEmpty());
        a.enqueue(20);
        a.enqueue(30);
        Iterator<Integer> iter = a.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}