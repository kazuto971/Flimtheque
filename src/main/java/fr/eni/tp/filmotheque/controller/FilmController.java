package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.controller.dto.FilmDto;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes({"genresEnSession"})
@Controller
public class FilmController {

    private final FilmService filmService;
    private final GenreService genreService;

    public FilmController(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }

    @GetMapping("/films/detail")
    public String afficherUnFilm(@RequestParam(name = "id") long identifiant, Model model) {
        Film film = this.filmService.findFilmById(identifiant);
        model.addAttribute("film", film);
        return "view-film-detail";
    }

    @GetMapping("/films")
    public String afficherFilms(Model model) {
        List<Film> films = this.filmService.findAllFilms();
        model.addAttribute("films", films);
        return "view-films";
    }

    /*@GetMapping("/films/creer")
    public String afficherFormulaire(Model model) {
        model.addAttribute("film", new FilmDto());
        model.addAttribute("genres", genreService.findAllGenres());
        model.addAttribute("participants", filmService.consulterParticipants());
        return "view-film-form";
    }

    @PostMapping("/films/creer")
    public String creerFilm(
            @Valid @ModelAttribute("film") FilmDto filmDto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("genres", genreService.findAllGenres());
            model.addAttribute("participants", filmService.consulterParticipants());
            return "view-film-form";
        }

        Film film = new Film();
        film.setTitre(filmDto.getTitre());
        film.setAnnee(filmDto.getAnnee());
        film.setDuree(filmDto.getDuree());
        film.setSynopsis(filmDto.getSynopsis());
        film.setGenre(genreService.findGenreById(filmDto.getGenreId()));

        Participant realisateur = filmService.consulterParticipantParId(filmDto.getRealisateurId());
        film.setRealisateur(realisateur);

        if (film.getActeurs() == null) {
            film.setActeurs(new java.util.ArrayList<>());
        }
        for (Long id : filmDto.getActeurIds()) {
            Participant acteur = filmService.consulterParticipantParId(id);
            if (acteur != null) {
                film.getActeurs().add(acteur);
            }
        }

        filmService.saveFilm(film); // Nouvelle méthode BLL
        return "redirect:/films";
    }*/

    @ModelAttribute("genresEnSession")
    public List<Genre> chargerGenres() {
        return genreService.findAllGenres();
    }
}
