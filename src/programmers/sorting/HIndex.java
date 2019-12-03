package programmers.sorting;

import helpers.TestHelper;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        int[] citations = {1, 0}; //1
//        int[] citations = {0, 0}; //0
//        int[] citations = {0}; //0
//        int[] citations = {3, 0, 6, 1, 5}; //3
        TestHelper.printSolution(new Solution().solution(citations));
    }

    static class Solution {
        public int solution(int[] citations) {
            Arrays.sort(citations);
            // 정렬을 하고 나면 HIndex 후보 중 최대값을 알게 된다.
            // 그 값을 시작으로 1씩 줄여나가면서 해당 후보값으로 binary search를 해본다.
            for (int h = citations[citations.length - 1]; h > 0; h--) {
                int loc = Arrays.binarySearch(citations, h);
                if (loc < 0) {
                    loc = -(loc + 1);
                }
                // binary search에서 리턴된 array location 값 기준으로
                // 'h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용'
                // 의 조건에 부합하는지 체크한다.
                int citatedCount = citations.length - loc;
                if (citatedCount >= h && loc <= h) {
                    return h;
                }
            }
            return 0;
        }
    }
}