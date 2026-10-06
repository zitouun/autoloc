package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ContratService  implements IContratService {

    private final ContratRepository contratRepository;


    @Override
    public Contrat ajouterContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifierContrat(Contrat contrat) {
        return null;
    }

    @Override
    public List<Contrat> afficherTousContrats() {
        return List.of();
    }

    @Override
    public Contrat afficherContratById(Long id) {
        return null;
    }

    @Override
    public void supprimerContrat(Long id) {

    }
}
