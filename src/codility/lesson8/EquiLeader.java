package codility.lesson8;

import helpers.TestHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class EquiLeader {
    public static void main(String[] args) {
        int [] P =
//            {4, 3, 4, 4, 4, 2}
//            {3, 4, 3, 2, 3, -1, 3, 3}
//            {0}
//            {0, 1}
//            {0, 0}
//            {5, 10, 8}
//            {1, 1, 1}
//            {1, -1, 1}
            {0, 0, 1, 0, 0}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private int electLeader(Map<Integer, Integer> numToCount, int leaderOrMaxSoFar, int index, int value, int half, int[] output) {
            /*int count = numToCount.compute(value, (k, v) -> {
                if (v == null) return 1;
                else return v + 1;
            });*/
            Integer count = numToCount.getOrDefault(value, 0);
            count = count + 1;
            numToCount.put(value, count);

            int leaderCount = numToCount.getOrDefault(leaderOrMaxSoFar, 0);

            if (count > leaderCount) {
                leaderCount = count;
                leaderOrMaxSoFar = value;
            }
            if (leaderCount > half) {
                output[index] = leaderOrMaxSoFar;
            } else {
                output[index] = NO_LEADER;
            }
            return leaderOrMaxSoFar;
        }

        private static final int NO_LEADER = Integer.MAX_VALUE;
        public int solution(int[] A) {
            if (A.length == 1) return 0;
            if (A.length == 2) {
                if (A[0] == A[1]) return 1;
                else return 0;
            }

            Map<Integer, Integer> numToCount = new HashMap<>();
            int leaderOrMaxSoFar = NO_LEADER;

            int[] prefixLeaders = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                leaderOrMaxSoFar = electLeader(numToCount, leaderOrMaxSoFar, i, A[i], (i + 1) / 2, prefixLeaders);
            }

            int[] suffixLeaders = new int[A.length];
            numToCount.clear();
            leaderOrMaxSoFar = NO_LEADER;
            for (int i = A.length - 1, j = 0; i >= 0; i--, j++) {
                leaderOrMaxSoFar = electLeader(numToCount, leaderOrMaxSoFar, i, A[i], (j + 1) / 2, suffixLeaders);
            }
//            TestHelper.printArray(prefixLeaders);
//            TestHelper.printArray(suffixLeaders);

            int equiLeader = 0;
            for (int S = 0; S < A.length - 1; S++) {
                if (prefixLeaders[S] != NO_LEADER && prefixLeaders[S] == suffixLeaders[S + 1]) {
                    equiLeader++;
                }
            }
            return equiLeader;
        }
    }
}