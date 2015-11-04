package com.acme.edu;


public class Decorators implements DecoratorFactory {
    private static Decorator dummyDecorator = s -> s;
    private static Decorator intDecorator = s -> "primitive: " + s;
    private static Decorator stringDecorator = s -> "string: " + s;
    private static Decorator charDecorator = s -> "char: " + s;
    private static Decorator boolDecorator = s -> "primitive: " + s;
    private static Decorator objectDecorator = s -> "reference: " + s;
    private static Decorator intArrayDecorator = s -> "primitives array: " + s;
    private static Decorator intTwoDimensionalArrayDecorator = s -> "primitives matrix: " + s;
    private static Decorator intFourDimensionalArrayDecorator = s -> "primitives multimatrix: " + s;

    /**
     * Returns dummy decorator
     *
     * @return Decorator which does nothing with the message.
     */
    @Override
    public Decorator getDummyDecorator() {
        return dummyDecorator;
    }

    /**
     * Returns decorator for integer messages.
     *
     * @return decorator that adds "primitive" prefix.
     */
    @Override
    public Decorator getIntDecorator() {
        return intDecorator;
    }

    /**
     * Returns decorator for string messages.
     *
     * @return decorator that adds "string" prefix.
     */
    @Override
    public Decorator getStringDecorator() {
        return stringDecorator;
    }

    /**
     * Returns decorator for char messages.
     *
     * @return decorator that adds "char" prefix.
     */
    @Override
    public Decorator getCharDecorator() {
        return charDecorator;
    }

    /**
     * Returns decorator for boolean messages.
     *
     * @return decorator that adds "primitive" prefix.
     */
    @Override
    public Decorator getBoolDecorator() {
        return boolDecorator;
    }

    /**
     * Returns decorator for reference messages.
     *
     * @return decorator that adds "reference" prefix.
     */
    @Override
    public Decorator getObjectDecorator() {
        return objectDecorator;
    }

    /**
     * Returns decorator for integer array messages.
     *
     * @return decorator that adds "primitives array" prefix.
     */
    @Override
    public Decorator getIntArrayDecorator() {
        return intArrayDecorator;
    }

    /**
     * Returns decorator for integer matrix messages.
     *
     * @return decorator that adds "primitives matrix" prefix.
     */
    @Override
    public Decorator getIntTwoDimensionalArrayDecorator() {
        return intTwoDimensionalArrayDecorator;
    }

    /**
     * Returns decorator for four-dimensional integer array messages.
     *
     * @return decorator that adds "primitives multimatrix" prefix.
     */
    @Override
    public Decorator getIntFourDimensionalArrayDecorator() {
        return intFourDimensionalArrayDecorator;
    }
}
