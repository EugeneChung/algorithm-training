package codility.lesson13;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ladder {
    public static void main(String[] args) {
        int[] A =
            {4, 4, 5, 5, 1} // [5, 1, 8, 0, 1]
//            {3, 3}
//            {100, 150}
//            {1, 1}
            ;
        int[] B =
            {3, 2, 4, 3, 1}
//            {1, 30}
//            {10, 30}
//            {1, 1}
            ;

        Object solution = new Solution().solution(A, B);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private int maxInArray(int... array) {
            int max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
            return max;
        }

        public int[] solution(int[] A, int[] B) {
            int maxL = maxInArray(A);
            List<Integer> fiboLadders = new ArrayList<>(maxL);
            fiboLadders.add(1); // n = 1
            fiboLadders.add(1); // n = 2
            int maxMod = 1 << 30; // 2^30
            for (int i = 2; i <= maxL; i++) {
                fiboLadders.add((fiboLadders.get(i - 2) + fiboLadders.get(i - 1)) % maxMod);
            }
            //TestHelper.log(fiboLadders);

            int[] answer = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                int fibo = fiboLadders.get(A[i]);
                answer[i] = fibo % (1 << B[i]); // 2^B[i]
            }
            return answer;
        }
    }
}