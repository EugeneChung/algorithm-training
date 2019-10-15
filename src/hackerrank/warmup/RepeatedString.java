package hackerrank.warmup;

import helpers.TestHelper;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RepeatedString {
    public static void main(String[] args) {
//        String s = "aba"; int n = 10;
        String s = "aba"; int n = 1;

        TestHelper.printSolution(
            repeatedString(s, n)
        );
    }

    // Complete the repeatedString function below.
    static long countA(String s, int length) {
        long count = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == 'a') count++;
        }
        return count;
    }
    static long repeatedString(String s, long n) {
        if (s.length() >= n) {
            return countA(s, (int)n);
        }
        long fullCountA = countA(s, s.length());
        if (fullCountA == 0) return 0;
        long remainder = n / s.length();
        int mod = (int) (n % s.length());
        return remainder * fullCountA + countA(s, mod); 
    }
}
