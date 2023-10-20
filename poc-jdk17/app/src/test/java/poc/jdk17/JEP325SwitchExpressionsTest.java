package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JEP325SwitchExpressionsTest {
    @Test
    void transform() {
        /**
            var day = "M";
            var result = switch (day) {
                case "M", "W", "F" -> "MWF";
                case "T", "TH", "S" -> "TTS";
                default -> {
                    if(day.isEmpty())
                        return "Please insert a valid day.";
                else
                    return "Looks like a Sunday.";
                }

            };

            System.out.println(result);

        Assertions.assertEquals("gnaloG", result);
         */
    }
}
