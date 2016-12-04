/**
 * Created by t.simonov on 01.12.16.
 */
import edu.princeton.cs.algs4.StdRandom;

public class Board {

    private int[][] blocks;

    public Board(int[][] blocks) {

        this.blocks = blocks;

    }
    public int dimension() {

        return blocks.length * blocks[0].length;

    }
    public int hamming() {

        int humming = 0;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j <blocks[0].length; j++) {
                if (blocks[i][j] != 0 && (blocks[i][j] != goalValue(i, j)) ) {
                    humming++;
                }
            }
        }
        return humming;
    }

    public int manhattan() {

        int result = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                int value = blocks[i][j];
                result += Math.abs(getI(value) - i) + Math.abs(getJ(value) - j);
            }
        }
        return result;
    }

    public boolean isGoal() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                int value = blocks[i][j];
                if (value != goalValue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        Board twin = new Board(this.blocks.clone());
        int i = StdRandom.uniform(blocks.length);
        int j = StdRandom.uniform(blocks[0].length);
        while (twin.getBlockValue(i, j) == 0) {
            i = StdRandom.uniform(blocks.length);
            j = StdRandom.uniform(blocks[0].length);
        }
        int m = StdRandom.uniform(blocks.length);
        int n = StdRandom.uniform(blocks[0].length);
        while (twin.getBlockValue(m, n) == 0) {
            m = StdRandom.uniform(blocks.length);
            n = StdRandom.uniform(blocks[0].length);
        }
        twin.blocks[i][j] = this.blocks[m][n];
        twin.blocks[m][n] = this.blocks[i][j];
        return twin;
    }

    public boolean equals(Object y) {

        Board second = (Board) y;
        int secondDimension = second.dimension();
        int thisDimenshion = this.dimension();

        if (secondDimension != thisDimenshion) return false;
        if (second.getJ(secondDimension - 1) != this.getJ(thisDimenshion - 1)) return false;

        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < this.blocks[0].length; j++) {
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
        int rowLength = blocks.length;
        return rowLength * i + j + 1;
    }

    private int getI (int value) {
        int rowLength = blocks.length;
        int i = (value + rowLength - 1) / rowLength;
        return i - 1;
    }

    private int getJ (int value) {
        int rowLength = blocks.length;
        int j = value - (getI(value) * rowLength);
        return j - 1;
    }

    private int getBlockValue (int i, int j) {
        return blocks[i][j];
    }

    public static void main(String[] args) {

    }
}
