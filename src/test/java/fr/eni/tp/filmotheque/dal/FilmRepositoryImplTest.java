package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
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

    

}
