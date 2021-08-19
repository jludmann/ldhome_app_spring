package lu.ldhome.applicationweb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lu.ldhome.applicationweb.dao.*;
import lu.ldhome.applicationweb.dao.ProprietaireDao;
import lu.ldhome.applicationweb.model.Proprietaire;
import lu.ldhome.applicationweb.model.Logement;
import lu.ldhome.applicationweb.security.JwtUtil;
import lu.ldhome.applicationweb.security.UserDetailsServiceCustom;
import lu.ldhome.applicationweb.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class ProprietaireController {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceCustom userDetailsServiceCustom;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private ProprietaireDao proprietaireDao;
    private LogementDao logementDao;


    @Autowired
    ProprietaireController(ProprietaireDao proprietaireDao,
                           LogementDao logementDao,
                           JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager,
                           UserDetailsServiceCustom userDetailsServiceCustom,
                           PasswordEncoder passwordEncoder) {

        this.proprietaireDao = proprietaireDao;
        this.logementDao = logementDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/agent/proprietaire/{id}")
    @JsonView(CustomJsonView.VueProprietaire.class)
    public ResponseEntity<Proprietaire> getProprietaire(@PathVariable Long id) {
        Optional<Proprietaire> proprietaire = proprietaireDao.findById(id);

        if (proprietaire.isPresent()) {
            return ResponseEntity.ok(proprietaire.get());
        }

        else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("proprietaire/proprietaire")
    @JsonView(CustomJsonView.VueProprietaire.class)
    public ResponseEntity<Proprietaire> getProprietaire(@RequestHeader(value = "Authorization") String authorization) {

        String token = authorization.substring(7);

        String mail = jwtUtil.getTokenBody(token).getSubject();

        return ResponseEntity.ok(proprietaireDao.findByMail(mail).get());

    }

}
