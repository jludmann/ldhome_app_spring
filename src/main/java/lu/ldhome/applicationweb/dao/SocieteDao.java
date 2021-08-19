package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocieteDao extends JpaRepository<Societe, Long> {
}
