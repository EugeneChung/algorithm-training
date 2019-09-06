package codility;

import java.util.Arrays;

public class CyclicRotation {
    public static void main(String[] args) {
        //int[] array = {3, 8, 9, 7, 6};
        //int K = 3;
        int[] array = {1, 2, 3, 4};
        int K = 4;
        //int[] array = {0, 0, 0};
        //int K = 100;
        System.out.println(Arrays.toString(new Solution().solution(array, K)));
    }

    static class Solution {
        public int[] solution(int[] A, int K) {
            if (A.length == 0) return A;

            int realMove = K % A.length;
            if (realMove == 0) return A;

            int[] answer = new int[A.length];
            int toBeShiftedLength = A.length - realMove;
            System.arraycopy(A, toBeShiftedLength, answer, 0, realMove);
            System.arraycopy(A, 0, answer, realMove, toBeShiftedLength);
            return answer;
        }
    }
}