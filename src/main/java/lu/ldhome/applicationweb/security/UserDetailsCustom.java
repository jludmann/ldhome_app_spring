package lu.ldhome.applicationweb.security;

import lu.ldhome.applicationweb.model.Role;
import lu.ldhome.applicationweb.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsCustom implements UserDetails {

    private Long id;
    private String mail;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public UserDetailsCustom(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.mail = utilisateur.getMail();
        this.password = utilisateur.getPassword();
        this.active = true;

        authorities = new ArrayList<>();

        for(Role role : utilisateur.getListeRole()){
            authorities.add(new SimpleGrantedAuthority(role.getDesignation()));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
