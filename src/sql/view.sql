CREATE VIEW vw_matiere_composition AS
SELECT mc.*, mp.matierePremiere
FROM Matiere_Composition mc
         INNER JOIN MatierePremiere mp ON mc.idmatierePremiere = mp.idmatierePremiere;


CREATE OR REPLACE VIEW Vue_Composition_Details AS
SELECT
    c.idcomposition,
    c.nomcomposition,
    cat.categorie AS categorie_nom,
    mc.idmatiere_composition,
    mp.idmatierePremiere,
    mp.matierePremiere,
    vc.idvolume_composition,
    t.taille,
    vc.quantite
FROM
    Composition c
        JOIN
    Categorie cat ON c.idcategorie = cat.idcategorie
        JOIN
    Matiere_Composition mc ON c.idcomposition = mc.idcomposition
        JOIN
    MatierePremiere mp ON mc.idmatierepremiere = mp.idmatierepremiere
        JOIN
    Volume_Composition vc ON mc.idmatiere_composition = vc.idmatiere_composition
        JOIN
    Taille t ON vc.idtaille = t.idtaille
WHERE
        vc.quantite <> 0;

-- Création de la vue pour afficher les détails de composition avec matière première
CREATE OR REPLACE VIEW Vue_Composition_Matiere AS
SELECT
    c.idcomposition,
    c.nomcomposition,
    c.idcategorie,
    c.idstyle,
    s.style AS nomstyle,
    mc.idmatiere_composition,
    mc.idmatierePremiere,
    mp.matierePremiere
FROM
    Composition c
        JOIN
    Matiere_Composition mc ON c.idcomposition = mc.idcomposition
        JOIN
    MatierePremiere mp ON mc.idmatierePremiere = mp.idmatierePremiere
        JOIN
    Style s ON c.idstyle = s.idstyle;

