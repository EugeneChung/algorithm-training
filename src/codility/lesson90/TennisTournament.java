package codility.lesson90;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TennisTournament {
    public static void main(String[] args) {
//        int P = 5, C = 3; // 2
//        int P = 10, C = 3; // 3
//        int P = 1, C = 3; // 0
//        int P = 10, C = 1; // 1
//        int P = 1, C = 1; // 0
        int P = 20000, C = 9999; // 0
            ;
        Object solution = new Solution().solution(P, C);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int P, int C) {
            return Math.min(P / 2, C);
        }
    }
}