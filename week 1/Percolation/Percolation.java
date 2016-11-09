/**
 * Created by t.simonov on 09.11.16.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private int nSites;
    private boolean[] openMatrix;
    private static final int TOP = 0;
    private int BOTTOM;
    private WeightedQuickUnionUF matrix;


    public Percolation(int n) {

        validate(n);
        this.n = n;
        nSites = n * n + 2;
        BOTTOM = nSites - 1;
        matrix = getMatrix(n);
        openMatrix = new boolean[nSites];
        setOpenMatrix();
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return openMatrix[convert2Dto1D(i, j)];
    }

    public void open(int i, int j) {
        if (isOpen(i, j)) return;
        openMatrix[convert2Dto1D(i, j)] = true;

        if (i < n && isOpen(i + 1, j)) {
            matrix.union(convert2Dto1D(i + 1, j), convert2Dto1D(i, j));
        }

        if (i > 1 && isOpen(i - 1, j)) {
            matrix.union(convert2Dto1D(i - 1, j), convert2Dto1D(i, j));
        }

        if (j < n && isOpen(i , j + 1)) {
            matrix.union(convert2Dto1D(i, j + 1), convert2Dto1D(i, j));
        }

        if (j > 1 && isOpen(i, j - 1)) {
            matrix.union(convert2Dto1D(i, j - 1), convert2Dto1D(i, j));
        }
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        return (isOpen(i, j) && matrix.connected(TOP, convert2Dto1D(i, j)));
    }

    public boolean percolates() {
        return matrix.connected(TOP, BOTTOM);
    }

    private WeightedQuickUnionUF getMatrix(int n) {
        validate(n);
        WeightedQuickUnionUF matrix = new WeightedQuickUnionUF(nSites);
        for (int i = 1; i <= n; i++) {
            matrix.union(TOP, i);
        }
        for (int i = BOTTOM - n; i < BOTTOM ; i++) {
            matrix.union(BOTTOM, i);
        }
        return matrix;
    }

    private void validate(int n) {
        if (n < 1) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private void validate(int i, int j) {
        if (i < 1 || j < 1) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private int convert2Dto1D(int i, int j) {
        validate(i, j);
        return (i - 1) * n + j;
    }

    private void setOpenMatrix() {
        openMatrix[TOP] = true;
        openMatrix[BOTTOM] = true;
        for (int i = 1; i < BOTTOM; i++) {
            openMatrix[i] = false;
        }
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        System.out.println(perc);
        System.out.println();
    }
}
