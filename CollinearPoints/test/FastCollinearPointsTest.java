import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FastCollinearPointsTest {

    @Test
    void FastCollinearPoints_NullInput_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> new FastCollinearPoints(null));
    }

    @Test
    void FastCollinearPoints_NullInArray_IllegalArgumentException() {
        Point points[] = new Point[] {new Point(1,1), null, new Point(2,2), new Point(3,3)};
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(points));
    }

    @Test
    void FastCollinearPoints_RepeatPoint_IllegalArgumentException() {
        Point[] points = new Point[] {new Point(1,1), new Point(2,2), new Point(1,1), new Point(3,3)};
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(points));
    }

}