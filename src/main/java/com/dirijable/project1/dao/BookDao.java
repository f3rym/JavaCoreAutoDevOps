package com.dirijable.project1.dao;

import com.dirijable.project1.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookDao implements Dao<Book> {


    private final String FIND_ALL = """
            SELECT  *
            FROM book
            """;
    private final JdbcClient jdbcClient;

    @Autowired
    public BookDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Book> findAll() {
        return jdbcClient.sql(FIND_ALL).query(Book.class).list();
    }

    @Override
    public Book findById(int id) {
        String FIND_BY_ID = FIND_ALL + """
                WHERE id = ?
                """;
        String a = ""+id;
        String[] split = a.split("");
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("^");

        Arrays.stream(split)
                .forEach(number -> stringBuilder.append("[0-%s]".formatted(number)));
        stringBuilder.append("$");


        return jdbcClient.sql(FIND_BY_ID)
                .param(1, id)
                .query(Book.class)
                .single();
    }

    public List<Book> findByPersonId(int personId) {

        String FIND_BY_PERSON_ID = FIND_ALL + """
                where person_id = ?
                """;
        return jdbcClient.sql(FIND_BY_PERSON_ID)
                .param(1, personId)
                .query(Book.class).list();
    }

    public void addBookToPerson(Integer personId, int bookId){
        Book book = findById(bookId);
        book.setPersonId(personId);
        update(bookId, book);
    }
    @Override
    public void update(int id, Book book) {
        String UPDATE = """
                UPDATE book
                SET title = ?, author = ?, publish_year = ?, person_id = ?
                WHERE id = ?
                """;
        jdbcClient.sql(UPDATE)
                .param(1, book.getTitle())
                .param(2, book.getAuthor())
                .param(3, book.getPublishYear())
                .param(4, book.getPersonId())
                .param(5, id)
                .update();
    }

    @Override
    public void delete(int id) {
        String DELETE = """
                DELETE
                FROM book
                WHERE id = ?
                """;
        jdbcClient.sql(DELETE)
                .param(1, id)
                .update();
    }

    @Override
    public void save(Book book) {

        String SAVE = """
                INSERT INTO book (title, author, publish_year)
                VALUES (?, ?, ?)
                """;
        jdbcClient.sql(SAVE)
                .param(1, book.getTitle())
                .param(2, book.getAuthor())
                .param(3, book.getPublishYear())
                .update();
    }

    public void freeBook(int bookId) {
        String FREE_BOOK = """
                UPDATE book
                SET person_id = NULL
                WHERE id = ?
                """;
        jdbcClient.sql(FREE_BOOK)
                .param(1, bookId)
                .update();
    }
}
