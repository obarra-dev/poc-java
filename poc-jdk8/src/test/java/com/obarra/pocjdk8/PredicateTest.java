package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicateTest {

    @Test
    public void test() {
        Predicate<Person> doNotHaveSecondLastName = (person) -> person.getSecondLastName() == null;
        Person person = new Person();
        assertTrue(doNotHaveSecondLastName.test(person));
    }
}
