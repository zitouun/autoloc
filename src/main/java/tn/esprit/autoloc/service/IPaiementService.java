package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement (Paiement paiement ) ;
    Paiement modifierPaiment (Paiement paiement) ;
    List<Paiement> afiicherTousPaiement() ;
    Paiement afficherPaiementById (Long id) ;
    void supprimerPaiement (Long id) ;

}
