package helpers;

import java.util.Arrays;

public class TestHelper {
    public static void printSolution(Object solution) {
        log("-- Solution --");
        printObject(solution);
    }

    public static void printObject(Object message) {
        if (message.getClass().isArray()) {
            if (message instanceof int[])
                log(Arrays.toString((int[]) message));
            else if (message instanceof long[])
                log(Arrays.toString((long[]) message));
            else if (message instanceof boolean[])
                log(Arrays.toString((boolean[]) message));
            else
                log(Arrays.toString((Object[]) message));
        } else {
            log(message.toString());
        }
    }

    public static void log(Object message) {
        System.out.println(message);
    }
}
