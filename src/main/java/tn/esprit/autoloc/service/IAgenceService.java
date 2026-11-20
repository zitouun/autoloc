package tn.esprit.autoloc.service;

import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.autoloc.domain.Agence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface IAgenceService {

    private static String maintenant() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    Agence ajouerAgence(Agence agence) ;
    Agence modifierAgence(Agence agence);
    List<Agence> afficherTousAgence();
   Agence afficherAgenceById(Long id);

    void supprimerAgence(Long id);

    Agence ajouterAgenceEtAffecterVehicules(Agence agence);

    @Scheduled(fixedDelay = 120000)
    default void suivreNombreAgences() {
        List<Agence> agences = afficherTousAgence();
        System.out.println("=== Suivi des agences ===");
        System.out.println("Nombre total d'agences enregistrées : " + agences.size());
        System.out.println("Date et heure de la vérification : " + maintenant());
    }

}
