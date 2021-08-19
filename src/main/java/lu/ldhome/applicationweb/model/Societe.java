package lu.ldhome.applicationweb.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Societe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;
    private Date dateIntervention;
    private String fonction;

    @OneToMany(mappedBy = "societeMenage")
    private List<Logement> listeLogement = new ArrayList<>();

    @OneToMany(mappedBy = "societe")
    private List<Sinistre> listeSinistre = new ArrayList<>();

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

    public List<Logement> getListeLogement() {
        return listeLogement;
    }

    public void setListeLogement(List<Logement> listeLogement) {
        this.listeLogement = listeLogement;
    }

    public Date getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(Date dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public List<Sinistre> getListeSinistre() {
        return listeSinistre;
    }

    public void setListeSinistre(List<Sinistre> listeSinistre) {
        this.listeSinistre = listeSinistre;
    }
}
