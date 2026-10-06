package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class EmployeService implements IEmployeService{
    private final EmployeRepository employeRepository;
    @Override
    public Employe ajouterEmploye(Employe employe) {
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
