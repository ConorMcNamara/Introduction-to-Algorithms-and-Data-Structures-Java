import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PercolatesTest {

    @Test
    void Percolates_NegativeNumber_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Percolates(-1));
    }

    @Test
    void open_NegativeRow_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.open(-1, 1));
    }

    @Test
    void open_NegativeColumn_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.open(1,-1));
    }

    @Test
    void open_GreaterRow_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.open(3,1));
    }

    @Test
    void open_GreaterColumn_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.open(1,3));
    }

    @Test
    void isOpen_NegativeRow_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(-1, 1));
    }

    @Test
    void isOpen_NegativeColumn_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(1,-1));
    }

    @Test
    void isOpen_GreaterRow_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(3,1));
    }

    @Test
    void isOpen_GreaterColumn_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(1,3));
    }

    @Test
    void isFull_NegativeRow_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isFull(-1, 1));
    }

    @Test
    void isFull_NegativeColumn_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isFull(1,-1));
    }

    @Test
    void isFull_GreaterRow_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isFull(3,1));
    }

    @Test
    void isFull_GreaterColumn_IllegalArgumentException() {
        Percolates perc = new Percolates(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isFull(1,3));
    }

    @Test
    void percolates_FullyPercolates_True() {
        Percolates perc = new Percolates(1);
        perc.open(1,1);
        Assertions.assertTrue(perc.percolates());
    }

    @Test
    void percolates_FullyPercolates_False() {
        Percolates perc = new Percolates(1);
        Assertions.assertFalse(perc.percolates());
    }
}