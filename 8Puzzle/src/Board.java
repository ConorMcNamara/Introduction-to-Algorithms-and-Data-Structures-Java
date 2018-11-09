import java.util.LinkedList;

public class Board {
    private int[][] gameBoard;
    private final static int blankSpace = 0;

    // construct a board from an n-by-n array of blocks where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new IllegalArgumentException("Cannot have null as a game board");
        }
        checkDimensions(blocks);
        checkNumbers(blocks);
        gameBoard = copy(blocks); //used to use clone, until I realized that it used shallow cloning and not deep cloning
    }

    private void checkDimensions(int[][] blocks) {
        int rowLength = blocks.length;
        int colLength = blocks[1].length;
        if (rowLength != colLength) {
            throw new IllegalArgumentException("Game Board is not a square");
        }
    }

    private void checkNumbers(int[][] blocks) {
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (blocks[row][col] < 0) {
                    throw new IllegalArgumentException("Cannot have negative numbers in game board");
                }
                if (blocks[row][col] >= (blocks.length * blocks.length)) {
                    throw new IllegalArgumentException("Cannot have numbers greater than or equal to length of board squared");
                }
            }
        }
    }

    public int dimension() {  // board dimension n
        return gameBoard.length;
    }

    public int hamming() {  // number of blocks out of place
        int outOfPlace = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (!isBlankSpace(row, col)) {
                    if (gameBoard[row][col] != dimension() * row + col + 1) {
                        outOfPlace++;
                    }
                }
            }
        }
        return outOfPlace;
    }

    private boolean isBlankSpace(int row, int col) {
        return gameBoard[row][col] == blankSpace;
    }

    public int manhattan() { // sum of Manhattan distances between blocks and goal
        int distance = 0;
        for (int row = 0; row < dimension(); ++row) {
            for (int col = 0; col < dimension(); ++col) {
                int value = gameBoard[row][col];
                distance += isBlankSpace(row, col) ? 0 : Math.abs(row - ((value - 1) / dimension())) +
                        Math.abs(col - ((value - 1) % dimension()));
            }
        }
        return distance;
    }

    public boolean isGoal() {  // is this board the goal board?
        return hamming() == 0;
    }

    public Board twin() { // a board that is obtained by exchanging any pair of blocks
        for (int row = 0; row < gameBoard.length; ++row) {
            for (int col = 0; col < gameBoard.length - 1; ++col) {
                if (!isBlankSpace(row, col) && !isBlankSpace(row, col + 1)) {
                    return new Board(swapBoard(row, col, row, col + 1));
                }
            }
        }
        throw new RuntimeException("Could not swap any available blocks");
    }

    private int[][] copy(int[][] blocks) {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++)
            for (int col = 0; col < blocks.length; col++)
                copy[row][col] = blocks[row][col];

        return copy;
    }

    private int[][] swapBoard(int row1, int col1, int row2, int col2) {
        int[][] twinBoard = copy(gameBoard);
        int value = twinBoard[row1][col1];
        twinBoard[row1][col1] = twinBoard[row2][col2];
        twinBoard[row2][col2] = value;
        return twinBoard;
    }

    public boolean equals(Object y) {  // does this board equal y?
        if (this == y) {
            return true;
        }
        if (y == null || !(y instanceof Board) || ((Board) y).dimension() != this.dimension()) {
            return false;
        }
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (((Board) y).gameBoard[row][col] != this.gameBoard[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }


    public Iterable<Board> neighbors() { // all neighboring boards
        LinkedList<Board> neighborBoard = new LinkedList<>();
        int[] blankSpaceLocation = getBlankSpace();
        int blankSpaceRow = blankSpaceLocation[0];
        int blankSpaceCol = blankSpaceLocation[1];
        if (blankSpaceRow > 0) {
            neighborBoard.add(new Board(swapBoard(blankSpaceRow, blankSpaceCol, blankSpaceRow - 1, blankSpaceCol)));
        }
        if (blankSpaceRow < dimension() - 1) {
            neighborBoard.add(new Board(swapBoard(blankSpaceRow, blankSpaceCol, blankSpaceRow + 1, blankSpaceCol)));
        }
        if (blankSpaceCol > 0) {
            neighborBoard.add(new Board(swapBoard(blankSpaceRow, blankSpaceCol, blankSpaceRow, blankSpaceCol - 1)));
        }
        if (blankSpaceCol < dimension() - 1) {
            neighborBoard.add(new Board(swapBoard(blankSpaceRow, blankSpaceCol, blankSpaceRow, blankSpaceCol + 1)));
        }
        return neighborBoard;
    }

    private int[] getBlankSpace() {
        int[] blankSpaceLocation = new int[2];
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.length; col++) {
                if (isBlankSpace(row, col)) {
                    blankSpaceLocation[0] = row;
                    blankSpaceLocation[1] = col;
                    return blankSpaceLocation;
                }
            }
        }
        throw new RuntimeException("Could not find blank space in game board");
    }

    public String toString() { // string representation of this board (in the output format specified below)
        StringBuilder str = new StringBuilder();
        str.append(dimension() + "\n");
        for (int row = 0; row < dimension(); ++row) {
            for (int col = 0; col < dimension(); ++col) {
                str.append(String.format("%2d ", gameBoard[row][col]));
            }
            str.append("\n");
        }
        return str.toString();
    }

    public static void main(String[] args) { // unit tests (not graded)
    }
}