package codility.lesson3;

public class TapeEquilibrium {
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
            int[] prefixSum = new int[A.length];
            int[] suffixSum = new int[A.length];

            int sum = 0;
            for (int i = 0; i < A.length; i++) {
                sum += A[i];
                prefixSum[i] = sum;
            }
            sum = 0;
            for (int i = A.length - 1; i >= 0; i--) {
                sum += A[i];
                suffixSum[i] = sum;
            }

            int answer = Integer.MAX_VALUE;
            for (int P = 1; P < A.length; P++) {
                answer = Math.min(Math.abs(prefixSum[P - 1] - suffixSum[P]), answer);
                if (answer == 0) {
                    break;
                }
            }

            return answer;
        }
    }
}