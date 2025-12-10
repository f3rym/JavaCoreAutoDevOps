package com.dirijable.project1.controller;

import com.dirijable.project1.dao.BookDao;
import com.dirijable.project1.dao.PersonDao;
import com.dirijable.project1.model.Book;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    private BookDao bookDao;
    @Mock
    private PersonDao personDao;
    @InjectMocks
    private BookController bookController;

    @Test
    @SuppressWarnings(value = {"unchecked"})
    void shouldReturnAllBooks() {

        Model model = new BindingAwareModelMap();
        List<Book> books = List.of(
                Book.builder()
                        .id(1)
                        .author("testAuthor1")
                        .personId(1)
                        .publishYear(1999)
                        .title("testTitle1")
                        .build(),
                Book.builder()
                        .id(2)
                        .author("testAuthor2")
                        .personId(2)
                        .publishYear(2000)
                        .title("testTitle2")
                        .build()
        );
        Mockito.doReturn(books).when(bookDao).findAll();

        String viewName = bookController.allBooks(model);
        assertThat(viewName).isEqualTo("book/books");
        assertThat(model.containsAttribute("books")).isTrue();
        List<Book> actual = (List<Book>) model.getAttribute("books");
        assertThat(actual)
                .isNotNull()
                .hasSize(2);

    }


    @Test
    void byIdBooks() {
    }

    @Test
    void byIdBook() {
    }

    @Test
    void update() {
    }

    @Test
    void addBookToPerson() {
    }

    @Test
    void newBook() {
    }

    @Test
    void create() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void freeBook() {
    }
}