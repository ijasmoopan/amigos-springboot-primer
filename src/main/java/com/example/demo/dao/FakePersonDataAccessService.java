package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPerson() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id)
                .map(p -> {
                    int indexToUpdate = DB.indexOf(p);
                    if (indexToUpdate >= 0) {
                        DB.set(indexToUpdate, new Person(p.getId(), person.getName()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> optionalPerson = selectPersonById(id);
        if (optionalPerson.isEmpty()) {
            return 0;
        }
        DB.remove(optionalPerson.get());
        return 1;
    }

}
