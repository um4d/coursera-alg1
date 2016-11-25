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

        for (int i = 0; i < len - 1; i++) {

            List<Point> monoSlope = new ArrayList<Point>();
            System.out.println("new monoslope");

            Point p = points[i];
            Arrays.sort(copyPoints, p.SLOPE_ORDER);

            for (int j = 1; j < len - 1; j++) {
                if (p.slopeTo(copyPoints[j]) == p.slopeTo(copyPoints[j + 1])) {
                    monoSlope.add(copyPoints[j + 1]);
                } else if (monoSlope.size() >= 4){
                    lineSegments.add(new LineSegment(p, copyPoints[j + 1]));
                    
                }
            }
        }




//            for (int j = 1; j < len - 1; j++) {
//                Point q = copyPoints[j];
//                Point r = copyPoints[j + 1];
//                Double curSlope = p.slopeTo(q);
//
//                monoSlope.add(p);
//                monoSlope.add(q);
//
//                if (p.slopeTo(r) == curSlope) {
//                    monoSlope.add(r);
//                    if(monoSlope.size() == 4) {
//                        lineSegments.add(new LineSegment(p, copyPoints[j]));
//                        System.out.println(monoSlope.size());
//                        monoSlope = new ArrayList<Point>();
//                    }
//                }
//            }

    }


    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] result = lineSegments.toArray(new LineSegment[numberOfSegments()]);
        return  result;
    }
//    private void validateSlopeOrder (Point[] points) {
//        for (int i = 0; i < points.length - 1; i++) {
//            System.out.println(points[i].slopeTo(points[i + 1]));
//        }
//    }

}
