package codility.lesson12;

import helpers.TestHelper;

import java.util.*;

public class ChocolatesByNumbers {
    public static void main(String[] args) {
        int N =
            200
//            10
//            4
//            2
//            1_000_000_000
            ;
        int M =
            120
//            4
//            4
//            1
//            1
            ;

        Object solution = new Solution().solution(N, M);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private int binaryGCD(int p, int q) {
            //https://introcs.cs.princeton.edu/java/23recursion/BinaryGCD.java.html
            if (q == 0) return p;
            if (p == 0) return q;

            // p and q even
            if ((p & 1) == 0 && (q & 1) == 0) return binaryGCD(p >> 1, q >> 1) << 1;

                // p is even, q is odd
            else if ((p & 1) == 0) return binaryGCD(p >> 1, q);

                // p is odd, q is even
            else if ((q & 1) == 0) return binaryGCD(p, q >> 1);

                // p and q odd, p >= q
            else if (p >= q) return binaryGCD((p - q) >> 1, q);

                // p and q odd, p < q
            else return binaryGCD(p, (q - p) >> 1);
        }

        public int solution(int N, int M) {
            if (N == 1) return 1;

            int gcd = binaryGCD(N, M);
            return N / gcd;
        }
    }
}