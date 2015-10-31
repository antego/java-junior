package com.acme.edu;

import java.util.Arrays;

/**
 * This class contains static methods to log values of different types.
 * You <b>MUST</b> call #stopLogging on the end of logging.
 */
public class Logger {
    /**
     * Platform independent line separator.
     */
    public static final String SEP = System.lineSeparator();
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static int intSum = 0;
    private static boolean intSumSetted = false;

    private static String currentString = "";
    private static int sameStringsCount = 0;

    /**
     * Method to stop logging and flush buffers for {@code int}, {@code byte} and {@code String}.
     * You <b>MUST</b> call this method on the end of logging.
     */
    public static void stopLogging() {
        printIntSum();
        printCurrString();
        intSumSetted = false;
        intSum = 0;
        currentString = "";
        sameStringsCount = 0;
    }

    /**
     * Logs sum of {@code integer} values.
     *
     * @param message value for logging.
     */
    public static void log(int message) {
        printNumericValue(message, Integer.MAX_VALUE);
    }

    /**
     * Logs sum of {@code byte } values.
     *
     * @param message value for logging.
     */
    public static void log(byte message) {
        printNumericValue(message, Byte.MAX_VALUE);
    }

    /**
     * Logs {@code boolean} value.
     *
     * @param message value for logging.
     */
    public static void log(boolean message) {
        logBoolAndChar(PRIMITIVE_PREFIX, message + "");
    }

    /**
     * Logs {@code char} value.
     *
     * @param message value for logging.
     */
    public static void log(char message) {
        logBoolAndChar("char: ", message + "");
    }

    /**
     * Logs {@code String} message.
     *
     * @param message value for logging.
     */
    public static void log(String message) {
        if(message == null) return;
        printIntSum();
        if (message.equals(currentString)) {
            sameStringsCount++;
        } else {
            printCurrString();
            sameStringsCount = 1;
            currentString = message;
        }
    }

    /**
     * Logs input {@code Object.toString()}.
     *
     * @param message value for logging.
     */
    public static void log(Object message) {
        if(message == null) return;
        printIntSum();
        printInConsole("reference: ", message.toString());
    }

    /**
     * Logs input integer array.
     *
     * @param oneDimensionalIntArray integer array for logging.
     */
    public static void log(int[] oneDimensionalIntArray) {
        printInConsole("primitives array: ", Arrays.toString(oneDimensionalIntArray).replace("[", "{").replace("]", "}"));
    }

    /**
     * Logs input integer matrix.
     *
     * @param integerMatrix integer matrix.
     */
    public static void log(int[][] integerMatrix) {
        StringBuilder stringBuilder = new StringBuilder("{" + SEP);
        for (int[] oneDimensionalIntArray : integerMatrix) {
            //Array dumps
            stringBuilder.append(Arrays.toString(oneDimensionalIntArray).replace("[", "{").replace("]", "}")).append(SEP);
        }
        stringBuilder.append("}");
        printInConsole("primitives array: ", stringBuilder.toString());
    }

    /**
     * Logs multidimensional array.
     *
     * @param multiMatrix input multidimensional array.
     */
    public static void log(Object[] multiMatrix) {
        printInConsole("primitives multimatrix: ", Arrays.deepToString(multiMatrix).replace("[", "{" + SEP).replace("]", SEP + "}"));
    }

    /**
     * Logs array of Strings.
     *
     * @param arrayOfStrings input {@code String} array.
     */
    public static void log(String... arrayOfStrings) {
        if(arrayOfStrings == null) return;
        for (String singleString : arrayOfStrings) {
            printInConsole("", singleString);
        }
    }

    /**
     * Logs sum of four int values.
     *
     * @param i1 input value.
     * @param i2 input value.
     * @param i3 input value.
     * @param i4 input value.
     */
    public static void log(int i1, int i2, int i3, int i4) {
        //Can't do this with varargs or array because it breaks log(int[]) test
        printInConsole("", i1 + i2 + i3 + i4 + "");
    }

    private static void logBoolAndChar(String prefix, String message) {
        printIntSum();
        printCurrString();
        printInConsole(prefix, message);
    }

    //Method to log integer and byte values.
    //Main difference between types are MAX_VALUE which passes as argument to generic function
    private static void printNumericValue(int message, int maxValue) {
        printCurrString();
        if (((long) message + intSum) > maxValue) {
            printIntSum();
            intSum = message;
        } else {
            intSum += message;
        }
        intSumSetted = true;
    }

    private static void printIntSum() {
        if (intSumSetted) {
            printInConsole(PRIMITIVE_PREFIX, intSum + "");
        }
        intSum = 0;
        intSumSetted = false;
    }

    private static void printCurrString() {
        if (sameStringsCount == 1) {
            printInConsole("string: ", currentString);
        } else if (sameStringsCount > 1) {
            printInConsole("string: ", currentString + " (x" + sameStringsCount + ")");
        }
        currentString = "";
        sameStringsCount = 0;
    }

    private static void printInConsole(String prefix, String message) {
        System.out.println(prefix + message);
    }
}
