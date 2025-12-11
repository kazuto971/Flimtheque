package fr.eni.tp.filmotheque.security;

import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FilmothequeUserDetailsService implements UserDetailsService {

    @Autowired
    private MembreService membreService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Membre membre = membreService.findByPseudo(username);

        if (membre == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable : " + username);
        }


        String role = membre.isAdmin() ? "ADMIN" : "USER";

        return User.withUsername(membre.getPseudo())
                .password(membre.getMotDePasse())
                .roles(role)
                .build();
    }


}
