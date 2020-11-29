package com.obarra.pocjdk8;

import com.obarra.pocjdk8.funtionalinterface.ArrayInitializer;
import com.obarra.pocjdk8.funtionalinterface.Constant;
import com.obarra.pocjdk8.funtionalinterface.Operator;

public class LambdaFactory {

    public static Constant getConstantTen() {
        return () -> 10;
    }

    public static Operator getOperatorAdder() {
        return (a, b) -> a + b;
    }

    public static ArrayInitializer getArrayInitializer() {
        return (array, value) -> {
            for (int i = 0; i < array.length; i++) {
                array[i] = value;
            }
        };
    }
}
