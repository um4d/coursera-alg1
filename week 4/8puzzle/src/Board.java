/**
 * Created by t.simonov on 01.12.16.
 */
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;

public class Board {

    private int[][] blocks;
    private int N;

    public Board(int[][] blocks) {

        N = blocks.length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }


    }


    public int dimension() {

        return N;

    }
    public int hamming() {

        int hamming = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != 0 && (blocks[i][j] != goalValue(i, j)) ) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    public int manhattan() {

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = blocks[i][j];
                result += Math.abs(getI(value) - i) + Math.abs(getJ(value) - j);
            }
        }
        return result;
    }

    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = blocks[i][j];
                if (value != goalValue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        Board twin = new Board(this.blocks);
        int i = StdRandom.uniform(N);
        int j = StdRandom.uniform(N);
        while (blocks[i][j] == 0) {
            j = StdRandom.uniform(N);
        }
        int k = StdRandom.uniform(N);
        int m = StdRandom.uniform(N);
        while (blocks[k][m] == 0 || j == m) {
            m = StdRandom.uniform(N);
        }
        twin.blocks[i][j] = this.blocks[k][m];
        twin.blocks[k][m] = this.blocks[i][j];
        return twin;
    }

    public boolean equals(Object y) {

        Board second = (Board) y;
        int secondDimension = second.dimension();
        int thisDimenshion = this.dimension();

        if (secondDimension != thisDimenshion) return false;

        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                int thisValue = this.blocks[i][j];
                int secondValue = ((Board) y).getBlockValue(i, j);
                if (thisValue != secondValue) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();



    }


    public String toString() {
        String result = "";
        for (int[] block : blocks) {
            for (int value : block) {
                result += value + "  ";
            }
            result += "\n";
        }
        return result;
    }

    private int goalValue (int i, int j) {
        return N * i + j + 1;
    }

    private int getI (int value) {
        int i = (value - 1) / N;
        return i;
    }

    private int getJ (int value) {
        int j = (value - 1) % N;
        return j;
    }


    private int getBlockValue (int i, int j) {
        return blocks[i][j];
    }

    public static void main(String[] args) {

    }
}
