import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.ArrayList;

public class Solver {
    private boolean solved;
    private ArrayList<Board> solutions = new ArrayList<>();

    private class BoardMove implements Comparable<BoardMove> {
        private BoardMove predecessor;
        private int numMoves = 0;
        private Board board;

        public BoardMove(Board board) {
            this.board = board;
        }

        public BoardMove(Board board, BoardMove predecessor) {
            this.board = board;
            this.predecessor = predecessor;
            this.numMoves = predecessor.numMoves + 1;
        }

        @Override
        public int compareTo(BoardMove o) {
            return (this.board.manhattan() + this.numMoves) - (o.board.manhattan() + o.numMoves);
        }
    }

    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        if (initial == null) {
            throw new IllegalArgumentException("Cannot have null as a board");
        }
        BoardMove step;
        MinPQ<BoardMove> moves = new MinPQ<>();
        MinPQ<BoardMove> twinMoves = new MinPQ<>();
        moves.insert(new BoardMove(initial));
        twinMoves.insert(new BoardMove(initial.twin()));

        while (!moves.min().board.isGoal() && !twinMoves.min().board.isGoal()) {
            step = moves.delMin();
            for (Board neighbor : step.board.neighbors()) {
                if (!solutionPath(step, neighbor)) {
                    moves.insert(new BoardMove(neighbor, step));
                }
            }

            BoardMove twinStep = twinMoves.delMin();
            for (Board neighbor : twinStep.board.neighbors()) {
                if (!solutionPath(twinStep, neighbor)) {
                    twinMoves.insert(new BoardMove(neighbor, twinStep));
                }
            }
        }
        step = moves.delMin();
        solved = step.board.isGoal();

        solutions.add(step.board);
        while ((step = step.predecessor) != null) {
            solutions.add(0, step.board);
        }
    }

    private boolean solutionPath(BoardMove move, Board board) {
        BoardMove previousMove = move;
        while ((previousMove = previousMove.predecessor) != null) {
            if (previousMove.board.equals(board)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSolvable() { // is the initial board solvable?
        return solved;
    }

    public int moves() { // min number of moves to solve initial board; -1 if unsolvable
        return isSolvable() ? solutions.size() - 1 : -1;
    }

    public Iterable<Board> solution() {
        Iterable<Board> iterable;
        if (isSolvable()) {
            iterable = new Iterable<Board>() {
                @Override
                public Iterator<Board> iterator() {
                    return new SolutionIterator();
                }
            };
        } else {
            iterable = null;
        }
        return iterable;
    }


    private class SolutionIterator implements Iterator<Board> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < solutions.size();
        }

        @Override
        public Board next() {
            return solutions.get(index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("It is not supported to remove a board from the solution.");
        }
    }


    public static void main(String[] args) { // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}