package programmers.hash;

import helpers.TestHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 전화번호목록 {
    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"}; // false

        Object solution = new Solution().solution(phone_book);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public boolean solution(String[] phone_book) {
            Set<String> uniqueSet = new HashSet<>();
            for (String phoneNumber : phone_book) {
                if (!uniqueSet.add(phoneNumber)) {
                    return false;
                }
            }

            Set<String> numberStrings = new HashSet<>();
            boolean answer = true;
            for (String phoneNumber : uniqueSet) {
                StringBuilder builder = new StringBuilder();
                // 중복 string은 없는 상태에서 prefix만 조회하도록 고려하면 되므로
                // 맨 마지막 character는 고려하지 않는다.
                for (int i = 0; i < phoneNumber.length() - 1; i++) {
                    builder.append(phoneNumber.charAt(i));
                    numberStrings.add(builder.toString());
                }
            }
            for (String phoneNumber : uniqueSet) {
                if (numberStrings.contains(phoneNumber)) {
                    answer = false;
                    break;
                }
            }
            return answer;
        }
    }
}