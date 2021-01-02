package com.mateo9x.recruitmentapp.controllers;

import com.mateo9x.recruitmentapp.commands.BookCommand;
import com.mateo9x.recruitmentapp.commands.ReservationCommand;
import com.mateo9x.recruitmentapp.converters.BookCommandToBook;
import com.mateo9x.recruitmentapp.converters.ReservationCommandToReservation;
import com.mateo9x.recruitmentapp.model.Book;
import com.mateo9x.recruitmentapp.model.Reservation;
import com.mateo9x.recruitmentapp.repository.BookRepository;
import com.mateo9x.recruitmentapp.repository.ReservationRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    @RequestMapping("reservation/{id}/show")
    public String getReservations(Model model, Book book){
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservation/show";
    }

        @GetMapping("/book/{id}/reserve")
        public String makeReservation(@PathVariable("id") Long id, Model model,Book book, @ModelAttribute Reservation reservation, Authentication authentication, LocalDateTime dateTime) {
        //    Book bookReserve = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
                reservation.setUserId(authentication.getName());
                dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = dateTime.format(formatter);
                reservation.setReservedAt(formattedDateTime);
            model.addAttribute("books", bookRepository.findById(id).get());
                model.addAttribute("reservations", new ReservationCommand());
                return "reservation/reserve";

        }

    @PostMapping("reservation")
    public String save(@ModelAttribute ReservationCommand command){

        Optional<Reservation> reservationOptional = reservationRepository.findAllByUserId(command.getUserId());

        if (!reservationOptional.isPresent()) {
            Reservation detachedReservation = reservationCommandToReservation.convert(command);
            Reservation saveReservation = reservationRepository.save(detachedReservation);
            return "redirect:/";
        } else {
            System.out.println("Sorry, there's such reservation in db");
            return "redirect:/";
        }
    }
}
