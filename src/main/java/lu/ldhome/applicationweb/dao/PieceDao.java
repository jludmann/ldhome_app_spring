package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceDao extends JpaRepository<Piece, Long> {
}
