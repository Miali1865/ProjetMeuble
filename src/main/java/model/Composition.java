package model;

import connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Composition {

    public int idcomposition;
    public String nomcomposition;

    public int getIdcomposition() {
        return idcomposition;
    }

    public void setIdcomposition(int idcomposition) {
        this.idcomposition = idcomposition;
    }

    public String getNomcomposition() {
        return nomcomposition;
    }

    public void setNomcomposition(String nomcomposition) {
        this.nomcomposition = nomcomposition;
    }

    public  int idmeuble ;
    public  int idstyle;
    public int idmatierePremiere;
    public int idcategorie ;
    public MatierePremiere matierePremiere;
    public Style style;
    public Categorie categorie;
    public Taille taille;

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public MatierePremiere getMatierePremiere() {
        return matierePremiere;
    }

    public void setMatierePremiere(MatierePremiere matierePremiere) {
        this.matierePremiere = matierePremiere;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getIdmeuble() {
        return idmeuble;
    }

    public void setIdmeuble(int idmeuble) {
        this.idmeuble = idmeuble;
    }

    public int getIdstyle() {
        return idstyle;
    }

    public void setIdstyle(int idstyle) {
        this.idstyle = idstyle;
    }

    public int getIdmatierePremiere() {
        return idmatierePremiere;
    }

    public void setIdmatierePremiere(int idmatierePremiere) {
        this.idmatierePremiere = idmatierePremiere;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }


    public int idmatiere_composition;

    public int getIdmatiere_composition() {
        return idmatiere_composition;
    }

    public void setIdmatiere_composition(int idmatiere_composition) {
        this.idmatiere_composition = idmatiere_composition;
    }

    public boolean insertComposition (Connexion connexion , Composition composition) throws Exception {
        boolean opened = false;
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("INSERT INTO Composition (nomcomposition,idstyle,idcategorie) values (?,?,?)");
        statement.setString(1,composition.getNomcomposition());
        statement.setInt(2,composition.getIdstyle());
        statement.setInt(3,composition.getIdcategorie());
        // 1 corresponds @ ilay laharan'ilay "?" ( ex : INSERT INTO employe (nom,prenom) values (?,?) donc niampy zany io hoe statement.setString(2,employe.getPrenom());
        try {
            statement.executeUpdate();
            if(opened){
                c.commit();
            }
            return true;
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            statement.close();
            if(opened){
                c.close();
            }
        }
    }

    public boolean insertMatiereComposition (Connexion connexion , int idcomposition,int idmatierePremiere) throws Exception {
        boolean opened = false;
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("INSERT INTO Matiere_Composition (idcomposition,idmatierePremiere) values (?,?)");
        statement.setInt(1,idcomposition);
        statement.setInt(2,idmatierePremiere);
        // 1 corresponds @ ilay laharan'ilay "?" ( ex : INSERT INTO employe (nom,prenom) values (?,?) donc niampy zany io hoe statement.setString(2,employe.getPrenom());
        try {
            statement.executeUpdate();
            if(opened){
                c.commit();
            }
            return true;
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            statement.close();
            if(opened){
                c.close();
            }
        }
    }

    public int get_id_max_composition (Connexion connexion ) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select max(idcomposition) as idcomposition from Composition");
        ResultSet resultat = statement.executeQuery();
        int idcomposition = 0;
        try{
            while ( resultat.next() ) {
                Composition composition = new Composition();
                composition.setIdcomposition(resultat.getInt("idcomposition"));
                idcomposition = composition.getIdcomposition();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw new RuntimeException("Error retrieving magasins", e);
        }finally {
            // Close the connection in the finally block to ensure it is always closed
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Log or handle the exception as needed
                }
            }
        }
        return idcomposition;
    }


    public List<Composition> get_list_composition (Connexion connexion ) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select * from Composition");
        ResultSet resultat = statement.executeQuery();
        List<Composition> list_composition = new ArrayList<>();
        try{
            while ( resultat.next() ) {
                Composition composition = new Composition();
                composition.setIdcomposition(resultat.getInt("idcomposition"));
                composition.setNomcomposition(resultat.getString("nomcomposition"));
                composition.setIdcategorie(resultat.getInt("idcategorie"));
                composition.setIdstyle(resultat.getInt("idstyle"));
                list_composition.add(composition);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw new RuntimeException("Error retrieving magasins", e);
        }finally {
            // Close the connection in the finally block to ensure it is always closed
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Log or handle the exception as needed
                }
            }
        }
        return list_composition;
    }

    public List<Composition> get_list_matiere_composition (Connexion connexion,int idcomposition) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select * from vw_matiere_composition where idcomposition = ?");
        statement.setInt(1, idcomposition);
        ResultSet resultat = statement.executeQuery();
        List<Composition> list_composition = new ArrayList<>();
        try{
            while ( resultat.next() ) {
                MatierePremiere matierePremiere1 = new MatierePremiere();
                matierePremiere1.setMatierePremiere(resultat.getString("matierepremiere"));
                Composition composition = new Composition();
                composition.setIdmatiere_composition(resultat.getInt("idmatiere_composition"));
                composition.setIdcomposition(resultat.getInt("idcomposition"));
                composition.setIdmatierePremiere(resultat.getInt("idmatierepremiere"));
                composition.setMatierePremiere(matierePremiere1);
                list_composition.add(composition);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw new RuntimeException("Error retrieving magasins", e);
        }finally {
            // Close the connection in the finally block to ensure it is always closed
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Log or handle the exception as needed
                }
            }
        }
        return list_composition;
    }


    public List<Composition> get_list_style_composition (Connexion connexion,int idstyle) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select * from Vue_Composition_Matiere where idstyle = ?");
        statement.setInt(1, idstyle);
        ResultSet resultat = statement.executeQuery();
        List<Composition> list_composition = new ArrayList<>();
        try{
            while ( resultat.next() ) {
                MatierePremiere matierePremiere1 = new MatierePremiere();
                matierePremiere1.setMatierePremiere(resultat.getString("matierePremiere"));
                Style style1 = new Style();
                style1.setStyle(resultat.getString("nomstyle"));
                Composition composition = new Composition();
                composition.setMatierePremiere(matierePremiere1);
                composition.setStyle(style1);
                list_composition.add(composition);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw new RuntimeException("Error retrieving magasins", e);
        }finally {
            // Close the connection in the finally block to ensure it is always closed
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Log or handle the exception as needed
                }
            }
        }
        return list_composition;
    }
}
