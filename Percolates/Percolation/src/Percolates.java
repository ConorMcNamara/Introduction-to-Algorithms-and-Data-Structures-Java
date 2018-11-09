import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolates {
    private final int[][] grid;
    private final WeightedQuickUnionUF unionArray;
    private final int n;

    //creates an n-by-n grid, with all sites blocked
    public Percolates(int n) { //create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("Column or row value is less than or equal to 0");
        }
        this.n = n;
        this.grid = new int[n][n];
        this.unionArray = new WeightedQuickUnionUF(n * n);
    }

    private void printGrid() {
        System.out.println("[");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println("]");
    }

    private void setGrid(int row, int col, int value) {
        /** We are operating under the assumption that matrix input will be in mathematical notation (i.e., the top-left
         * corner is inputted as (1,1)) instead of typical coding languages, which has it as (0,0).
         */
        grid[row - 1][col - 1] = value;
    }

    private int getGrid(int row, int col) {
        return grid[row - 1][col - 1];
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Column or row value is less than or equal to 0");
        } else if (row > n || col > n) {
            throw new IllegalArgumentException("Column or row value is greater than number of columns/rows");
        }
        if (!isOpen(row, col)) {
            if (row == 1) {
                unionArray.union(flattenGrid(row, col), flattenGrid(1, 1));
            }
            if (row == n) {
                unionArray.union(flattenGrid(row, col), flattenGrid(n, n));
            }
            setGrid(row, col, 1);
            checkConnected(row, col);
        }
    }

    private int flattenGrid(int row, int col) {
        /** Our unionArray is one long array from 0 to n^2-1. This converts our matrix format (row, col) to be useable
         * for unionArray
         * @returns unionArray index that corresponds to row and column
         */
        return ((row - 1) * n + (col - 1));
    }

    private void doQuickUnionIfSafe(int aRow, int aCol, int bRow, int bCol) {
        try {
            if (isOpen(bRow, bCol)) {
                unionArray.union(flattenGrid(bRow, bCol), flattenGrid(aRow, aCol));
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
        }
    }

    private void checkConnected(int row, int col) {
        doQuickUnionIfSafe(row, col, row - 1, col);
        doQuickUnionIfSafe(row, col, row + 1, col);
        doQuickUnionIfSafe(row, col, row, col - 1);
        doQuickUnionIfSafe(row, col, row, col + 1);
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Column or row value is less than or equal to 0");
        } else if (row > n || col > n) {
            throw new IllegalArgumentException("Column or row value is greater than length of rows/columns");
        }
        return grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Column or row value is less than or equal to 0");
        } else if (row > n || col > n) {
            throw new IllegalArgumentException("Column or row value is greater than length of rows/columns");
        }
        if (isOpen(row, col)) {
            return unionArray.connected(flattenGrid(row, col), flattenGrid(1, 1));
        }
        return false;
    }

    public int numberOfOpenSites() { // number of open sites
        return (calculatePerimeter(grid));
    }

    private static int calculatePerimeter(int[][] matrix) {
        int perimeter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                perimeter += matrix[i][j];
            }
        }
        return perimeter;
    }

    public boolean percolates() { // does the system percolate
        return unionArray.connected(flattenGrid(1, 1), flattenGrid(n, n));
    }

    public static void main(String[] args) {
    }
}
