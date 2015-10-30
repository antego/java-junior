package com.acme.edu;

/**
 * This class contains static methods to log values of different types.
 */
public class Logger {
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String OBJECT_PREFIX = "reference: ";
    private static final String STRING_PREFIX = "string: ";

    private static int intSumm = 0;
    private static boolean intSetted = false;

    public static void startLogging() {
        intSetted = false;
        intSumm = 0;
    }

    /**
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}.
     * @param message value for logging.
     */
    public static void log(int message) {
        printInConsole(PRIMITIVE_PREFIX, message + "", "");
        if(((long) message + intSumm) > Integer.MAX_VALUE) {
            intSumm = Integer.MAX_VALUE;
        } else {
            intSumm += message;
        }
        intSetted = true;
    }

//    /**
//     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}.
//     * @param message value for logging.
//     */
//    public static void log(byte message) {
//        printInConsole(Logger.PRIMITIVE_PREFIX + message);
//    }

    /**
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}.
     * @param message value for logging.
     */
    public static void log(boolean message) {
        printSumm(intSumm);
        printInConsole(PRIMITIVE_PREFIX, message + "", "");
    }

    /**
     * Logs input value with prefix {@value #CHAR_PREFIX}.
     * @param message value for logging.
     */
    public static void log(char message) {
        printSumm(intSumm);
        printInConsole(CHAR_PREFIX, message + "", "");
    }

    /**
     * Logs input value with prefix {@value #STRING_PREFIX}.
     * @param message value for logging.
     */
    public static void log(String message) {
        printSumm(intSumm);
        printInConsole(STRING_PREFIX, message, "");
    }

    /**
     * Logs input {@code Object.toString()} with prefix {@value #OBJECT_PREFIX}.
     * @param message value for logging.
     */
    public static void log(Object message) {
        printSumm(intSumm);
        printInConsole(OBJECT_PREFIX, message.toString(), "");
    }

    private static void printSumm(int s){
        if(intSetted) {
            printInConsole("primitive: ", intSumm + "","");
        }
        intSumm = 0;
        intSetted = false;
    }

    private static void printInConsole(String prefix, String message, String postfix) {
        System.out.println(prefix + message + postfix);
    }
}
