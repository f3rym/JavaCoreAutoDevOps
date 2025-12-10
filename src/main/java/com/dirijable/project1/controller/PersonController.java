package com.dirijable.project1.controller;

import com.dirijable.project1.dao.BookDao;
import com.dirijable.project1.dao.PersonDao;
import com.dirijable.project1.model.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public PersonController(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }



    @GetMapping
    public String allPeople(Model model) {
        model.addAttribute("people", personDao.findAll());
        return "people/all";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        personDao.save(person);
        return "redirect: /people";
    }

    @GetMapping("/{id}")
    public String personById(@PathVariable("id") int id,
                             Model model){
        Person person = personDao.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("books", bookDao.findByPersonId(id));
        return "people/personById";
    }

    @GetMapping("/{id}/edit")
    public String editForm(
            @PathVariable("id") int id,
            Model model){
        model.addAttribute("person", personDao.findById(id));
        return "people/edit";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         @PathVariable("id") int id,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        personDao.update(id, person);
        return "redirect: /people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDao.delete(id);
        return "redirect: /people";
    }

}
