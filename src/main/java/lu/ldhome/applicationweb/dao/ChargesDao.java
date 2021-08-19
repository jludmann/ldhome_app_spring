package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Charges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargesDao extends JpaRepository<Charges, Long> {
}
