package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class EmployeService implements IEmployeService{
    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public Employe ajouterEmploye(Employe employe) {
        if (employe.getAgence() == null || employe.getAgence().getIdAgence() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Une agence existante doit être fournie"
            );
        }
        if (!agenceRepository.existsById(employe.getAgence().getIdAgence())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Agence introuvable : " + employe.getAgence().getIdAgence()
            );
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifierEmploye(Employe employe) {
        return null;
    }

    @Override
    public List<Employe> afficherTousEmployes() {
        return List.of();
    }

    @Override
    public Employe afficherEmployeById(Long id) {
        return null;
    }

    @Override
    public void supprimerEmploye(Long id) {

    }

}
