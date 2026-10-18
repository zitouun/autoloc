package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class MaintenanceController {
    private final IMaintenanceService maintenanceService;

    @GetMapping("/afficherToutesMaintenances")
    public List<Maintenance> getAllMaintenances(){
        return maintenanceService.afficherTousMaintenance();
    }

    @PostMapping("/ajouterMaintenance")
    public Maintenance ajouterMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.ajouterMaintenace(maintenance);
    }

    @PutMapping("/modifierMaintenance")
    public Maintenance modifierMaintenance(@RequestBody Maintenance maintenance){
        return maintenanceService.modifierMaintenance(maintenance);
    }

    @DeleteMapping("/supprimerMaintenance/{idMaintenance}")
    public void supprimerMaintenance(@PathVariable Long idMaintenance){
        maintenanceService.supprimerMaintenance(idMaintenance);
    }
}
