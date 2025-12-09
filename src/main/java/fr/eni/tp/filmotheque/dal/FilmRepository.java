package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface FilmRepository {

    List<Film> findAllFilms();

    Film findFilmById(long id);

    Film saveFilm(Film film);

    List<Participant> findParticipantsByFilmId(long filmId);

}
