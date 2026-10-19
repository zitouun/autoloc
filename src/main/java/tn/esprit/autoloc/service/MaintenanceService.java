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
        if (maintenance.getIdMaintenance() == null
                || !maintenanceRepository.existsById(maintenance.getIdMaintenance())) {
            throw new IllegalArgumentException("Maintenance introuvable : " + maintenance.getIdMaintenance());
        }
        return maintenanceRepository.save(maintenance);
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
