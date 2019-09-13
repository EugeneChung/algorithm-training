package codility.lesson14;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NailingPlanks {
    public static void main(String[] args) {
        int[] A =
            {1, 4, 5, 8}
//            {1, 4, 5, 8}
//            {1, 4, 5, 8}
            ;
        int[] B =
            {4, 5, 9, 10}
//            {1, 4, 5, 8}
//            {2, 4, 5, 8}
            ;
        int[] C =
            {4, 6, 7, 10, 2} // 4
//            {4, 6, 7, 10, 2}
//            {8, 5, 4, 2}
            ;
        Object solution = new Solution().solution(A, B, C);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A, int[] B, int[] C) {
            boolean[] nailed = new boolean[A.length];
            int low = 1;
            int high = C.length;

            int minNailCount = -1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (isAllPlanksNailed(A, B, C, mid, nailed)) {
                    minNailCount = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return minNailCount;
        }

        private boolean isAllPlanksNailed(int[] A, int[] B, int[] C, int targetNailCount, boolean[] nailed) {
            for (int i = 0; i < targetNailCount; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[j] <= C[i] && C[i] <= B[j]) {
                        nailed[j] = true;
                    }
                }
            }

            for (boolean v : nailed) {
                if (!v) return false;
            }
            Arrays.fill(nailed, false);
            return true;
        }
    }
}