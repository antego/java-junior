package com.acme.edu;

import com.acme.edu.printer.PrinterException;
import com.acme.edu.state.*;

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
    private StateManager stateManager;

    /**
     * Constructor creates new instance of Logger with specified printer object
     *
     * @param stateManager printer object that can print in various output channels.
     */
    public Logger(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * Method to stop logging and flush buffers for {@code int}, {@code byte} and {@code String}.
     * You <b>MUST</b> call this method on the end of logging.
     */
    public void stopLogging() throws LogException {
        try {
            stateManager.getWantedState(state, new NoBufferState());
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs sum of {@code integer} values.
     *
     * @param message value for logging.
     */
    public void log(int message) throws LogException {
        try {
            state = stateManager.getWantedState(state, new IntBufferState());
            state.processMessage(message + "", "primitive: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs sum of {@code byte } values.
     *
     * @param message value for logging.
     */
    public void log(byte message) throws LogException {
        log((int) message);
    }

    /**
     * Logs {@code boolean} value.
     *
     * @param message value for logging.
     */
    public void log(boolean message) throws LogException {
        try {
            state = stateManager.getWantedState(state, new NoBufferState());
            state.processMessage(message + "", "primitive: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs {@code char} value.
     *
     * @param message value for logging.
     */
    public void log(char message) throws LogException {
        try {
            state = stateManager.getWantedState(state, new NoBufferState());
            state.processMessage(message + "", "char: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs {@code String} message.
     *
     * @param message value for logging.
     */
    public void log(String message) throws LogException {
        if (message == null) {
            throw new LogException(new IllegalArgumentException());
        }
        try {
            state = stateManager.getWantedState(state, new StringBufferState());
            state.processMessage(message, "string: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs input {@code Object.toString()}.
     *
     * @param message value for logging.
     */
    public void log(Object message) throws LogException {
        if (message == null) {
            throw new LogException(new IllegalArgumentException());
        }
        try {
            state = stateManager.getWantedState(state, new NoBufferState());
            state.processMessage(message.toString(), "reference: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs sum of elements of input integer array.
     *
     * @param oneDimensionalIntArray integer array for logging.
     */
    public void log(int... oneDimensionalIntArray) throws LogException {
        if (oneDimensionalIntArray == null) {
            throw new LogException(new IllegalArgumentException());
        }
        try {
            state = stateManager.getWantedState(state, new NoBufferState());
            int sumOfIntegers = 0;
            for (int arrayElement : oneDimensionalIntArray) {
                sumOfIntegers += arrayElement;
            }
            state.processMessage(sumOfIntegers + "", "primitives array: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs input integer matrix.
     *
     * @param integerMatrix integer matrix.
     */
    public void log(int[][] integerMatrix) throws LogException {
        if (integerMatrix == null) {
            throw new LogException(new IllegalArgumentException());
        }
        try {
            state = stateManager.getWantedState(state, new NoBufferState());
            state.processMessage(dumpTwoDimensionalArray(integerMatrix), "primitives matrix: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs four-dimensional int array.
     *
     * @param fourDimensionalIntArray input four-dimensional array.
     */
    public void log(int[][][][] fourDimensionalIntArray) throws LogException {
        if (fourDimensionalIntArray == null) {
            throw new LogException(new IllegalArgumentException());
        }
        try {
            state = stateManager.getWantedState(state, new NoBufferState());
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
            state.processMessage(stringBuilder.toString(), "primitives multimatrix: ");
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    /**
     * Logs array of Strings.
     *
     * @param arrayOfStrings input {@code String} array.
     */
    public void log(String... arrayOfStrings) throws LogException {
        if (arrayOfStrings == null) {
            throw new LogException(new IllegalArgumentException());
        }
        try {
            state = stateManager.getWantedState(state, new NoBufferState());
            for (String singleString : arrayOfStrings) {
                state.processMessage("" + singleString, "");
            }
        } catch (PrinterException e) {
            throw new LogException(e);
        }
    }

    private String dumpTwoDimensionalArray(int[][] twoDimensionalArray) throws LogException {
        StringBuilder stringBuilder = new StringBuilder("{" + SEP);
        for (int[] oneDimensionalIntArray : twoDimensionalArray) {
            stringBuilder.append("{");
            for (int i = 0; i < oneDimensionalIntArray.length - 1; i++) {
                stringBuilder.append(oneDimensionalIntArray[i]).append(", ");
            }
            try {
                stringBuilder.append(oneDimensionalIntArray[oneDimensionalIntArray.length - 1]).append("}").append(SEP);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new LogException(new IllegalArgumentException());
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
