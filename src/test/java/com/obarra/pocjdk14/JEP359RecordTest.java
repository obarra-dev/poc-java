package com.obarra.pocjdk14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JEP359RecordTest {

    @Test
    void test() {
        var person = new Person("Omar", 29);
        Assertions.assertEquals("Omar", person.firstName());
        Assertions.assertEquals(29, person.age());
        Assertions.assertEquals("OMAR", person.getNickName());
        Assertions.assertEquals("Person[firstName=Omar, age=29]", person.toString());
        Assertions.assertTrue(person.equals(new Person("Omar", 29)));
        Assertions.assertFalse(person.equals(new Person("Omarx", 29)));
        Assertions.assertNotNull(person.hashCode());
    }

    /**
     *  Compact Constructor is a point to validation and/or normalization code need to be given in the constructor body.
     */
    @Test
    void compactConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                 new Person("Omar", -1);
        });
    }
}
