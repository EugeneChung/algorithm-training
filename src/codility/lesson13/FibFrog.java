package codility.lesson13;

import helpers.TestHelper;

import java.math.BigInteger;
import java.util.*;

public class FibFrog {
    public static void main(String[] args) {
        int[] A =
            {1, 0, 0} // 2
//            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} // 3
//            {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0} // 3
//            {1} // 1
//            {0, 0, 0} //-1
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
            if (A.length == 0) return -1;

            Set<BigInteger> fiboSet = buildFiboSet(BigInteger.valueOf(A.length));
            List<Integer> jumpList = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                if (A[i] == 1) {
                    jumpList.add(i);
                }
            }
            jumpList.add(A.length);
//            TestHelper.log(jumpList);

            int curpos = -1;
            int tarpos = A.length;
            int jumpListLowerLimit = -1;
            int jumpListIndex = jumpList.size() - 1;
            int answer = 0; //jmps

            while (curpos != tarpos) {
                int distance = tarpos - curpos;

//                TestHelper.log("curpos=" + curpos + ", tarpos=" + tarpos + ", distance=" + distance + ", jumpListLowerLimit=" + jumpListLowerLimit);

                if (fiboSet.contains(BigInteger.valueOf(distance))) {
                    jumpListLowerLimit = jumpListIndex;
                    jumpListIndex = jumpList.size() - 1;
                    curpos = tarpos;
                    answer++;
                } else {
                    jumpListIndex--;
                    if (jumpListIndex <= jumpListLowerLimit) {
                        break;
                    }
                }
                tarpos = jumpList.get(jumpListIndex);
            }

            if (curpos != tarpos || answer == 0) {
                return -1;
            }
            return answer;
        }
    }
}