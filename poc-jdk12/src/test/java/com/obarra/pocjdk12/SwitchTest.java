package com.obarra.pocjdk12;

import org.junit.jupiter.api.Test;

public class SwitchTest {

    @Test
    void test() {
        var day = "M";
        var result = switch (day) {
            case "M", "W", "F" -> "MWF";
            case "T", "TH", "S" -> "TTS";
            default -> {
                if(day.isEmpty())
                    break "Please insert a valid day.";
                else
                break "Looks like a Sunday.";
            }

        };

        System.out.println(result);
    }
}
