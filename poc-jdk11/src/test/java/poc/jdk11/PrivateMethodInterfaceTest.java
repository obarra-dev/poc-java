package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// since java 9
class PrivateMethodInterfaceTest {

    @Test
    void privateMethod() {
       var card = new CardImpl();
        Assertions.assertEquals("crated card ID: 4", card.createCard());
        Assertions.assertEquals("crated card ID: 2 using private interface method", card.defaultCreateCard());
        Assertions.assertEquals("crated card ID: 3 using static private interface method", Card.staticCreateCard());
    }
}
