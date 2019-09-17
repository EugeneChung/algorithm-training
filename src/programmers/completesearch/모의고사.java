package programmers.completesearch;

import helpers.TestHelper;

import java.util.*;

public class 모의고사 {
    public static void main(String[] args) {
//        int[] answers = {1, 3, 2, 4, 2}; //1,2,3
//        int[] answers = {1, 2, 3, 4, 5}; //1
//        int[] answers = {4, 5}; //[]
        int[] answers = {4}; //[]
        TestHelper.printSolution(new Solution().solution(answers));
    }

    static class Solution {
        private static final int[] solutionsOfFirst = {1, 2, 3, 4, 5};
        private static final int[] solutionsOfSecond = {2, 1, 2, 3, 2, 4, 2, 5};
        private static final int[] solutionsOfThrid = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        public int[] solution(int[] answers) {
            if (answers.length == 0) return new int[]{};

            int first = 0;
            int second = 0;
            int third = 0;

            for (int i = 0; i < answers.length; i++) {
                int answer = answers[i];
                if (answer == solutionsOfFirst[i % solutionsOfFirst.length]) {
                    first++;
                }
                if (answer == solutionsOfSecond[i % solutionsOfSecond.length]) {
                    second++;
                }
                if (answer == solutionsOfThrid[i % solutionsOfThrid.length]) {
                    third++;
                }
            }
            int max = Math.max(Math.max(first, second), third);
            if (max == 0) return new int[]{};

            List<Integer> array = new ArrayList<>();
            if (max == first) array.add(1);
            if (max == second) array.add(2);
            if (max == third) array.add(3);
            return array.stream().mapToInt(x -> x).toArray();
        }
    }
}