package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipementService implements IEquipementService{

    private final EquipementRepository equipementRepository;


    @Override
    public Equipement ajouterEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement modifierEquipement(Equipement equipement) {
        return null;
    }

    @Override
    public List<Equipement> afficherTousEquipement() {
        return List.of();
    }

    @Override
    public Equipement afficherEquipementById(Long id) {
        return null;
    }

    @Override
    public void supprimerEquipement(Long id) {

    }

}
