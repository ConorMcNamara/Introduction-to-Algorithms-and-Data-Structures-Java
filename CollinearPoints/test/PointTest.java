import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PointTest{

    @Test
    void compareTo_SamePoint_Zero() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(1,1);
        Assertions.assertEquals(0, p1.compareTo(p2));
    }

    @Test
    void compareTo_SameYSmallerX_NegativeOne() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(5, 1);
        Assertions.assertEquals(-1, p1.compareTo(p2));
    }

    @Test
    void compareTo_SameYBiggerX_PositiveOne() {
        Point p1 = new Point(5,1);
        Point p2 = new Point(0,1);
        Assertions.assertEquals(1, p1.compareTo(p2));
    }

    @Test
    void compareTo_BiggerY_NegativeOne() {
        Point p1 = new Point(2,1);
        Point p2 = new Point(1, 5);
        Assertions.assertEquals(-1, p1.compareTo(p2));
    }

    @Test
    void slopeTo_HorizontalLine_PositiveZero() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(5, 1);
        Assertions.assertEquals(+0.0, p1.slopeTo(p2));
    }

    @Test
    void slopeTo_VerticalLine_PositiveInfinity() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(1,5);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2));
    }

    @Test
    void slopeTo_SamePoint_NegativeInfinity() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(1,1);
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2));
    }


    @Test
    void slopeOrder_SamePoint_Zero() {
        Point p = new Point(4,8);
        Point q = new Point(4,6);
        Assertions.assertEquals(0, p.slopeOrder().compare(q, q));
    }

    @Test
    void slopeOrder_VerticalLine_Zero() {
        Point p = new Point(4,8);
        Point q = new Point(4,6);
        Point r = new Point(4,4);
        Assertions.assertEquals(0, p.slopeOrder().compare(q, r));
    }

    @Test
    void slopeOrder_HorizontalLine_Zero() {
        Point p = new Point(8,4);
        Point q = new Point(6,4);
        Point r = new Point(4,4);
        Assertions.assertEquals(0, p.slopeOrder().compare(q, r));
    }

    @Test
    void slopeOrder_PointOneHigherSlope_One() {
        Point p = new Point(1,1);
        Point q = new Point(2,2);
        Point r = new Point(2,-2);
        Assertions.assertEquals(1, p.slopeOrder().compare(q, r));
    }

    @Test
    void slopeOrder_PointTwoHigherSlope_NegativeOne() {
        Point p = new Point(1,1);
        Point q = new Point(2,-2);
        Point r = new Point(2,2);
        Assertions.assertEquals(-1, p.slopeOrder().compare(q, r));
    }
}