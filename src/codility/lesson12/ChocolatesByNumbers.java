package codility.lesson12;

import helpers.TestHelper;

import java.util.*;

public class ChocolatesByNumbers {
    public static void main(String[] args) {
        int N =
//            10
//            4
//            2
            1_000_000_000
            ;
        int M =
//            4
//            4
//            1
            1
            ;

        Object solution = new Solution().solution(N, M);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int N, int M) {
            if (N == 1) return 1;

            int ateChocolateCount = 1;
            int currentChocolate = 0;

            do {
                int nextChocolate = (currentChocolate + M) % N;
                if (nextChocolate == 0) {
                    break;
                }
                currentChocolate = nextChocolate;
                ateChocolateCount++;
            } while (true);

            return ateChocolateCount;
        }
    }
}