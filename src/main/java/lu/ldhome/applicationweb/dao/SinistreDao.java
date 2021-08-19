package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Sinistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinistreDao extends JpaRepository<Sinistre, Long> {
}
