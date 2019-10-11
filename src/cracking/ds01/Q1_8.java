package cracking.ds01;

import helpers.TestHelper;

public class Q1_8 {
    public static void main(String[] args) {
//        int[][] A = {{1,2},{3,4}, {5,6}};
        int[][] A = {{1,2},{3,0}, {5,6}};

        TestHelper.printMatrix(
            new Q1_8.Solution().solution(A)
        );
    }

    static class Solution {
        //A.length => M
        //A[0].length => N
        public int[][] solution(int[][] A) {
            boolean firstRowHasZero = false;
            boolean firstColumnHasZero = false;
            for (int i = 0; i < A.length; i++) {
                if (A[i][0] == 0) {
                    firstRowHasZero = true;
                    break;
                }
            }
            for (int j = 0; j < A[0].length; j++) {
                if (A[0][j] == 0) {
                    firstColumnHasZero = true;
                    break;
                }
            }
            for (int i = 1; i < A.length; i++) {
                for (int j = 1; j < A[0].length; j++) {
                    if (A[i][j] == 0) {
                        A[i][0] = 0;
                        A[0][j] = 0;
                    }
                }
            }

            for (int i = 1; i < A.length; i++) {
                if (A[i][0] == 0) {
                    // zeroing the row i
                    for (int j = 1; j < A[0].length; j++) {
                        A[i][j] = 0;
                    }
                }
            }
            for (int j = 1; j < A[0].length; j++) {
                if (A[0][j] == 0) {
                    // zeroing the column j
                    for (int i = 1; i < A.length; i++) {
                        A[i][j] = 0;
                    }
                }
            }
            if (firstRowHasZero) {
                for (int j = 0; j < A[0].length; j++) {
                    A[0][j] = 0;
                }
            }
            if (firstColumnHasZero) {
                for (int i = 0; i < A.length; i++) {
                    A[i][0] = 0;
                }
            }

            return A;
        }
    }
}
