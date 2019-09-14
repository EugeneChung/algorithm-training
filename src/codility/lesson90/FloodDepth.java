package codility.lesson90;

import helpers.TestHelper;

public class FloodDepth {
    public static void main(String[] args) {
        int[] A =
            {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2, 3}
//            {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2}
//            {5, 8}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length < 3) return 0;

            int depth = 0;
            int maxDepth = 0;
            for (int i = 1; i < A.length - 1; i++) {
                int localDepth = A[i - 1] - A[i];
                if (localDepth < 0) {
                    depth += localDepth;
                    if (depth < 0) depth = 0;
                }
                else depth += localDepth;

                TestHelper.log(depth);
            }
            return maxDepth;
        }
    }
}