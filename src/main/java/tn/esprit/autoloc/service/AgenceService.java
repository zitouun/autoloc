package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class AgenceService implements IAgenceService{

    private final AgenceRepository agenceRepository ;


    @Override
    public Agence ajouerAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        if (agence.getIdAgence() == null || !agenceRepository.existsById(agence.getIdAgence())) {
            throw new IllegalArgumentException("Agence introuvable : " + agence.getIdAgence());
        }
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> afficherTousAgence() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence afficherAgenceById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agence introuvable : " + id));
    }

    @Override
    public void supprimerAgence(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Agence introuvable : " + id);
        }
        agenceRepository.deleteById(id);
    }


}
