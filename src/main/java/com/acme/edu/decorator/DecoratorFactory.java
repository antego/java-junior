package com.acme.edu.decorator;

/**
 * Factory of decorators for corresponding log() methods that must be passed to the Logger.
 */
public interface DecoratorFactory {
    /**
     * @return Decorator which does nothing.
     */
    Decorator getDummyDecorator();

    /**
     *
     * @return Decorator which decorates int messages.
     */
    Decorator getIntDecorator();

    /**
     *
     * @return Decorator which decorates string messages.
     */
    Decorator getStringDecorator();

    /**
     *
     * @return Decorator which decorates char messages.
     */
    Decorator getCharDecorator();

    /**
     *
     * @return Decorator which decorates boolean messages.
     */
    Decorator getBoolDecorator();

    /**
     *
     * @return Decorator which decorates reference messages.
     */
    Decorator getObjectDecorator();

    /**
     *
     * @return Decorator which decorates integer array messages.
     */
    Decorator getIntArrayDecorator();

    /**
     *
     * @return Decorator which decorates two dimensional integer arrays.
     */
    Decorator getIntTwoDimensionalArrayDecorator();

    /**
     *
     * @return Decorator which decorates four dimensional integer array.
     */
    Decorator getIntFourDimensionalArrayDecorator();
}
