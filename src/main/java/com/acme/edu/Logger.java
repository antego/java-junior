package com.acme.edu;


import com.acme.edu.decorator.DecoratorFactory;
import com.acme.edu.state.State;

/**
 * This class contains static methods to log values of different types.
 * You <b>MUST</b> call #stopLogging on the end of logging.
 */
public class Logger {
    /**
     * Platform independent line separator.
     */
    public static final String SEP = System.lineSeparator();

    private State state;
    private DecoratorFactory decorators;

    /**
     * Constructor creates new instance of Logger with specified printer object
     *
     * @param state printer object that can print in various output channels.
     */
    public Logger(State state, DecoratorFactory decorators) {
        this.state = state;
        this.decorators = decorators;
    }

    /**
     * Method to stop logging and flush buffers for {@code int}, {@code byte} and {@code String}.
     * You <b>MUST</b> call this method on the end of logging.
     */
    public void stopLogging() {
        state.getBlankState();
    }

    /**
     * Logs sum of {@code integer} values.
     *
     * @param message value for logging.
     */
    public void log(int message) {
        state = state.getIntState();
        state.processMessage(message + "", decorators.getIntDecorator());
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
        state = state.getBlankState();
        state.processMessage(message + "", decorators.getBoolDecorator());
    }

    /**
     * Logs {@code char} value.
     *
     * @param message value for logging.
     */
    public void log(char message) {
        state = state.getBlankState();
        state.processMessage(message + "", decorators.getCharDecorator());
    }

    /**
     * Logs {@code String} message.
     *
     * @param message value for logging.
     */
    public void log(String message) {
        state = state.getStringState();
        state.processMessage(message, decorators.getStringDecorator());
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
        state = state.getBlankState();
        state.processMessage(message.toString(), decorators.getObjectDecorator());
    }

    /**
     * Logs sum of elements of input integer array.
     *
     * @param oneDimensionalIntArray integer array for logging.
     */
    public void log(int... oneDimensionalIntArray) {
        state = state.getBlankState();
        int sumOfIntegers = 0;
        for (int arrayElement : oneDimensionalIntArray) {
            sumOfIntegers += arrayElement;
        }
        state.processMessage(sumOfIntegers + "", decorators.getIntArrayDecorator());
    }

    /**
     * Logs input integer matrix.
     *
     * @param integerMatrix integer matrix.
     */
    public void log(int[][] integerMatrix) {
        state = state.getBlankState();
        state.processMessage(dumpTwoDimensionalArray(integerMatrix), decorators.getIntTwoDimensionalArrayDecorator());
    }

    /**
     * Logs four-dimensional int array.
     *
     * @param fourDimensionalIntArray input four-dimensional array.
     */
    public void log(int[][][][] fourDimensionalIntArray) {
        state = state.getBlankState();
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
        state.processMessage(stringBuilder.toString(), decorators.getIntFourDimensionalArrayDecorator());
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
        state = state.getBlankState();
        for (String singleString : arrayOfStrings) {
            state.processMessage("" + singleString, decorators.getDummyDecorator());
        }
    }

    private String dumpTwoDimensionalArray(int[][] twoDimensionalArray) {
        StringBuilder stringBuilder = new StringBuilder("{" + SEP);
        for (int[] oneDimensionalIntArray : twoDimensionalArray) {
            stringBuilder.append("{");
            for (int i = 0; i < oneDimensionalIntArray.length - 1; i++) {
                stringBuilder.append(oneDimensionalIntArray[i]).append(", ");
            }
            stringBuilder.append(oneDimensionalIntArray[oneDimensionalIntArray.length - 1]).append("}" + SEP);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
