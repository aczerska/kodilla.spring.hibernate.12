package com.kodilla.homework12;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CustomFieldSetMapper implements FieldSetMapper<Person> {

    @Override
    public Person mapFieldSet(FieldSet fieldSet) throws BindException {
        Person person = new Person();
        person.setName(fieldSet.readString("name"));
        person.setSurname(fieldSet.readString("surname"));

        String dateOfBirth = fieldSet.readString("dateOfBirth");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDay = LocalDate.parse(dateOfBirth,formatter);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDay, currentDate).getYears();

        person.setAge(age);
        return person;
    }
}
