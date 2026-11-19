package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor

public class VehiculeService implements IVehiculeService{

    private static final DateTimeFormatter FORMAT_DATE_HEURE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final VehiculeRepository vehiculeRepository ;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void actualiserEtatVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        System.out.println("=== Actualisation des véhicules ===");
        System.out.println("Nombre total de véhicules enregistrés : " + vehicules.size());
        System.out.println("Date et heure de l'actualisation : "
                + LocalDateTime.now().format(FORMAT_DATE_HEURE));
    }

    @Scheduled(cron = "0 0 9 * * MON")
    public void genererRapportVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        int nombreTotal = vehicules.size();
        System.out.println("=== Rapport périodique des véhicules ===");
        System.out.println("Nombre total de véhicules : " + nombreTotal);
        System.out.println("Date et heure de génération : "
                + LocalDateTime.now().format(FORMAT_DATE_HEURE));
    }

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        if (vehicule.getAgence() == null || vehicule.getAgence().getIdAgence() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Un véhicule doit être associé à une agence existante"
            );
        }

        Long idAgence = vehicule.getAgence().getIdAgence();
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agence introuvable : " + idAgence
                ));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        if (vehicule.getIdVehicule() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idVehicule est obligatoire pour modifier un véhicule");
        }
        Vehicule existant = vehiculeRepository.findById(vehicule.getIdVehicule())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicule introuvable : " + vehicule.getIdVehicule()
                ));
        vehicule.setMaintenances(existant.getMaintenances());
        if (vehicule.getAgence() == null || vehicule.getAgence().getIdAgence() == null) {
            vehicule.setAgence(existant.getAgence());
        } else {
            Long idAgence = vehicule.getAgence().getIdAgence();
            Agence agence = agenceRepository.findById(idAgence)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Agence introuvable : " + idAgence
                    ));
            vehicule.setAgence(agence);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> afficherTousVehicule() {

        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule AfficherVehiculeById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicule introuvable : " + id));
    }
    
    @Override
    public void supprimerVehicule(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehicule introuvable : " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeExistant(idVehicule);
        Equipement equipement = equipementExistant(idEquipement);
        vehicule.getEquipements().add(equipement);
        equipement.getVehicules().add(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeExistant(idVehicule);
        if (idEquipement == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'idEquipement est obligatoire");
        }
        Equipement aRetirer = null;
        for (Equipement candidat : vehicule.getEquipements()) {
            if (idEquipement.equals(candidat.getIdEquipement())) {
                aRetirer = candidat;
                break;
            }
        }
        if (aRetirer == null) {
            if (!equipementRepository.existsById(idEquipement)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Equipement introuvable : " + idEquipement
                );
            }
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cet équipement n'est pas affecté à ce véhicule"
            );
        }
        vehicule.getEquipements().remove(aRetirer);
        aRetirer.getVehicules().remove(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    private Vehicule vehiculeExistant(Long idVehicule) {
        if (idVehicule == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'idVehicule est obligatoire");
        }
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicule introuvable : " + idVehicule
                ));
    }

    private Equipement equipementExistant(Long idEquipement) {
        if (idEquipement == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'idEquipement est obligatoire");
        }
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Equipement introuvable : " + idEquipement
                ));
    }

}
