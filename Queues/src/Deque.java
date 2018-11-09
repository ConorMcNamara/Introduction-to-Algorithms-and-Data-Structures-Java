import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head = new Node<>(null);
    private Node<Item> tail = new Node<>(null);
    private int size;

    private static class Node<Item> {
        public Node<Item> prev, next;
        private Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    public Deque() { // construct an empty deque
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public boolean isEmpty() {// is the deque empty?
        return size == 0;
    }

    public int size() { // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) { // add the item to the front
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> oldHead = head.next;
        Node<Item> newHead = new Node<Item>(item);
        head.next = newHead;
        newHead.prev = head;
        newHead.next = oldHead;
        oldHead.prev = newHead;
        size++;
    }

    public void addLast(Item item) { // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> oldTail = tail.prev;
        Node<Item> newTail = new Node<Item>(item);
        tail.prev = newTail;
        newTail.next = tail;
        newTail.prev = oldTail;
        oldTail.next = newTail;
        size++;
    }

    public Item removeFirst() { // remove and return the item from the front
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node<Item> oldHead = head.next;
        Node<Item> newHead = oldHead.next;
        head.next = newHead;
        newHead.prev = head;
        oldHead.next = null;
        oldHead.prev = null;
        size--;
        return oldHead.item;
    }

    public Item removeLast() { //remove and return the item from the back
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node<Item> oldTail = tail.prev;
        Node<Item> newTail = oldTail.prev;
        tail.prev = newTail;
        newTail.next = tail;
        oldTail.next = null;
        oldTail.prev = null;
        size--;
        return oldTail.item;
    }

    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = head.next;

        @Override
        public boolean hasNext() {
            return current != tail;
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
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> a = new Deque<Integer>();
        System.out.println(a.isEmpty());
        a.addFirst(10);
        a.addFirst(15);
        a.addLast(5);
        Iterator<Integer> iter = a.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}