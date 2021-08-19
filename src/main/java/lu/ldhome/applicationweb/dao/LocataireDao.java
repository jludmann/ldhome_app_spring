package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Locataire;
import lu.ldhome.applicationweb.model.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocataireDao extends JpaRepository<Locataire, Long> {
    Optional<Locataire> findById(Long idUtilisateur);
    Optional<Locataire> findByLogement(Logement logement);
    Optional<Locataire> findByMail(String mail);
}
