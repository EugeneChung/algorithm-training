package codility.lesson3;

public class FrogJmp {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(1, 1000000000, 4));
    }

    static class Solution {
        public int solution(int X, int Y, int D) {
            if (X == Y) return 0;

            int diff = Y - X;
            int jmps = diff / D;
            if (diff % D == 0) return jmps;
            else return jmps + 1;
        }
    }
}