package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.ReservationReopitory;

import java.util.List;

@Service
@AllArgsConstructor
public class ContratService  implements IContratService {

    private final ContratRepository contratRepository;
    private final ReservationReopitory reservationReopitory;


    @Override
    public Contrat ajouterContrat(Contrat contrat) {
        if (contrat.getReservation() == null || contrat.getReservation().getIdReservation() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Un contrat doit être associé à une réservation existante"
            );
        }
        Long idReservation = contrat.getReservation().getIdReservation();
        Reservation reservation = reservationReopitory.findById(idReservation)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Réservation introuvable : " + idReservation
                ));
        if (reservation.getContrat() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La réservation " + idReservation + " a déjà un contrat : " + reservation.getContrat().getIdContrat()
            );
        }
        contrat.setIdContrat(null);
        contrat.setReservation(reservation);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifierContrat(Contrat contrat) {
        if (contrat.getIdContrat() == null) {
            throw new IllegalArgumentException("Contrat introuvable : null");
        }
        Contrat existant = contratRepository.findById(contrat.getIdContrat())
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable : " + contrat.getIdContrat()));
        contrat.setPaiements(existant.getPaiements());
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> afficherTousContrats() {

        return contratRepository.findAll();
    }

    @Override
    public Contrat afficherContratById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable : " + id));
    }
    
    @Override
    public void supprimerContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new IllegalArgumentException("Contrat introuvable : " + id);
        }
        contratRepository.deleteById(id);
    }
}
