package codility.lesson5;

import helpers.TestHelper;

import java.util.TreeSet;

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
            TreeSet<Integer> minAverageStartingPoints = new TreeSet<>();
            for (int i = 0; i < A.length; i++) {
                for (int j = i + 1; j < A.length; j++) {
                    double sum = PS[j + 1] - PS[i];
                    double sliceLen = j - i + 1;
                    double avg = sum / sliceLen;

                    int result = Double.compare(avg, minAverage);
                    if (result < 0) {
                        minAverageStartingPoints.clear();
                        minAverage = avg;
                        minAverageStartingPoints.add(i);
                    } else if (result == 0) {
                        minAverageStartingPoints.add(i);
                    }
                }
            }
//            TestHelper.log(minAverage);
//            TestHelper.log(minAverageStartingPoints);
            return minAverageStartingPoints.first();
        }
    }
}