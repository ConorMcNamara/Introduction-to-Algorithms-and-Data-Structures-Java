import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class KdTree {
    private static final double X_MIN = 0.0;
    private static final double X_MAX = 1.0;
    private static final double Y_MIN = 0.0;
    private static final double Y_MAX = 1.0;
    private int size;
    private Node root;

    public KdTree() {
        this.size = 0;
        this.root = null;
    }

    public boolean isEmpty() { // is the tree empty?
        return size == 0;
    }

    public int size() { // number of points in the set
        return size;
    }

    private int comparePoints(Point2D point1, Point2D point2, int level) {
        if (level % 2 == 0) {
            int xComparison = Double.compare(point1.x(), point2.x());
            if (xComparison == 0) {
                return Double.compare(point1.y(), point2.y());
            } else {
                return xComparison;
            }
        } else {
            int yComparison = Double.compare(point1.y(), point2.y());
            if (yComparison == 0) {
                return Double.compare(point1.x(), point2.x());
            } else {
                return yComparison;
            }
        }
    }

    private Node insert(Node root, Point2D value, double xMin, double yMin, double xMax, double yMax, int level) {
        if (root == null) {
            size++;
            return new Node(value, new RectHV(xMin, yMin, xMax, yMax));
        }
        int comparison = comparePoints(value, root.point, level);
        if (comparison < 0) {
            if (level % 2 == 0) {
                root.left_or_down = insert(root.left_or_down, value, xMin, yMin, root.point.x(), yMax, level + 1);
            } else {
                root.left_or_down = insert(root.left_or_down, value, xMin, yMin, xMax, root.point.y(), level + 1);
            }
        } else if (comparison > 0) {
            if (level % 2 == 0) {
                root.right_or_up = insert(root.right_or_up, value, root.point.x(), yMin, xMax, yMax, level + 1);
            } else {
                root.right_or_up = insert(root.right_or_up, value, xMin, root.point.y(), xMax, yMax, level + 1);
            }
        }
        return root;

    }

    public void insert(Point2D p) { // add the point to the tree (if it is not already in the set)
        if (p == null) {
            throw new IllegalArgumentException("Cannot have null point");
        }
        root = insert(root, p, X_MIN, Y_MIN, X_MAX, Y_MAX, 0);
    }

    private Point2D contains(Node node, Point2D p, int level) {
        while (node != null) {
            int compare = comparePoints(p, node.point, level);
            if (compare < 0) {
                return contains(node.left_or_down, p, level);
            } else if (compare > 0) {
                return contains(node.right_or_up, p, level);
            } else {
                return node.point;
            }
        }
        return null;
    }

    public boolean contains(Point2D p) { // does the tree contain point p?
        if (p == null) {
            throw new IllegalArgumentException("Cannot have null point");
        }
        return (contains(root, p, 0) != null);
    }

    private void drawLine (Node node, int level) {
        if (node != null) {
            drawLine(node.left_or_down, level + 1);

            StdDraw.setPenRadius();
            if (level % 2 == 0) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.point.x(), node.rectHV.ymin(), node.point.x(), node.rectHV.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(node.rectHV.xmin(), node.point.y(), node.rectHV.xmax(), node.point.y());
            }

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            node.point.draw();

            drawLine(node.right_or_up, level + 1);
        }
    }

    public void draw() {
        drawLine(root, 0);
    }

    private void addRange(Node node, RectHV rectHV, ArrayList<Point2D> arrayList) {
        if (node != null && rectHV.intersects(node.rectHV)) {
            if (rectHV.contains(node.point)) {
                arrayList.add(node.point);
            }
            addRange(node.left_or_down, rectHV, arrayList);
            addRange(node.right_or_up, rectHV, arrayList);
        }
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null) {
            throw new IllegalArgumentException("Cannot have null rectangle");
        }
        ArrayList<Point2D> arrayList = new ArrayList<>();
        addRange(root, rect, arrayList);
        return arrayList;
    }

    private Point2D nearest(Node node, Point2D p, Point2D min) {
        if (node != null) {
            if (min == null) {
                min = node.point;
            }
            if (min.distanceSquaredTo(p) >= node.rectHV.distanceSquaredTo(p)) {
                if (node.point.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                    min = node.point;
                }
                if (node.right_or_up != null && node.right_or_up.rectHV.contains(p)) {
                    min = nearest(node.right_or_up, p, min);
                    min = nearest(node.left_or_down, p, min);
                } else {
                    min = nearest(node.left_or_down, p, min);
                    min = nearest(node.right_or_up, p, min);
                }
            }
        }
        return min;
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the tree to point p; null if the set is empty
        if (p == null) {
            throw new IllegalArgumentException("Cannot have null point");
        }
        if (isEmpty()) {
            return null;
        } else {
            Point2D result = null;
            result = nearest(root, p, result);
            return result;
        }
    }

    private static class Node {
        private Node left_or_down, right_or_up;
        private Point2D point;
        private RectHV rectHV;

        public Node(Point2D p, RectHV rect) {
            this.point = p;
            this.rectHV = rect;
        }
    }

    public static void main(String[] args) { // unit testing of the methods (optional)

    }
}
