package programmers.greedy;

import helpers.TestHelper;

import java.util.Arrays;

public class 구명보트 {
    public static void main(String[] args) {
//        int[] people = {70, 50, 80, 50}; int limit = 100; //3
//        int[] people = {70, 50, 80}; int limit = 100; //3
//        int[] people = {40}; int limit = 40; //1
//        int[] people = {40, 40, 40, 40, 40}; int limit = 40; //5
//        int[] people = {40, 40, 50, 40, 40}; int limit = 40; //5
        int[] people = {40, 40, 50, 40, 40}; int limit = 90; //3

        TestHelper.printSolution(
            new Solution().solution(people, limit)
        );
    }

    static class Solution {
        public int solution(int[] people, int limit) {
            Arrays.sort(people);
            int answer = 0;

            int low = 0;
            int high = people.length - 1;
            while (low <= high) {
                if (people[low] + people[high] <= limit) {
                    low++;
                    high--;
                } else if (people[low] <= people[high]) {
                    high--;
                } else {
                    low++;
                }
                answer++;
            }

            return answer;
        }
    }
}
