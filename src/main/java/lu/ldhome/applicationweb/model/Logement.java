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
public class Logement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private Long id;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private String typeLogement;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private String typeLocation;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private boolean occupe;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private int surface;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private int etage;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private int nbPieces;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private String adresse;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private int codePostal;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private String ville;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private String quartier;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    private double loyer;

    @JsonView(CustomJsonView.VueLogement.class)
    @ManyToOne
    private Locataire locataire;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    @ManyToOne
    private Proprietaire proprietaire;

    @JsonView(CustomJsonView.VueLogement.class)
    @ManyToOne
    private Societe societeMenage;

    @OneToMany(mappedBy = "logement")
    @JsonView(CustomJsonView.VueLogement.class)
    private List<Piece> listePiece = new ArrayList<>();

    @OneToMany(mappedBy = "logement")
    @JsonView(CustomJsonView.VueLogement.class)
    private List<Charges> listeCharge = new ArrayList<>();

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    @Transient
    private double sommeCharges;

    @JsonView({CustomJsonView.VueLogement.class, CustomJsonView.VueLocataire.class})
    @Transient
    private double total;

    public double sommeCharge(List<Charges> listeCharge) {
        double somme = 0;
        for (Charges charges : listeCharge) {
            somme += charges.getValeur();
        }
        return somme;
    }

    public double getSommeCharges() {
        return sommeCharges;
    }

    public void setSommeCharges(double sommeCharges) {
        this.sommeCharges = sommeCharges;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeLogement() {
        return typeLogement;
    }

    public void setTypeLogement(String typeLogement) {
        this.typeLogement = typeLogement;
    }

    public String getTypeLocation() {
        return typeLocation;
    }

    public void setTypeLocation(String typeLocation) {
        this.typeLocation = typeLocation;
    }

    public boolean isOccupe() {
        return occupe;
    }

    public void setOccupe(boolean occupe) {
        this.occupe = occupe;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getNbPieces() {
        return nbPieces;
    }

    public void setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
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

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public double getLoyer() {
        return loyer;
    }

    public void setLoyer(double loyer) {
        this.loyer = loyer;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Societe getSocieteMenage() {
        return societeMenage;
    }

    public void setSocieteMenage(Societe societeMenage) {
        this.societeMenage = societeMenage;
    }

    public List<Piece> getListePiece() {
        return listePiece;
    }

    public void setListePiece(List<Piece> listePiece) {
        this.listePiece = listePiece;
    }

    public List<Charges> getListeCharge() {
        return listeCharge;
    }

    public void setListeCharge(List<Charges> listeCharge) {
        this.listeCharge = listeCharge;
    }

}
