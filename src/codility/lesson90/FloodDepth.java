package codility.lesson90;

import helpers.TestHelper;

public class FloodDepth {
    public static void main(String[] args) {
        int[] A =
//            {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2, 3}
//            {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2} // 2
//            {4, 3, 2, 1, 2, 4} // 3
//            {4, 3, 2, 1, 2} // 1
//            {4, 3, 2, 1, 2, 5} // 3
//            {5, 8, 3} //0 
//            {5, 8} // 0
            {4, 3, 2, 1} // 0
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            int res = 0;
            int high = A[0];
            int low = A[0];
            for (int i = 1; i < A.length; i++) {
                if (A[i - 1] < A[i]) {
                    if (A[i] <= high) {
                        if (res < A[i] - low) res = A[i] - low;
                    } else {
                        if (res < high - low) res = high - low;
                        high = A[i];
                        low = A[i];
                    }
                } else {
                    if (A[i] < low) low = A[i];
                }
            }
            return res;
        }
    }
}