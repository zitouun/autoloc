package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class PaiementController {
    private final IPaiementService paiementService;

    @GetMapping("/afficherTousPaiements")
    public List<Paiement> getAllPaiements(){
        return paiementService.afiicherTousPaiement();
    }

    @GetMapping("/afficherPaiement/{idPaiement}")
    public Paiement getPaiementById(@PathVariable Long idPaiement) {
        return paiementService.afficherPaiementById(idPaiement);
    }

    @PostMapping("/ajouterPaiement")
    public Paiement ajouterPaiement(@RequestBody Paiement paiement) {
        return paiementService.ajouterPaiement(paiement);
    }

    @PutMapping("/modifierPaiement")
    public Paiement modifierPaiement(@RequestBody Paiement paiement){
        return paiementService.modifierPaiment(paiement);
    }

    @DeleteMapping("/supprimerPaiement/{idPaiement}")
    public void supprimerPaiement(@PathVariable Long idPaiement){
        paiementService.supprimerPaiement(idPaiement);
    }
}
