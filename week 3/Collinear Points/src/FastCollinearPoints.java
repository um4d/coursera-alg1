/**
 * Created by t.simonov on 24.11.16.
 */
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        lineSegments = new ArrayList<LineSegment>();

        int len = points.length;
        Arrays.sort(points);
        Point[] copyPoints = points.clone();

        for (int i = 0; i < len - 3; i++) {
            Point p = points[i];
            Arrays.sort(copyPoints);
            Arrays.sort(copyPoints, p.slopeOrder());
            for (int q = 1, r = 2; r < len; r++) {
                while( r < len && p.slopeTo(copyPoints[q]) == p.slopeTo(copyPoints[r])) {
                    r++;
                }
                if ((r - q >= 3) && p.compareTo(copyPoints[q]) < 0) {
                    lineSegments.add(new LineSegment(p, copyPoints[r - 1]));
                }
                q = r;
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] result = lineSegments.toArray(new LineSegment[numberOfSegments()]);
        return result;
    }
}