package tn.esprit.autoloc.service;

import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.autoloc.domain.Vehicule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface IVehiculeService  {

    private static String maintenant() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    Vehicule ajouterVehicule (Vehicule vehicule) ;
    Vehicule modifierVehicule (Vehicule vehicule) ;
    List<Vehicule> afficherTousVehicule();
    Vehicule AfficherVehiculeById (Long id) ;
    void supprimerVehicule(Long id) ;

    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);

    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);

    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    default void actualiserEtatVehicules() {
        List<Vehicule> vehicules = afficherTousVehicule();
        System.out.println("=== Actualisation des véhicules ===");
        System.out.println("Nombre total de véhicules enregistrés : " + vehicules.size());
        System.out.println("Date et heure de l'actualisation : " + maintenant());
    }

    @Scheduled(cron = "0 0 9 * * MON")
    default void genererRapportVehicules() {
        List<Vehicule> vehicules = afficherTousVehicule();
        int nombreTotal = vehicules.size();
        System.out.println("=== Rapport périodique des véhicules ===");
        System.out.println("Nombre total de véhicules : " + nombreTotal);
        System.out.println("Date et heure de génération : " + maintenant());
    }

}
