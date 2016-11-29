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

            System.out.println("----------------------------");
            System.out.println("P = " + i);
            Point p = points[i];
            Arrays.sort(copyPoints, p.slopeOrder());
            int count = 1;
            Point[] monoSlope = new Point[len];
            monoSlope[0] = p;


            for (int j = 1; j < len - 1; j++) {
                monoSlope[1] = copyPoints[j];
                System.out.println("p -> " + (j + 1) + " " + p.slopeTo(copyPoints[j + 1]) + " p -> " + j + " " + p.slopeTo(copyPoints[j]));
                if (p.slopeTo(copyPoints[j + 1]) == p.slopeTo(copyPoints[j])) {
                    monoSlope[count + 1] = copyPoints[j + 1];
                    count++;
                } else if (count > 2) {
                    Point[] trueMonoSlope = new Point[count + 1];
                    System.arraycopy(monoSlope, 0, trueMonoSlope, 0, trueMonoSlope.length);
                    Arrays.sort(trueMonoSlope);
                    LineSegment line = new LineSegment(trueMonoSlope[0], trueMonoSlope[count]);
                    count = 1;
                    if (!lineEqual(line)) {
                        lineSegments.add(line);
                        break;
                    }

                }

//                    if (j == len - 2 && count > 3) {
//                        lineSegments.add(new LineSegment(copyPoints[j - 1], copyPoints[j + count - 2]));
//                    }
//                } else {
//                    Arrays.sort(copyPoints, f, f + count);
//                    LineSegment line = new LineSegment(copyPoints[f], copyPoints[f + count - 1]);
//                    if (count > 3 && !lineEqual(line) ) {
//                        lineSegments.add(line);
//                        j = f + count - 2;
//                        count = 1;
//                    }
//                    f = j;
//                }
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