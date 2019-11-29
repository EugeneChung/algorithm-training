package programmers.binarysearch;

import helpers.TestHelper;

public class 입국심사 {
    public static void main(String[] args) {
        int[] times = {7, 10}; int n = 6; //28
//        int[] times = {1,2,2,2,100}; int n = 5;//2
//        int[] times = {1,1,10}; int n = 5;
//        int[] times = {3,4,10}; int n = 11;//18

        TestHelper.printSolution(new Solution().solution(n, times));
    }

    static class Solution {
        static int maxInArray(int... array) {
            int max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
            return max;
        }

        public long solution(int n, int[] times) {
            int maxTime = maxInArray(times);
            long low = 1;
            long high = (long)maxTime * (long)n;
            long minProcessingTime = 0;

            while (low <= high) {
                long people = n;
                long mid = (low + high) / 2;

                for (int time : times) {
                    people -= (mid / time);
                    if (people <= 0) {
                        break;
                    }
                }

                if (people > 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                    minProcessingTime = mid;
                }
            }
            return minProcessingTime;
        }
    }
}