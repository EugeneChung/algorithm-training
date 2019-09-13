package codility.lesson13;

import helpers.TestHelper;

import java.math.BigInteger;
import java.util.*;

public class FibFrog {
    public static void main(String[] args) {
        int[] A =
//            {1, 1, 0, 0, 0} // 2
//            {1, 0, 0} // 2
//            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} // 3
//            {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0} // 3
//            {1} // 1
//            {0, 0, 0} //-1
//            {0, 0, 0, 1, 0, 0, 0, 0, 0} //-1
//            {0, 0, 1, 1, 0, 0, 0, 0, 0} //-1
            {0, 0, 1, 1, 0, 0, 1, 0, 0} //4
            ;

        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private Set<BigInteger> buildFiboSet(BigInteger limit) {
            List<BigInteger> fiboList = new ArrayList<>(Arrays.asList(
                BigInteger.valueOf(0L), BigInteger.valueOf(1L))
            );

            while (true) {
                BigInteger fibo = fiboList.get(fiboList.size() - 2).add(fiboList.get(fiboList.size() - 1));

                int result = fibo.compareTo(limit);
                fiboList.add(fibo);
                if (result > 0) {
                    break;
                }
            }

            return new HashSet<>(fiboList);
        }

        public int solution(int[] A) {
            if (A.length == 0) return 1;

            Set<BigInteger> fiboSet = buildFiboSet(BigInteger.valueOf(A.length));
            List<Integer> jumpList = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                if (A[i] == 1) {
                    jumpList.add(i);
                }
            }
            jumpList.add(A.length);
            final int NO_JUMP = Integer.MAX_VALUE;

            int[] minJumps = new int[A.length + 1];
            Arrays.fill(minJumps, NO_JUMP);

            int curpos = -1;
            for (int i = 0; i < jumpList.size(); i++) {
                int prevJump = 0;
                if (curpos >= 0) {
                    prevJump = minJumps[curpos];
                }
                if (prevJump != NO_JUMP) {
                    for (int j = i; j < jumpList.size(); j++) {
                        int tarpos = jumpList.get(j);
                        int distance = tarpos - curpos;

                        if (fiboSet.contains(BigInteger.valueOf(distance))) {
                            minJumps[tarpos] = Math.min(minJumps[tarpos], prevJump + 1);
                        }
                    }

//                    TestHelper.log("curpos=" + curpos + ", minJumps=" + Arrays.toString(minJumps));
                }

                curpos = jumpList.get(i);
            }

            if (minJumps[A.length] == Integer.MAX_VALUE) {
                return -1;
            }
            return minJumps[A.length];
        }
    }
}