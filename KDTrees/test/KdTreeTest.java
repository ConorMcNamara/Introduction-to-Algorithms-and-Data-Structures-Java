import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KdTreeTest {

    @Test
    void isEmpty_NoValues_True() {
        KdTree kdTree = new KdTree();
        Assertions.assertTrue(kdTree.isEmpty());
    }

    @Test
    void isEmpty_ContainsValues_False() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.75));
        Assertions.assertFalse(kdTree.isEmpty());
    }

    @Test
    void insert_NullInput_IllegalArgumentException() {
        KdTree kdTree = new KdTree();
        Assertions.assertThrows(IllegalArgumentException.class, () -> kdTree.insert(null));
    }

    @Test
    void contains_NullInput_IllegalArgumentException() {
        KdTree kdTree = new KdTree();
        Assertions.assertThrows(IllegalArgumentException.class, () -> kdTree.contains(null));
    }

    @Test
    void range_NullInput_IllegalArgumentException() {
        KdTree kdTree = new KdTree();
        Assertions.assertThrows(IllegalArgumentException.class, () -> kdTree.range(null));
    }

    @Test
    void nearest_NullInput_IllegalArgumentException() {
        KdTree kdTree = new KdTree();
        Assertions.assertThrows(IllegalArgumentException.class, () -> kdTree.contains(null));
    }

    @Test
    void contains_ActualPoint_True() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.5, 0.5));
        Assertions.assertTrue(kdTree.contains(new Point2D(0.25, 0.25)));
    }

    @Test
    void contains_NoActualPoint_False() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.5, 0.5));
        Assertions.assertFalse(kdTree.contains(new Point2D(0.25, 0.5)));
    }

    @Test
    void nearest_PointInput_Value() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.5, 0.5));
        Assertions.assertEquals(new Point2D(0.5, 0.5), kdTree.nearest(new Point2D(0.7, 0.7)));
    }
}