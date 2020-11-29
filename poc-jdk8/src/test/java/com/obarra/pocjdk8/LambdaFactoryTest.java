package com.obarra.pocjdk8;

import com.obarra.pocjdk8.funtionalinterface.ArrayInitializer;
import com.obarra.pocjdk8.funtionalinterface.Constant;
import com.obarra.pocjdk8.funtionalinterface.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaFactoryTest {

    @Test
    public void getConstantTen() {
        Constant constant = LambdaFactory.getConstantTen();
        Assertions.assertEquals(10, constant.value());
    }

    @Test
    public void getOperatorAdder() {
        Operator operator = LambdaFactory.getOperatorAdder();
        assertEquals(10, operator.operate(7, 3));
    }

    @Test
    public void getArrayInitializer() {
        ArrayInitializer arrayInitializer = LambdaFactory.getArrayInitializer();
        String[] array = new String[4];
        arrayInitializer.initialize(array, "M");
        assertArrayEquals(new String[]{"M", "M", "M", "M"}, array);
    }
}