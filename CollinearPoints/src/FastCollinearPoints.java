import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Cannot have array as null");
        }
        if (Arrays.asList(points).contains(null)) {
            throw new IllegalArgumentException("Cannot have null value in Points array");
        }
        checkDuplicates(points);
        Point[] pointsSlopeOrder = points.clone();
        Point[] pointsPointOrder = points.clone();
        ArrayList<LineSegment> segmentsList = new ArrayList<>();
        Arrays.sort(pointsPointOrder);
        for (int i = 0; i < pointsPointOrder.length; ++i) {
            Point origin = pointsPointOrder[i];
            Arrays.sort(pointsSlopeOrder);
            Arrays.sort(pointsSlopeOrder, origin.slopeOrder());
            int count = 1;
            Point pivotPoint = null;
            for (int j = 0; j < pointsSlopeOrder.length - 1; ++j) {
                if (pointsSlopeOrder[j].slopeTo(origin) == pointsSlopeOrder[j + 1].slopeTo(origin)) {
                    count++;
                    if (count == 2) {
                        pivotPoint = pointsSlopeOrder[j];
                        count++;
                    } else if (count >= 4 && j + 1 == pointsSlopeOrder.length - 1) {
                        if (pivotPoint.compareTo(origin) > 0) {
                            segmentsList.add(new LineSegment(origin, pointsSlopeOrder[j + 1]));
                        }
                        count = 1;
                    }
                } else if (count >= 4) {
                    if (pivotPoint.compareTo(origin) > 0) {
                        segmentsList.add(new LineSegment(origin, pointsSlopeOrder[j]));
                    }
                    count = 1;
                } else {
                    count = 1;
                }
            }

        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
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