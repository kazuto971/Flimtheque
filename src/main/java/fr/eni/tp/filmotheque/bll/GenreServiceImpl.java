package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import fr.eni.tp.filmotheque.exeception.GenreNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAllGenres();
    }

    @Override
    public Genre findGenreById(long id) throws GenreNotFoundException {
        Genre genre = genreRepository.findGenreById(id);
        if (genre == null) {
            throw new GenreNotFoundException("Genre avec id " + id + " non trouvé");
        }
        return genre;
    }


    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.saveGenre(genre);
    }
}