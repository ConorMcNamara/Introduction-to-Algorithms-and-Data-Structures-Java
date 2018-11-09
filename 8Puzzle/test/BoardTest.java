import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class BoardTest {

    @Test
    void board_nullInput_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Board(null));
    }

    @Test
    void board_nonSquare_IllegalArgumentException() {
        int[][] matrix = new int[2][3];
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Board(matrix));
    }

    @Test
    void board_negativeNumber_IllegalArgumentException() {
        int[][] matrix = new int[2][2];
        matrix[0][0] = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Board(matrix));
    }

    @Test
    void board_numberGreaterThanOrEqualToLengthSquared_IllegalArgumentException() {
        int[][] matrix = new int[2][2];
        matrix[0][0] = matrix.length * matrix.length;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Board(matrix));
    }

    @Test
    void hamming_PerfectSolution_Zero() {
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        Board board = new Board(matrix);
        Assertions.assertEquals(0, board.hamming());
    }

    @Test
    void manhattan_PerfectSolution_Zero() {
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        Board board = new Board(matrix);
        Assertions.assertEquals(0, board.manhattan());
    }

    @Test
    void isGoal_PerfectSolution_True() {
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        Board board = new Board(matrix);
        Assertions.assertTrue(board.isGoal());
    }

    @Test
    void equals_Itself_True() {
        int[][] matrix = new int[2][2];
        Board board = new Board(matrix);
        Assertions.assertTrue(board.equals(board));
    }

    @Test
    void equals_SameBoard_True() {
        int[][] matrix = new int[2][2];
        Board board1 = new Board(matrix);
        Board board2 = new Board(matrix);
        Assertions.assertTrue(board1.equals(board2));
    }

    @Test
    void equals_nullInput_False() {
        int[][] matrix = new int[2][2];
        Board board = new Board(matrix);
        Assertions.assertFalse(board.equals(null));
    }

    @Test
    void equals_DifferentDimensions_False() {
        int[][] matrix1 = new int[2][2];
        int[][] matrix2 = new int[3][3];
        Board board1 = new Board(matrix1);
        Board board2 = new Board(matrix2);
        Assertions.assertFalse(board1.equals(board2));
    }

    @Test
    void equals_NotBoard_False() {
        int[][] matrix = new int[2][2];
        Board board = new Board(matrix);
        Assertions.assertFalse(board.equals(matrix));
    }

    @Test
    void equals_DifferentValues_False() {
        int[][] matrix1 = new int[2][2];
        matrix1[0][0] = 1;
        int[][] matrix2 = new int[2][2];
        Board board1 = new Board(matrix1);
        Board board2 = new Board(matrix2);
        Assertions.assertFalse(board1.equals(board2));
    }
}