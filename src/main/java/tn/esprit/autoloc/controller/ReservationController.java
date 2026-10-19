package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class ReservationController {
    private final IReservationService reservationService;

    @GetMapping("/afficherToutesReservations")
    public List<Reservation> getAllReservations(){
        return reservationService.afficherTousReservation();
    }

    @GetMapping("/afficherReservation/{idReservation}")
    public Reservation getReservationById(@PathVariable Long idReservation) {
        return reservationService.afficherReservationById(idReservation);
    }

    @PostMapping("/ajouterReservation")
    public Reservation ajouterReservation(@RequestBody Reservation reservation) {
        return reservationService.ajouterReservation(reservation);
    }

    @PutMapping("/modifierReservation")
    public Reservation modifierReservation(@RequestBody Reservation reservation){
        return reservationService.modifierReservation(reservation);
    }

    @DeleteMapping("/supprimerReservation/{idReservation}")
    public void supprimerReservation(@PathVariable Long idReservation){
        reservationService.supprimerReservation(idReservation);
    }
}
