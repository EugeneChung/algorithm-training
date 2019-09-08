package codility.lesson3;

import java.util.Arrays;

public class TapeEq {
    public static void main(String[] args) {
        int[] A =
            //{0, 0};
            //{ 3, 1, 2, 4, 3 };
            {-10, -5, -3, -4, -5};
            //{ 1 };
            //{ 1, 2, 1, 3, 5, 2, 3, 1, 1, 2, 2 };
            //{ 1000000, 2000000, 1000000, 30000000, 5000000, 2000000, 30000000 };
            //{ 3, 3, 3, 5, 5 };
        System.out.println(new Solution().solution(A));
    }

    static class Solution {
        public int solution(int[] A) {
            int[] sum_asc = new int[A.length];
            int[] sum_desc = new int[A.length];

            int sum = 0;
            for (int i = 0; i < A.length; i++) {
                sum += A[i];
                sum_asc[i] = sum;
            }
            sum = 0;
            for (int i = A.length - 1; i >= 0; i--) {
                sum += A[i];
                sum_desc[i] = sum;
            }

            int answer = Integer.MAX_VALUE;
            for (int P = 1; P < A.length; P++) {
                answer = Math.min(Math.abs(sum_asc[P - 1] - sum_desc[P]), answer);
                if (answer == 0) {
                    break;
                }
            }

            return answer;
        }
    }
}