package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    @GetMapping("/afficherTousVehicules")
    public List<Vehicule> getAllVehicules(){
        return vehiculeService.afficherTousVehicule();
    }

    @PostMapping("/ajouterVehicule")
    public Vehicule ajouterVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.ajouterVehicule(vehicule);
    }

    @PutMapping("/modifierVehicule")
    public Vehicule modifierVehicule(@RequestBody Vehicule vehicule){
        return vehiculeService.modifierVehicule(vehicule);
    }

    @DeleteMapping("/supprimerVehicule/{idVehicule}")
    public void supprimerVehicule(@PathVariable Long idVehicule){
        vehiculeService.supprimerVehicule(idVehicule);
    }
}
