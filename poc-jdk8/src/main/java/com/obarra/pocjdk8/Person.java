package com.obarra.pocjdk8;

import java.util.Objects;
import java.util.Optional;

public class Person {
    public String firstName;
    public String lastName;
    public String secondLastName;
    public Optional<String> firstNameCanBeNull;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", firstNameCanBeNull=" + firstNameCanBeNull +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(secondLastName, person.secondLastName) &&
                Objects.equals(firstNameCanBeNull, person.firstNameCanBeNull);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, secondLastName, firstNameCanBeNull);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Optional<String> getFirstNameCanBeNull() {
        return firstNameCanBeNull;
    }

    public void setFirstNameCanBeNull(Optional<String> firstNameCanBeNull) {
        this.firstNameCanBeNull = firstNameCanBeNull;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

}
