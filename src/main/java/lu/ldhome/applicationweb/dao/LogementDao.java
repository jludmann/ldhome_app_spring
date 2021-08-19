package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Locataire;
import lu.ldhome.applicationweb.model.Logement;
import lu.ldhome.applicationweb.model.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogementDao extends JpaRepository<Logement, Long> {

    Optional<List<Logement>> findAllByProprietaire(Proprietaire proprietaire);
    Optional<List<Logement>> findAllByLocataire(Locataire locataire);
    Optional<Logement> findByLocataire(Locataire locataire);
    Optional<List<Logement>> findByProprietaireAndLocataireNotNull(Proprietaire proprietaire);
}
