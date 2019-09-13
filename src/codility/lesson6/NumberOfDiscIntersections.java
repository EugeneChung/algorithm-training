package codility.lesson6;

import helpers.TestHelper;

import java.util.Arrays;

public class NumberOfDiscIntersections {
    public static void main(String[] args) {
        int [] P =
            {1, 5, 2, 1, 4, 0}
//            {0}
//            {0, 1}
//            {5, 10, 8}
//            {1, 1, 1}
//            {1, -1, 1}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length < 2) return 0;

            long[] L = new long[A.length];
            long[] R = new long[A.length];
            for (int i = 0; i < A.length; i++) {
                long radius = A[i];
                L[i] = i - radius;
                R[i] = i + radius;
            }
            Arrays.sort(L);
            Arrays.sort(R);
            TestHelper.printObject(L);
            TestHelper.printObject(R);

            long answer = 0;
            for (int i = 0; i < A.length; i++) {
                long count = Arrays.binarySearch(L, R[i]);
                if (count < 0) {
                    count = -count - 1L;
                } else {
                    while (count < A.length && L[(int)count] == R[i]) {
                        count++;
                    }
                }
                //TestHelper.log(count);
                answer += count - 1L - (long)i;
                if (answer > 10000000) {
                    return -1;
                }
            }
            return (int)answer;
        }
    }
}