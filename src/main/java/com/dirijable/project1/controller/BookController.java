package com.dirijable.project1.controller;

import com.dirijable.project1.dao.BookDao;
import com.dirijable.project1.dao.PersonDao;
import com.dirijable.project1.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String allBooks(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "book/books";
    }

    @GetMapping("/{id}/edit")
    public String byIdBooks(@PathVariable("id") int id,
                            Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "book/edit";
    }

    @GetMapping("/{id}")
    public String byIdBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("book", bookDao.findById(id));
        model.addAttribute("persons", personDao.findAll());
        return "book/bookById";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "book/edit";
        bookDao.update(id, book);
        return "redirect: /books";
    }

    @PatchMapping("/{id}/assign")
    public String addBookToPerson(@PathVariable("id") int id,
                                  @RequestParam(value = "personId", required = false) Integer personId) {
        bookDao.addBookToPerson(personId, id);
         return "redirect: /books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "book/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";
        bookDao.save(book);
        return "redirect: /books";

    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect: /books";
    }

    @PatchMapping("/{id}/release")
    public String freeBook(@PathVariable("id") int id){
        bookDao.freeBook(id);
        return "redirect: /books";
    }

}
