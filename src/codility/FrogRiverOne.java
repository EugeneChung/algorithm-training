package codility;

import java.util.*;

public class FrogRiverOne {
    public static void main(String[] args) {
        int[] A =
            { 1 }
            //{ 1, 3, 1, 4, 2, 3, 5, 4 }
            //{ 1, 2, 3, 5, 4, 5 }
            //{1, 3, 1, 3, 2, 1, 3}
            //{1, 3, 2}
            ;
        int X =
            1
            //5
            //5
            //3
            //3
            ;
        System.out.println(
            new Solution().solution(X, A)
        );
    }

    static class Solution {
        public int solution(int X, int[] A) {
            Set<Integer> positionSet = new HashSet<>();
            for (int i = 0; i < A.length; i++) {
                int pos = A[i];
                positionSet.add(pos);
                if (positionSet.size() == X) {
                    return i;
                }
            }
            return -1;
        }
    }
}