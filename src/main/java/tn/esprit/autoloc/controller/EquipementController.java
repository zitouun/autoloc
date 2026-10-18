package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class EquipementController {
    private final IEquipementService equipementService;

    @GetMapping("/afficherTousEquipements")
    public List<Equipement> getAllEquipements(){
        return equipementService.afficherTousEquipement();
    }

    @PostMapping("/ajouterEquipement")
    public Equipement ajouterEquipement(@RequestBody Equipement equipement) {
        return equipementService.ajouterEquipement(equipement);
    }

    @PutMapping("/modifierEquipement")
    public Equipement modifierEquipement(@RequestBody Equipement equipement){
        return equipementService.modifierEquipement(equipement);
    }

    @DeleteMapping("/supprimerEquipement/{idEquipement}")
    public void supprimerEquipement(@PathVariable Long idEquipement){
        equipementService.supprimerEquipement(idEquipement);
    }
}
