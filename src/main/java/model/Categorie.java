package model;

import connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categorie {
    public int idcategorie;
    public  String categorie;

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<Categorie> get_list_categorie (Connexion connexion ) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select * from Categorie");
        ResultSet resultat = statement.executeQuery();
        List<Categorie> list_categorie = new ArrayList<>();
        try{
            while ( resultat.next() ) {
                Categorie categorie = new Categorie();
                categorie.setIdcategorie(resultat.getInt("idcategorie"));
                categorie.setCategorie(resultat.getString("categorie"));
                list_categorie.add(categorie);
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
        return list_categorie;
    }

    public boolean insertCategorie (Connexion connexion , Categorie categorie ) throws Exception {
        boolean opened = false;
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("INSERT INTO Categorie (categorie) values (?)");
        statement.setString(1,categorie.getCategorie());
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
}
