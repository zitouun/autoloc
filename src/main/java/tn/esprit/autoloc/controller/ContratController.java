package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class ContratController {
    private final IContratService contratService;

    @GetMapping("/afficherTousContrats")
    public List<Contrat> getAllContrats(){
        return contratService.afficherTousContrats();
    }

    @PostMapping("/ajouterContrat")
    public Contrat ajouterContrat(@RequestBody Contrat contrat) {
        return contratService.ajouterContrat(contrat);
    }

    @PutMapping("/modifierContrat")
    public Contrat modifierContrat(@RequestBody Contrat contrat){
        return contratService.modifierContrat(contrat);
    }

    @DeleteMapping("/supprimerContrat/{idContrat}")
    public void supprimerContrat(@PathVariable Long idContrat){
        contratService.supprimerContrat(idContrat);
    }
}
