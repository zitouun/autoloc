package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class ClientService implements IClientService {

 private final ClientRepository clientRepository;

    @Override
    public Client ajouterClient(Client client) {
        valider(client);
        client.setIdClient(null);
        client.setReservations(new ArrayList<>());
        return enregistrer(client);
    }

    @Override
    public Client modifierClient(Client client) {
        if (client.getIdClient() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idClient est obligatoire pour modifier un client");
        }
        Client existant = clientRepository.findById(client.getIdClient())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Client introuvable : " + client.getIdClient()
                ));
        valider(client);
        client.setReservations(existant.getReservations());
        return enregistrer(client);
    }

    @Override
    public List<Client> afficherTousClients() {

        return clientRepository.findAll();
    }

    @Override
    public Client afficherClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable : " + id));
    }
    
    @Override
    public void supprimerClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client introuvable : " + id);
        }
        clientRepository.deleteById(id);
    }

    private void valider(Client client) {
        if (client == null
                || vide(client.getNom())
                || vide(client.getPrenom())
                || vide(client.getEmail())
                || vide(client.getTelephone())
                || vide(client.getNumPermis())
                || client.getDateInscription() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "nom, prenom, email, telephone, numPermis et dateInscription sont obligatoires"
            );
        }
    }

    private Client enregistrer(Client client) {
        try {
            return clientRepository.save(client);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email ou numéro de permis déjà utilisé : " + client.getEmail() + " / " + client.getNumPermis()
            );
        }
    }

    private boolean vide(String valeur) {
        return valeur == null || valeur.isBlank();
    }


}
