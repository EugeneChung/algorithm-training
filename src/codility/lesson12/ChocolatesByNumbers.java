package codility.lesson12;

import helpers.TestHelper;

import java.util.*;

public class ChocolatesByNumbers {
    public static void main(String[] args) {
        int N =
//            10
//            4
//            2
            1_000_000_000
            ;
        int M =
//            4
//            4
//            1
            1
            ;

        Object solution = new Solution().solution(N, M);
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

        public int solution(int N, int M) {
            if (N == 1) return 1;

            int gcd = gcd(N, M);
            return N / gcd;
        }
    }
}