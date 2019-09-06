package codility;

import helpers.TestHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingInteger {
    public static void main(String[] args) {
        int[] A =
            //{ 1, 3, 6, 4, 1, 2 }
            { 1, 2, 3 }
            //{ -1, -3 }
            //{1, 3, 1, 3, 2, 1, 3}
            //{1, 3, 2}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            Arrays.sort(A);
            int prev = 0;
            int answer = 1;

            for (final int curr : A) {
                if (curr <= 0) continue;
                if (prev != curr) {
                    if (curr != answer) {
                        break;
                    } else {
                        answer += 1;
                        prev = curr;
                    }
                }
            }

            return answer;
        }
    }
}