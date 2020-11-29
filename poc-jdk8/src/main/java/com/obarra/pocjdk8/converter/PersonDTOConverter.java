package com.obarra.pocjdk8.converter;

import com.obarra.pocjdk8.Person;
import com.obarra.pocjdk8.PersonDTO;

public class PersonDTOConverter implements GenericConverter<PersonDTO, Person> {

    @Override
    public PersonDTO apply(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setSecondLastName(person.getSecondLastName());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        return personDTO;
    }
}
