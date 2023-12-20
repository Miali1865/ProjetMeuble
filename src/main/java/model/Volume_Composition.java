package model;

import connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Volume_Composition {

    public int idvolume_composition;
    public int idmatiere_composition;
    public int idtaille;
    public double quantite;

    public Categorie categorie;
    public MatierePremiere matierePremiere;
    public Taille taille;
    public Composition composition;

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getIdvolume_composition() {
        return idvolume_composition;
    }

    public void setIdvolume_composition(int idvolume_composition) {
        this.idvolume_composition = idvolume_composition;
    }

    public int getIdmatiere_composition() {
        return idmatiere_composition;
    }

    public void setIdmatiere_composition(int idmatiere_composition) {
        this.idmatiere_composition = idmatiere_composition;
    }

    public int getIdtaille() {
        return idtaille;
    }

    public void setIdtaille(int idtaille) {
        this.idtaille = idtaille;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public boolean insertVolumeComposition (Connexion connexion , Volume_Composition volume_composition ) throws Exception {
        boolean opened = false;
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("INSERT INTO Volume_Composition (idmatiere_composition,idtaille,quantite) values (?,?,?)");
        statement.setInt(1, volume_composition.getIdmatiere_composition());
        statement.setInt(2, volume_composition.getIdtaille());
        statement.setDouble(3,volume_composition.getQuantite());
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

    public List<Volume_Composition> get_list_matiere_produit (Connexion connexion, int idmatierePremiere) throws Exception {
        Connection c = connexion.getConnectionPostGresql();
        PreparedStatement statement = c.prepareStatement("select * from Vue_Composition_Details where idmatierePremiere = ?");
        statement.setInt(1, idmatierePremiere);
        ResultSet resultat = statement.executeQuery();
        List<Volume_Composition> list_composition = new ArrayList<>();
        try{
            while ( resultat.next() ) {
                MatierePremiere matierePremiere1 = new MatierePremiere();
                matierePremiere1.setMatierePremiere(resultat.getString("matierePremiere"));
                Categorie categorie1 = new Categorie();
                categorie1.setCategorie(resultat.getString("categorie_nom"));
                Taille taille = new Taille();
                taille.setTaille(resultat.getString("taille"));
                Composition composition = new Composition();
                composition.setNomcomposition(resultat.getString("nomcomposition"));
                Volume_Composition volume_composition = new Volume_Composition();
                volume_composition.setQuantite(resultat.getDouble("quantite"));
                volume_composition.setMatierePremiere(matierePremiere1);
                volume_composition.setCategorie(categorie1);
                volume_composition.setTaille(taille);
                volume_composition.setComposition(composition);
                list_composition.add(volume_composition);
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
