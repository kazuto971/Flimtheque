package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.exeception.GenreNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenreRepositoryJdbcImpl implements GenreRepository {

    private final JdbcTemplate jdbcTemplate;

    public GenreRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAllGenres() {
        String sql = "SELECT id, titre FROM genres";
        return jdbcTemplate.query(sql, new GenreRowMapper());
    }


    @Override
    public Genre findGenreById(long id) {
        String sql = "SELECT id, titre FROM genres WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new GenreRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundException("Genre " + id + " introuvable");
        }
    }


    @Override
    public Genre saveGenre(Genre genre) {
        String sql = "INSERT INTO genres (id, titre) VALUES (?, ?)";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, genre.getId());
                ps.setString(2, genre.getTitre());
            }
        };

        jdbcTemplate.update(sql, pss);

        return genre;
    }



    @Override
    public void modifyGenre(long id, String newTitre) {
        String sql = "UPDATE genres SET titre = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, newTitre, id);

        if (rows == 0) {
            throw new GenreNotFoundException("Genre " + id + " introuvable");
        }
    }

    @Override
    public void deleteGenre(long id) {
        String sql = "delete from genres where id = ?";
        jdbcTemplate.update(sql, id);
    }

    class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre();
            genre.setId(rs.getLong("id"));
            genre.setTitre(rs.getString("titre"));
            return genre;
        }
    }
}


