package com.acme.edu;

import java.util.Arrays;

/**
 * This class contains static methods to log values of different types.
 * You <b>MUST</b> call #stopLogging on the end of logging.
 */
public class Logger {
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static int intSumm = 0;
    private static boolean intSummSetted = false;

    private static byte byteSumm = 0;
    private static boolean byteSummSetted = false;

    private static String currentString = "";
    private static int sameStringsCount = 0;

    /**
     * Method to stop logging and flush buffers for {@code int}, {@code byte} and {@code String}.
     * You <b>MUST</b> call this method on the end of logging.
     */
    public static void stopLogging() {
        printSumm();
        printCurrString();
        byteSummSetted = false;
        byteSumm = 0;
        intSummSetted = false;
        intSumm = 0;
        currentString = "";
        sameStringsCount = 0;
    }

    /**
     * Logs sum of {@code integer} values.
     * @param message value for logging.
     */
    public static void log(int message) {
        printCurrString();
        if(((long) message + intSumm) > Integer.MAX_VALUE) {
            printIntSumm();
            intSumm = Integer.MAX_VALUE;
        } else {
            intSumm += message;
        }
        intSummSetted = true;
    }

    /**
     * Logs sum of {@code byte } values.
     * @param message value for logging.
     */
    public static void log(byte message) {
        printCurrString();
        if(((int) message + byteSumm) > Byte.MAX_VALUE) {
            printByteSumm();
            byteSumm = Byte.MAX_VALUE;
        } else {
            byteSumm += message;
        }
        byteSummSetted = true;
    }

    /**
     * Logs {@code boolean} value.
     * @param message value for logging.
     */
    public static void log(boolean message) {
        printSumm();
        printCurrString();
        printInConsole(PRIMITIVE_PREFIX, message + "");
    }

    /**
     * Logs {@code char} value.
     * @param message value for logging.
     */
    public static void log(char message) {
        printSumm();
        printCurrString();
        printInConsole("char: ", message + "");
    }

    /**
     * Logs {@code String} message.
     * @param message value for logging.
     */
    public static void log(String message) {
        printSumm();
        if(message.equals(currentString)) {
            sameStringsCount++;
        } else {
            printCurrString();
            sameStringsCount = 1;
            currentString = message;
        }
    }

    /**
     * Logs input {@code Object.toString()}.
     * @param message value for logging.
     */
    public static void log(Object message) {
        printSumm();
        printInConsole("reference: ", message.toString());
    }

    /**
     * Logs input integer array.
     * @param arr integer array for logging.
     */
    public static void log(int[] arr) {
        printInConsole("primitives array: ", Arrays.toString(arr).replace("[","{").replace("]","}"));
    }

    private static void printSumm() {
        printByteSumm();
        printIntSumm();
    }


    private static void printByteSumm() {
        if(byteSummSetted) {
            printInConsole(PRIMITIVE_PREFIX, byteSumm + "");
        }
        byteSumm = 0;
        byteSummSetted = false;
    }

    private static void printIntSumm() {
        if(intSummSetted) {
            printInConsole(PRIMITIVE_PREFIX, intSumm + "");
        }
        intSumm = 0;
        intSummSetted = false;
    }

    private static void printCurrString() {
        if(sameStringsCount == 1) {
            printInConsole("string: ", currentString);
        } else if(sameStringsCount > 1) {
            printInConsole("string: ", currentString + " (x" + sameStringsCount + ")");
        }
        currentString = "";
        sameStringsCount = 0;
    }

    private static void printInConsole(String prefix, String message) {
        System.out.println(prefix + message);
    }
}
