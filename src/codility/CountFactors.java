package codility;

import helpers.TestHelper;

import java.util.HashSet;
import java.util.Set;

public class CountFactors {
    public static void main(String[] args) {
        int N =
            2_147_483_647
            ;
        Object solution = new Solution().solution(N);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int N) {
            if (N == 1) return 1;
            Set<Integer> factors = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                if (factors.contains(i)) {
                    break;
                }
                if (N % i == 0) {
                    factors.add(i);
                    factors.add(N / i);
                }
            }
            //TestHelper.log(factors);
            return factors.size();
        }
    }
}