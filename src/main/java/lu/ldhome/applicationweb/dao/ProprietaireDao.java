package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProprietaireDao extends JpaRepository<Proprietaire, Long> {
    Optional<Proprietaire> findById(Long id);
    Optional<Proprietaire> findByMail(String mail);
}
