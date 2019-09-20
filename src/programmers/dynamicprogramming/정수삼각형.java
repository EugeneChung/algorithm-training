package programmers.dynamicprogramming;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 정수삼각형 {
    public static void main(String[] args) {
        int[][] triangle = {{1}, {2, 3}}; //4
//        int[][] triangle = {{1}, {2, 3},{4, 5, 6}}; //10
//        int[][] triangle = {{1}, {2, 3}, {4, 5, 6}, {7, 8, 9, 10}};
//        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        TestHelper.printSolution(new Solution().solution(triangle));
    }

    static class Solution {
        public int solution(int[][] triangle) {
            if (triangle.length == 1) return triangle[0][0];

            int[][] dp = new int[triangle.length][0];
            for (int i = 0; i < triangle.length; i++) {
                int[] row = triangle[i];
                dp[i] = new int[row.length];
            }
            int[] finalRow = triangle[triangle.length - 1];
            System.arraycopy(finalRow, 0, dp[triangle.length - 1], 0, finalRow.length);
            
            for (int i = triangle.length - 1; i > 0; i--) {
                int[] row = triangle[i];
                for (int j = 0; j < row.length; j++) {
                    if (j == 0) {
                        dp[i - 1][0] = Math.max(dp[i - 1][0], dp[i][0] + triangle[i - 1][0]);
                    } else if (j == row.length - 1) {
                        int prevLastPosition = triangle[i - 1].length - 1;
                        dp[i - 1][prevLastPosition] = Math.max(dp[i - 1][prevLastPosition], dp[i][j] + triangle[i - 1][prevLastPosition]);
                    } else {
                        dp[i - 1][j] = Math.max(dp[i - 1][j], dp[i][j] + triangle[i - 1][j]);
                        dp[i - 1][j - 1] = Math.max(dp[i - 1][j - 1], dp[i][j] + triangle[i - 1][j - 1]);
                    }
                }
            }
            return dp[0][0];
        }
    }
}