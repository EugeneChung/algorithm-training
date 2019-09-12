package codility.lesson8;

import helpers.TestHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class EquiLeader {
    public static void main(String[] args) {
        int [] P =
//            {4, 3, 4, 4, 4, 2} // 2
//            {3, 4, 3, 2, 3, -1, 3, 3} //4
//            {0} // 0
//            {0, 1} // 0
//            {0, 0} // 1
//            {5, 10, 8} // 0
//            {1, 1, 1} // 2
            {1, -1, 1} // 0
//            {0, 0, 1, 0, 0} // 4
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private static final int NO_LEADER = Integer.MAX_VALUE;

        public int solution(int[] A) {
            if (A.length == 1) return 0;
            if (A.length == 2) {
                if (A[0] == A[1]) return 1;
                else return 0;
            }

            // get the leader and its occurrence count using stack-based elimination technic
            final int half = A.length >> 1;
            int lastValue = -1;
            int size = 0;
            for (int value : A) {
                if (size == 0) {
                    size++;
                    lastValue = value;
                } else {
                    if (lastValue != value) size--;
                    else size++;
                }
            }
            if (size == 0) {
                return 0;
            }

            int candidate = lastValue;
            int count = 0;
            int leader = NO_LEADER;
            for (int value : A) {
                if (candidate == value) {
                    count++;
                    if (count > half) {
                        leader = candidate;
                    }
                }
            }
            if (leader == NO_LEADER) {
                return 0;
            }

            // The leader of the whole array must be the leader of each slice.
            int equiLeader = 0;
            int rightLeaderCount = 0;
            for (int S = 0; S < A.length - 1; S++) {
                if (A[S] == leader) {
                    rightLeaderCount++;
                }
                int rightHalf = (S + 1) >> 1;
                int leftHalf = (A.length - (S + 1)) >> 1;
                if (rightLeaderCount > rightHalf && (count - rightLeaderCount) > leftHalf) {
                    equiLeader++;
                }
            }
            return equiLeader;
        }
    }
}