import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * Created by t.simonov on 25.11.16.
 */
public class SlopeOrderTest {
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }


        System.out.println(Arrays.toString(points));
        Arrays.sort(points, points[0].SLOPE_ORDER);
        System.out.println(Arrays.toString(points));
        Double[] points2 = new Double[points.length];
        for (int i = 0; i < points.length - 1; i++) {
            System.out.println(points[0].slopeTo(points[i]));
            points2[i] = points[i].slopeTo(points[i + 1]);
            points2[points2.length-1] = 0.0;
        }
        Arrays.sort(points2);
        System.out.println(Arrays.toString(points2));
    }
}
