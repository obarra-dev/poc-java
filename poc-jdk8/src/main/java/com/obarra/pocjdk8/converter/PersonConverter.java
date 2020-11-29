package com.obarra.pocjdk8.converter;


import com.obarra.pocjdk8.Person;
import com.obarra.pocjdk8.PersonDTO;

public class PersonConverter implements GenericConverter<Person, PersonDTO> {

    @Override
    public Person apply(final PersonDTO personDTO) {
        Person person = new Person();
        person.setSecondLastName(personDTO.getSecondLastName());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        return person;
    }

}
