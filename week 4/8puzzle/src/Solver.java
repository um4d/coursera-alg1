import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by t.simonov on 01.12.16.
 */
public class Solver {

    private MinPQ<Node> pq;
    private int moves;
    ArrayList<Board> solve;


    private class Node implements Comparable<Node>{

        private Board board;
        private int moves;
        Node previous;

        private Node (Board board, int moves, Node previuos) {
            this.board = board;
            this.moves = moves;
            this.previous = previuos;
        }

        @Override
        public int compareTo(Node s) {
            return (this.board.manhattan() + this.moves) - (s.board.manhattan() + s.moves);
        }
    }

    public Solver(Board initial) {

        pq = new MinPQ<Node>();
        pq.insert(new Node(initial, 0, null));
        solve = new ArrayList<>();

        Node curr = pq.delMin();
        solve.add(curr.board);
        moves = 0;

        while (!curr.board.isGoal()) {
//            System.out.println(curr.board.toString());
            ArrayList<Board> neighbors = (ArrayList<Board>) curr.board.neighbors();
            for (Board b : neighbors) {
                if (curr.previous == null || !b.equals(curr.previous.board)) {
                    pq.insert(new Node(b, moves, curr));
                }
            }
            moves++;
            curr = pq.delMin();
            solve.add(curr.board);
        }




    }

//    public boolean isSolvable()
//    public int moves()
//    public Iterable<Board> solution()
    public static void main(String[] args) {
        int[][] block = {{1,2,3},{4,5,6},{7,8,0}};
        Board box = new Board(block);
        box = box.twin();
        box = box.twin();

        System.out.println(box.toString());
        Solver s = new Solver(box);
//        System.out.println("solve:");
//        for (Board b : s.solve) {
//            System.out.println(b.toString());
//        }
        System.out.println("moves:");
        System.out.println(s.moves);

    }
}