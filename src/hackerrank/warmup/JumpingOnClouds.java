package hackerrank.warmup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JumpingOnClouds {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        if (c.length <= 3) return 1;
        return Math.min(jump(c, 0, 1, 0), jump(c, 0, 2, 0));
    }

    private static int jump(int[] c, int position, int distance, int numJumps) {
        int nextPos = position + distance;
        if (nextPos > c.length - 1 || c[nextPos] == 1) {
            return Integer.MAX_VALUE;
        } else if (nextPos == c.length - 1) {
            return numJumps + 1;
        }
        int jump1 = jump(c, nextPos, 1, numJumps + 1);
        int jump2 = jump(c, nextPos, 2, numJumps + 1);
        return Math.min(jump1, jump2);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
