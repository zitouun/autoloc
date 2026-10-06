package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouterMaintenace (Maintenance maintenance);
    Maintenance modifierMaintenance (Maintenance maintenance) ;
    List<Maintenance> afficherTousMaintenance();
    Maintenance afficherMaintenaceById(Long id);
    void supprimerMaintenance(Long id);
}
