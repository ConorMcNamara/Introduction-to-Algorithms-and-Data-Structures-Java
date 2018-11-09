import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolverTest {

    @Test
    void solver_NullInput_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Solver(null));
    }

    @Test
    void isSolvable_NotSolvable_False() {
        int[][] matrix = new int[3][3];
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;
        matrix[1][0] = 4;
        matrix[1][1] = 5;
        matrix[1][2] = 6;
        matrix[2][0] = 8;
        matrix[2][1] = 7;
        Assertions.assertFalse(() -> new Solver(new Board(matrix)).isSolvable());
    }

}