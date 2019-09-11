package codility.lesson4;

import java.util.Arrays;

public class PermCheck {
    public static void main(String[] args) {
        int[] A =
            { 3, 1, 2, 4, 5 };
            //{2, 2, 2};
            //{ 1 };
        System.out.println(new Solution().solution(A));
    }

    static class Solution {
        public int solution(int[] A) {
            Arrays.sort(A);

            int expected = 1;
            for (int num : A) {
                if (num != expected) return 0;
                else expected = expected + 1;
            }
            return 1;
        }
    }
}