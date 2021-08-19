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
public class Proprietaire extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "proprietaire")
    private List<Logement> listeLogement = new ArrayList<>();

    public List<Logement> getListeLogement() {
        return listeLogement;
    }

    public void setListeLogement(List<Logement> listeLogement) {
        this.listeLogement = listeLogement;
    }
}
