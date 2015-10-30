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
    private static boolean intSummSetted = false;

    private static byte byteSumm = 0;
    private static boolean byteSummSetted = false;

    private static String currentString = "";
    private static int sameStringsCount = 0;

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
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}.
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
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}.
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
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}.
     * @param message value for logging.
     */
    public static void log(boolean message) {
        printSumm();
        printCurrString();
        printInConsole("", message + "", "");
    }

    /**
     * Logs input value with prefix {@value #CHAR_PREFIX}.
     * @param message value for logging.
     */
    public static void log(char message) {
        printSumm();
        printCurrString();
        printInConsole("", message + "", "");
    }

    /**
     * Logs input value with prefix {@value #STRING_PREFIX}.
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
     * Logs input {@code Object.toString()} with prefix {@value #OBJECT_PREFIX}.
     * @param message value for logging.
     */
    public static void log(Object message) {
        printSumm();
        printInConsole("", message.toString(), "");
    }

    private static void printSumm() {
        printByteSumm();
        printIntSumm();
    }


    private static void printByteSumm() {
        if(byteSummSetted) {
            printInConsole("", byteSumm + "","");
        }
        byteSumm = 0;
        byteSummSetted = false;
    }

    private static void printIntSumm() {
        if(intSummSetted) {
            printInConsole("", intSumm + "","");
        }
        intSumm = 0;
        intSummSetted = false;
    }

    private static void printCurrString() {
        if(sameStringsCount == 1) {
            printInConsole("", currentString,"");
        } else if(sameStringsCount > 1) {
            printInConsole("", currentString + " (x" + sameStringsCount + ")", "");
        }
        currentString = "";
        sameStringsCount = 0;
    }

    private static void printInConsole(String prefix, String message, String postfix) {
        System.out.println(prefix + message + postfix);
    }
}
