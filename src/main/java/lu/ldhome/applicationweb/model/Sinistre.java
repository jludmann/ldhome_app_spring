package lu.ldhome.applicationweb.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Sinistre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String niveauIntervention;
    private String titre;
    private String description;
    private String intervenant;
    private String note;
    private String categorie;
    private String etat;
    private Timestamp rdv;
    private Date dateDeclaration;

    @ManyToOne
    private Societe societe;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Logement logement;

    @OneToMany(mappedBy = "sinistre")
    private List<Photo> listePhoto = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNiveauIntervention() {
        return niveauIntervention;
    }

    public void setNiveauIntervention(String niveauIntervention) {
        this.niveauIntervention = niveauIntervention;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Timestamp getRdv() {
        return rdv;
    }

    public void setRdv(Timestamp rdv) {
        this.rdv = rdv;
    }

    public Date getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
}
