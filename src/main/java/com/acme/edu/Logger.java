package com.acme.edu;

public class Logger {
    public static void log(int message) {
        printInConsole("primitive: " + message);
    }

    public static void log(byte message) {
        printInConsole("primitive: " + message);
    }

    public static void log(boolean message) {
        printInConsole("primitive: " + message);
    }

    public static void log(char message) {
        printInConsole("char: " + message);
    }

    private static void printInConsole(String message) {
        System.out.println(message);
    }
}
