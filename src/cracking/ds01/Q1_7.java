package cracking.ds01;

import helpers.TestHelper;

public class Q1_7 {
    public static void main(String[] args) {
//        int[][] A = {{1,2},{3,4}};
        int[][] A = {{1,2, 3},{4, 5, 6}, {7, 8, 9}};

        TestHelper.printMatrix(
            new Q1_7.Solution().solution(A)
        );
    }

    static class Solution {
        public int[][] solution(int[][] A) {
            int[][] rotated = new int[A.length][A.length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    rotated[j][A.length - 1 - i] = A[i][j];
                }
            }
            return rotated;
        }
    }
}
