package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {

    private final JdbcTemplate jdbcTemplate;

    public FilmRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> findAllFilms() {

        String sql = """
            SELECT
                f.id AS film_id,
                f.titre AS film_titre,
                f.annee,
                f.duree,
                f.synopsis,
                
                g.id AS genre_id,
                g.titre AS genre_titre,

                r.id AS realisateur_id,
                r.prenom AS realisateur_prenom,
                r.nom AS realisateur_nom

            FROM films f
            JOIN genres g ON f.genreId = g.id
            JOIN participants r ON f.realisateurId = r.id
            """;

        return jdbcTemplate.query(sql, new FilmRowMapper());
    }


    @Override
    public Film findFilmById(long id) {

        String sql = """
            SELECT
                f.id AS film_id,
                f.titre AS film_titre,
                f.annee,
                f.duree,
                f.synopsis,
                
                g.id AS genre_id,
                g.titre AS genre_titre,

                r.id AS realisateur_id,
                r.prenom AS realisateur_prenom,
                r.nom AS realisateur_nom

            FROM films f
            JOIN genres g ON f.genreId = g.id
            JOIN participants r ON f.realisateurId = r.id
            WHERE f.id = ?
            """;

        List<Film> films = jdbcTemplate.query(sql, new FilmRowMapper(), id);

        return films.isEmpty() ? null : films.get(0);
    }


    @Override
    public Film saveFilm(Film film) {
        return null;
    }


    class FilmRowMapper implements RowMapper<Film> {

        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film film = new Film();
            film.setId(rs.getLong("film_id"));
            film.setTitre(rs.getString("film_titre"));
            film.setAnnee(rs.getInt("annee"));
            film.setDuree(rs.getInt("duree"));
            film.setSynopsis(rs.getString("synopsis"));

            Genre genre = new Genre();
            genre.setId(rs.getInt("genre_id"));
            genre.setTitre(rs.getString("genre_titre"));
            film.setGenre(genre);

            Participant realisateur = new Participant();
            realisateur.setId(rs.getInt("realisateur_id"));
            realisateur.setPrenom(rs.getString("realisateur_prenom"));
            realisateur.setNom(rs.getString("realisateur_nom"));
            film.setRealisateur(realisateur);

            return film;
        }
    }
}
