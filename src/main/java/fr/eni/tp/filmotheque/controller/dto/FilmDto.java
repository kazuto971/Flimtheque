package fr.eni.tp.filmotheque.controller.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class FilmDto {
    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotNull(message = "L'année est obligatoire")
    @Min(value = 1900, message = "L'année doit être supérieure à 1900")
    private Integer annee;

    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être supérieure à 0")
    private Integer duree;

    @NotBlank(message = "Le synopsis est obligatoire")
    @Size(max = 1000, message = "Le synopsis est trop long")
    private String synopsis;

    @NotNull(message = "Le genre est obligatoire")
    private Long genreId;

    @NotNull
    private Long realisateurId;

    private List<Long> acteurIds;


    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public Integer getDuree() { return duree; }
    public void setDuree(Integer duree) { this.duree = duree; }

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public Long getGenreId() { return genreId; }
    public void setGenreId(Long genreId) { this.genreId = genreId; }

    public Long getRealisateurId() { return realisateurId; }
    public void setRealisateurId(Long realisateurId) { this.realisateurId = realisateurId; }

    public List<Long> getActeurIds() { return acteurIds; }
    public void setActeurIds(List<Long> acteurIds) { this.acteurIds = acteurIds; }

}

