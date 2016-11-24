/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;
    public final Comparator<Point> SLOPE_ORDER = slopeOrder();

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    public double slopeTo(Point that) {

        int x0 = this.x;
        int y0 = this.y;
        int x1 = that.x;
        int y1 = that.y;

        if (y0 == y1 && x0 == x1) {
            return Double.NEGATIVE_INFINITY;
        } else if (x0 == x1) {
            return Double.POSITIVE_INFINITY;
        } else if (y0 == y1) {
            return +0.0;
        } else return (double)(y1 - y0) / (x1 - x0);
    }

    public int compareTo(Point that) {

        int x0 = this.x;
        int y0 = this.y;
        int x1 = that.x;
        int y1 = that.y;

        int result = y1 - y0;
        if (result == 0) {
            result = x1 - x0;
        }
        return result;
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeComparator();
    }

    private class SlopeComparator implements Comparator<Point> {

        public int compare(Point p1, Point p2) {

            double s1 = slopeTo(p1);
            double s2 = slopeTo(p2);

            if (s1 == s2) return 0;
            if (s1 - s2 > 0) return 1;
            return -1;
        }
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}