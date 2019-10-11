package backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SPAVANAC {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(s);
        int A = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());

        int hour;
        int minute;
        int remaining = B - 45;

        if (remaining >= 0) {
            hour = A;
            minute = remaining;
        } else {
            hour = A - 1;
            if (hour < 0) {
                hour = 23;
            }
            minute = 60 + remaining;
        }
        System.out.println(hour + " " + minute);
    }
}
