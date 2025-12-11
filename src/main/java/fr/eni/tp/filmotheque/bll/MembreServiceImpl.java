package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MembreServiceImpl implements MembreService {

    private final MembreRepository membreRepository;
    private final PasswordEncoder passwordEncoder;

    public MembreServiceImpl(MembreRepository membreRepository,
                             PasswordEncoder passwordEncoder) {
        this.membreRepository = membreRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void ajouterMembre(Membre membre) {
        String hashedPassword = passwordEncoder.encode(membre.getMotDePasse());
        membre.setMotDePasse(hashedPassword);

        membreRepository.ajouterMembre(membre);
    }

    @Override
    public Membre findByPseudo(String pseudo) {
        return membreRepository.findByPseudo(pseudo);
    }
}

