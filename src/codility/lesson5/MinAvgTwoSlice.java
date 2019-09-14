package codility.lesson5;

import helpers.TestHelper;

public class MinAvgTwoSlice {
    public static void main(String[] args) {
        int[] A =
//            {4, 2, 2, 5, 1, 5, 8} //1
//            {1, 2} // 0
//            {1, 2, 3} // 0
//            {3, 2, 1} // 1
//            {-3, -2, -1} // 0
//            {-1, -2, -3} // 1
//            {-1, 0, 1} // 0
//            {0, -1, 0, -1} // 1
            {1, 2, 3, 1, 2, 3} // 0
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 2) return 0;

            int[] PS = new int[A.length + 1];

            for (int i = 0; i < A.length; i++) {
                PS[i + 1] = A[i] + PS[i];
            }

            double minAverage = Double.MAX_VALUE;
            int minAvgIndex = -1;
            for (int i = 0; i < A.length - 1; i++) {
                double sum = PS[i + 2] - PS[i];
                double avg = sum / 2;
                int result = Double.compare(avg, minAverage);
                if (result < 0) {
                    minAverage = avg;
                    minAvgIndex = i;
                }
                if (i + 3 <= A.length) {
                    sum = PS[i + 3] - PS[i];
                    avg = sum / 3;
                    result = Double.compare(avg, minAverage);
                    if (result < 0) {
                        minAverage = avg;
                        minAvgIndex = i;
                    }
                } 
            }
//            TestHelper.log(minAverage);
            return minAvgIndex;
        }
    }
}