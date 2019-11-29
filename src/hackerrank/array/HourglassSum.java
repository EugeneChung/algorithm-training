package hackerrank.array;

import helpers.TestHelper;

public class HourglassSum {
    public static void main(String[] args) {
        int arr[][] = {{-9, -9, -9, 1, 1, 1}, {0, -9, 0, 4, 3, 2}, {-9, -9, -9, 1, 2, 3}, {0, 0, 8, 6, 6, 0}, {0, 0, 0, -2, 0, 0}, {0, 0, 1, 2, 4, 0}};
//        int arr[][] = {{1, 1, 1, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};

        TestHelper.printSolution(
            hourglassSum(arr) 
        );
    }

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int[][] prefixSum = new int[6][4];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length - 2; j++) {
                prefixSum[i][j] = arr[i][j] + arr[i][j + 1] + arr[i][j + 2];
            }
        }
        //TestHelper.printMatrix(prefixSum);
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < prefixSum.length - 2; i++) {
            for (int j = 0; j < prefixSum[i].length; j++) {
                int sum = prefixSum[i][j] + arr[i + 1][j + 1] + prefixSum[i + 2][j];
                answer = Math.max(answer, sum);
            }
        }
        return answer;
    }
}
