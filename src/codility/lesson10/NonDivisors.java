package codility.lesson10;

import helpers.TestHelper;

import java.util.*;

public class NonDivisors {
    public static void main(String[] args) {
        int[] A =
            {100, 10, 5, 15, 2, 3} // [2, 3, 5, 3, 5, 5]
//            {1, 2} // [1, 0]
//            {1, 1} // [0, 0]
//            {1} // [0]
//            {3, 1, 2, 3, 6, 1, 1} // [2, 4, 3, 2, 0, 4, 4]
//            {3, 1, 2, 3, 6} // [2, 4, 3, 2, 0]
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private class Element implements Comparable<Element> {
            final int index;
            final int value;

            Element(int index, int value) {
                this.index = index;
                this.value = value;
            }

            @Override
            public String toString() {
                return "A[" + index + "]=" + value;
            }

            @Override
            public int compareTo(Element o) {
                return Integer.compare(this.value, o.value);
            }
        }

        public static boolean[] buildPrimeCheckSet(int n) {
            // https://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
            boolean[] isPrime = new boolean[n + 1];
            for (int i = 2; i <= n; i++) {
                isPrime[i] = true;
            }

            // mark non-primes <= n using Sieve of Eratosthenes
            for (int factor = 2; factor * factor <= n; factor++) {

                // if factor is prime, then mark multiples of factor as nonprime
                // suffices to consider mutiples factor, factor+1, ...,  n/factor
                if (isPrime[factor]) {
                    for (int j = factor; factor * j <= n; j++) {
                        isPrime[factor * j] = false;
                    }
                }
            }

            return isPrime;
        }

        private Set<Integer> getDivisorSet(int n) {
            if (n == 1) return Collections.singleton(1);
            Set<Integer> set = new HashSet<>();
            int i = 1;
            while (i * i < n) {
                if (n % i == 0) {
                    set.add(i);
                    set.add(n / i);
                }
                i++;
            }
            if (n % i == 0) {
                set.add(i);
            }
            return set;
        }

        public int[] solution(int[] A) {
            if (A.length == 1) return new int[]{0};

            Map<Integer, Integer> counterMap = new HashMap<>();
            List<Element> elements = new ArrayList<>(A.length);
            for (int i = 0; i < A.length; i++) {
                elements.add(new Element(i, A[i]));

                Integer count = counterMap.getOrDefault(A[i], 0);
                count = count + 1;
                counterMap.put(A[i], count);
            }
            Collections.sort(elements);

//            TestHelper.log(elements);
//            TestHelper.log(counterMap);

            boolean[] primeCheker = buildPrimeCheckSet(elements.get(elements.size() - 1).value);

            int[] answer = new int[A.length];
            int countOfOne = counterMap.getOrDefault(1, 0);
            int prevValue = 0;
            int prevResult = 0;

            for (Element element : elements) {
                if (prevValue == element.value) {
                    answer[element.index] = prevResult;
                } else {
                    if (element.value == 1) {
                        answer[element.index] = A.length - countOfOne;
                    } else if (primeCheker[element.value]) {
                        answer[element.index] = A.length - (countOfOne + counterMap.get(element.value));
                    } else {
                        Set<Integer> divisorSet = getDivisorSet(element.value);
                        int divisorCount = 0;
                        for (int divisor : divisorSet) {
                            divisorCount += counterMap.getOrDefault(divisor, 0);
                        }
                        answer[element.index] = A.length - divisorCount;
                    }

                    // update for minor optimization
                    prevValue = element.value;
                    prevResult = answer[element.index];
                }
            }
            return answer;
        }
    }
}