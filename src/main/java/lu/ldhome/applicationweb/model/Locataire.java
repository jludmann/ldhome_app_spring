package lu.ldhome.applicationweb.model;

import com.fasterxml.jackson.annotation.JsonView;
import lu.ldhome.applicationweb.view.CustomJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Locataire extends Utilisateur implements Serializable {

    @JsonView(CustomJsonView.VueLocataire.class)
    private Date dateEntree;
    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private Date dateSortie;
    private boolean paiementAJour;
    private Date datePaiementLoyer;
    private boolean clesRemises;
    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private int nbPersonnes;

    @ManyToOne
    @JsonView(CustomJsonView.VueLocataire.class)
    private Logement logement;

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public boolean isPaiementAJour() {
        return paiementAJour;
    }

    public void setPaiementAJour(boolean paiementAJour) {
        this.paiementAJour = paiementAJour;
    }

    public Date getDatePaiementLoyer() {
        return datePaiementLoyer;
    }

    public void setDatePaiementLoyer(Date datePaiementLoyer) {
        this.datePaiementLoyer = datePaiementLoyer;
    }

    public boolean isClesRemises() {
        return clesRemises;
    }

    public void setClesRemises(boolean clesRemises) {
        this.clesRemises = clesRemises;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }
}
