package lu.ldhome.applicationweb.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Agent extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "agent")
    private List<Note> listeNote = new ArrayList<>();

}
