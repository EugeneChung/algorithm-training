package codility.lesson15;

import helpers.TestHelper;

import java.util.Arrays;

public class CountTriangle {
    public static void main(String[] args) {
        int[] A =
            {10, 2, 5, 1, 8, 12, 11, 15} // 20
//            {10, 2, 5, 1, 8, 12} // 4
//            {3, 2, 1} // 0
//            {3, 2, 7} // 0
//            {3, 2, 3} // 1
//            {} // 0
//            {1} // 0
//            {1, 2} // 0
//            {1, 2} // 0
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length < 3) return 0;

            Arrays.sort(A);

            int answer = 0;

            for (int left = 0; left < A.length - 2; left++) {
                int right = left + 2;
                for (int mid = left + 1; mid < A.length - 1; mid++) {
                    while (right < A.length && A[left] + A[mid] > A[right]) {
                        right++;
                    }
                    answer += right - mid - 1;
                    //TestHelper.log("left=" + left + ",mid=" + mid + ",right=" + right + ",count=" + answer);
                }
            }

            return answer;
        }

        private int findTriangeWhileMovingLeft(int[] A, int left, int right) {
            int count = 0;
            if (right - left >= 3) {
                int i = 2;
                while (left + i < right) {
                    if (A[left] + A[left + i] > A[right]) {
                        count++;
                    }
                    i++;
                }
            }
            return count;
        }
    }
}