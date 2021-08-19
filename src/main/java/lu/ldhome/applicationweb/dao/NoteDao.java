package lu.ldhome.applicationweb.dao;

import lu.ldhome.applicationweb.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<Note, Long> {
}
