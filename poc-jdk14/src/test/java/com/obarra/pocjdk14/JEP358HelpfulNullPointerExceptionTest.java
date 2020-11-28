package com.obarra.pocjdk14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class JEP358HelpfulNullPointerExceptionTest {

    @Test
    void helpfulNullPointerExceptionTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            bigDecimalAdd();
        });
    }

    private void bigDecimalAdd() throws NullPointerException {
        try {
            BigDecimal myVariable = null;
            myVariable.add(BigDecimal.TEN);
        }catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    @Test
    void helpfulNullPointerExceptionMapPutGet() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            mapPutGet();
        });
    }

    private void mapPutGet() {
        try {
            BigDecimal myVariable = null;
            Map<String, BigDecimal> map = new HashMap<>();
            map.put("first", myVariable);
            map.get("first").add(BigDecimal.TEN);
        }catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }

    }
}
