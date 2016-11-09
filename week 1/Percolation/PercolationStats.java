/**
 * Created by varos on 06.11.2016.
 */
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private int [] count;
    private int trials;
    private int nq;

    public PercolationStats(int p, int trials) {

        if (p <= 0 || trials <= 0 ){
            throw new java.lang.IllegalArgumentException();
        }
        int n = p;
        this.trials = trials;
        count = new int[trials];
        nq = n * n;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            count[i] = 0;
            while (!percolation.percolates()) {
                int y = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(y, j)) {
                    percolation.open(y, j);
                    count[i]++;
                }
            }
        }
    }

    public double mean() {
        return StdStats.mean(count) / nq;
    }

    public double stddev() {
        return StdStats.stddev(count) / (nq - 1);
    }

    public double confidenceLo() {
        return StdStats.mean(count) / nq - (1.96 * (StdStats.stddev(count)/(nq - 1) / Math.sqrt(trials)));
    }

    public double confidenceHi() {
        return StdStats.mean(count) / nq + (1.96 * (StdStats.stddev(count)/(nq - 1) / Math.sqrt(trials)));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.print("95% confidence interval = " + percolationStats.confidenceLo());
        StdOut.println(", " + percolationStats.confidenceHi());

    }

}