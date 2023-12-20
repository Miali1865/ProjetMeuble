package model;

import connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatierePremiere {
    public  int idmatierePremiere;
    public  String matierePremiere;

    public int getIdmatierePremiere() {
        return idmatierePremiere;
    }

    public void setIdmatierePremiere(int idmatierePremiere) {
        this.idmatierePremiere = idmatierePremiere;
    }

    public String getMatierePremiere() {
        return matierePremiere;
    }

    public void setMatierePremiere(String matierePremiere) {
        this.matierePremiere = matierePremiere;
    }

    public List<MatierePremiere> get_list_matierePremiere (Connexion connexion ) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select * from MatierePremiere");
        ResultSet resultat = statement.executeQuery();
        List<MatierePremiere> list_matierePremiere = new ArrayList<>();
        try{
            while ( resultat.next() ) {
                MatierePremiere matierePremiere = new MatierePremiere();
                matierePremiere.setIdmatierePremiere(resultat.getInt("idmatierePremiere"));
                matierePremiere.setMatierePremiere(resultat.getString("matierePremiere"));
                list_matierePremiere.add(matierePremiere);
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
        return list_matierePremiere;
    }

    public boolean insertmatierePremiere (Connexion connexion , MatierePremiere matierePremiere ) throws Exception {
        boolean opened = false;
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("INSERT INTO MatierePremiere (matierePremiere) values (?)");
        statement.setString(1,matierePremiere.getMatierePremiere());
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
