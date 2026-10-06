package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class VehiculeService implements IVehiculeService{

    private final VehiculeRepository vehiculeRepository ;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        return null;
    }

    @Override
    public List<Vehicule> afficherTousVehicule() {
        return List.of();
    }

    @Override
    public Vehicule AfficherVehiculeById(Long id) {
        return null;
    }

    @Override
    public void supprimerVehicule(Long id) {

    }


}
