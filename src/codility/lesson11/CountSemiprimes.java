package codility.lesson11;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountSemiprimes {
    public static void main(String[] args) {
        int N =
            4
//            26
//            26
            ;
        int [] P =
            {1, 1, 1, 1, 3, 4}
//            {1, 4, 16}
//            {1, 4, 4, 5, 5}
            ;
        int [] Q =
            {1, 2, 3, 4, 4, 4}
//            {26, 10, 20}
//            {15, 11, 10, 15, 20}
            ;
        Object solution = new Solution().solution(N, P, Q);
        TestHelper.printSolution(solution);
    }

    static class Solution {
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

        public int[] solution(int N, int[] P, int[] Q) {
            if (N < 4) {
                return new int[P.length];
            }

            List<Integer> primes = new ArrayList<>();
            for (int i = 2; i * 2 <= N; i++) {
                if (isPrime(i)) primes.add(i);
            }
            //TestHelper.log(primes);

            List<Integer> semiprimes = new ArrayList<>();
            for (int i = 0; i < primes.size(); i++) {
                int prime1 = primes.get(i);

                for (int j = i; j < primes.size(); j++) {
                    int semiprime = prime1 * primes.get(j);
                    if (semiprime <= N) semiprimes.add(semiprime);
                    else break;
                }
            }
            Collections.sort(semiprimes);
//            TestHelper.log(semiprimes);

            int[] answers = new int[P.length];
            for (int i = 0; i < P.length; i++) {
                boolean fromNotFound = false;
                boolean toNotFound = false;
                int from = Collections.binarySearch(semiprimes, P[i]);

                if (from < 0) {
                    from = -(from + 1);
                    fromNotFound = true;
                }
//                TestHelper.log("from : " + from + ", found? " + !fromNotFound);

                int to = from;
                if (P[i] == Q[i]) {
                    toNotFound = fromNotFound;
                } else {
                    to = Collections.binarySearch(semiprimes, Q[i]);
                    if (to < 0) {
                        to = -(to + 1) - 1;
                        toNotFound = true;
                    }
                }
//                TestHelper.log("to : " + to + ", found? " + !toNotFound);

                if (fromNotFound && toNotFound && to == from) {
                    answers[i] = 0;
                } else {
                    answers[i] = to - from + 1;
                }
            }

            return answers;
        }
    }
}