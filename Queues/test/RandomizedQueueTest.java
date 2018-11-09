import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.NoSuchElementException;

class RandomizedQueueTest {

    @Test
    void isEmpty_True() {
        RandomizedQueue<Integer> rQueue = new RandomizedQueue<>();
        Assertions.assertTrue(rQueue.isEmpty());
    }

    @Test
    void isEmpty_False() {
        RandomizedQueue<Integer> rQueue = new RandomizedQueue<>();
        rQueue.enqueue(10);
        Assertions.assertFalse(rQueue.isEmpty());
    }

    @Test
    void enqueue_Null_IllegalArgumentException() {
        RandomizedQueue<Integer> rQueue = new RandomizedQueue<>();
        Assertions.assertThrows(IllegalArgumentException.class, ()-> rQueue.enqueue(null));
    }

    @Test
    void dequeue_isEmpty_NoSuchElementException() {
        RandomizedQueue<Integer> rQueue = new RandomizedQueue<>();
        Assertions.assertThrows(NoSuchElementException.class, ()-> rQueue.dequeue());
    }

    @Test
    void sample_IsEmpty_NoSuchElementException() {
        RandomizedQueue<Integer> rQueue = new RandomizedQueue<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> rQueue.sample());
    }
}