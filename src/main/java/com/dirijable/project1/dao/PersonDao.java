package com.dirijable.project1.dao;

import com.dirijable.project1.model.Book;
import com.dirijable.project1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao implements Dao<Person> {

    private final String FIND_ALL = """
            SELECT  *
            FROM person
            """;
    private final JdbcClient jdbcClient;

    @Autowired
    public PersonDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Person> findAll() {
        return jdbcClient.sql(FIND_ALL).query(Person.class).list();
    }

    @Override
    public Person findById(int id) {
        String FIND_BY_ID = FIND_ALL + """
                WHERE id = ?
                """;
        return jdbcClient.sql(FIND_BY_ID)
                .param(1, id)
                .query(Person.class)
                .single();
    }

    @Override
    public void update(int id, Person toUpdate) {

        String UPDATE = """
                UPDATE person
                SET name = ?, birthyear = ?
                WHERE id = ?
                """;
        jdbcClient.sql(UPDATE)
                .param(1, toUpdate.getName())
                .param(2, toUpdate.getBirthyear())
                .param(3, id)
                .update();
    }

    @Override
    public void delete(int id) {
        String DELETE = """
                DELETE
                FROM person
                WHERE id = ?
                """;
        jdbcClient.sql(DELETE)
                .param(1, id)
                .update();
    }

    @Override
    public void save(Person toSave) {
        String SAVE = """
                INSERT INTO person (name, birthyear)
                VALUES (?, ?)
                """;
        jdbcClient.sql(SAVE)
                .param(1, toSave.getName())
                .param(2, toSave.getBirthyear())
                .update();
    }


}
