package coursera.algorithms_part1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private final int[][] grid; // 0 - blocked, 1 - open, 2 - full
    private final WeightedQuickUnionUF unionFind;
    private final WeightedQuickUnionUF unionFindForFull;
    private boolean isPercolated;
    private final int virtualTop;
    private final int virtualBottom;
    private int numberOfOpenSites;

    /**
     * Creates N-by-N grid, with all sites blocked
     * @param N size of grid
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();

        this.N = N;
        this.grid = new int[N][N];
        int N_2 = N * N;
        this.unionFind = new WeightedQuickUnionUF(N_2 + 2); // plus two virtual sites
        this.unionFindForFull = new WeightedQuickUnionUF(N_2 + 1); // plus virtual top
        this.virtualTop = N_2;
        this.virtualBottom = N_2 + 1;
    }

    /**
     * Open site (row row, column col) if it is not already.
     */
    public void open(int row, int col) {
        checkParameters(row, col);
        int actualRow = row - 1;
        int actualCol = col - 1;
        if (grid[actualRow][actualCol] > 0) {
            return;
        }

        boolean updateDownSite = false;
        boolean updateLeftSite = false;
        boolean updateRightSite = false;
        boolean isFullOpen = false;

        int targetSite = actualRow * N + actualCol;
        if (actualRow == 0) {
            isFullOpen = true;
            unionFind.union(virtualTop, targetSite);
            unionFindForFull.union(virtualTop, targetSite);
        }
        if (actualRow == N - 1) {
            unionFind.union(virtualBottom, targetSite);
        }

        // union with adjacent sites if possible
        if (actualRow > 0 && grid[actualRow - 1][actualCol] > 0) {
            int upSite = (actualRow - 1) * N + actualCol;
            unionFind.union(targetSite, upSite);
            unionFindForFull.union(targetSite, upSite);
            if (grid[(actualRow - 1)][actualCol] == 2) {
                isFullOpen = true;
            }
        }
        if (actualRow < (N - 1) && grid[actualRow + 1][actualCol] > 0) {
            int downSite = (actualRow + 1) * N + actualCol;
            unionFind.union(targetSite, downSite);
            unionFindForFull.union(targetSite, downSite);
            if (isFullOpen) {
                updateDownSite = true;
            } else if (grid[(actualRow + 1)][actualCol] == 2) {
                isFullOpen = true;
            }
        }
        if (actualCol > 0 && grid[actualRow][actualCol - 1] > 0) {
            int leftSite = actualRow * N + actualCol - 1;
            unionFind.union(targetSite, leftSite);
            unionFindForFull.union(targetSite, leftSite);
            if (isFullOpen) {
                updateLeftSite = true;
            } else if (grid[actualRow][(actualCol - 1)] == 2) {
                isFullOpen = true;
            }
        }
        if (actualCol < (N - 1) && grid[actualRow][actualCol + 1] > 0) {
            int rightSite = actualRow * N + actualCol + 1;
            unionFind.union(targetSite, rightSite);
            unionFindForFull.union(targetSite, rightSite);
            if (isFullOpen) {
                updateRightSite = true;
            } else if (grid[actualRow][(actualCol + 1)] == 2) {
                isFullOpen = true;
            }
        }

        if (isFullOpen) {
            grid[actualRow][actualCol] = 2;
            if (updateDownSite) {
                grid[(actualRow + 1)][actualCol] = 2;
            }
            if (updateLeftSite) {
                grid[(actualRow)][actualCol - 1] = 2;
            }
            if (updateRightSite) {
                grid[(actualRow)][actualCol + 1] = 2;
            }
        } else {
            grid[actualRow][actualCol] = 1;
        }
        numberOfOpenSites++;
    }

    private void checkParameters(int row, int col) {
        if (row < 1 || row > N) {
            throw new IllegalArgumentException("invalid row = " + row);
        }
        if (col < 1 || col > N) {
            throw new IllegalArgumentException("invalid col = " + col);
        }
    }

    /**
     * Is site (row row, column col) open?
     */
    public boolean isOpen(int row, int col) {
        checkParameters(row, col);
        int actualRow = row - 1;
        int actualCol = col - 1;
        return grid[actualRow][actualCol] > 0;
    }

    /**
     * is site (row row, column col) full?
     */
    public boolean isFull(int row, int col) {
        checkParameters(row, col);

        int actualRow = row - 1;
        int actualCol = col - 1;

        if (grid[actualRow][actualCol] == 2) {
            return true;
        }

        int thisSite = actualRow * N + actualCol;
        if (unionFindForFull.connected(virtualTop, thisSite)) {
            grid[actualRow][actualCol] = 2;
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        if (isPercolated) {
            return true;
        }
        if (unionFind.connected(virtualTop, virtualBottom)) {
            isPercolated = true;
        }
        return isPercolated;
    }
}