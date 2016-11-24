/**
 * Created by varos on 23.11.2016.
 */
import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints {

    private int count;
    ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {

        int len = points.length;
        count = 0;
        lineSegments = new ArrayList<LineSegment>();

        // Arrays.sort(points);

        for (int p = 0; p < len; p++) {
            for (int q = p + 1; q < len; q++) {
                double pqSlope = points[p].slopeTo(points[q]);
                if (pqSlope == Double.NEGATIVE_INFINITY) return;
                for (int r = q + 1; r < len; r++) {
                    double prSlope = points[p].slopeTo(points[r]);
                    if (prSlope == Double.NEGATIVE_INFINITY) return;
                    if (pqSlope == prSlope) {
                        for (int s = r + 1; s < len; s++) {
                            double psSlope = points[p].slopeTo(points[s]);
                            if (pqSlope == psSlope) {
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
}
