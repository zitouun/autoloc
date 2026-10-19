package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class ClientService implements IClientService {

 private final ClientRepository clientRepository;

    @Override
    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client modifierClient(Client client) {
        if (client.getIdClient() == null || !clientRepository.existsById(client.getIdClient())) {
            throw new IllegalArgumentException("Client introuvable : " + client.getIdClient());
        }
        return clientRepository.save(client);
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


}
