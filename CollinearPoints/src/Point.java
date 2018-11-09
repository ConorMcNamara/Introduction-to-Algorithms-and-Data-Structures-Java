import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) { // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() { // draws this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) { // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public String toString() { // string representation
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point that) { // compare two points by y-coordinates, breaking ties by x-coordinates
        if (this.y > that.y) {
            return 1;
        } else if (this.y == that.y) {
            if (this.x > that.x) {
                return 1;
            } else if (this.x == that.x) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public double slopeTo(Point that) { // the slope between this point and that point
        if (that.x == this.x && that.y != this.y) { //test for vertical line
            return Double.POSITIVE_INFINITY;
        } else if (that.x != this.x && that.y == this.y) { //test for horizontal line
            return +0.0;
        } else if (that.x == this.x && that.y == this.y) { //test for same point
            return Double.NEGATIVE_INFINITY;
        }
        return ((that.y - this.y) / (double) (that.x - this.x));
    }

    public Comparator<Point> slopeOrder() { // compare two points by slopes they make with this point
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double slope1 = slopeTo(o1);
                double slope2 = slopeTo(o2);
                if (slope1 > slope2) {
                    return 1;
                } else if (slope1 == slope2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        };
    }

    public static void main(String[] args) {
        Point p = new Point(4, 8);
        Point q = new Point(4, 6);
        Point r = new Point(4, 4);
        System.out.println(p.slopeOrder().compare(q, r));
    }

}

