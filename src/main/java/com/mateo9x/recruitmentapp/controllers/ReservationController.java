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

   /*    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username= authentication.getName(); */


    @GetMapping
    @RequestMapping(value ={ "reservation/show"})
    public String getReservations(Model model){
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservation/show";
    }

        @GetMapping("/book/{id}/reserve")
        public String makeReservation(@PathVariable("id") Long id, Model model, @ModelAttribute Reservation reservation, Authentication authentication) {
            Book bookReserve = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
          //  reservation.setUserId(authentication.getName());
            model.addAttribute("reservation", new ReservationCommand());

            return "reservation/reserve";
        }

    @PostMapping("reservation")
    public String save(@ModelAttribute ReservationCommand command){

        Optional<Reservation> reservationOptional = reservationRepository.FindAllWithQuery();

        if (!reservationOptional.isPresent()) {
            Reservation detachedReservation = reservationCommandToReservation.convert(command);
            Reservation saveReservation = reservationRepository.save(detachedReservation);
            return "redirect:/";
        } else {
            System.out.println("Sorry, there's such book in db");
            return "redirect:/";
        }
    }
}
