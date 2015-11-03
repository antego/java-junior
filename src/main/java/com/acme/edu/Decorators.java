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

    @Override
    public Decorator getDummyDecorator() {
        return dummyDecorator;
    }

    @Override
    public Decorator getIntDecorator() {
        return intDecorator;
    }

    @Override
    public Decorator getStringDecorator() {
        return stringDecorator;
    }

    @Override
    public Decorator getCharDecorator() {
        return charDecorator;
    }

    @Override
    public Decorator getBoolDecorator() {
        return boolDecorator;
    }

    @Override
    public Decorator getObjectDecorator() {
        return objectDecorator;
    }

    @Override
    public Decorator getIntArrayDecorator() {
        return intArrayDecorator;
    }

    @Override
    public Decorator getIntTwoDimensionalArrayDecorator() {
        return intTwoDimensionalArrayDecorator;
    }

    @Override
    public Decorator getIntFourDimensionalArrayDecorator() {
        return intFourDimensionalArrayDecorator;
    }
}
