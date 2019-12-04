package programmers.dynamicprogramming;

import helpers.TestHelper;

import java.util.*;

public class N으로표현 {
    public static void main(String[] args) {
        int N = 5, number = 12; //4
//        int N = 5, number = 1; //2
//        int N = 7, number = 2; //2
//        int N = 7, number = 32000; //-1

        TestHelper.printSolution(new Solution().solution(N, number));
    }

    static class Solution {
        public int solution(int N, int number) {
            if (N == number) return 1;

            List<Set<Integer>> resultsList = new ArrayList<>();
            for (int i = 0; i <= 8; i++) {
                resultsList.add(new HashSet<>());
            }
            resultsList.get(1).add(N);

            List<Integer> nCombinationList = new ArrayList<>();
            nCombinationList.add(0);
            nCombinationList.add(N);
            for (int i = 2; i <= 8; i++) {
                nCombinationList.add(10 * nCombinationList.get(i - 1) + N);
            }

            for (int i = 2; i <= 8; i++) {
                Set<Integer> currentResults = resultsList.get(i);
                for (int j = 1; j < i; j++) { // j == the number of N used
                    Set<Integer> prevResults = resultsList.get(i - j);
                    int targetNcomb = nCombinationList.get(j);
                    for (int result : prevResults) {
                        currentResults.add(result + targetNcomb);
                        currentResults.add(result - targetNcomb);
                        currentResults.add(result * targetNcomb);
                        currentResults.add(result / targetNcomb);
                    }
                }
                currentResults.add(nCombinationList.get(i));
                if (currentResults.contains(number)) {
                    return i;
                }
            }

            return -1;
        }
    }
}