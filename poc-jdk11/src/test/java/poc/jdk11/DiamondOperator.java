package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// since java 9
class DiamondOperator {

    @Test
    void diamondOperatorInAnonymousInnerClasses() {
        Addition<Integer> integerAddition = new Addition<>() {
            @Override
            Integer add(Integer t1, Integer t2) {
                return t1 + t2;
            }
        };
        Assertions.assertEquals(8, integerAddition.add(3, 5));
    }
}
