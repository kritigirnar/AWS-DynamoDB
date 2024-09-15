package com.aws.aws_dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.aws_dynamodb.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository {

    private DynamoDBMapper dbmappr;

    public Person addPerson(Person person)
    {
         dbmappr.save(person);
        return person;
    }

    public Person getPersonbyId(String personId)
    {
        return dbmappr.load(Person.class, personId);
    }

    public String deletePerson(Person person)
    {
        dbmappr.delete(person);
        return "person removed";
    }

    public String editPerson(Person person)
    {
        dbmappr.save(person, buildExpressionForSave(person));
        return "person Updated";
    }

    private DynamoDBSaveExpression buildExpressionForSave(Person person)
    {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();

        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("personId",new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
        dynamoDBSaveExpression.setExpected(expectedMap);

        return dynamoDBSaveExpression;

    }
}

