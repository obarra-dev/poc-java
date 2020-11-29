package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void accept() {
        Consumer<Person> mix = (person) -> {
            String auxiliary = person.getLastName();
            person.setLastName(person.getSecondLastName());
            person.setSecondLastName(person.getFirstName());
            person.setFirstName(auxiliary);

        };

        Person person = new Person();
        person.setLastName("Barra");
        person.setFirstName("Omar");
        person.setSecondLastName("Barreto");

        mix.accept(person);

        Assertions.assertEquals("Omar", person.getSecondLastName());
        Assertions.assertEquals("Barra", person.getFirstName());
        Assertions.assertEquals("Barreto", person.getLastName());
    }
}