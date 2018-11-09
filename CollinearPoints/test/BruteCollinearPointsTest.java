import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BruteCollinearPointsTest {

    @Test
    void BruteCollinearPoints_NullInput_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null));
    }

    @Test
    void BruteCollinearPoints_RepeatPoint_IllegalArgumentException() {
        Point[] points = new Point[] { new Point(1,1), new Point(2,2), new Point(1,1), new Point(3,3)};
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(points));
    }

    @Test
    void BruteCollinearPoints_NullInArray_IllegalArgumentException() {
        Point[] points = new Point[] {new Point(1,1), null, new Point(2,2), new Point(3,3)};
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(points));
    }




}