package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.FilmothequeApplication;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.exeception.GenreNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GenreRepositoryImplTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("test findAllGenres cas plusieurs genres existent ")
    void testFindAllGenres() {

        //AAA
        //Arrange: preparation du test


        //Act: appel de la method a tester
        List<Genre> genres = genreRepository.findAllGenres();

        //Assert: verification du resultat du Act
        assertNotNull(genres);
        assertEquals(genres.size(), 6);
    }

    @Test
    @DisplayName("test findGenreById cas l'id existe existent ")
    void testFindGenreById_ok() {
        //AAA
        //Arrange: preparation du test
        long id = 1;
        //Act: appel de la method a tester
        Genre genre = genreRepository.findGenreById(id);

        //Assert: verification du resultat du Act
        assertNotNull(genre);
        assertEquals(id, genre.getId());
        assertEquals("Animation",genre.getTitre());
    }

    @Test
    @DisplayName("test D'ajout genre cas droit")
    void testSaveGenre() {
        //AAA
        //Arrange: preparation du test
        Genre genre = new Genre(7,"Fantaisie");
        //Act: appel de la method a tester
        Genre newGenre = genreRepository.saveGenre(genre);
        //Assert: verification du resultat du Act
        assertNotNull(genre);
        Genre gen = genreRepository.findGenreById(newGenre.getId());
        assertEquals(genre, gen);

    }

    @Test
    @DisplayName("test modifier un Genre cas droit")
    void testModifyGenre() {
        //AAA
        //Arrange: preparation du test
        long id = 1;
        String newTitre = "Horreur";
        //Act: appel de la method a tester
        genreRepository.modifyGenre(id,newTitre);
        //Assert: verification du resultat du Act
        assertNotNull(genreRepository.findGenreById(id));
        assertEquals(newTitre, genreRepository.findGenreById(id).getTitre());
    }

    @Test
    @DisplayName("test supprimer un Genre cas droit")
    void testdeleteGenre() {
        //AAA
        //Arrange: preparation du test
        Genre genre = new Genre(7,"Fantaisie");
        genreRepository.saveGenre(genre);
        //Act: appel de la method a tester
        genreRepository.deleteGenre(genre.getId());
        //Assert: verification du resultat du Act
        assertThrows(GenreNotFoundException.class, () -> genreRepository.findGenreById(genre.getId()));
    }


}
