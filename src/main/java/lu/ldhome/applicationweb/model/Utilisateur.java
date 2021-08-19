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
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private Long id;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String prenom;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String nom;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String profession;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String sexe;

    @Column(unique = true)
    @JsonView({CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String mail;

    private String password;

    @JsonView({CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String telephone;

    @JsonView({CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String adresse;

    @JsonView({CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private int codePostal;

    @JsonView({CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String ville;

    @JsonView({CustomJsonView.VueLocataire.class, CustomJsonView.VueProprietaire.class})
    private String pays;
    private boolean connecte = false;
    private int nbConnexion;
    private boolean avisDonne;

    @ManyToMany
    @JoinTable(
            name = "role_utilisateur",
            joinColumns = @JoinColumn(name = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private List<Role> listeRole = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<Sinistre> listeSinistre = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public boolean isConnecte() {
        return connecte;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }

    public int getNbConnexion() {
        return nbConnexion;
    }

    public void setNbConnexion(int nbConnexion) {
        this.nbConnexion = nbConnexion;
    }

    public boolean isAvisDonne() {
        return avisDonne;
    }

    public void setAvisDonne(boolean avisDonne) {
        this.avisDonne = avisDonne;
    }

    public List<Role> getListeRole() {
        return listeRole;
    }

    public void setListeRole(List<Role> listeRole) {
        this.listeRole = listeRole;
    }

    public List<Sinistre> getListeSinistre() {
        return listeSinistre;
    }

    public void setListeSinistre(List<Sinistre> listeSinistre) {
        this.listeSinistre = listeSinistre;
    }
}
