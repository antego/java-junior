package com.acme.edu;


public class SerialPrinter implements Printer {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
