package codility.lesson10;

import helpers.TestHelper;

public class MinPerimeterRectangle {
    public static void main(String[] args) {
        int N =
            //1_000_000_000
            4
            ;
        Object solution = new Solution().solution(N);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(long N) {
            if (N == 1) return 4; // 2 * (1 + 1)

            long minPerimeter = Integer.MAX_VALUE;
            long i = 1;
            while (i * i < N) {
                if (N % i == 0) {
                    minPerimeter = Math.min(minPerimeter, 2 * (i + N / i));
                }
                i++;
            }
            if (i * i == N) {
                minPerimeter = Math.min(minPerimeter, 2 * (i + N / i));
            }
            return (int)minPerimeter;
        }
    }
}