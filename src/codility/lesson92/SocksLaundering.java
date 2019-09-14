package codility.lesson92;

import helpers.TestHelper;

import java.util.*;

public class SocksLaundering {
    public static void main(String[] args) {
//        int K = 0; int[] C = {1, 2, 1, 1}, D = {1, 4, 3, 2, 4}; //1
          int K = 10; int[] C = {1,2,1,1,3}, D = {1,4,3,2,4}; //5
//        int K = 2; int[] C = {1, 2, 1, 1}, D = {1, 4, 3, 2, 4}; //3
//        int K = 10; int[] C = {1, 1, 1}, D = {2, 2, 2, 1}; //3
//        int K = 1; int[] C = {1, 1, 2, 1}, D = {2, 2, 2}; //2
//        int K = 5; int[] C = {2, 3, 4}, D = {2, 2, 2, 2, 2}; //3
//        int K = 100; int[] C = {2, 3, 4}, D = {2, 2, 2, 2, 2}; //3
//        int K = 100; int[] C = {1, 3, 4}, D = {2, 2, 2, 2, 2}; //2
//        int K = 100; int[] C = {2, 3, 4}, D = {2, 2, 2, 2, 2}; //3
//          int K = 1; int[] C = {1, 3, 4}, D = {2, 2, 2, 2, 2}; //2
//          int K = 2; int[] C = {1, 3, 4}, D = {2, 2, 2, 2, 2}; //2
//        int K = 10; int[] C = {1, 1}, D = {2, 2, 2, 1}; //2
//        int K = 10; int[] C = {1, 1}, D = {2, 2, 2}; //2
//        int K = 10; int[] C = {1, 1}, D = {2}; //1
//        int K = 10; int[] C = {1}, D = {2}; //0
//        int K = 10; int[] C = {1, 1, 1, 1, 4}, D = {1, 4, 3, 2, 4}; //3
//        int K = 3; int[] C = {1, 1, 1, 1}, D = {1, 4, 3, 2, 4}; //3
//        int K = 2; int[] C = {1, 1, 1, 1}, D = {1, 4, 3, 2, 4}; //3

        Object solution = new Solution().solution(K, C, D);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int K, int[] C, int[] D) {
            Arrays.sort(C);

            int currentSock = 0;
            int currentSockCount = 0;
            Set<Integer> cleanOddSockSet = new HashSet<>();
            for (int sock : C) {
                if (currentSock != sock) {
                    if (currentSockCount % 2 == 1) {
                        cleanOddSockSet.add(currentSock);
                    }
                    currentSock = sock;
                    currentSockCount = 1;
                } else {
                    currentSockCount++;
                }
            }
            if (currentSockCount % 2 == 1) {
                cleanOddSockSet.add(currentSock);
            }

            int cleanedSocksCount = 0;
            if (K > 0) {
                Map<Integer, Integer> dirtySocketMap = new HashMap<>();
                for (int value : D) {
                    int count = dirtySocketMap.getOrDefault(value, 0);
                    count++;
                    dirtySocketMap.put(value, count);
                }

                int laundaryRemainingCount = K;
                for (Iterator<Integer> iterator = cleanOddSockSet.iterator(); iterator.hasNext(); ) {
                    int dirtySock = iterator.next();
                    int count = dirtySocketMap.getOrDefault(dirtySock, 0);
                    if (count > 0) {
                        count--;
                        dirtySocketMap.put(dirtySock, count);
                        iterator.remove();
                        cleanedSocksCount++;
                        laundaryRemainingCount--;
                    }
                }
                while (laundaryRemainingCount > 1) {
                    int uselessEntryCount = 0;
                    for (Map.Entry<Integer, Integer> entry : dirtySocketMap.entrySet()) {
                        if (entry.getValue() > 1) {
                            cleanedSocksCount += 2;
                            laundaryRemainingCount = laundaryRemainingCount - 2;
                            entry.setValue(entry.getValue() - 2);
                        } else {
                            uselessEntryCount++;
                        }
                    }
                    if (uselessEntryCount == dirtySocketMap.size()) {
                        laundaryRemainingCount = 0;
                    }
                }
            }
//            TestHelper.log(cleanOddSockSet);

            int cleanSocks = C.length - cleanOddSockSet.size() + cleanedSocksCount;
            return cleanSocks / 2;
        }
    }
}