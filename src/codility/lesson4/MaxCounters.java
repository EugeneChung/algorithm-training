package codility.lesson4;

import helpers.TestHelper;

import java.util.Arrays;

public class MaxCounters {
    public static void main(String[] args) {
        int[] A =
//            { 3, 4, 4, 6, 1, 4, 4 }
//            { 3, 4, 4, 6, 1, 4, 4, 6 }
//            { 3, 3, 3, 3, 3 }
//            { 3, 3, 3, 3, 3 }
//            { 3, 3, 3, 3, 3, 4 }
//            { 1, 2, 3, 5, 4, 5 }
//            {1, 3, 4, 3, 2, 1, 3}
            {1, 3, 2}
            ;
        int N =
//            5 //[3, 2, 2, 4, 2]
//            5 //[4, 4, 4, 4, 4]
//            2 //[0, 0]
//            3 //[0, 0, 5]
//            3 //[5, 5, 5]
//            5 //[1, 1, 1, 1, 2]
//            3 //[2, 2, 3]
            3
            ;
        Object solution = new Solution().solution(N, A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int[] solution(int N, int[] A) {
            final int maxCounterCommand = N + 1;
            int[] counters = new int[N];
            int currentMax = 0;
            int currentMaxCounter = 0;

            for (int operation : A) {
                if (operation == maxCounterCommand) {
                    currentMaxCounter = currentMax;
                } else {
                    //max counter 고려
                    int c = counters[operation - 1];
                    if (c < currentMaxCounter) {
                        c = currentMaxCounter;
                    }
                    c = c + 1;
                    counters[operation - 1] = c;
                    currentMax = Math.max(currentMax, c);
                }
            }
            for (int i = 0; i < N; i++) {
                if (counters[i] < currentMaxCounter) {
                    counters[i] = currentMaxCounter;
                }
            }
            return counters;
        }
    }
}