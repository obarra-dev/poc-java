package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TODO add edge cases from java20 and 21 and 19
// since java 17
// JEP406, JEP420, JEP427, JEP433, JEP441
class JEP406PatternMatchingForSwitchTest {

    @Test
    void patternMatchingMoreCases() {
        assertEquals("1", getStringValue(BigDecimal.ONE));
        assertEquals("12343242", getStringValue("12343242"));
        assertEquals("123", getStringValue(123));
        assertEquals("-999999999-01-01", getStringValue(LocalDate.MIN));
        assertEquals("unknown", getStringValue(Double.MIN_VALUE));

        // if the switch does not check the null it throws a Null pointer, it does not happen using if with instanceof
        Assertions.assertThrows(NullPointerException.class, () -> {
            getStringValue(null);
        });
    }


    // The compiler performs an “analysis of exhaustiveness” for Pattern Matching for switch.
    // That means the switch statement or expression must cover all possible cases – or contain a default branch.
    // Since the Object class in the example above is arbitrarily extensible, a default branch is mandatory.
    private String getStringValue(Object anyValue) {
        return switch (anyValue) {
            case String str -> str;
            case BigDecimal bd -> bd.toEngineeringString();
            case Integer i -> Integer.toString(i);
            case LocalDate ld -> ld.format(DateTimeFormatter.ISO_LOCAL_DATE);
            default -> "unknown";
        };
    }

    @Test
    void patternMatchingWithNull() {
        // using switch we have to check for null, in this way we avoid the null pointer
        assertEquals("null", asStringValueWithNull(null));
        assertEquals("1", asStringValueWithNull(BigDecimal.ONE));
        assertEquals("12343242", asStringValueWithNull("12343242"));
        assertEquals("123", asStringValueWithNull(123));
        assertEquals("-999999999-01-01", asStringValueWithNull(LocalDate.MIN));
        assertEquals("unknown", asStringValueWithNull(Double.MIN_VALUE));
    }

    private String asStringValueWithNull(Object anyValue) {
        return switch (anyValue) {
            case String str -> str;
            case BigDecimal bd -> bd.toEngineeringString();
            case Integer i -> Integer.toString(i);
            case LocalDate ld -> ld.format(DateTimeFormatter.ISO_LOCAL_DATE);
            case null -> "null";
            default -> "unknown";
        };
    }

    @Test
    void patternMatchingCombiningNullAndDefault() {
        assertEquals("unknown", asStringCombiningNullAndDefault(null));
        assertEquals("unknown", asStringCombiningNullAndDefault(Double.MIN_VALUE));
    }

    private String asStringCombiningNullAndDefault(Object anyValue) {
        return switch (anyValue) {
            case String str -> str;
            case null, default -> "unknown";
        };
    }

    // Guard Clauses, using when
    @Test
    void patternMatchingCombiningWithConditional() {
        Assertions.assertEquals(3.8, getDoubleValueUsingParenthesizedPatterns("3.8"));
        Assertions.assertEquals(0.0, getDoubleValueUsingParenthesizedPatterns("##3.8"));
    }

    private static double getDoubleValueUsingParenthesizedPatterns(Object object) {
        return switch (object) {
            case String s when !s.isEmpty() && !(s.contains("#") || s.contains("@")) -> Double.parseDouble(s);
            default -> 0d;
        };
    }

    @Test
    void patterMatchingWithSealedClassAndRecord() {
        var result = getRegistrationNumberByOverrideMethod(new CarRecord("123"));
        Assertions.assertEquals("123 by override method in CarRecord", result);

        result = getRegistrationNumberByOverrideMethod(new TruckRecord("123"));
        Assertions.assertEquals("123 by override method in TruckRecord", result);
    }

    // default is no needed because we cover all possible input
    // the compiles infers if we cover all the possible input
    // a default branch is not necessary if the switch covers all possibilities of a sealed class hierarchy
    private static String getRegistrationNumberByOverrideMethod(VehicleSealedInterface obj) {
        return switch (obj) {
            case TruckRecord truckRecord -> truckRecord.getRegistrationNumberByOverrideMethod();
            case CarRecord carRecord -> carRecord.getRegistrationNumberByOverrideMethod();
        };
    }

    @Test
    void caseDominance() {
        Assertions.assertEquals("String: omar", validOrder("omar"));
        Assertions.assertEquals("CS: omar", validOrder(new StringBuilder("omar")));
    }

    //  we must match against String first, and CharSequence later, or else the compiler won’t be happy:
    private String validOrder(Object obj) {
        return switch (obj) {
            case String s -> "String: " + s;
            case CharSequence cs -> "CS: " + cs;
            default -> throw new IllegalStateException("Unexpected value: " + obj);
        };
    }

    // ERROR:
    // this case label is dominated by a preceding case label
    //         case String s        -> System.out.println("String: " + s);
    //              ^------^
    /*
    private void invalidOrder(Object obj) {
        switch (obj) {
            case CharSequence cs -> System.out.println("CS length " + cs.length());
            case String s        -> System.out.println("String: " + s);
            default              -> { break; }
        }
    }
     */

    @Test
    void caseDominanceUsingGuard() {
        Assertions.assertEquals("empty0", getStringWithCorrectDominance(""));
        Assertions.assertEquals("no-empty4", getStringWithCorrectDominance("omar"));
    }

    private static String getStringWithCorrectDominance(Object obj) {
        // if the guard is not first, the compiler says  "Label is dominated by a preceding case label 'String s'"
        return switch (obj) {
            case String s when !s.isEmpty() -> "no-empty" + s.length();
            case String s -> "empty" + s.length();
            default -> throw new IllegalStateException("Unexpected value: " + obj);
        };
    }

    // NOTE
    /**
     * In essence, the case labels need to be defined in most to least precise matching order,
     * leading to more predictable and reasonable labels.
     *
     * Here is the suggested order:
     *
     * Constants
     * Guarded Patterns
     * Unguarded Patterns
     */


    @Test
    void qualifieddsdsds() {
        Assertions.assertEquals("CLUBS", getStringSSS(SuitEnum.CLUBS));
        Assertions.assertEquals("SPADES", getStringSSS(SuitEnum.SPADES));
        Assertions.assertEquals("poc.jdk21.TarotClass", getStringSSS(new TarotClass()));
    }

    private String getStringSSS(CardClassification c) {
        return switch (c) {
            case SuitEnum s when s == SuitEnum.CLUBS -> s.toString();
            case SuitEnum s when s == SuitEnum.DIAMONDS -> s.toString();
            case SuitEnum s when s == SuitEnum.HEARTS -> s.toString();
            case SuitEnum s -> s.toString();
            case TarotClass t -> t.getClass().getName();
        };
    }

    @Test
    void switchExpressionOverEnumConstants() {
        Assertions.assertEquals("Flying north", getStringFromEnum(CompassDirectionEnum.NORTH));
        Assertions.assertEquals("Flying south", getStringFromEnum(CompassDirectionEnum.SOUTH));
        Assertions.assertEquals("Flying east", getStringFromEnum(CompassDirectionEnum.EAST));
        Assertions.assertEquals("Flying west", getStringFromEnum(CompassDirectionEnum.WEST));
        Assertions.assertEquals("Gaining altitude", getStringFromEnum(VerticalDirectionEnum.UP));
        Assertions.assertEquals("Losing altitude", getStringFromEnum(VerticalDirectionEnum.DOWN));
    }

    private String getStringFromEnum(DirectionSealedInterface direction) {
        return switch (direction) {
            case CompassDirectionEnum.NORTH -> "Flying north";
            case CompassDirectionEnum.SOUTH -> "Flying south";
            case CompassDirectionEnum.EAST  -> "Flying east";
            case CompassDirectionEnum.WEST  -> "Flying west";
            case VerticalDirectionEnum.UP   -> "Gaining altitude";
            case VerticalDirectionEnum.DOWN -> "Losing altitude";
        };
    }


}
