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
        Point[] copyPoints = new Point[len];
        System.arraycopy(points, 0, copyPoints, 0, len);

        for (int i = 0; i < len; i++) {

            Point p = points[i];
            Arrays.sort(copyPoints, p.slopeOrder());
            int count = 2;
            int f = 1;

            for (int j = 1; j < len - 1; j++) {
                if (p.slopeTo(copyPoints[j + 1]) == p.slopeTo(copyPoints[j])) {
                    count++;
                    if (j == len - 2 && count > 3) {
                        lineSegments.add(new LineSegment(copyPoints[j - 1], copyPoints[j + count - 2]));
                    }
                } else if (count > 3) {
                    lineSegments.add(new LineSegment(copyPoints[j - 1], copyPoints[j + count - 2]));
                    j = j + count - 2;
                    count = 2;
                }
            }
        }
    }

    private boolean lineEqual(LineSegment line) {
        for (LineSegment segment : lineSegments) {
            if (segment.toString().equals(line.toString())) {
                return true;
            }
        }
        return false;
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] result = lineSegments.toArray(new LineSegment[numberOfSegments()]);
        return result;
    }
}