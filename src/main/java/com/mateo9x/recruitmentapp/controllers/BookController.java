package com.mateo9x.recruitmentapp.controllers;

import com.mateo9x.recruitmentapp.commands.BookCommand;
import com.mateo9x.recruitmentapp.converters.BookCommandToBook;
import com.mateo9x.recruitmentapp.converters.ReservationCommandToReservation;
import com.mateo9x.recruitmentapp.model.Book;
import com.mateo9x.recruitmentapp.repository.BookRepository;
import com.mateo9x.recruitmentapp.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class BookController {

    private BookRepository bookRepository;
    private ReservationRepository reservationRepository;
    private BookCommandToBook bookCommandToBook;
    private ReservationCommandToReservation reservationCommandToReservation;

    public BookController(BookRepository bookRepository, ReservationRepository reservationRepository, BookCommandToBook bookCommandToBook, ReservationCommandToReservation reservationCommandToReservation) {
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.reservationCommandToReservation = reservationCommandToReservation;
    }


    @GetMapping
    @RequestMapping(value ={"/books", "book/list"})
    public String getBook(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "book/list";
    }

    @GetMapping("/book/new")
    public String newBook(Model model){
        model.addAttribute("book", new BookCommand());

        return "book/add";
    }

  @GetMapping("/book/{id}/edit")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping("book")
    public String saveOrUpdate(@ModelAttribute BookCommand command){

        Optional<Book> bookOptional = bookRepository.getBooksByName(command.getName());

        if (!bookOptional.isPresent()) {
            Book detachedBook = bookCommandToBook.convert(command);
            Book savedBook = bookRepository.save(detachedBook);
            return "redirect:/";
        } else {
            System.out.println("Sorry, there's such book in db");
            return "redirect:/";
        }
    }

}
