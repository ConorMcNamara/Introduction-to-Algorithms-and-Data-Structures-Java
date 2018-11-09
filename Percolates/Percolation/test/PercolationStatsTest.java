import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class PercolationStatsTest {

    @Test
    void PercolationStats_NegativeN_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PercolationStats(-1, 100));
    }

    @Test
    void PercolationStats_NegativeTrials_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PercolationStats(10, -1));
    }

}