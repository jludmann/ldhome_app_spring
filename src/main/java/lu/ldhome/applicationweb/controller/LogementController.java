package lu.ldhome.applicationweb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lu.ldhome.applicationweb.dao.LocataireDao;
import lu.ldhome.applicationweb.dao.LogementDao;
import lu.ldhome.applicationweb.dao.ProprietaireDao;
import lu.ldhome.applicationweb.model.Locataire;
import lu.ldhome.applicationweb.model.Logement;
import lu.ldhome.applicationweb.model.Proprietaire;
import lu.ldhome.applicationweb.security.JwtUtil;
import lu.ldhome.applicationweb.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class LogementController {

    private LogementDao logementDao;
    private ProprietaireDao proprietaireDao;
    private LocataireDao locataireDao;
    private JwtUtil jwtUtil;

    @Autowired
    public void LogementController(
            LogementDao logementDao,
            JwtUtil jwtUtil,
            ProprietaireDao proprietaireDao,
            LocataireDao locataireDao
    ) {
        this.logementDao = logementDao;
        this.jwtUtil = jwtUtil;
        this.proprietaireDao = proprietaireDao;
        this.locataireDao = locataireDao;

    }

    @JsonView(CustomJsonView.VueLogement.class)
    @GetMapping("/proprietaire/logements")
    public ResponseEntity<List<Logement>> getAllLogement(@RequestHeader(value = "Authorization") String authorization) {

        //on récupère le token de l'utilisateur connecté
        String token = authorization.substring(7);

        //on récupère l'id de l'utilisateur connecté depuis le token
        Long idUtilisateur = jwtUtil.getTokenBody(token).get("id", Long.class);

        //on recherche le propriétaire correspondant à l'id récupéré
        Proprietaire connectedUser = proprietaireDao.findById(idUtilisateur).get();

        List<Logement> logements = logementDao.findAllByProprietaire(connectedUser).get();

        for (Logement logement: logements) {
            logement.setSommeCharges(logement.sommeCharge(logement.getListeCharge()));
            logement.setTotal(logement.getLoyer()+logement.getSommeCharges());
        }

        return ResponseEntity.ok(logements);

    }

    @PostMapping("/agent/logement")
    public ResponseEntity<String> addLogement(@RequestBody Logement logement) {

        if (logement.getId() == null) {
            logementDao.saveAndFlush(logement);

            return ResponseEntity.created(URI.create("/user/logement/"+logement.getId())).build();
        }

        else {
            Optional<Logement> logementBdd = logementDao.findById(logement.getId());

            if (logementBdd.isPresent()) {
                return ResponseEntity.badRequest().body("Le logement que vous souhaitez ajouter existe déjà !");
            }

            else {
                logementDao.saveAndFlush(logementBdd.get());

                return ResponseEntity.created(URI.create("/user/logement/"+logement.getId())).build();
            }

        }

    }

    @GetMapping("user/logement/{id}")
    @JsonView(CustomJsonView.VueLogement.class)
    public ResponseEntity<Logement> getLogement(@PathVariable Long id) {

        Optional<Logement> logement = logementDao.findById(id);

        if (logement.isPresent()) {
            logement.get().setSommeCharges(logement.get().sommeCharge(logement.get().getListeCharge()));
            logement.get().setTotal(logement.get().getLoyer() + logement.get().getSommeCharges());
            return ResponseEntity.ok(logement.get());
        }

        else {
            return ResponseEntity.noContent().build();
        }

    }

    @JsonView(CustomJsonView.VueLogement.class)
    @GetMapping("/agent/logements")
    public ResponseEntity<List<Logement>> getAllLogement() {
        List<Logement> logements = logementDao.findAll();

        for (Logement logement: logements) {
            logement.setSommeCharges(logement.sommeCharge(logement.getListeCharge()));
            logement.setTotal(logement.getLoyer()+logement.getSommeCharges());
        }

        return ResponseEntity.ok(logements);

    }

    @JsonView(CustomJsonView.VueLogement.class)
    @GetMapping("/locataire/logements")
    public ResponseEntity<List<Logement>> getLocataion(@RequestHeader(value = "Authorization") String authorization) {

        //on récupère le token de l'utilisateur connecté
        String token = authorization.substring(7);

        //on récupère l'id de l'utilisateur connecté depuis le token
        Long idUtilisateur = jwtUtil.getTokenBody(token).get("id", Long.class);

        //on recherche le locataire correspondant à l'id récupéré
        Locataire connectedUser = locataireDao.findById(idUtilisateur).get();

        List<Logement> logements = logementDao.findAllByLocataire(connectedUser).get();

        for (Logement logement: logements) {
            logement.setSommeCharges(logement.sommeCharge(logement.getListeCharge()));
            logement.setTotal(logement.getLoyer()+logement.getSommeCharges());
        }

        return ResponseEntity.ok(logements);
    }

    @GetMapping("proprietaire/logementsloues")
    @JsonView(CustomJsonView.VueLogement.class)
    public ResponseEntity<List<Logement>> getLogementsLoues(@RequestHeader(value = "Authorization") String authorization) {

        //on récupère le token de l'utilisateur connecté
        String token = authorization.substring(7);

        //on récupère l'id de l'utilisateur connecté depuis le token
        Long idUtilisateur = jwtUtil.getTokenBody(token).get("id", Long.class);

        Proprietaire proprietaire = proprietaireDao.getById(idUtilisateur);

        List<Logement> logementsLoues = logementDao.findByProprietaireAndLocataireNotNull(proprietaire).get();

        return ResponseEntity.ok(logementsLoues);

    }

}
