package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        SELECT f.id AS film_id, f.titre AS film_titre, f.annee, f.duree, f.synopsis,
               g.id AS genre_id, g.titre AS genre_titre,
               r.id AS realisateur_id, r.prenom AS realisateur_prenom, r.nom AS realisateur_nom
        FROM films f
        JOIN genres g ON f.genreId = g.id
        JOIN participants r ON f.realisateurId = r.id
    """;

        List<Film> films = jdbcTemplate.query(sql, new FilmRowMapper());

        // Pour chaque film, on récupère les acteurs
        for (Film film : films) {
            List<Participant> acteurs = findParticipantsByFilmId(film.getId());
            film.setActeurs(acteurs);
        }

        return films;
    }


    @Override
    public Film findFilmById(long id) {
        String sql = """
        SELECT f.id AS film_id, f.titre AS film_titre, f.annee, f.duree, f.synopsis,
               g.id AS genre_id, g.titre AS genre_titre,
               r.id AS realisateur_id, r.prenom AS realisateur_prenom, r.nom AS realisateur_nom
        FROM films f
        JOIN genres g ON f.genreId = g.id
        JOIN participants r ON f.realisateurId = r.id
        WHERE f.id = ?
    """;

        List<Film> films = jdbcTemplate.query(sql, new FilmRowMapper(), id);

        if (films.isEmpty()) {
            return null;
        }

        Film film = films.get(0);

        // Récupérer les acteurs séparément
        List<Participant> acteurs = findParticipantsByFilmId(film.getId());
        film.setActeurs(acteurs);

        return film;
    }


    @Override
    public List<Participant> findParticipantsByFilmId(long filmId) {
        String sql = """
        SELECT p.id AS acteur_id, p.prenom AS acteur_prenom, p.nom AS acteur_nom
        FROM participants p
        JOIN acteurs a ON p.id = a.participantId
        WHERE a.filmId = ?
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Participant acteur = new Participant();
            acteur.setId(rs.getInt("acteur_id"));
            acteur.setPrenom(rs.getString("acteur_prenom"));
            acteur.setNom(rs.getString("acteur_nom"));
            return acteur;
        }, filmId);
    }




    @Override
    public Film saveFilm(Film film) {
        String sql = "INSERT INTO films (titre, annee, duree, synopsis, genreId, realisateurId) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, film.getTitre());
            ps.setInt(2, film.getAnnee());
            ps.setInt(3, film.getDuree());
            ps.setString(4, film.getSynopsis());
            ps.setLong(5, film.getGenre().getId());
            ps.setLong(6, film.getRealisateur().getId());
            return ps;
        }, keyHolder);

        film.setId(keyHolder.getKey().longValue());

        // Sauvegarde des acteurs si besoin
        if (film.getActeurs() != null) {
            for (Participant acteur : film.getActeurs()) {
                jdbcTemplate.update(
                        "INSERT INTO acteurs (filmId, participantId) VALUES (?, ?)",
                        film.getId(), acteur.getId()
                );
            }
        }

        return film;
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
