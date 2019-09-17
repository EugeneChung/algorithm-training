package programmers.completesearch;

import helpers.TestHelper;

import java.util.HashSet;
import java.util.Set;

public class 소수찾기 {
    public static void main(String[] args) {
        String S = "013"; //3
//        String S = "17"; //3
//        String S = "011"; //2
//        String S = "0011";//1
//        String S = "1";//0
//        String S = "99999";//0
//        String S = "9000000";//0
//        String S = "22222";//1
        TestHelper.printSolution(new Solution().solution(S));
    }

    static class Solution {
        static void permutation(char[] arr, int depth, int n, int r, Set<Integer> targetNumbers) {
            if (depth == r) {
                targetNumbers.add(Integer.valueOf(new String(arr, 0, r)));
                return;
            }

            for (int i = depth; i < n; i++) {
                swap(arr, depth, i);
                permutation(arr, depth + 1, n, r, targetNumbers);
                swap(arr, depth, i);
            }
        }

        static void swap(char[] arr, int depth, int i) {
            char temp = arr[depth];
            arr[depth] = arr[i];
            arr[i] = temp;
        }

        public int solution(String numbers) {
            Set<Integer> targetNumbers = new HashSet<>();
            char[] arr = numbers.toCharArray();
            for(int r = 1; r <= arr.length; r++) {
                permutation(arr, 0, numbers.length(), r, targetNumbers);
            }
            TestHelper.log(targetNumbers);

            int answer = 0;
            for (int targetNumber : targetNumbers) {
               if (isPrime(targetNumber)) {
                   answer++; 
               }
            }
            return answer;
        }

        private boolean isPrime(long val) {
            if (val <= 1) return false;
            long i = 2;
            while (i * i <= val) {
                if (val % i == 0) {
                    return false;
                }
                i++;
            }
            return true;
        }
    }
}