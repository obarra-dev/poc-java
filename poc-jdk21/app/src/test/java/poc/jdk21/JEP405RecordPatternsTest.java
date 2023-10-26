package poc.jdk21;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TODO java 20 edge cases
// since java 19
// JEP405, JEP432
class JEP405RecordPatternsTest {

    // deconstruct
    @Test
    void recordPatternWithInstanceofUsingTheAttributes() {
        Object object = new PersonRecord("omar", 12);

        if (object instanceof PersonRecord(String n, int a)) {
            assertEquals("omar", n);
            assertEquals(12, a);
        } else {
            fail();
        }
    }

    @Test
    void recordPatternWithInstanceofUsingInterfaceAndRecord() {
        VehicleSealedInterface object = new CarRecord("123");

        if (object instanceof CarRecord(String reg)) {
            assertEquals("123", reg);
        } else {
            fail();
        }
    }

    @Test
    void recordPatternWithSwitchUsingTheAttributes() {
        Object object = new PersonRecord("omar", 12);
        switch (object) {
            case PersonRecord(String n, int a) -> {
                assertEquals("omar", n);
                assertEquals(12, a);
            }
            default -> fail();
        }
    }

    @Test
    void recordPatternWithSwitchUsingInterfaceAndRecord() {
        VehicleSealedInterface object = new CarRecord("123");

        switch (object) {
            case CarRecord(String reg) -> {
                assertEquals("123", reg);
            }
            default -> fail();
        }
    }

    public record Position(int x, int y) {
    }

    public record Path(Position from, Position to) {
    }

    @Test
    void recordPatternWithInstanceofForNestedRecord() {
        Object object = new Path(new Position(1, 2), new Position(3, 4));

        if (object instanceof Path(Position(int x1, int y1), Position(int x2, int y2))) {
            assertEquals("object is a path, x1 = 1, y1 = 2, x2 = 3, y2 = 4",
                    "object is a path, x1 = " + x1 + ", y1 = " + y1
                            + ", x2 = " + x2 + ", y2 = " + y2);
        } else fail();
    }

    @Test
    void recordPatternWithSwitchForNestedRecord() {
        Object object = new Path(new Position(1, 2), new Position(3, 4));

        switch (object) {
            case Path(Position(int x1, int y1), Position(int x2, int y2)) ->
                    assertEquals("object is a path, x1 = 1, y1 = 2, x2 = 3, y2 = 4",
                            "object is a path, x1 = " + x1 + ", y1 = " + y1
                                    + ", x2 = " + x2 + ", y2 = " + y2);
            default -> fail();
        }
    }
}
