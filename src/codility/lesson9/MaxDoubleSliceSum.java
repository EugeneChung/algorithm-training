package codility.lesson9;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.List;

public class MaxDoubleSliceSum {
    public static void main(String[] args) {
        int [] A =
            {3, 2, 6, -1, 4, 5, -1, 2}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 3) return 0;

            int maxEndingLeft = 0;
            int maxEndingRight = 0;

            List<Integer> maxEndingFromLeftList = new ArrayList<>(A.length);
            List<Integer> maxEndingFromRightList = new ArrayList<>(A.length);

            maxEndingFromLeftList.add(0); // for empty slice (X=0, Y=1)
            for (int i = 1; i < A.length - 1; i++) {
                maxEndingLeft = Math.max(0, maxEndingLeft + A[i]);
                maxEndingFromLeftList.add(maxEndingLeft);
            }
            maxEndingFromLeftList.add(0); // for empty slice (Y=N-2, Z=N-1)

            maxEndingFromRightList.add(0, 0);
            for (int j = A.length - 2; j > 0; j--) {
                maxEndingRight = Math.max(0, maxEndingRight + A[j]);
                maxEndingFromRightList.add(0, maxEndingRight);
            }
            maxEndingFromRightList.add(0, 0);
//            TestHelper.log(maxEndingFromLeftList);
//            TestHelper.log(maxEndingFromRightList);

            int answer = Integer.MIN_VALUE;
            for (int i = 1; i < maxEndingFromLeftList.size() - 1; i++) {
                int leftSum = maxEndingFromLeftList.get(i - 1);
                int rightSum = maxEndingFromRightList.get(i + 1);
                answer = Math.max(answer, leftSum + rightSum);
            }
            return answer;
        }
    }
}