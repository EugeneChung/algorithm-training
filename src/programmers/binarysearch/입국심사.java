package programmers.binarysearch;

import helpers.TestHelper;

import java.util.Arrays;

public class 입국심사 {
    public static void main(String[] args) {
        int[] budgets = {120, 110, 140, 150}; int M = 485; //484

        TestHelper.printSolution(new Solution().solution(budgets, M));
    }

    static class Solution {
        public static int minInArray(int... array) {
            int min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }
            return min;
        }

        public long solution(int n, int[] times) {
            int minTime = minInArray(times);

            // Budget 합이 총 예산을 넘는 경우에는 maxBudget을 최대 상한액으로 고려한 상태에서 최적의 상한액을 찾는 binary search 수행
            long low = 0;
            long high = (long)minTime * (long)n;
            long minProcessingTime = 0;

            while (low <= high) {
                long mid = (low + high) / 2;
                if (check(times, mid)) {
                    high = mid - 1;
                    minProcessingTime = Math.min(minProcessingTime, mid);
                } else {
                    low = mid + 1;
                }
            }
            return minProcessingTime;
        }

        private boolean check(int[] times, long targetProcessingTime) {
            return false;
        }
    }
}