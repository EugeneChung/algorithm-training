package programmers.binarysearch;

import helpers.TestHelper;

import java.util.*;

public class 예산 {
    public static void main(String[] args) {
        int[] budgets = {120, 110, 140, 150}; int M = 485; //484

        TestHelper.printSolution(new Solution().solution(budgets, M));
    }

    static class Solution {
        public int solution(int[] budgets, int M) {
            // Budget 합이 총 예산을 넘지 않는 경우
            long totalBudget = 0L;
            int maxBudget = 0;
            for (int budget : budgets) {
                totalBudget += budget;
                maxBudget = Math.max(maxBudget, budget);
            }
            if (totalBudget <= (long)M) return maxBudget;

            // Budget 합이 총 예산을 넘는 경우에는 maxBudget을 최대 상한액으로 고려한 상태에서 최적의 상한액을 찾는 binary search 수행
            int low = 0;
            int high = maxBudget;
            int targetLimit = 0;
            long maxTotal = Integer.MIN_VALUE;

            while (low <= high) {
                int mid = (low + high) / 2;
                // check 함수에서 상한액 조건에 따라 total 값을 리턴한다. 
                long total = check(budgets, mid);
                if (total == (long)M) {
                    return mid;
                } else if (total < (long)M) {
                    //현재 설정한 상한액으로 구한 total 값이 총 예산보다 작은 경우에는 상한액을 늘려서 시도하도록 세팅한다.
                    //현재 설정한 상한액이 최적의 경우일 수도 있으므로 이를 정답 후보로 기록해둔다.
                    low = mid + 1;
                    if (maxTotal < total) {
                        maxTotal = total;
                        targetLimit = mid;
                    }
                } else {
                    //현재 설정한 상한액으로 구한 total 값이 총 예산을 넘어가므로 당연히 상한액을 줄여야 한다.
                    high = mid - 1;
                }
            }
            return targetLimit;
        }

        private long check(int[] budgets, int limit) {
            long totalBudget = 0L;
            for (int budget : budgets) {
                if (budget > limit) {
                    budget = limit;
                }
                totalBudget += budget;
            }
            return totalBudget;
        }
    }
}