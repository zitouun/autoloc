package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.MaintenanceRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class MaintenanceService implements IMaintenanceService{

    private final MaintenanceRepository maintenanceRepository ;

    @Override
    public Maintenance ajouterMaintenace(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance modifierMaintenance(Maintenance maintenance) {
        return null;
    }

    @Override
    public List<Maintenance> afficherTousMaintenance() {
        return List.of();
    }

    @Override
    public Maintenance afficherMaintenaceById(Long id) {
        return null;
    }

    @Override
    public void supprimerMaintenance(Long id) {

    }

}
