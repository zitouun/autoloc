package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence ajouerAgence(Agence agence) ;
    Agence modifierAgence(Agence agence);
    List<Agence> afficherTousAgence();
   Agence afficherAgenceById(Long id);

    void supprimerAgence(Long id);

}
