package com.mateo9x.recruitmentapp.controllers;

import com.mateo9x.recruitmentapp.commands.BookCommand;
import com.mateo9x.recruitmentapp.commands.ReservationCommand;
import com.mateo9x.recruitmentapp.converters.BookCommandToBook;
import com.mateo9x.recruitmentapp.converters.ReservationCommandToReservation;
import com.mateo9x.recruitmentapp.model.Book;
import com.mateo9x.recruitmentapp.model.Reservation;
import com.mateo9x.recruitmentapp.repository.BookRepository;
import com.mateo9x.recruitmentapp.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ReservationController {

        private BookRepository bookRepository;
        private ReservationRepository reservationRepository;
        private BookCommandToBook bookCommandToBook;
        private ReservationCommandToReservation reservationCommandToReservation;

        public ReservationController(BookRepository bookRepository, ReservationRepository reservationRepository, BookCommandToBook bookCommandToBook, ReservationCommandToReservation reservationCommandToReservation) {
            this.bookRepository = bookRepository;
            this.reservationRepository = reservationRepository;
            this.bookCommandToBook = bookCommandToBook;
            this.reservationCommandToReservation = reservationCommandToReservation;
        }

    @GetMapping
    @RequestMapping(value ={"/reservations", "reservation/show"})
    public String getReservations(Model model){
            model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservation/show";
    }

        @GetMapping("/book/{id}/reserve")
        public String makeReservation(@PathVariable("id") Long id, Model model, @ModelAttribute Reservation reservation) {
            Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            //Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            //reservation.setUserId("1");
            model.addAttribute("book", book);
            model.addAttribute("reservation", reservation);
            return "reservation/reserve";
        }

    @PostMapping("/reservation")
    public String saveOrUpdate(@ModelAttribute Reservation reservation, Model model){

            model.addAttribute("reservation", reservation);
            return"redirect:/";
    }
}
