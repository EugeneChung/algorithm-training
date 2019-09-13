package codility.lesson15;

import helpers.TestHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinAbsSumOfTwo {
    public static void main(String[] args) {
        int[] A =
//            {-7, 0, 3} // 0
//            {-7, -4, -3} // 6
//            {-7, -4, 0} // 0
//            {3, 0, 1} // 0
//            {3, 2, 1} // 2
//            {0} // 0
//            {-3} // 6
//            {3} // 2
//            {-8, 4, 5, -10, 3} // 3
//            {8, -3, -4, -5, 10} // 3
//            {-8, 4, -2, 5, -10, 3} // 1
            {8, -4, 2, -5, 10, -3} // 1
//            {1, 4, -3} // 1
//            {1, 4, -3} // 1
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 1) return Math.abs(A[0] << 1);

            Arrays.sort(A);

            if (A[A.length - 1] <= 0) return Math.abs(A[A.length - 1] << 1);
            if (A[0] >= 0) return A[0] << 1;
            if (Arrays.binarySearch(A, 0) >= 0) return 0;

            int minAbsSum = Integer.MAX_VALUE;
            int left = 0;
            int right = A.length - 1;
            while (left <= right) {
                int abs = Math.abs(A[left] + A[right]);
                minAbsSum = Math.min(minAbsSum, abs);
                TestHelper.log("left=" + left + ",right=" + right + ", minAbsSum=" + minAbsSum + ", abs=" + abs);

                int absLeft = Math.abs(A[left]);
                int absRight = Math.abs(A[right]);
                if (absLeft < absRight) {
                    right--;
                } else if (absLeft > absRight) {
                    left++;
                } else {
                    right--;
                    left++;
                }
                if (A[left] > 0 || A[right] < 0) {
                    return minAbsSum;
                }
            }
            return minAbsSum;
        }
    }
}