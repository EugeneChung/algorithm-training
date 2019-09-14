package codility.lesson12;

import helpers.TestHelper;

public class CommonPrimeDivisors {
    public static void main(String[] args) {
        int[] A =
//            {15, 10, 9} // 1
//            {1, 1, 1, 5, 15} //1
//            {1} // 1
//            {9} // 0
//            {3} // 1
//            {24} // 1
            {3*5*3*2} // 0
//            {551} // 1
            ;
        int[] B =
//            {75, 30, 5}
//            {1, 2, 100, 1, 3}
//            {1}
//            {5}
//            {9}
//            {36}
            {3*5*5*7}
//            {303601}
            ;

        Object solution = new Solution().solution(A, B);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public static int binaryGCD(int p, int q) {
            //https://introcs.cs.princeton.edu/java/23recursion/BinaryGCD.java.html
            if (q == 0) return p;
            if (p == 0) return q;

            int pmod2 = p & 1;
            int qmod2 = q & 1;

            // p and q even
            if (pmod2 == 0 && qmod2 == 0) return binaryGCD(p >> 1, q >> 1) << 1;

                // p is even, q is odd
            else if (pmod2 == 0) return binaryGCD(p >> 1, q);

                // p is odd, q is even
            else if (qmod2 == 0) return binaryGCD(p, q >> 1);

                // p and q odd, p >= q
            else if (p >= q) return binaryGCD((p - q) >> 1, q);

                // p and q odd, p < q
            else return binaryGCD(p, (q - p) >> 1);
        }

        private boolean checkIfGCDHasAllPrimeDivisors(final int gcd, final int value) {
            int gcdRest = gcd;
            int rest = value / gcd;
            while (gcd % rest != 0) {
                gcdRest = binaryGCD(gcdRest, rest);
                if (gcdRest == 1) {
                    return false;
                }
                rest = rest / gcdRest;
            }
            return true;
        }

        public int solution(int[] A, int[] B) {
            int answer = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) {
                    answer++;
                    continue;
                }
                int gcd = binaryGCD(A[i], B[i]);
                if (gcd != 1) {
                    if (checkIfGCDHasAllPrimeDivisors(gcd, A[i]) && checkIfGCDHasAllPrimeDivisors(gcd, B[i])) {
                        answer++;
                    }
                }
            }

            return answer;
        }
    }
}