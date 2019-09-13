package codility.lesson15;

import helpers.TestHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinAbsSumOfTwo {
    public static void main(String[] args) {
        int[] A =
            {-8, 4, 5, -10, 3} // 3
//            {1, 4, -3} // 1
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 1) return Math.abs(2 * A[0]);

            Arrays.sort(A);
            int minAbsSum = Integer.MAX_VALUE;
            for (int left = 0; left < A.length; left++) {
                int right = left;
                while (right < A.length) {
                    int abs = Math.abs(A[left] + A[right]);
                    TestHelper.log("left=" + left + ",right=" + right + ", minAbsSum=" + minAbsSum + ", abs=" + abs);
                    if (minAbsSum <= abs) {
                        break;
                    } else {
                        minAbsSum = abs;
                    }
                    right++;
                }
            }
            return minAbsSum;
        }
    }
}