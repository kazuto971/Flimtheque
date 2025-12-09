package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ParticipantRepositoryImpl implements ParticipantRipository {

    private final JdbcTemplate jdbcTemplate;

    public ParticipantRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Participant> findAllParticipants() {
        String sql = "SELECT id, prenom, nom FROM participants ORDER BY nom";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Participant(
                        rs.getLong("id"),
                        rs.getString("prenom"),
                        rs.getString("nom")
                ));
    }

    @Override
    public Participant findParticipantById(long id) {
        String sql = "SELECT id, prenom, nom FROM participants WHERE id = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Participant(
                        rs.getLong("id"),
                        rs.getString("prenom"),
                        rs.getString("nom")
                ),
                id);
    }

}
