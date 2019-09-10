package codility.lesson15;

import helpers.TestHelper;

import java.util.*;

public class CountDistinctSlice {
    public static void main(String[] args) {
        int M =
            6
            ;
        int[] A =
//            {3, 4, 5, 5, 2}
            {1, 2, 3, 4, 1, 3, 4}
//            {100, 150}
//            {0, 1}
//            {0, 1, 0}
//            {3, 2, 1}
            ;
        Object solution = new Solution().solution(M, A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int M, int[] A) {
            if (A.length == 1) return 1;
            int[] nextPositions = new int[M + 1];
            int left = 0;
            int answer = 0;

            for (int right = 0; right < A.length; right++) {
                if (nextPositions[A[right]] > left) {
                    left = nextPositions[A[right]];
                }
                nextPositions[A[right]] = right + 1;
                answer += right - left + 1;
                if (answer > 1_000_000_000) return 1_000_000_000;
            }
            return answer;
        }
    }
}