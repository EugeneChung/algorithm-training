package codility.lesson4;

import helpers.TestHelper;

import java.util.Arrays;

public class MaxCounters {
    public static void main(String[] args) {
        int[] A =
            //{ 3, 4, 4, 6, 1, 4, 4 }
            //{ 3, 4, 4, 6, 1, 4, 4, 6 }
            //{ 3, 3, 3, 3, 3 }
            //{ 3, 3, 3, 3, 3 }
            //{ 1, 2, 3, 5, 4, 5 }
            {1, 3, 4, 3, 2, 1, 3}
//            {1, 3, 2}
            ;
        int N =
            //5
            //5
//            2
            //3
            //5
            3
//            3
            ;
        Object solution = new Solution().solution(N, A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int[] solution(int N, int[] A) {
            final int MAX_COUNTER = N + 1;
            int[] counters = new int[N];
            int currentMax = 0;
            boolean maxCounterOn = false;
            int currentMaxCounter = 0;

            for (int opValue : A) {
                if (opValue == MAX_COUNTER) {
                    if (!maxCounterOn) {
                        maxCounterOn = true;
                        currentMaxCounter = currentMax;
                    }
                } else {
                    if (maxCounterOn) {
                        counters[opValue - 1] = currentMaxCounter + 1;
                        maxCounterOn = false;
                    } else {
                        if (counters[opValue - 1] <= currentMaxCounter) {
                            counters[opValue - 1] = currentMaxCounter + 1;
                        } else {
                            counters[opValue - 1] += 1;
                        }
                    }
                    currentMax = Math.max(counters[opValue - 1], currentMax);
                }
            }

            for (int i = 0; i < counters.length; i++) {
                counters[i] = Math.max(counters[i], currentMaxCounter);
            }

            return counters;
        }
    }
}