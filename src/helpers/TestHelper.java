package helpers;

import java.util.Arrays;

public class TestHelper {
    public static void printSolution(Object solution) {
        log("-- Solution --");
        printArray(solution);
    }

    public static void printArray(Object array) {
        if (array.getClass().isArray()) {
            if (array instanceof int[])
                log(Arrays.toString((int[]) array));
            else if (array instanceof long[])
                log(Arrays.toString((long[]) array));
            else if (array instanceof boolean[])
                log(Arrays.toString((boolean[]) array));
            else
                log(Arrays.toString((Object[]) array));
        } else {
            log(array.toString());
        }
    }

    public static void log(Object message) {
        System.out.println(message);
    }
}
