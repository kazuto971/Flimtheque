package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MembreRepositoryImpl implements MembreRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Membre> mapper = new MembreDtoRowMapper();

    public MembreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Membre findByPseudo(String pseudo) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Membres WHERE pseudo = ?",
                mapper,
                pseudo
        );
    }

    @Override
    public void ajouterMembre(Membre membre) {
        jdbcTemplate.update(
                "INSERT INTO Membres(prenom, nom, pseudo, motDePasse, admin) VALUES (?, ?, ?, ?, ?)",
                membre.getPrenom(),
                membre.getNom(),
                membre.getPseudo(),
                membre.getMotDePasse(),
                membre.isAdmin()
        );
    }

    private static class MembreDtoRowMapper implements RowMapper<Membre> {

        @Override
        public Membre mapRow(ResultSet rs, int rowNum) throws SQLException {

            Membre membre = new Membre();

            membre.setId(rs.getInt("id"));
            membre.setPrenom(rs.getString("prenom"));
            membre.setNom(rs.getString("nom"));
            membre.setPseudo(rs.getString("pseudo"));
            membre.setMotDePasse(rs.getString("motDePasse"));
            membre.setAdmin(rs.getBoolean("admin"));

            return membre;
        }
    }
}
