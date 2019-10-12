package cracking.ds02;

import helpers.TestHelper;

import java.util.*;

public class Q2_1 {
    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>(Arrays.asList(1, 3, 1, 4, 5));

        TestHelper.printSolution(
            new Q2_1.Solution().solution(l)
        );
    }

    static class Solution {
        public LinkedList<Integer> solution(LinkedList<Integer> l) {
            Set<Integer> checkSet = new HashSet<>(l.size());
            l.removeIf(next -> !checkSet.add(next));
            return l;
        }
    }
}
