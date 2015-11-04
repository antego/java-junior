package com.acme.edu.decorator;


public class DefaultDecoratorFactory implements DecoratorFactory {
    /**
     * Returns dummy decorator
     *
     * @return Decorator which does nothing with the message.
     */
    @Override
    public Decorator getDummyDecorator() {
        return s -> s;
    }

    /**
     * Returns decorator for integer messages.
     *
     * @return decorator that adds "primitive" prefix.
     */
    @Override
    public Decorator getIntDecorator() {
        return s -> "primitive: " + s;
    }

    /**
     * Returns decorator for string messages.
     *
     * @return decorator that adds "string" prefix.
     */
    @Override
    public Decorator getStringDecorator() {
        return s -> "string: " + s;
    }

    /**
     * Returns decorator for char messages.
     *
     * @return decorator that adds "char" prefix.
     */
    @Override
    public Decorator getCharDecorator() {
        return s -> "char: " + s;
    }

    /**
     * Returns decorator for boolean messages.
     *
     * @return decorator that adds "primitive" prefix.
     */
    @Override
    public Decorator getBoolDecorator() {
        return s -> "primitive: " + s;
    }

    /**
     * Returns decorator for reference messages.
     *
     * @return decorator that adds "reference" prefix.
     */
    @Override
    public Decorator getObjectDecorator() {
        return s -> "reference: " + s;
    }

    /**
     * Returns decorator for integer array messages.
     *
     * @return decorator that adds "primitives array" prefix.
     */
    @Override
    public Decorator getIntArrayDecorator() {
        return s -> "primitives array: " + s;
    }

    /**
     * Returns decorator for integer matrix messages.
     *
     * @return decorator that adds "primitives matrix" prefix.
     */
    @Override
    public Decorator getIntTwoDimensionalArrayDecorator() {
        return s -> "primitives matrix: " + s;
    }

    /**
     * Returns decorator for four-dimensional integer array messages.
     *
     * @return decorator that adds "primitives multimatrix" prefix.
     */
    @Override
    public Decorator getIntFourDimensionalArrayDecorator() {
        return s -> "primitives multimatrix: " + s;
    }
}
