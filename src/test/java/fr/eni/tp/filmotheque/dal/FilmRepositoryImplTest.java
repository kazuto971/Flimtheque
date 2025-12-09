package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilmRepositoryImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FilmRepositoryImpl repository;
    @Autowired
    private FilmRepositoryImpl filmRepositoryImpl;


    @Test
    void testFindAllFilms() {
        List<Film> films = repository.findAllFilms();

        assertEquals(4, films.size());
        assertEquals("Jurassic Park", films.get(0).getTitre());
        assertEquals("The Fly", films.get(1).getTitre());
    }

    @Test
    void testFindFilmById_existingId() {
        Film film = repository.findFilmById(4);

        assertNotNull(film);
        assertEquals("Bienvenue chez les Ch'tis", film.getTitre());
        assertEquals(2008, film.getAnnee());
        assertEquals(106, film.getDuree());
    }

    @Test
    void testSaveFilm() {

        Genre genreAction = new Genre(4, "Action");
        Participant spielberg = new Participant(1, "Steven", "Spielberg");

        // Constructeur complet qui appelle this() pour initialiser les listes
        Film film = new Film(
                "Ready Player One",
                2018,
                140,
                "Dans un futur proche, un adolescent doit résoudre des énigmes dans un monde virtuel pour hériter d'une fortune.",
                genreAction,
                spielberg
        );

        // Ajout d’acteurs
        film.getActeurs().add(new Participant(5, "Jeff", "Goldblum"));

        Film filmEnregistre = filmRepositoryImpl.saveFilm(film);

        assertNotNull(filmEnregistre);
        assertEquals("Ready Player One", filmEnregistre.getTitre());

    }

}
