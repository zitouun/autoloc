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
        return null;
    }

    @Override
    public List<Agence> afficherTousAgence() {
        return List.of();
    }

    @Override
    public Agence afficherAgenceById(Long id) {
        return null;
    }

    @Override
    public void supprimerAgence(Long id) {

    }


}

