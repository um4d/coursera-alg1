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

        for (int i = 0; i < len - 3; i++) {

            Point p = points[i];
            Arrays.sort(copyPoints, p.SLOPE_ORDER);
//            Arrays.sort(copyPoints, i, i + 4);
            List<Point> monoSlope = new ArrayList<Point>();
            System.out.println("new monoslope");
            System.out.println(monoSlope.size());
            monoSlope.add(p);
            monoSlope.add(copyPoints[i + 1]);
            Double curSlope = p.slopeTo(copyPoints[i + 1]);
            for (int j = i + 2; j < i + 4 && j < len; j++) {
                System.out.println(curSlope+ " | " + p.slopeTo(copyPoints[j]) + " | " + ((p.slopeTo(copyPoints[j]) == curSlope)));
                if (p.slopeTo(copyPoints[j]) != curSlope) { continue; }
                else {
                    monoSlope.add(copyPoints[j]);
                    if(monoSlope.size() == 4) {
                        lineSegments.add(new LineSegment(p, copyPoints[j]));
                        System.out.println(monoSlope.size());
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] result = lineSegments.toArray(new LineSegment[numberOfSegments()]);
        return  result;
    }

}
