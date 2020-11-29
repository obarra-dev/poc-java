package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class BiPredicateTest {

    @Test
    public void test() {
        BiPredicate<Person, Person> areRelatives = (person, otherPerson) ->
                person.getLastName().equals(otherPerson.getLastName());
        Person person = new Person();
        person.setLastName("Barreto");

        Person otherPerson = new Person();
        otherPerson.setLastName("Barra");
        assertFalse(areRelatives.test(person, otherPerson));
    }
}
