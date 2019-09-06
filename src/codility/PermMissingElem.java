package codility;

import java.util.Arrays;

public class PermMissingElem {
    public static void main(String[] args) {
        int[] A =
            {};
            //{ 2, 3, 1, 5 };
            //{ 1 };
            //{ 1, 2, 1, 3, 5, 2, 3, 1, 1, 2, 2 };
            //{ 1000000, 2000000, 1000000, 30000000, 5000000, 2000000, 30000000 };
            //{ 3, 3, 3, 5, 5 };
        System.out.println(new Solution().solution(A));
    }

    static class Solution {
        public int solution(int[] A) {
            int answer = 1;

            Arrays.sort(A);
            for (int num : A) {
                if (num != answer) break;
                else answer = answer + 1;
            }

            return answer;
        }
    }
}