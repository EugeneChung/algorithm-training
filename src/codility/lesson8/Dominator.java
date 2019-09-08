package codility.lesson8;

import helpers.TestHelper;

import java.util.*;

public class Dominator {
    public static void main(String[] args) {
        int [] P =
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
        public int solution(int[] A) {
            if (A.length == 0) return -1;
            if (A.length == 1) return 0;

            final int half = A.length >> 1;

            int lastValue = -1;
            int size = 0;
            for (int i = 0; i < A.length; i++) {
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
            for (int i = 0; i < A.length; i++) {
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
    }
}