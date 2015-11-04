package com.acme.edu;

/**
 * Decorator that decorates input message
 */
@FunctionalInterface
public interface Decorator {
    /**
     * Decorates message by specified rules.
     *
     * @param message Message to decorate
     * @return Decorated message
     */
    String decorateMessage(String message);
}
