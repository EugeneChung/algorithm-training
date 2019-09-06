package helpers;

import java.util.Arrays;

public class TestHelper {
    public static void printSolution(Object solution) {
        if (solution.getClass().isArray()) {
            if (solution instanceof int[])
                System.out.println(Arrays.toString((int[]) solution));
            else if (solution instanceof long[])
                System.out.println(Arrays.toString((long[]) solution));
            else if (solution instanceof boolean[])
                System.out.println(Arrays.toString((boolean[]) solution));
            else
                System.out.println(Arrays.toString((Object[]) solution));
        } else {
            System.out.println(solution);
        }
    }
}
