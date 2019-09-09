package codility.lesson13;

import helpers.TestHelper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ladder {
    public static void main(String[] args) {
        int[] A =
            {4, 4, 5, 5, 1}
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
        private static final List<BigInteger> twoExponents = new ArrayList<>(Collections.singletonList(BigInteger.valueOf(2L)));
        static {
            for (int i = 1; i < 30; i++) {
                BigInteger l = twoExponents.get(twoExponents.size() - 1);
                twoExponents.add(l.multiply(BigInteger.valueOf(2)));
            }
        }

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
            List<BigInteger> fiboLadders = new ArrayList<>(Arrays.asList(
                BigInteger.valueOf(0L), BigInteger.valueOf(1L), BigInteger.valueOf(2L), BigInteger.valueOf(3L))
            );
            int maxL = maxInArray(A);
            for (int i = 4; i <= maxL; i++) {
                fiboLadders.add(fiboLadders.get(fiboLadders.size() - 2).add(fiboLadders.get(fiboLadders.size() - 1)));
            }
            TestHelper.log(fiboLadders);

            int[] answer = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                BigInteger count = fiboLadders.get(A[i]);
                answer[i] = count.mod(twoExponents.get(B[i] - 1)).intValue();
            }
            return answer;
        }
    }
}