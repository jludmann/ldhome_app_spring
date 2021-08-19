package lu.ldhome.applicationweb.model;

import com.fasterxml.jackson.annotation.JsonView;
import lu.ldhome.applicationweb.view.CustomJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Piece implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CustomJsonView.VueLogement.class)
    private Long id;

    @JsonView(CustomJsonView.VueLogement.class)
    private String designation;

    @JsonView(CustomJsonView.VueLogement.class)
    private int surface;

    @JsonView(CustomJsonView.VueLogement.class)
    private String remarque;

    @ManyToOne
    private Logement logement;

    @OneToMany(mappedBy = "piece")
    private List<Photo> listePhoto = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }

    public List<Photo> getListePhoto() {
        return listePhoto;
    }

    public void setListePhoto(List<Photo> listePhoto) {
        this.listePhoto = listePhoto;
    }
}
