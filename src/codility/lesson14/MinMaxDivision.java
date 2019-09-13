package codility.lesson14;

import helpers.TestHelper;

import java.util.*;

public class MinMaxDivision {
    public static void main(String[] args) {
        int K = 3;
        int M = 5;
        int[] A =
            {2, 1, 5, 1, 2, 2, 2}
            ;
        Object solution = new Solution().solution(K, M, A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int K, int M, int[] A) {
            // write your code in Java SE 8
            int low = -1;
            int high = 0;
            for (int val : A) {
                if (val > low) {
                    low = val;
                }
                high += val;
            }

            int mid = 0;
            int smallestSum = 0;

            while (high >= low) {
                mid = (high + low) / 2;
                int numberOfBlock = blockCount(mid, A);

                if (numberOfBlock > K) {
                    low = mid + 1;
                } else {
                    smallestSum = mid;
                    high = mid - 1;
                }

            }
            return smallestSum;
        }

        private int blockCount(int max, int[] A) {
            int current = 0;
            int count = 1;
            for (int i = 0; i< A.length; i++) {
                if (current + A[i] > max) {
                    current = A[i];
                    count++;
                } else {
                    current += A[i];
                }
            }
            return count;
        }
    }
}