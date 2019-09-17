package programmers.dynamicprogramming;

import helpers.TestHelper;

import java.util.*;

public class 타일장식물 {
    public static void main(String[] args) {
//        int N = 5; //26
//        int N = 6; //42
        int N = 4; //16

        TestHelper.printSolution(new Solution().solution(N));
    }

    static class Solution {
        private List<Long> buildFiboByCount(int countLimit) {
            List<Long> fiboList = new ArrayList<>(Arrays.asList(1L, 1L));
            int count = 2;

            while (count < countLimit) {
                long fibo = fiboList.get(fiboList.size() - 2) + fiboList.get(fiboList.size() - 1);
                fiboList.add(fibo);
                count++;
            }

            return fiboList;
        }

        public long solution(int N) {
            if (N <= 2) return N;
            List<Long> fibos = buildFiboByCount(N);
            TestHelper.log(fibos);
            long width = fibos.get(fibos.size() - 1) + fibos.get(fibos.size() - 2);
            long height = fibos.get(fibos.size() - 1);
            return 2L * (width + height);
        }
    }
}