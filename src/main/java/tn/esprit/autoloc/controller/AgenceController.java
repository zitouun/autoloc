package tn.esprit.autoloc.autolocapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.service.IAgenceService1;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class AgenceController {
    private final IAgenceService1 agenceService1;
    @GetMapping("/afficherToutesAgences")
    public List<Agence> getAllAgences(){
        return agenceService1.afficherToutesAgences();
    }
    public Agence ajouterAgence(@RequestBody Agence agence) {
        return agenceService1.ajouteragence(agence);
    }
    @PutMapping("/modifierAgence")
    public Agence modifierAgence(@RequestBody Agence agence){
        return agenceService1.modifieragence(agence);
    }
    @DeleteMapping("/supprimerAgence/{idAgence}")
    public void supprimerAgence(@PathVariable Long idAgence){
        agenceService1.supprimerAgence(idAgence);
    }
}
