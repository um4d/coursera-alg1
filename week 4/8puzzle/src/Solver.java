import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by t.simonov on 01.12.16.
 */
public class Solver {

    private Node best = null;

    private class Node implements Comparable<Node>{

        private Board board;
        private int moves;
        private Node previous;
        private int priority;

        private Node (Board board, int moves, Node previuos) {
            this.board = board;
            this.moves = moves;
            this.previous = previuos;
            this.priority = this.board.manhattan() + this.moves;
        }

        @Override
        public int compareTo(Node s) {
            return this.priority - s.priority;
        }
    }

    public Solver(Board initial) {

        if (initial == null) {
            throw new java.lang.NullPointerException();
        }

        MinPQ<Node> pq = new MinPQ<Node>();
        Node curr = new Node(initial, 0, null);
        Node twin = new Node(initial.twin(), 0, null);
        pq.insert(curr);
        pq.insert(twin);

        best = null;

//        while (!curr.board.isGoal()) {
        while (!pq.isEmpty()) {
            ArrayList<Board> neighbors = (ArrayList<Board>) curr.board.neighbors();
            for (Board b : neighbors) {
                if (curr.previous == null || !b.equals(curr.previous.board)) {
                    pq.insert(new Node(b, curr.moves + 1, curr));
                }
            }
            curr = pq.delMin();
            if (curr.board.isGoal()) break;
        }
        best = curr;
    }

    public boolean isSolvable() {
        if (rootNode(best).equals())
    }

    public int moves() {
        return best.moves;
    }

    public Iterable<Board> solution() {
        ArrayList<Board> solution = new ArrayList<>();
        Node curr = best;
        while (curr.previous != null) {
            solution.add(curr.board);
            curr = curr.previous;
        }
        Collections.reverse(solution);
        return solution;
    }

    private Node rootNode(Node node) {
        while (node.previous != null) {
            node = node.previous;
        }
        return node;
    }

    public static void main(String[] args) {
//        int[][] block = {{1,2,3},{4,5,6},{7,8,0}};
        int[][] block = {{0,3},{2,1}};
        Board box = new Board(block);


        System.out.println(box.toString());
        Solver s = new Solver(box);

        System.out.println("moves:");
        Node curr = s.best;
        int moves = 0;
        for (Board b : s.solution()) {
            System.out.println(b.toString());
            moves++;
        }
        System.out.println(moves);
        System.out.println(s.moves());
    }
}