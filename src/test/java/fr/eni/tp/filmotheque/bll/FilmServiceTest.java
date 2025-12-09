package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilmServiceTest {

    @Autowired
    private FilmService filmService;

    @Test
    void testFindAllFilms() {
        List<Film> films = filmService.findAllFilms();
        assertNotNull(films, "La liste des films ne doit pas être nulle");
        assertFalse(films.isEmpty(), "La liste des films ne doit pas être vide");

        // Optionnel : vérifier le premier film si tu connais la base
        Film premierFilm = films.get(0);
        assertNotNull(premierFilm.getTitre());
    }

    @Test
    void testFindFilmById_existingId() {
        // Id connu dans la base de test
        long idFilm = 1;
        Film film = filmService.findFilmById(idFilm);

        assertNotNull(film, "Le film doit exister pour l'id " + idFilm);
        assertEquals(idFilm, film.getId(), "L'ID du film doit correspondre");
    }

    @Test
    void testSaveFilm() {
        Genre genreAction = new Genre(4, "Action");
        Participant realisateur = new Participant(1, "Steven", "Spielberg");

        Film film = new Film(
                "Test Film BLL",
                2025,
                120,
                "Synopsis pour le test BLL",
                genreAction,
                realisateur
        );

        // Initialisation liste acteurs
        film.setActeurs(new java.util.ArrayList<>());
        film.getActeurs().add(new Participant(5, "Jeff", "Goldblum"));

        Film filmEnregistre = filmService.saveFilm(film);

        assertNotNull(filmEnregistre, "Le film enregistré ne doit pas être nul");
        assertNotNull(filmEnregistre.getId(), "L'ID du film enregistré doit être généré");
        assertEquals("Test Film BLL", filmEnregistre.getTitre());
        assertEquals(1, filmEnregistre.getActeurs().size(), "Le film doit avoir un acteur");
    }
}
