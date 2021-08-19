package lu.ldhome.applicationweb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lu.ldhome.applicationweb.dao.UtilisateurDao;
import lu.ldhome.applicationweb.model.Role;
import lu.ldhome.applicationweb.model.Utilisateur;
import lu.ldhome.applicationweb.security.JwtUtil;
import lu.ldhome.applicationweb.security.UserDetailsCustom;
import lu.ldhome.applicationweb.security.UserDetailsServiceCustom;
import lu.ldhome.applicationweb.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UtilisateurController {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceCustom userDetailsServiceCustom;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private UtilisateurDao utilisateurDao;


    @Autowired
    UtilisateurController(UtilisateurDao utilisateurDao,
                          JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          UserDetailsServiceCustom userDetailsServiceCustom,
                          PasswordEncoder passwordEncoder) {

        this.utilisateurDao = utilisateurDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody Utilisateur utilisateur) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getMail(), utilisateur.getPassword()
                    )
            );
        }
        catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        UserDetailsCustom userDetails = this.userDetailsServiceCustom.loadUserByUsername(utilisateur.getMail());
        utilisateur = utilisateurDao.findByMail(utilisateur.getMail()).get();

        //on change le statut de l'utilisateur à "connecté" et on incrémente le nombre de connexion
        utilisateur.setConnecte(true);
        utilisateur.setNbConnexion(utilisateur.getNbConnexion()+1);

        utilisateurDao.saveAndFlush(utilisateur);

        String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwtToken);

    }

    @PostMapping("/agent/inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur) {

        Optional<Utilisateur> utilisateurDoublon = utilisateurDao.findByMail(utilisateur.getMail());

        if (utilisateurDoublon.isPresent()) {
            return ResponseEntity.badRequest().body("Cette adresse mail est déjà utilisée");
        } else {
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

            utilisateurDao.saveAndFlush(utilisateur);

            return ResponseEntity.ok(Long.toString(utilisateur.getId()));
        }
    }

    @GetMapping("/agent/utilisateursconnectes")
    public ResponseEntity<String> getUtilisateursConnectes() {

        //on récupère la liste d'utilisateurs connectés et on retourne la taille de la liste
        //pour récupérer le nombres d'utilisateurs connectés

        List<Utilisateur> utilisateursConnectes =
                utilisateurDao.findAllByConnecte(true);

        return ResponseEntity.ok(Integer.toString(utilisateursConnectes.size()));

    }

    @GetMapping("/utilisateur/roles")
    public ResponseEntity<List<Role>> getRoles(@RequestHeader(value = "Authorization") String authorization) {
        String token = authorization.substring(7);
        List<Role> listeRole = (List<Role>) jwtUtil.getTokenBody(token).get("roles");
        return ResponseEntity.ok(listeRole);
    }

    @JsonView(CustomJsonView.VueUtilisateur.class)
    @GetMapping("/utilisateur/utilisateur")
    public ResponseEntity<Utilisateur> getInformationUtilisateurConnecte(
            @RequestHeader(value = "Authorization") String authorization) {
        //la valeur du champs authorization est extrait de l'entête de la requête

        //On supprime la partie "Bearer " de la valeur de l'authorization
        String token = authorization.substring(7);

        //on extrait l'information souhaitée du token
        String mail = jwtUtil.getTokenBody(token).getSubject();

        Optional<Utilisateur> utilisateur = utilisateurDao.findByMail(mail);

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok().body(utilisateur.get());
        }

        return ResponseEntity.notFound().build();
    }

}
