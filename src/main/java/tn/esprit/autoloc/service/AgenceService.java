package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class AgenceService implements IAgenceService{

    private final AgenceRepository agenceRepository ;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Agence ajouerAgence(Agence agence) {

        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        if (agence.getIdAgence() == null || !agenceRepository.existsById(agence.getIdAgence())) {
            throw new IllegalArgumentException("Agence introuvable : " + agence.getIdAgence());
        }
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> afficherTousAgence() {

        return agenceRepository.findAll();
    }

    @Override
    public Agence afficherAgenceById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agence introuvable : " + id));
    }

    @Override
    public void supprimerAgence(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Agence introuvable : " + id);
        }
        agenceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        if (agence == null
                || agence.getNom() == null || agence.getNom().isBlank()
                || agence.getVille() == null || agence.getVille().isBlank()
                || agence.getAdresse() == null || agence.getAdresse().isBlank()
                || agence.getTelephone() == null || agence.getTelephone().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le nom, la ville, l'adresse et le téléphone de l'agence sont obligatoires"
            );
        }
        if (agence.getVehicules() == null || agence.getVehicules().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Au moins un véhicule doit être fourni pour être affecté à l'agence"
            );
        }

        List<Vehicule> vehicules = new ArrayList<>(agence.getVehicules());
        for (Vehicule vehicule : vehicules) {
            validerVehicule(vehicule);
        }

        agence.setVehicules(new ArrayList<>());
        agence.setEmployes(new ArrayList<>());
        Agence agenceEnregistree = agenceRepository.save(agence);

        for (Vehicule vehicule : vehicules) {
            vehicule.setAgence(agenceEnregistree);
            try {
                Vehicule vehiculeEnregistre = vehiculeRepository.saveAndFlush(vehicule);
                agenceEnregistree.getVehicules().add(vehiculeEnregistre);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Immatriculation déjà utilisée : " + vehicule.getImmatriculation()
                );
            }
        }
        return agenceEnregistree;
    }

    private void validerVehicule(Vehicule vehicule) {
        if (vehicule == null
                || vehicule.getImmatriculation() == null || vehicule.getImmatriculation().isBlank()
                || vehicule.getMarque() == null || vehicule.getMarque().isBlank()
                || vehicule.getModele() == null || vehicule.getModele().isBlank()
                || vehicule.getCategorie() == null
                || vehicule.getTarifJournalier() == null
                || vehicule.getStatut() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Chaque véhicule doit avoir une immatriculation, une marque, un modèle, une catégorie, un tarif journalier et un statut"
            );
        }
    }

}
