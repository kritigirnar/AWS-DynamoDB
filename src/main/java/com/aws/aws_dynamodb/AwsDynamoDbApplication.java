package com.aws.aws_dynamodb;

import com.aws.aws_dynamodb.entity.Person;
import com.aws.aws_dynamodb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class AwsDynamoDbApplication {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/savePerson")
    public Person savePerzon(@RequestBody  Person person)
    {
        return personRepository.addPerson(person);
    }


    @GetMapping("/getPerson/{personId}")
    public Person getPerzon(String personId)
    {
        return personRepository.getPersonbyId(personId);
    }

    @DeleteMapping("/deletePerson")
    public String deletePerzon(@RequestBody Person person)
    {
        return personRepository.deletePerson(person);
    }

    @PostMapping("/updatePerson")
    public String updatePerson(@RequestBody Person person)
    {
        return personRepository.editPerson(person);
    }

    public static void main(String[] args) {
        SpringApplication.run(AwsDynamoDbApplication.class, args);
    }

}
