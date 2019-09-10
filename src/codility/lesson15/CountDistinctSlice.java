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
//            {3, 3}
//            {100, 150}
//            {0, 1}
//            {0, 1, 0}
            {3, 2, 1}
            ;
        Object solution = new Solution().solution(M, A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int M, int[] A) {
            if (A.length == 1) return 1;

            final int maxIndex = A.length - 1;
            int answer = A.length;
            int left = 0;
            int right = 0;
            Set<Integer> uniqueSlice = new HashSet<>();
            uniqueSlice.add(A[0]);

            while (left < maxIndex) {
                if (right < maxIndex && uniqueSlice.add(A[right + 1])) {
                    right++;
                    if (uniqueSlice.size() > 1) {
                        answer++;
                    }
                } else {
                    uniqueSlice.remove(A[left]);
                    left++;
                    if (left != right && uniqueSlice.size() > 1) {
                        answer++;
                    }
                }
//                TestHelper.log("left=" + left + ", right=" + right + ", slice=" + uniqueSlice);
                if (answer > 1_000_000_000) return 1_000_000_000;
            }

            return answer;
        }
    }
}