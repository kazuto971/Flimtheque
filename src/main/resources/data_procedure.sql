-- Supprime la procédure si elle existe (sécurisé)
IF OBJECT_ID('dbo.sp_AjoutFilm', 'P') IS NOT NULL
    DROP PROCEDURE dbo.sp_AjoutFilm;
GO

CREATE PROCEDURE dbo.sp_AjoutFilm
    @titre        VARCHAR(100),
    @annee        INT,
    @duree        INT,
    @synopsis     VARCHAR(MAX),
    @genreId      INT,
    @realisateurId INT,
    @nouvelId     INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Films(titre, annee, duree, synopsis, genreId, realisateurId)
    VALUES (@titre, @annee, @duree, @synopsis, @genreId, @realisateurId);

    -- récupère l'id généré (IDENTITY)
    SET @nouvelId = SCOPE_IDENTITY();
END;
GO

