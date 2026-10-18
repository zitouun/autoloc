package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class ClientController {
    private final IClientService clientService;

    @GetMapping("/afficherTousClients")
    public List<Client> getAllClients(){
        return clientService.afficherTousClients();
    }

    @PostMapping("/ajouterClient")
    public Client ajouterClient(@RequestBody Client client) {
        return clientService.ajouterClient(client);
    }

    @PutMapping("/modifierClient")
    public Client modifierClient(@RequestBody Client client){
        return clientService.modifierClient(client);
    }

    @DeleteMapping("/supprimerClient/{idClient}")
    public void supprimerClient(@PathVariable Long idClient){
        clientService.supprimerClient(idClient);
    }
}
