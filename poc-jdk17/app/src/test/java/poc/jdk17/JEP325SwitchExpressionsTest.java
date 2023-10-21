package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// JEP325, JEP54
class JEP325SwitchExpressionsTest {

    @Test
    void switchTest() {
        var day = "asado";
        var result = switch (day) {
            case "M", "W", "F" -> "MWF";
            case "T", "TH", "S" -> "TTS";
            default -> "sunday";
        };

        Assertions.assertEquals("sunday", result);
    }

    @Test
    void switchUsingYield() {
        var day = "asado";
        var result = switch (day) {
            case "M", "W", "F" -> "MWF";
            case "T", "TH", "S" -> "TTS";
            default -> {
                if (day.isEmpty())
                    yield "Please insert a valid day.";
                else
                    yield "Looks like a Sunday.";
            }
        };

        Assertions.assertEquals("Looks like a Sunday.", result);
    }

    @Test
    public void switchWithoutArrow() {
        var me = 4;
        var operation = "squareMe";
        var result = switch (operation) {
            case "doubleMe": {
                yield me * 2;
            }
            case "squareMe": {
                yield me * me;
            }
            default:
                yield me;
        };

        Assertions.assertEquals(16, result);
    }

    // old switch can be used
    @Test
    void switchOld() {
        var day = "asado";
        var result = "";

        switch (day) {
            case "M":
            case "W":
            case "F":
                result = "MWF";
                break;
            case "T":
            case "TH":
            case "S":
                result = "TTS";
                break;
            default:
                result = "sunday";
        }

        Assertions.assertEquals("sunday", result);
    }
}
