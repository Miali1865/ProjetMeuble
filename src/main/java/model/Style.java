package model;

import connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Style {
    public int idstyle;
    public String style;

    public int getIdstyle() {
        return idstyle;
    }

    public void setIdstyle(int idstyle) {
        this.idstyle = idstyle;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean insertStyle (Connexion connexion , Style style ) throws Exception {
        boolean opened = false;
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("INSERT INTO Style (style) values (?)");
        statement.setString(1,style.getStyle());
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

    public List<Style> get_list_style (Connexion connexion ) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select * from Style");
        ResultSet resultat = statement.executeQuery();
        List<Style> list_style = new ArrayList<>();
        try{
            while ( resultat.next() ) {
                Style style = new Style();
                style.setIdstyle(resultat.getInt("idstyle"));
                style.setStyle(resultat.getString("style"));
                list_style.add(style);
                System.out.println("aiza ilay erreur = "+list_style.size());
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
        return list_style;
    }
}
