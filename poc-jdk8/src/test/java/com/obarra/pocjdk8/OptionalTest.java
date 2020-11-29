package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionalTest {

    /**
     * Es mas explicito pero sigue siendo poco idiomatico, no es la forma mas habitual para trabajar con optional
     */
    @Test
    public void isPresentAndGet() {
        Person person = new Person();
        person.setFirstName("Omar");
        Optional<Person> optional = Optional.of(person);

        String greet;
        if (optional.isPresent()) {
            greet = "Hi " + optional.get().getFirstName();
        } else {
            greet = "I'm alone";
        }

        assertEquals("Hi Omar", greet);
    }

    @Test
    public void mapAndOrElse() {
        Person person = new Person();
        person.setFirstName("Omar");
        Optional<Person> optional = Optional.of(person);

        String greet = optional.map(it -> "Hi " + it.getFirstName())
                .orElse("I'm Alone");
        assertEquals("Hi Omar", greet);
    }

    /**
     * Map solo se ejecuta si tiene elementos caso contrario devuelve otro Optional vacio
     */
    @Test
    public void mapAndOrElseWithReferenceMethods() {
        Person person = new Person();
        person.setFirstName("Omar");
        Optional<Person> optional = Optional.of(person);

        String greet = optional.map(Person::getFirstName)
                .map("Hi "::concat)
                .orElse("I'm Alone");
        assertEquals("Hi Omar", greet);
    }

    @Test
    public void mapAndOrElseWithReferenceMethodsWhenFirstNameIsNull() {
        Person person = new Person();
        Optional<Person> optional = Optional.of(person);

        String greet = optional.map(Person::getFirstName)
                .map("Hi "::concat)
                .orElse("I'm Alone");
        assertEquals("I'm Alone", greet);
    }

    /**
     * Se necesita aplanar Optional<Optional<String>> a Optional<String> para lograrlo se deberia usar flatMap
     */
    @Test
    public void mapAndOrElseWithReferenceMethodsWhenUsesOptionalFirstName() {
        Person person = new Person();
        person.setFirstNameCanBeNull(Optional.ofNullable("Omar"));
        Optional<Person> optional = Optional.of(person);
        Optional<Optional<String>> greet = optional.map(Person::getFirstNameCanBeNull);

        Optional<Optional<String>> expected = Optional.of(Optional.of("Omar"));
        assertEquals(expected, greet);
    }


    /**
     * Map solo se ejecuta si tiene elementos caso contrario devuelve otro Optional vacio
     */
    @Test
    public void flatMapAndOrElseWithReferenceMethodsWhenUsesOptionalFirstName() {
        Person person = new Person();
        person.setFirstNameCanBeNull(Optional.ofNullable("Omar"));
        Optional<Person> optional = Optional.of(person);

        String greet = optional.flatMap(Person::getFirstNameCanBeNull)
                .map("Hi "::concat)
                .orElse("I'm Alone");
        assertEquals("Hi Omar", greet);
    }

    /**
     * Estudiar este caso. no existe alguna manera de trbajar con los optinals nullos?
     */
    @Test
    public void flatMapAndOrElseWithReferenceMethodsWhenUsesOptionalFirstNameAndItIsNull() {
        Person person = new Person();
        Optional<Person> optional = Optional.of(person);

        assertThrows(NullPointerException.class,
                () -> optional.flatMap(Person::getFirstNameCanBeNull).orElse("I'm Alone"));
    }
}
