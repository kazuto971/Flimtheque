package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService{

    private final FilmRepository filmRepository;

    // Injection du repository via le constructeur
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAllFilms();
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(FilmServiceImpl.class);

    @Override
    public Film findFilmById(long id) {
        LOGGER.info("Recherche du film ID : {}", id);
        Film film = filmRepository.findFilmById(id);

        if (film == null) {
            LOGGER.warn("Aucun film trouvé pour ID : {}", id);
        }

        return film;
    }

    @Override
    public Film saveFilm(Film film) {
        // Tu peux ajouter des validations ici si nécessaire
        return filmRepository.saveFilm(film);
    }
}
