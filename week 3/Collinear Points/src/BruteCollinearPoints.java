/**
 * Created by varos on 23.11.2016.
 */
import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {

        validatePoints(points);
        Arrays.sort(points);

        int len = points.length;
        lineSegments = new ArrayList<LineSegment>();

        for (int p = 0; p < len; p++) {
            for (int q = p + 1; q < len; q++) {
                double pqSlope = points[p].slopeTo(points[q]);
//                if (pqSlope == Double.NEGATIVE_INFINITY) continue;
                for (int r = q + 1; r < len; r++) {
                    double prSlope = points[p].slopeTo(points[r]);
                    if (prSlope == pqSlope) {
//                        double qrSlope = points[q].slopeTo(points[r]);
//                        if (qrSlope == Double.NEGATIVE_INFINITY) continue;
                        for (int s = r + 1; s < len; s++) {
                            double psSlope = points[p].slopeTo(points[s]);
                            double rsSlope = points[r].slopeTo(points[s]);
                            if (rsSlope == pqSlope){
//                                double qsSlope = points[q].slopeTo(points[s]);
//                                if (psSlope == Double.NEGATIVE_INFINITY) continue;
//                                if (qsSlope == Double.NEGATIVE_INFINITY) continue;
                                LineSegment segment = new LineSegment(points[p], points[s]);
                                lineSegments.add(segment);
                            }
                        }
                    }
                }
            }
        }
    }

    public           int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] result = lineSegments.toArray(new LineSegment[numberOfSegments()]);
        return  result;
    }

    private void validatePoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.NullPointerException();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
    }

}
