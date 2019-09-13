package codility.lesson13;

import helpers.TestHelper;

import java.util.*;

public class FibFrog {
    public static void main(String[] args) {
        int[] A =
//            {1, 1, 0, 0, 0} // 2
//            {1, 0, 0} // 2
//            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} // 3
//            {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0} // 3
//            {1} // 1
            {1, 1} // 1
//            {0, 0, 0} //-1
//            {0, 0, 0, 1, 0, 0, 0, 0, 0} //-1
//            {0, 0, 1, 1, 0, 0, 0, 0, 0} //-1
//            {0, 0, 1, 1, 0, 0, 1, 0, 0} //4
            ;

        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private List<Integer> buildFibo(int limit) {
            List<Integer> fiboList = new ArrayList<>(Arrays.asList(1, 1));

            while (true) {
                int fibo = fiboList.get(fiboList.size() - 2) + fiboList.get(fiboList.size() - 1);
                fiboList.add(fibo);

                if (fibo > limit) {
                    break;
                }
            }

            return fiboList;
        }

        public int solution(int[] A) {
            if (A.length == 0) return 1;

            List<Integer> fiboList = buildFibo(A.length);
            final int NO_JUMP = Integer.MAX_VALUE;

            int[] minJumps = new int[A.length + 1];
            Arrays.fill(minJumps, NO_JUMP);

            int curpos = -1;
            for (int i = 0; i <= A.length; i++) {
                if (i == A.length || A[i] == 1) {
                    int prevJump = 0;
                    if (curpos >= 0) {
                        prevJump = minJumps[curpos];
                    }
                    if (prevJump != NO_JUMP) {
                        for (int fibo : fiboList) {
                            int targetPos = curpos + fibo;
                            if (targetPos == A.length || (targetPos < A.length && A[targetPos] == 1)) {
                                minJumps[targetPos] = Math.min(minJumps[targetPos], prevJump + 1);
                            }
                        }

                        TestHelper.log("curpos="  + curpos + ", minJumps=" + Arrays.toString(minJumps));
                    }
                    curpos = i;
                }
            }

            if (minJumps[A.length] == Integer.MAX_VALUE) {
                return -1;
            }
            return minJumps[A.length];
        }
    }
}