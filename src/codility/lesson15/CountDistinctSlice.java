package codility.lesson15;

import helpers.TestHelper;

import java.util.*;

public class CountDistinctSlice {
    public static void main(String[] args) {
        int M =
            6
            ;
        int[] A =
//            {3, 4, 5, 5, 2} // 9
//            {3, 4, 5, 6, 6, 2} // 13
//            {1, 2, 3, 4, 1, 3, 4} // 20 
//            {100, 150} // 3
//            {0, 1, 0} // 5
            {3, 2, 1} // 5
            ;
        Object solution = new Solution().solution(M, A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int M, int[] A) {
            if (A.length == 1) return 1;

            int left = 0;
            int right = 0;
            int answer = 1;
            Set<Integer> uniqueSlice = new HashSet<>(Collections.singleton(A[0]));

            while (right < A.length) {
                if (right == A.length - 1 || !uniqueSlice.add(A[right + 1])) {
                    if (left == right) {
                        uniqueSlice.remove(A[left]);
                        left++;
                        right++;
                        if (right < A.length) {
                            uniqueSlice.add(A[right]);
                        } else if (uniqueSlice.isEmpty()) {
                            break;
                        }
                        answer++;
                    } else {
                        uniqueSlice.remove(A[left]);
                        left++;
                        answer += right - left + 1;
                    }
                } else {
                    right++;
                    answer++;
                }
//                TestHelper.log(uniqueSlice + " count=" + answer);
                if (answer > 1_000_000_000) return 1_000_000_000;
            }
            return answer;
        }
    }
}