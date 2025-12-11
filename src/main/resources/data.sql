DELETE FROM acteurs;
DELETE FROM films;
DELETE FROM participants;
DELETE FROM genres;
INSERT INTO genres (id, titre) VALUES (1, 'Animation');
INSERT INTO genres (id, titre) VALUES (2, 'Science-fiction');
INSERT INTO genres (id, titre) VALUES (3, 'Documentaire');
INSERT INTO genres (id, titre) VALUES (4, 'Action');
INSERT INTO genres (id, titre) VALUES (5, 'Comédie');
INSERT INTO genres (id, titre) VALUES (6, 'Drame');

-- Réinitialiser l'IDENTITY à 0, le prochain film inséré aura l'id 1
DBCC CHECKIDENT ('films', RESEED, 0);

-- Réinitialiser l'IDENTITY pour participants si nécessaire
DBCC CHECKIDENT ('participants', RESEED, 0);





-- ----------------------------
-- INSERTION PARTICIPANTS
-- ----------------------------
INSERT INTO participants (prenom, nom) VALUES ('Steven', 'Spielberg');   -- id 1
INSERT INTO participants (prenom, nom) VALUES ('David', 'Cronenberg');   -- id 2
INSERT INTO participants (prenom, nom) VALUES ('Dany', 'Boon');          -- id 3
INSERT INTO participants (prenom, nom) VALUES ('Richard', 'Attenborough'); -- id 4
INSERT INTO participants (prenom, nom) VALUES ('Jeff', 'Goldblum');     -- id 5
INSERT INTO participants (prenom, nom) VALUES ('Geena', 'Davis');        -- id 6
INSERT INTO participants (prenom, nom) VALUES ('Mark', 'Rylance');       -- id 7
INSERT INTO participants (prenom, nom) VALUES ('Ruby', 'Barnhill');      -- id 8
INSERT INTO participants (prenom, nom) VALUES ('Kad', 'Merad');          -- id 9

-- ----------------------------
-- INSERTION FILMS
-- ----------------------------
INSERT INTO films (titre, annee, duree, synopsis, genreId, realisateurId)
VALUES
    ('Jurassic Park', 1993, 128, 'Le film raconte l''histoire d''un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.', 2, 1);

INSERT INTO films (titre, annee, duree, synopsis, genreId, realisateurId)
VALUES
    ('The Fly', 1986, 95, 'Il s''agit de l''adaptation cinématographique de la nouvelle éponyme de l''auteur George Langelaan.', 2, 2);

INSERT INTO films (titre, annee, duree, synopsis, genreId, realisateurId)
VALUES
    ('The BFG', 2016, 117, 'Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.', 5, 1);

INSERT INTO films (titre, annee, duree, synopsis, genreId, realisateurId)
VALUES
    ('Bienvenue chez les Ch''tis', 2008, 106, 'Philippe Abrams est directeur de la poste de Salon-de-Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d''obtenir une mutation sur la Côte d''Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord.', 5, 3);

-- ----------------------------
-- INSERTION ACTEURS
-- ----------------------------
-- Jurassic Park : acteurs id 4 et 5
INSERT INTO acteurs (filmId, participantId) VALUES (1, 4);
INSERT INTO acteurs (filmId, participantId) VALUES (1, 5);

-- The Fly : acteurs id 5 et 6
INSERT INTO acteurs (filmId, participantId) VALUES (2, 5);
INSERT INTO acteurs (filmId, participantId) VALUES (2, 6);

-- The BFG : acteurs id 7 et 8
INSERT INTO acteurs (filmId, participantId) VALUES (3, 7);
INSERT INTO acteurs (filmId, participantId) VALUES (3, 8);

-- Bienvenue chez les Ch'tis : acteurs id 3 et 9
INSERT INTO acteurs (filmId, participantId) VALUES (4, 3);
INSERT INTO acteurs (filmId, participantId) VALUES (4, 9);

drop table Membres;

CREATE TABLE Membres(    id INT IDENTITY CONSTRAINT PK_Membres PRIMARY KEY,
                         prenom VARCHAR(50) NOT NULL,
                         nom VARCHAR(50) NOT NULL,
                         pseudo VARCHAR(50) NOT NULL UNIQUE,
                         motDePasse VARCHAR(100) NOT NULL,
                         admin BIT NOT NULL);

DELETE FROM Membres;

INSERT INTO Membres (prenom, nom, pseudo, motDePasse, admin)
VALUES (
           'Baille',
           'Anne-Lise',
           'abaille@campus-eni.fr',
           '{bcrypt}$2a$10$0HBkXwD023v72Lkig9itfu9AEZjEimXu48hkh8Iher7.BpJJv.glu',
           0
       );

INSERT INTO Membres (prenom, nom, pseudo, motDePasse, admin)
VALUES (
           'Joseph',
           'Pascal',
           'joseph@campus-eni.fr',
           '{bcrypt}$2a$10$mL/8uJL4m2AqsnNjngQ1cekapJ2LVz103vw4yuQrG8SsTML4E1GIG',
           1
       );
