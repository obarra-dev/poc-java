package com.obarra.pocjdk14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JEP361SwitchExpressionTest {

    /**
     * Switch Expression has attained permanent status, JEP361 is a Standard
     */
    @Test
    void switchExpressions() {
        String gender = "";
        String result = switch (gender) {
            case "F", "M" -> "old gender";
            case "I" -> "new gender";
            default -> {
                if(gender.isEmpty())
                    yield "Please insert a gender";
                else
                    yield "Invalid value";
            }
        };
        assertEquals("Please insert a gender", result);
    }

    @Test
    void switchExpressionsTraditional() {
        String gender = "";
        String result = "";
        switch (gender) {
            case "F" :
            case "M":
                result = "old gender";
                break;
            case "I":
                result = "new gender";
                break;
            default:
                if(gender.isEmpty())
                    result = "Please insert a gender";
                else
                    result =  "Invalid value";
        }

        assertEquals("Please insert a gender", result);
    }
}
