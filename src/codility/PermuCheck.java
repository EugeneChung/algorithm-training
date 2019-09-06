package codility;

import java.util.Arrays;

public class PermuCheck {
    public static void main(String[] args) {
        int[] A =
            //{0, 0};
            { 3, 1, 2, 4, 5 };
            //{2, 2, 2};
            //{ 1 };
            //{ 1, 2, 1, 3, 5, 2, 3, 1, 1, 2, 2 };
            //{ 1000000, 2000000, 1000000, 30000000, 5000000, 2000000, 30000000 };
            //{ 3, 3, 3, 5, 5 };
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