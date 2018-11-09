import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException("Cannot have array as null");
        }
        if (Arrays.asList(points).contains(null)) {
            throw new IllegalArgumentException("Cannot have null value in Points array");
        }
        checkDuplicates(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> allSegments = new ArrayList<>();
        Arrays.sort(pointsCopy);
        for (int p = 0; p < pointsCopy.length - 3; ++p) {
            for (int q = p + 1; q < pointsCopy.length - 2; ++q) {
                for (int r = q + 1; r < pointsCopy.length - 1; ++r) {
                    for (int s = r + 1; s < pointsCopy.length; ++s) {
                        if (isCollinear(pointsCopy[p], pointsCopy[q], pointsCopy[r], pointsCopy[s])) {
                            LineSegment tempSegment = new LineSegment(pointsCopy[p], pointsCopy[s]);
                            if (!allSegments.contains(tempSegment)) {
                                allSegments.add(tempSegment);
                            }
                        }
                    }
                }
            }
        }
        segments = allSegments.toArray(new LineSegment[allSegments.size()]);
    }

    private boolean isCollinear(Point p, Point q, Point r, Point s) {
        return (p.slopeTo(q) == p.slopeTo(r)) && (p.slopeTo(q) == p.slopeTo(s));
    }


    private void checkDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Cannot have repeated points");
                }
            }
        }
    }


    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() { // the line segments
        return segments;
    }
}