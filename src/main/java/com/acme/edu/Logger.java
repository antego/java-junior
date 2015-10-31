package com.acme.edu;


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
        if(message == null) {
            return;
        }
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
        if(message == null) {
            return;
        }
        printIntSum();
        printInConsole("reference: ", message.toString());
    }

    /**
     * Logs sum of elements of input integer array.
     *
     * @param oneDimensionalIntArray integer array for logging.
     */
    public static void log(int... oneDimensionalIntArray) {
        int sumOfIntegers = 0;
        for (int arrayElement : oneDimensionalIntArray) {
            sumOfIntegers += arrayElement;
        }
        printInConsole("primitives array: ", sumOfIntegers + "");
    }

    /**
     * Logs input integer matrix.
     *
     * @param integerMatrix integer matrix.
     */
    public static void log(int[][] integerMatrix) {
        StringBuilder stringBuilder = new StringBuilder("{" + SEP);
        for (int[] oneDimensionalIntArray : integerMatrix) {
            stringBuilder.append("{");
            for(int arrayElement : oneDimensionalIntArray) {
                stringBuilder.append(arrayElement).append(", ");
            }
            //Change last two symbols from comma and whitespace to bracket and newline
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}" + SEP);
        }
        stringBuilder.append("}");
        printInConsole("primitives array: ", stringBuilder.toString());
    }

    /**
     * Logs four-dimensional int array.
     *
     * @param fourDimensionalIntArray input four-dimensional array.
     */
    public static void log(int[][][][] fourDimensionalIntArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append(SEP);
        for (int[][][] threeDimensionalIntArray : fourDimensionalIntArray) {
            stringBuilder.append("{").append(SEP);
            for (int[][] twoDimensionalIntArray : threeDimensionalIntArray) {
                stringBuilder.append("{").append(SEP);
                for (int[] plainIntArray : twoDimensionalIntArray) {
                    stringBuilder.append("{").append(SEP);
                    for (int intElement : plainIntArray) {
                        stringBuilder.append(intElement).append(", ");
                        //Change last two symbols from comma and whitespace to newline
                        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(),SEP);
                    }
                    stringBuilder.append("}").append(SEP);
                }
                stringBuilder.append("}").append(SEP);
            }
            stringBuilder.append("}").append(SEP);
        }
        stringBuilder.append("}");
        printInConsole("primitives multimatrix: ", stringBuilder.toString());
    }

    /**
     * Logs array of Strings.
     *
     * @param arrayOfStrings input {@code String} array.
     */
    public static void log(String... arrayOfStrings) {
        if(arrayOfStrings == null) {
            return;
        }
        for (String singleString : arrayOfStrings) {
            printInConsole("", singleString);
        }
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
