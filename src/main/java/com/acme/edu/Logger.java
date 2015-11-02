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
    private static String buffer = "0";

    private static int sameStringsCount = 0;

    enum State {HAS_STRING, HAS_INT, NO_STRING_OR_INT}

    static State state = State.NO_STRING_OR_INT;

    /**
     * Method to stop logging and flush buffers for {@code int}, {@code byte} and {@code String}.
     * You <b>MUST</b> call this method on the end of logging.
     */
    public static void stopLogging() {
        printState();
        buffer = "0";
        state = State.NO_STRING_OR_INT;
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
        if (message == null) {
            return;
        }
        if (state == State.HAS_STRING && message.equals(buffer)) {
            sameStringsCount++;
        } else {
            printState();
            state = State.HAS_STRING;
            sameStringsCount = 1;
            buffer = message;
        }
    }

    /**
     * Logs input {@code Object.toString()}.
     *
     * @param message value for logging.
     */
    public static void log(Object message) {
        if (message == null) {
            return;
        }
        printState();
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
        printInConsole("primitives array: ", dumpTwoDimensionalArray(integerMatrix));
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
                stringBuilder.append(dumpTwoDimensionalArray(twoDimensionalIntArray)).append(SEP);
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
        if (arrayOfStrings == null) {
            return;
        }
        for (String singleString : arrayOfStrings) {
            printInConsole("", singleString);
        }
    }

    private static void printState() {
        switch (state) {
            case HAS_STRING:
                if (sameStringsCount == 1) {
                    printInConsole("string: ", buffer);
                } else if (sameStringsCount > 1) {
                    printInConsole("string: ", buffer + " (x" + sameStringsCount + ")");
                }
                buffer = "0";
                sameStringsCount = 0;
                state = State.NO_STRING_OR_INT;
                break;
            case HAS_INT:
                printInConsole(PRIMITIVE_PREFIX, buffer);
                buffer = "0";
                state = State.NO_STRING_OR_INT;
                break;
        }
    }

    private static String dumpTwoDimensionalArray(int[][] twoDimensionalArray) {
        StringBuilder stringBuilder = new StringBuilder("{" + SEP);
        for (int[] oneDimensionalIntArray : twoDimensionalArray) {
            stringBuilder.append("{");
            for (int arrayElement : oneDimensionalIntArray) {
                stringBuilder.append(arrayElement).append(", ");
            }
            //Change last two symbols from comma and whitespace to bracket and newline
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}" + SEP);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static void logBoolAndChar(String prefix, String message) {
        printState();
        printInConsole(prefix, message);
    }

    //Method to log integer and byte values.
    //Main difference between types are MAX_VALUE which passes as argument to generic function
    private static void printNumericValue(int message, int maxValue) {
        if (state != State.HAS_INT)
            printState();
        if (((long) message + Integer.parseInt(buffer)) > maxValue) {
            printState();
            buffer = message + "";
        } else {
            buffer = Integer.parseInt(buffer) + message + "";
        }
        state = State.HAS_INT;
    }

    private static void printInConsole(String prefix, String message) {
        System.out.println(prefix + message);
    }
}
