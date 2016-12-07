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
                if (blocks[i][j] != 0) {
                    int value = blocks[i][j];
                    result += Math.abs(getI(value) - i) + Math.abs(getJ(value) - j);
                }
            }
        }
        return result;
    }

    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != 0) {
                    if (blocks[i][j] != goalValue(i, j)) {
                        return false;
                    }
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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] == 0) {

                    if (i > 0) {
                        Board topBoard = new Board(this.blocks.clone());
                        topBoard.exch(i, j, i - 1, j);
                        neighbors.add(topBoard);
                    }
                    if (j < N - 1) {
                        Board rightBoard = new Board(this.blocks.clone());
                        rightBoard.exch(i, j, i, j + 1);
                        neighbors.add(rightBoard);
                    }
                    if (i < N - 1) {
                        Board bottomBoard = new Board(this.blocks.clone());
                        bottomBoard.exch(i, j, i + 1, j);
                        neighbors.add(bottomBoard);
                    }
                    if (j > 0) {
                        Board leftBoard = new Board(this.blocks.clone());
                        leftBoard.exch(i, j, i, j - 1);
                        neighbors.add(leftBoard);
                    }
                    return neighbors;
                }
            }
        }
        return null;
    }
    
    public String toString() {
        String result = N + "\n";
        result += this.manhattan() + "\n";
        for (int[] block : blocks) {
            for (int value : block) {
                result += " " + value;
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

    private void exch(int i1, int j1, int i2, int j2) {

        int tmp = this.blocks[i1][j1];
        this.blocks[i1][j1] = this.blocks[i2][j2];
        this.blocks[i2][j2] = tmp;
    }

    public static void main(String[] args) {
        int[][] block = {{1,2,3},{4,5,6},{7,8,0}};

        Board box = new Board(block);
        int x = 8;
        System.out.println(box.toString());
        for (Board board : box.neighbors()) {
            System.out.println(board.toString());
        }
    }
}
