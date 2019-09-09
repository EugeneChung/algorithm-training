package codility.lesson12;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.List;

public class CommonPrimeDivisors {
    public static void main(String[] args) {
        int[] A =
//            {15, 10, 3}
//            {3, 10, 15}
//            {5, 30, 75}
//            {1, 1, 1, 5, 15}
            {3}
            ;
        int[] B =
//            {75, 30, 5}
//            {5, 30, 75}
//            {3, 10, 15}
//            {1, 2, 100, 1, 3}
            {9}
            ;

        Object solution = new Solution().solution(A, B);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private int gcd(int N, int M) {
            int mod = N % M;
            if (mod == 0) {
                return M;
            } else {
                return gcd(M, mod);
            }
        }

        private boolean isPrime(long val) {
            long i = 2;
            while (i * i <= val) {
                if (val % i == 0) {
                    return false;
                }
                i++;
            }
            return true;
        }

        public int solution(int[] A, int[] B) {
            int maxLimit = 1;
            List<Integer> primes = new ArrayList<>();
            int answer = 0;

            for (int i = 0; i < A.length; i++) {
                boolean hasCommonPrimeDivisorSet = true;

                if (A[i] == B[i]) {
                    hasCommonPrimeDivisorSet = true;
                } else if (A[i] == 1 || B[i] == 1) {
                    hasCommonPrimeDivisorSet = false;
                } else {
                    // update prime list
                    int limit = Math.max(A[i], B[i]);
                    if (maxLimit < limit) {
                        //find primes from maxLimit + 1 ~ limit
                        for (int j = maxLimit + 1; j <= limit; j++) {
                            if (isPrime(j)) {
                                primes.add(j);
                            }
                        }
                        maxLimit = limit;
                    }
                    //TestHelper.log(primes);

                    // check if prime divisor is the same
                    for (int prime : primes) {
                        if (prime > limit) {
                            break;
                        }
                        boolean isADivisable = (prime <= A[i]) && (A[i] % prime == 0);
                        boolean isBDivisable = (prime <= B[i]) && (B[i] % prime == 0);
                        //TestHelper.log(prime + " " + isADivisable + " " + isBDivisable);
                        if (isADivisable != isBDivisable) {
                            hasCommonPrimeDivisorSet = false;
                            break;
                        }
                    }
                }
                if (hasCommonPrimeDivisorSet) {
                    answer++;
                }
            }

            return answer;
        }
    }
}