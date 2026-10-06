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
        return null;
    }

    @Override
    public List<Client> afficherTousClients() {
        return List.of();
    }

    @Override
    public Client afficherClientById(Long id) {
        return null;
    }

    @Override
    public void supprimerClient(Long id) {

    }


}
