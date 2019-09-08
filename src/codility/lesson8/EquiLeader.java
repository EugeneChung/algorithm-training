package codility.lesson8;

import helpers.TestHelper;

import java.util.HashMap;
import java.util.Map;

public class EquiLeader {
    public static void main(String[] args) {
        int [] P =
//            {4, 3, 4, 4, 4, 2}
            {3, 4, 3, 2, 3, -1, 3, 3}
//            {0}
//            {0, 1}
//            {0, 0}
//            {5, 10, 8}
//            {1, 1, 1}
//            {1, -1, 1}
//            {0, 0, 1, 0, 0}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        /* return the index of the leader */
        private int electLeader(int[] A, int from, int to) {
            if (from == to) return from;

            final int half = (to - from + 1) >> 1;

            int lastValue = -1;
            int size = 0;
            for (int i = from; i <= to; i++) {
                if (size == 0) {
                    size++;
                    lastValue = A[i];
                } else {
                    if (lastValue != A[i]) size--;
                    else size++;
                }
            }
            if (size == 0) {
                return -1;
            }

            int candidate = lastValue;
            int count = 0;
            int answer = -1;
            for (int i = from; i <= to; i++) {
                if (candidate == A[i]) {
                    count++;
                    if (count > half) {
                        answer = i;
                        break;
                    }
                }
            }
            return answer;
        }
        private static final int NO_LEADER = Integer.MAX_VALUE;
        public int solution(int[] A) {
            if (A.length == 1) return 0;
            if (A.length == 2) {
                if (A[0] == A[1]) return 1;
                else return 0;
            }
            //TestHelper.log(electLeader(new int[]{4, 3, 4}, 0, 2));

            int[] prefixLeaders = new int[A.length];
            int[] suffixLeaders = new int[A.length];

            for (int i = 0; i < A.length; i++) {
                int leaderIdx = electLeader(A, 0, i);
                if (leaderIdx >= 0) prefixLeaders[i] = A[leaderIdx];
                else prefixLeaders[i] = NO_LEADER;
            }
            for (int i = A.length - 1, j = 0; i >= 0; i--, j++) {
                int leaderIdx = electLeader(A, i, A.length - 1);
                if (leaderIdx >= 0) suffixLeaders[j] = A[leaderIdx];
                else suffixLeaders[j] = NO_LEADER;
            }
            //TestHelper.printArray(prefixLeaders);
            //TestHelper.printArray(suffixLeaders);

            int equiLeader = 0;
            for (int S = 0; S < A.length - 1; S++) {
                if (prefixLeaders[S] != NO_LEADER && prefixLeaders[S] == suffixLeaders[A.length - (S + 2)]) {
                    equiLeader++;
                }
            }
            return equiLeader;
        }
    }
}