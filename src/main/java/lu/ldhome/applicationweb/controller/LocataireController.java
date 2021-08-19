package lu.ldhome.applicationweb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lu.ldhome.applicationweb.dao.LocataireDao;
import lu.ldhome.applicationweb.dao.LogementDao;
import lu.ldhome.applicationweb.dao.ProprietaireDao;
import lu.ldhome.applicationweb.dao.UtilisateurDao;
import lu.ldhome.applicationweb.model.Locataire;
import lu.ldhome.applicationweb.model.Logement;
import lu.ldhome.applicationweb.model.Role;
import lu.ldhome.applicationweb.model.Utilisateur;
import lu.ldhome.applicationweb.security.JwtUtil;
import lu.ldhome.applicationweb.security.UserDetailsCustom;
import lu.ldhome.applicationweb.security.UserDetailsServiceCustom;
import lu.ldhome.applicationweb.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class LocataireController {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceCustom userDetailsServiceCustom;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private UtilisateurDao utilisateurDao;
    private LocataireDao locataireDao;
    private LogementDao logementDao;
    private ProprietaireDao proprietaireDao;


    @Autowired
    LocataireController(UtilisateurDao utilisateurDao,
                        LocataireDao locataireDao,
                        ProprietaireDao proprietaireDao,
                        LogementDao logementDao,
                        JwtUtil jwtUtil,
                        AuthenticationManager authenticationManager,
                        UserDetailsServiceCustom userDetailsServiceCustom,
                        PasswordEncoder passwordEncoder) {

        this.utilisateurDao = utilisateurDao;
        this.locataireDao = locataireDao;
        this.logementDao = logementDao;
        this.proprietaireDao = proprietaireDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/agent/locataire/{id}")
    @JsonView(CustomJsonView.VueLocataire.class)
    public ResponseEntity<Locataire> getLocataire(@PathVariable Long id) {
        Optional<Locataire> locataire = locataireDao.findById(id);

        if (locataire.isPresent()) {
            Logement logement = logementDao.findByLocataire(locataire.get()).get();
            logement.setSommeCharges(logement.sommeCharge(logement.getListeCharge()));
            logement.setTotal(logement.getLoyer()+logement.getSommeCharges());
            return ResponseEntity.ok(locataire.get());
        }

        else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("locataire/locataire")
    @JsonView(CustomJsonView.VueLocataire.class)
    public ResponseEntity<Locataire> getLocataire(@RequestHeader(value = "Authorization") String authorization) {

        String token = authorization.substring(7);

        String mail = jwtUtil.getTokenBody(token).getSubject();

        Logement logement = logementDao.findByLocataire(locataireDao.findByMail(mail).get()).get();
        logement.setSommeCharges(logement.sommeCharge(logement.getListeCharge()));
        logement.setTotal(logement.getLoyer()+logement.getSommeCharges());

        return ResponseEntity.ok(locataireDao.findByMail(mail).get());

    }

    @GetMapping("/proprietaire/locataire/{id}")
    @JsonView(CustomJsonView.VueLocataire.class)
    public ResponseEntity<Locataire> getLocataireFromLogement(@PathVariable Long id) {
        Optional<Locataire> locataire = locataireDao.findById(id);

        if (locataire.isPresent()) {
            Logement logement = logementDao.findByLocataire(locataire.get()).get();
            logement.setSommeCharges(logement.sommeCharge(logement.getListeCharge()));
            logement.setTotal(logement.getLoyer()+logement.getSommeCharges());
            return ResponseEntity.ok(locataire.get());
        }

        else {
            return ResponseEntity.noContent().build();
        }

    }

}
