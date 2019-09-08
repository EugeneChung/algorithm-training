package codility;

import helpers.TestHelper;

public class CountFactors {
    public static void main(String[] args) {
        int N =
            2_147_483_647
            ;
        Object solution = new Solution().solution(N);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(long N) {
            if (N == 1) return 1;
            int count = 0;
            long i = 1;
            while (i * i < N) {
                if (N % i == 0) {
                    count += 2;
                }
                i++;
            }
            if (i * i == N) {
                count += 1;
            }
            return count;
        }
    }
}