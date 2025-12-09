package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.exeception.GenreNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> findAllGenres();

    Genre findGenreById(long id) throws GenreNotFoundException;

    Genre saveGenre(Genre genre);

}
