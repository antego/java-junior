package com.acme.edu;


public class Logger {
    public static final String PRIMITIVE_PREFIX = "primitive: ";
    public static final String CHAR_PREFIX = "char: ";
    /**
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}
     * @param message value for logging
     */
    public static void log(int message) {
        printInConsole(PRIMITIVE_PREFIX + message);
    }

    /**
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}
     * @param message value for logging
     */
    public static void log(byte message) {
        printInConsole(PRIMITIVE_PREFIX + message);
    }

    /**
     * Logs input value with prefix {@value #PRIMITIVE_PREFIX}
     * @param message value for logging
     */
    public static void log(boolean message) {
        printInConsole(PRIMITIVE_PREFIX + message);
    }

    /**
     * Logs input value with prefix {@value #CHAR_PREFIX}
     * @param message value for logging
     */
    public static void log(char message) {
        printInConsole(CHAR_PREFIX + message);
    }

    private static void printInConsole(String message) {
        System.out.println(message);
    }
}
