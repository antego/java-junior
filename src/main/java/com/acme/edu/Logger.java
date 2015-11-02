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

    /**
     * Prefix for output formatting.
     */
    public static final String PRIMITIVE_PREFIX = "primitive: ";

    private final BlankState blankState;
    private final HasIntState hasIntState;
    private final HasStringState hasStringState;

    private State state;

    /**
     * Constructor creates new instance of Logger with specified printer object
     *
     * @param printer printer object that can print in various output channels.
     */
    public Logger(Printer printer) {
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
        if (state != hasIntState) {
            state.flushBuffer();
        }
        state = hasIntState;
        state.processMessage(message + "");
    }

    /**
     * Logs sum of {@code byte } values.
     *
     * @param message value for logging.
     */
    public void log(byte message) {
        log((int) message);
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
        if (state != hasStringState) {
            state.flushBuffer();
        }
        state = hasStringState;
        state.processMessage(message);
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
        flushState();
        state.processMessage("reference: " + message.toString());
    }

    /**
     * Logs sum of elements of input integer array.
     *
     * @param oneDimensionalIntArray integer array for logging.
     */
    public void log(int... oneDimensionalIntArray) {
        flushState();
        int sumOfIntegers = 0;
        for (int arrayElement : oneDimensionalIntArray) {
            sumOfIntegers += arrayElement;
        }
        state.processMessage("primitives array: " + sumOfIntegers + "");
    }

    /**
     * Logs input integer matrix.
     *
     * @param integerMatrix integer matrix.
     */
    public void log(int[][] integerMatrix) {
        flushState();
        state.processMessage("primitives array: " + dumpTwoDimensionalArray(integerMatrix));
    }

    /**
     * Logs four-dimensional int array.
     *
     * @param fourDimensionalIntArray input four-dimensional array.
     */
    public void log(int[][][][] fourDimensionalIntArray) {
        flushState();
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
        state.processMessage("primitives multimatrix: " + stringBuilder.toString());
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
        flushState();
        for (String singleString : arrayOfStrings) {
            state.processMessage("" + singleString);
        }
    }

    private String dumpTwoDimensionalArray(int[][] twoDimensionalArray) {
        flushState();
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
        flushState();
        state.processMessage(prefix + message);
    }

    private void flushState() {
        if (state != blankState) {
            state.flushBuffer();
            state = blankState;
        }
    }
}
