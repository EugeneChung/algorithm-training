package codility.lesson13;

import helpers.TestHelper;

import java.math.BigInteger;
import java.util.*;

public class FibFrog {
    public static void main(String[] args) {
        int[] A =
//            {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}
//            {1}
            {0, 0, 0}
            ;

        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private List<BigInteger> buildFiboList(BigInteger limit) {
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

            return fiboList;
        }

        public int solution(int[] A) {
            if (A.length == 0) return -1;

            List<BigInteger> fiboList = buildFiboList(BigInteger.valueOf(A.length));

            int distance = A.length + 1;
            int jumps = 0;
            int position = -1;

            // 바로 N으로 뛸 수 있는지 체크
            int loc = Collections.binarySearch(fiboList, BigInteger.valueOf(distance));
            if (loc >= 0) {
                return 1;
            }

            List<Integer> leafList = new ArrayList<>();
            for (int i = A.length - 1; i >= 0; i--) {
                distance--;

                if (A[i] == 1) {
                    loc = Collections.binarySearch(fiboList, BigInteger.valueOf(distance));
                    if (loc >= 0) {
//                        TestHelper.log("Current distance=" + distance + ", position=" + position);
                        position = i;
                        distance = A.length - position;
//                        TestHelper.log("Next distance=" + distance + ", position=" + position);
                        jumps++;
                        break;
                    } else {
                        leafList.add(i);
                    }
                }
            }

            // 바로 N으로 뛸 수 있는지 체크
            loc = Collections.binarySearch(fiboList, BigInteger.valueOf(distance));
            if (loc >= 0) {
                return jumps + 1;
            }

            for (int i = 0; i < leafList.size(); i++) {
                int nextLeaf = leafList.get(i);
                distance = nextLeaf - position;

                loc = Collections.binarySearch(fiboList, BigInteger.valueOf(distance));
                if (loc >= 0) {
                    jumps++;
                    position = nextLeaf;
                    distance = A.length - position;
                    leafList.remove(i);
                    i = 0;

                    // 바로 N으로 뛸 수 있는지 체크
                    loc = Collections.binarySearch(fiboList, BigInteger.valueOf(distance));
                    if (loc >= 0) {
                        jumps++;
                        position = A.length;
                        break;
                    }
                }
            }

            if (jumps == 0 || position < A.length) return -1;
            return jumps;
        }
    }
}