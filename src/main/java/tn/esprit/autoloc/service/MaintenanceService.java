package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class MaintenanceService implements IMaintenanceService{

    private final MaintenanceRepository maintenanceRepository ;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Maintenance ajouterMaintenace(Maintenance maintenance) {
        if (maintenance.getVehicule() == null || maintenance.getVehicule().getIdVehicule() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Une maintenance doit être associée à un véhicule existant"
            );
        }
        maintenance.setIdMaintenance(null);
        maintenance.setVehicule(trouverVehicule(maintenance.getVehicule().getIdVehicule()));
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance modifierMaintenance(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idMaintenance est obligatoire pour modifier une maintenance");
        }
        Maintenance existante = maintenanceRepository.findById(maintenance.getIdMaintenance())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Maintenance introuvable : " + maintenance.getIdMaintenance()
                ));
        if (maintenance.getVehicule() == null || maintenance.getVehicule().getIdVehicule() == null) {
            maintenance.setVehicule(existante.getVehicule());
        } else {
            maintenance.setVehicule(trouverVehicule(maintenance.getVehicule().getIdVehicule()));
        }
        return maintenanceRepository.save(maintenance);
    }

    private Vehicule trouverVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicule introuvable : " + idVehicule
                ));
    }

    @Override
    public List<Maintenance> afficherTousMaintenance() {

        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance afficherMaintenaceById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance introuvable : " + id));
    }
    
    @Override
    public void supprimerMaintenance(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new IllegalArgumentException("Maintenance introuvable : " + id);
        }
        maintenanceRepository.deleteById(id);
    }

}
