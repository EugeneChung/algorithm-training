package codility;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PassingCars {
    public static void main(String[] args) {
        int[] A =
            //{ 0, 1, 0, 1, 1 }
            //{ 1, 0, 1 }
            //{ 0, 0, 0 }
            //{ 1, 1, 1 }
            //{ 1 }
//            { 0 }
//            { 1, 0, 0 }
            { 0, 0, 1 }
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            int eastCarCount = 0;
            int pairs = 0;
            for (int carDirection : A) {
                if (carDirection == 0) {
                    eastCarCount += 1;
                } else {
                    pairs += eastCarCount;
                    if (pairs > 1000000000) {
                        return -1;
                    }
                }
            }
            return pairs;
        }
    }
}