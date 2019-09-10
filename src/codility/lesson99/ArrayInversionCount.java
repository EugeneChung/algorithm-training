package codility.lesson99;

import helpers.TestHelper;

import java.util.*;

public class ArrayInversionCount {
    public static void main(String[] args) {
        int[] A =
            {-1, 6, 3, 4, 7, 4}
//            {3, 3}
//            {100, 150}
//            {1, 1}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private class Pair {
            int lower;
            int higher;

            Pair(int lower, int higher) {
                this.lower = lower;
                this.higher = higher;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return lower == pair.lower &&
                    higher == pair.higher;
            }

            @Override
            public int hashCode() {
                return Objects.hash(lower, higher);
            }
        }
        private class InversionCounter {
            int counter;
            Set<Pair> pairs = new HashSet<>();
        }
        private class OverflowException extends RuntimeException {
        }

        private class Element implements Comparable<Element> {
            final int index;
            final int value;
            private final InversionCounter counter;

            private Element(int index, int value, InversionCounter counter) {
                this.index = index;
                this.value = value;
                this.counter = counter;
            }

            @Override
            public int compareTo(Element o) {
                int compare = Integer.compare(this.value, o.value);
                if (this.index > o.index && compare < 0) {
                    Pair pair = new Pair(o.index, this.index);
                    if (counter.pairs.add(pair)) {
                        counter.counter++;
                        if (counter.counter > 1_000_000_000) {
                            throw new OverflowException();
                        }
                    }
                }
                return compare;
            }
        }

        public int solution(int[] A) {
            if (A.length < 2) return 0;

            InversionCounter counter = new InversionCounter();
            List<Element> elementList = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                elementList.add(new Element(i, A[i], counter));
            }
            try {
                Collections.sort(elementList);
            } catch (OverflowException e) {
                return -1;
            }
            return counter.counter;
        }
    }
}