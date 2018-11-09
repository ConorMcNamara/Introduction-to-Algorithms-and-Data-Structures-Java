import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class DequeTest {

    @Test
    void check_IsEmpty_True() {
        Deque<Integer> deque = new Deque<>();
        Assertions.assertTrue(deque.isEmpty());
    }

    @Test
    void check_IsEmpty_False() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        Assertions.assertFalse(deque.isEmpty());
    }

    @Test
    void addFirst_NullIllegalArgumentException() {
        Deque<Integer> deque = new Deque<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
    }

    @Test
    void addLast_NullIllegalArgumentException() {
        Deque<Integer> deque = new Deque<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
    }

    @Test
    void removeFirst_IsEmpty_NoSuchElementException() {
        Deque<Integer> deque = new Deque<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
    }

    @Test
    void removeLast_IsEmpty_NoSuchElementException() {
        Deque<Integer> deque = new Deque<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> deque.removeLast());
    }

}