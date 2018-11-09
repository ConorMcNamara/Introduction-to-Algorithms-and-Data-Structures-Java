import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PointSETTest {

    @Test
    void isEmpty_ActuallyEmpty_True() {
        PointSET pointSET = new PointSET();
        Assertions.assertTrue(pointSET.isEmpty());
    }

    @Test
    void isEmpty_ContainsValues_False() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(1, 1));
        Assertions.assertFalse(pointSET.isEmpty());
    }

    @Test
    void insert_NullInput_IllegalArgumentException() {
        PointSET pointSET = new PointSET();
        Assertions.assertThrows(IllegalArgumentException.class, () -> pointSET.insert(null));
    }

    @Test
    void contains_NullInput_IllegalArgumentException() {
        PointSET pointSET = new PointSET();
        Assertions.assertThrows(IllegalArgumentException.class, () -> pointSET.contains(null));
    }

    @Test
    void range_NullInput_IllegalArgumentException() {
        PointSET pointSet = new PointSET();
        Assertions.assertThrows(IllegalArgumentException.class, () -> pointSet.range(null));
    }

    @Test
    void nearest_NullInput_IllegalArgumentException() {
        PointSET pointSET = new PointSET();
        Assertions.assertThrows(IllegalArgumentException.class, () -> pointSET.nearest(null));
    }

    @Test
    void contains_ActualPoint_True() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.25, 0.25));
        pointSET.insert(new Point2D(0.5, 0.5));
        Assertions.assertTrue(pointSET.contains(new Point2D(0.25, 0.25)));
    }

    @Test
    void contains_NoActualPoint_False() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.25, 0.25));
        pointSET.insert(new Point2D(0.5, 0.5));
        Assertions.assertFalse(pointSET.contains(new Point2D(0.25, 0.5)));
    }

    @Test
    void nearest_PointInput_Value() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.25, 0.25));
        pointSET.insert(new Point2D(0.5, 0.5));
        Assertions.assertEquals(new Point2D(.25, .25), pointSET.nearest(new Point2D(0.1, 0.1)));
    }

}