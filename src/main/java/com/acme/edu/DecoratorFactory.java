package com.acme.edu;

public interface DecoratorFactory {
    Decorator getDummyDecorator();
    Decorator getIntDecorator();
    Decorator getStringDecorator();
    Decorator getCharDecorator();
    Decorator getBoolDecorator();
    Decorator getObjectDecorator();
    Decorator getIntArrayDecorator();
    Decorator getIntTwoDimensionalArrayDecorator();
    Decorator getIntFourDimensionalArrayDecorator();
}
