package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.controller.dto.FilmDto;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"genresEnSession"})
@Controller
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping("/films/detail")
    public String afficherUnFilm(@RequestParam(name="id") long identifiant, Model model) {

        Film film = this.filmService.consulterFilmParId(identifiant);
        System.out.println(film);

        model.addAttribute("film", film);
        return "view-film-detail";
    }


    @GetMapping("/films")
    public String afficherFilms(Model model) {

        List<Film> films = this.filmService.consulterFilms();
        for (Film film : films) {
            System.out.println(film);
        }

        model.addAttribute("films", films);

        return "view-films";
    }

    @GetMapping("/films/creer")
    public String afficherFormulaire(Model model) {
        model.addAttribute("film", new FilmDto());
        model.addAttribute("genres", filmService.consulterGenres());
        model.addAttribute("participants", filmService.consulterParticipants());
        return "view-film-form";
    }



    @PostMapping("/films/creer")
    public String creerFilm(
            @Valid @ModelAttribute("film") FilmDto filmDto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("genres", filmService.consulterGenres());
            model.addAttribute("participants", filmService.consulterParticipants());
            return "view-film-form";
        }

        Film film = new Film();
        film.setTitre(filmDto.getTitre());
        film.setAnnee(filmDto.getAnnee());
        film.setDuree(filmDto.getDuree());
        film.setSynopsis(filmDto.getSynopsis());
        film.setGenre(filmService.consulterGenreParId(filmDto.getGenreId()));

        Participant realisateur = filmService.consulterParticipantParId(filmDto.getRealisateurId());
        film.setRealisateur(realisateur);

        for (Long id : filmDto.getActeurIds()) {
            Participant acteur = filmService.consulterParticipantParId(id);
            if (acteur != null) {
                film.getActeurs().add(acteur);
            }
        }

        filmService.creerFilm(film);
        return "redirect:/films";
    }





    @ModelAttribute("genresEnSession")
    public List<Genre> chargerGenres(){
        System.out.println("Chargement en Session - GENRES");
        return filmService.consulterGenres();
    }


}
