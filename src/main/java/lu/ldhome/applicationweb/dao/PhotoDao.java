package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDao extends JpaRepository<Photo, Long> {
}
