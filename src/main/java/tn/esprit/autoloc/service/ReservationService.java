package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ReservationReopitory;

import java.util.List;

@Service
@AllArgsConstructor

public class ReservationService implements IReservationService {
    private final ReservationReopitory reservationReopitory ;

    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        return reservationReopitory.save(reservation);
    }

    @Override
    public Reservation modifierReservation(Reservation reservation) {
        return null;
    }

    @Override
    public List<Reservation> afficherTousReservation() {
        return List.of();
    }

    @Override
    public Reservation afficherReservationById(Long id) {
        return null;
    }

    @Override
    public void supprimerReservation(Long id) {

    }


}
