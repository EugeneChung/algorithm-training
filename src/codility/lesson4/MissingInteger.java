package codility.lesson4;

import helpers.TestHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingInteger {
    public static void main(String[] args) {
        int[] A =
//            { 1, 3, 6, 4, 1, 2 } //5
//            { 1, 2, 3 } // 4
//            { -1, -3 } // 1
//            { 10, 57, 5 } // 1
//            { 10, 1, 57, 5 } // 2
//            { 10, 3, 2 } // 1
//            {1, 3, 1, 3, 2, 1, 3} // 4
//            {1, 1, 1, 1, 1, 1} //2
//            {-1, 3, 1, -1, 1, 0, 1, 5} //2
//            {1} // 2
//            {-1} // 1
            {0} // 1
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            Arrays.sort(A);

            if (A[A.length - 1] <= 0) return 1;
            int answer = 1;
            int prev = Integer.MIN_VALUE;
            for (int value : A) {
                if (value <= 0) continue;
                if (prev == value) continue;

                if (answer != value) {
                    return answer;
                }
                prev = value;
                answer++;
            }
            return answer;
        }
    }
}