package codility.lesson2;

import java.util.Arrays;

public class OddOccurrencesInArray {
    public static void main(String[] args) {
        int[] A =
            //{9, 3, 9, 3, 9, 7, 9};
            //{ 1, 2, 1, 3, 5, 2, 3 };
            //{ 1, 2, 1, 3, 5, 2, 3, 1, 1, 2, 2 };
            { 1000000, 2000000, 1000000, 30000000, 5000000, 2000000, 30000000 };
            //{ 3, 3, 3, 5, 5 };
        System.out.println(new Solution().solution(A));
    }

    static class Solution {
        public int solution(int[] A) {
            int occurrence = 0;
            int target = 0;

            Arrays.sort(A);
            for (int num : A) {
                if (num == target) {
                    occurrence = occurrence + 1;
                } else {
                    if (occurrence % 2 == 1) {
                        break;
                    } else {
                        target = num;
                        occurrence = 1;
                    }
                }
            }
            return target;
        }
    }
}