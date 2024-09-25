package ru.danilov.quality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danilov.quality.model.Person;
import ru.danilov.quality.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> findPersonList() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person findById(Integer id) {
        Optional<Person> byId = personRepository.findById(id);
        return byId.orElse(null);
    }


    public void createPerson(Person person) {
        personRepository.save(person);
    }

    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
