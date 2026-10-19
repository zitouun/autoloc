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
        if (contrat.getIdContrat() == null || !contratRepository.existsById(contrat.getIdContrat())) {
            throw new IllegalArgumentException("Contrat introuvable : " + contrat.getIdContrat());
        }
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
