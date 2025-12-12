-- supprime la vue si elle existe (SQL Server)
IF OBJECT_ID('dbo.v_Films', 'V') IS NOT NULL
    DROP VIEW dbo.v_Films;
GO

CREATE VIEW dbo.v_Films AS
SELECT
    f.id            AS film_id,
    f.titre         AS film_titre,
    f.annee         AS annee,
    f.duree         AS duree,
    f.synopsis      AS synopsis,

    g.id            AS genre_id,
    g.titre         AS genre_titre,

    r.id            AS realisateur_id,
    r.prenom        AS realisateur_prenom,
    r.nom           AS realisateur_nom
FROM films f
         JOIN genres g         ON f.genreId = g.id
         JOIN participants r   ON f.realisateurId = r.id;
GO
