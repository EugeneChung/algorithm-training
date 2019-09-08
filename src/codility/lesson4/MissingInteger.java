package codility.lesson4;

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
            int[] intList = IntStream.of(A).filter(e -> e > 0).sorted().toArray();
            int prev = 0;
            int answer = 1;

            for (final int curr : intList) {
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