package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class FunctionTest {

    @Test
    public void apply() {
        Function<Person, Person> mix = (person) -> {
            Person newPerson = new Person();
            newPerson.setLastName(person.getSecondLastName());
            newPerson.setFirstName(person.getLastName());
            newPerson.setSecondLastName(person.getFirstName());
            return newPerson;
        };

        Person person = new Person();
        person.setLastName("Barra");
        person.setFirstName("Omar");
        person.setSecondLastName("Barreto");

        Person otherPerson = mix.apply(person);

        Assertions.assertEquals(person.getFirstName(), otherPerson.getSecondLastName());
        Assertions.assertEquals(person.getLastName(), otherPerson.getFirstName());
        Assertions.assertEquals(person.getSecondLastName(), otherPerson.getLastName());
    }
}