package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByMail(String mail);

    @Query("FROM Utilisateur u JOIN FETCH u.listeRole WHERE mail = :mail")
    Optional<Utilisateur> findByMailWithRoles(@Param("mail") String mail);

    List<Utilisateur> findAllByConnecte(boolean connecte);
}
