package cracking.ds02;

import helpers.TestHelper;

import java.util.Arrays;
import java.util.LinkedList;

public class Q2_4 {
    public static void main(String[] args) {
//        LinkedList<Integer> l = new LinkedList<>(Arrays.asList(4, 5, 1, 2, 3)); int x = 2;
        LinkedList<Integer> l = new LinkedList<>(Arrays.asList(4, 5, 1, 2, 3)); int x = 3;
        
        TestHelper.printSolution(
            new Q2_4.Solution().solution(l, x)
        );
    }

    static class Solution {
        // when we know the length of list
        public LinkedList<Integer> solution(LinkedList<Integer> l, int x) {
            int i = 1, j = 0;

            for (; i < l.size(); i++) {
                int target = l.get(i);
                if (target < x) {
                    int temp = l.get(j);
                    l.set(j++, target);
                    l.set(i, temp);
                }
            }
            return l;
        }
    }
}
