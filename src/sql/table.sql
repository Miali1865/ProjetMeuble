create database meubles;

\c meubles;

create table Style (
    idstyle serial primary key ,
    style varchar
);

create table Categorie (
    idcategorie serial primary key,
    categorie varchar
);

create table  MatierePremiere (
    idmatierePremiere serial primary key ,
    matierePremiere varchar
);

create  table Taille (
                         idtaille serial primary key ,
                         taille varchar
);

create table Composition (
    idcomposition serial primary key ,
    nomcomposition varchar,
    idcategorie int,
    idstyle int,
    foreign key (idstyle) references Style(idstyle),
    foreign key (idcategorie) references  Categorie(idcategorie)
);

create table Matiere_Composition (
    idmatiere_composition serial primary key,
    idcomposition int,
    idmatierePremiere int,
    foreign key (idmatierePremiere) references  MatierePremiere(idmatierePremiere),
    foreign key (idcomposition) references Composition (idcomposition)
);

create table Volume_Composition (
    idvolume_composition serial primary key,
    idmatiere_composition int,
    idtaille int,
    quantite double precision,
    foreign key (idtaille) references Taille (idtaille),
    foreign key (idmatiere_composition) references Matiere_Composition (idmatiere_composition)
);
