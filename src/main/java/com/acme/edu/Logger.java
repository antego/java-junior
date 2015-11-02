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
    public static final String PRIMITIVE_PREFIX = "primitive: ";

    private final BlankState blankState;
    private final HasIntState hasIntState;
    private final HasStringState hasStringState;

    private State state;
    private Printer printer;

    public Logger(Printer printer) {
        this.printer = printer;
        blankState = new BlankState(printer);
        hasIntState = new HasIntState(printer);
        hasStringState = new HasStringState(printer);
        state = blankState;
    }

    /**
     * Method to stop logging and flush buffers for {@code int}, {@code byte} and {@code String}.
     * You <b>MUST</b> call this method on the end of logging.
     */
    public void stopLogging() {
        state.flushBuffer();
    }

    /**
     * Logs sum of {@code integer} values.
     *
     * @param message value for logging.
     */
    public void log(int message) {
        if(state != hasIntState) {
            state.flushBuffer();
        }
        state = hasIntState;
        state.processMessage(message + "", MessageType.INT);
    }

    /**
     * Logs sum of {@code byte } values.
     *
     * @param message value for logging.
     */
    public void log(byte message) {
        if(state != hasIntState) {
            state.flushBuffer();
        }
        state = hasIntState;
        state.processMessage(message + "", MessageType.BYTE);
    }

    /**
     * Logs {@code boolean} value.
     *
     * @param message value for logging.
     */
    public void log(boolean message) {
        logBoolAndChar(PRIMITIVE_PREFIX, message + "");
    }

    /**
     * Logs {@code char} value.
     *
     * @param message value for logging.
     */
    public void log(char message) {
        logBoolAndChar("char: ", message + "");
    }

    /**
     * Logs {@code String} message.
     *
     * @param message value for logging.
     */
    public void log(String message) {
        if(state != hasStringState) {
            state.flushBuffer();
        }
        state = hasStringState;
        state.processMessage(message, MessageType.STRING);
    }

    /**
     * Logs input {@code Object.toString()}.
     *
     * @param message value for logging.
     */
    public void log(Object message) {
        if (message == null) {
            return;
        }
        if (state != blankState) {
            state.flushBuffer();
            state = blankState;
        }
        printInConsole("reference: ", message.toString());
    }

    /**
     * Logs sum of elements of input integer array.
     *
     * @param oneDimensionalIntArray integer array for logging.
     */
    public void log(int... oneDimensionalIntArray) {
        if (state != null) {
            state.flushBuffer();
            state = null;
        }
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
    public void log(int[][] integerMatrix) {
        if (state != null) {
            state.flushBuffer();
            state = null;
        }
        printInConsole("primitives array: ", dumpTwoDimensionalArray(integerMatrix));
    }

    /**
     * Logs four-dimensional int array.
     *
     * @param fourDimensionalIntArray input four-dimensional array.
     */
    public void log(int[][][][] fourDimensionalIntArray) {
        if (state != null) {
            state.flushBuffer();
            state = null;
        }
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
    public void log(String... arrayOfStrings) {
        if (arrayOfStrings == null) {
            return;
        }
        if (state != null) {
            state.flushBuffer();
            state = null;
        }
        for (String singleString : arrayOfStrings) {
            printInConsole("", singleString);
        }
    }
//
//    //Method to log integer and byte values.
//    //Main difference between types are MAX_VALUE which passes as argument to generic function
//    private static void printNumericValue(int message, int maxValue) {
//        if (state != State.HAS_INT)
//            flushBuffer();
//        if (((long) message + (buffer.isEmpty() ? 0 : Integer.parseInt(buffer))) > maxValue) {
//            flushBuffer();
//            buffer = message + "";
//        } else {
//            buffer = (buffer.isEmpty() ? 0 : Integer.parseInt(buffer)) + message + "";
//        }
//        state = State.HAS_INT;
//    }

    private String dumpTwoDimensionalArray(int[][] twoDimensionalArray) {
        if (state != null) {
            state.flushBuffer();
            state = null;
        }
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

    private void logBoolAndChar(String prefix, String message) {
        if (state != blankState) {
            state.flushBuffer();
            state = blankState;
        }
        printInConsole(prefix, message);
    }

    private static void printInConsole(String prefix, String message) {
        System.out.println(prefix + message);
    }
}
