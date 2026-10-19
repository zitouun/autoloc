package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.PaimentRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class PaimentService  implements IPaiementService{
    private final PaimentRepository paimentRepository ;

    @Override
    public Paiement ajouterPaiement(Paiement paiement) {
        return paimentRepository.save(paiement);
    }

    @Override
    public Paiement modifierPaiment(Paiement paiement) {
        if (paiement.getIdPaiement() == null || !paimentRepository.existsById(paiement.getIdPaiement())) {
            throw new IllegalArgumentException("Paiement introuvable : " + paiement.getIdPaiement());
        }
        return paimentRepository.save(paiement);
    }

    @Override
    public List<Paiement> afiicherTousPaiement() {
        return paimentRepository.findAll();
    }

    @Override
    public Paiement afficherPaiementById(Long id) {
        return paimentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paiement introuvable : " + id));
    }
    
    @Override
    public void supprimerPaiement(Long id) {
        if (!paimentRepository.existsById(id)) {
            throw new IllegalArgumentException("Paiement introuvable : " + id);
        }
        paimentRepository.deleteById(id);
    }


}
