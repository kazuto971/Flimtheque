package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> findAllGenres();

    Genre findGenreById(long id);

    Genre saveGenre(Genre genre);

    void modifyGenre(long id, String newTitre);

    void deleteGenre(long id);


}
