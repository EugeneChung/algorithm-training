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
//            {4, 4, 5, 5, 1}
//            {3, 3}
//            {100, 150}
            {1, 1}
            ;
        int[] B =
//            {3, 2, 4, 3, 1}
//            {1, 30}
//            {10, 30}
            {1, 1}
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

        public int[] solution(int[] A, int[] B) {
            //TestHelper.log(twoExponents);
            List<BigInteger> fiboLadders = new ArrayList<>(Arrays.asList(
                BigInteger.valueOf(0L), BigInteger.valueOf(1L), BigInteger.valueOf(2L), BigInteger.valueOf(3L))
            );
            int maxL = fiboLadders.size() - 1;

            int[] answer = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                // update fiboLadders list
                if (maxL < A[i]) {
                    for (int j = 0; j < A[i] - maxL; j++) {
                        fiboLadders.add(fiboLadders.get(fiboLadders.size() - 2).add(fiboLadders.get(fiboLadders.size() - 1)));
                    }
                    maxL = A[i];
                }
                //TestHelper.log(fiboLadders);

                BigInteger count = fiboLadders.get(A[i]);
                answer[i] = count.mod(twoExponents.get(B[i] - 1)).intValue();
            }
            return answer;
        }
    }
}