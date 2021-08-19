package lu.ldhome.applicationweb.security;

import lu.ldhome.applicationweb.dao.UtilisateurDao;
import lu.ldhome.applicationweb.model.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    private final UtilisateurDao utilisateurDao;

    public UserDetailsServiceCustom(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetailsCustom loadUserByUsername(String mailSaisi) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDao
                .findByMailWithRoles(mailSaisi)
                .orElseThrow(() -> new UsernameNotFoundException(mailSaisi + " inconnu"));

        return new UserDetailsCustom(utilisateur);
    }
}
