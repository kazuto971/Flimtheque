package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import fr.eni.tp.filmotheque.exeception.GenreNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllGenres() {
        // Données simulées
        List<Genre> mockGenres = Arrays.asList(
                new Genre(1L, "Action"),
                new Genre(2L, "Comédie")
        );
        when(genreRepository.findAllGenres()).thenReturn(mockGenres);

        // Appel du service
        List<Genre> result = genreService.findAllGenres();

        // Vérifications
        assertEquals(2, result.size());
        assertEquals("Action", result.get(0).getTitre());
        verify(genreRepository, times(1)).findAllGenres();
    }

    @Test
    void testFindGenreById_ExistingId() throws GenreNotFoundException {
        Genre genre = new Genre(1L, "Action");
        when(genreRepository.findGenreById(1L)).thenReturn(genre);

        Genre result = genreService.findGenreById(1L);

        assertNotNull(result);
        assertEquals("Action", result.getTitre());
        verify(genreRepository, times(1)).findGenreById(1L);
    }


    @Test
    void testSaveGenre() {
        Genre genre = new Genre(3L, "Drame");
        when(genreRepository.saveGenre(genre)).thenReturn(genre);

        Genre result = genreService.saveGenre(genre);

        assertNotNull(result);
        assertEquals("Drame", result.getTitre());
        verify(genreRepository, times(1)).saveGenre(genre);
    }
}
