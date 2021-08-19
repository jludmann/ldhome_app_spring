package lu.ldhome.applicationweb.model;

import com.fasterxml.jackson.annotation.JsonView;
import lu.ldhome.applicationweb.view.CustomJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Charges implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CustomJsonView.VueLogement.class)
    private Long id;

    @JsonView(CustomJsonView.VueLogement.class)
    private String designation;

    @JsonView(CustomJsonView.VueLogement.class)
    private double valeur;

    @ManyToOne
    private Logement logement;

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

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }
}
