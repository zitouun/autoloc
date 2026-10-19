package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class VehiculeService implements IVehiculeService{

    private final VehiculeRepository vehiculeRepository ;
    private final AgenceRepository agenceRepository;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        if (vehicule.getAgence() == null || vehicule.getAgence().getIdAgence() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Un véhicule doit être associé à une agence existante"
            );
        }

        Long idAgence = vehicule.getAgence().getIdAgence();
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agence introuvable : " + idAgence
                ));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        if (vehicule.getIdVehicule() == null
                || !vehiculeRepository.existsById(vehicule.getIdVehicule())) {
            throw new IllegalArgumentException("Vehicule introuvable : " + vehicule.getIdVehicule());
        }
        if (vehicule.getAgence() == null || vehicule.getAgence().getIdAgence() == null) {
            throw new IllegalArgumentException("Une agence existante doit être fournie");
        }
        Agence agence = agenceRepository.findById(vehicule.getAgence().getIdAgence())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Agence introuvable : " + vehicule.getAgence().getIdAgence()));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> afficherTousVehicule() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule AfficherVehiculeById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicule introuvable : " + id));
    }
    
    @Override
    public void supprimerVehicule(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehicule introuvable : " + id);
        }
        vehiculeRepository.deleteById(id);
    }


}
