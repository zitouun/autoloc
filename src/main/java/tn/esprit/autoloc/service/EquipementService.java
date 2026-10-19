package tn.esprit.autoloc.service;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Tag(name = "gestion des agence",description = "CRUD d agence")
public class EquipementService implements IEquipementService{

    private final EquipementRepository equipementRepository;


    @Override
    public Equipement ajouterEquipement(Equipement equipement) {
        if (equipement == null || equipement.getLibelle() == null
                || equipement.getLibelle().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le libellé de l'équipement est obligatoire"
            );
        }

        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement modifierEquipement(Equipement equipement) {
        if (equipement == null || equipement.getIdEquipement() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "L'idEquipement est obligatoire"
            );
        }
        if (equipement.getLibelle() == null || equipement.getLibelle().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le libellé de l'équipement est obligatoire"
            );
        }
        if (!equipementRepository.existsById(equipement.getIdEquipement())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Equipement introuvable : " + equipement.getIdEquipement()
            );
        }
        return equipementRepository.save(equipement);
    }

    @Override
    public List<Equipement> afficherTousEquipement() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement afficherEquipementById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "L'idEquipement est obligatoire"
            );
        }
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Equipement introuvable : " + id
                ));
    }

    @Override
    public void supprimerEquipement(Long id) {
        if (id == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "L'idEquipement est obligatoire"
            );
        }
        if (!equipementRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Equipement introuvable : " + id
            );
        }
        equipementRepository.deleteById(id);
    }

}
