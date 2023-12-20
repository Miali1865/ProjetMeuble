package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AjoutCompositionServlet", value = "/AjoutCompositionServlet")
public class AjoutCompositionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connexion connexion = new Connexion();
        Style style = new Style();
        Categorie categorie = new Categorie();
        MatierePremiere matierePremiere = new MatierePremiere();
        Taille taille = new Taille();
        try {
            // Mise à jour des données avant de rediriger
            List<Style> listestyle = style.get_list_style(connexion);
            request.getSession().setAttribute("listestyle", listestyle);
            List<Categorie> listecategorie = categorie.get_list_categorie(connexion);
            request.getSession().setAttribute("listecategorie", listecategorie);
            List<MatierePremiere> listematierePremiere = matierePremiere.get_list_matierePremiere(connexion) ;
            request.getSession().setAttribute("listematierePremiere", listematierePremiere);
            List<Taille> listetaille = taille.get_list_taille(connexion);
            request.getSession().setAttribute("listetaille", listetaille);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("ajout_composition.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les valeurs des cases à cocher
        String[] matieresSelectionnees = request.getParameterValues("matierePremiere[]");
        int idstyle = Integer.parseInt(request.getParameter("style"));
        int idcategorie = Integer.parseInt(request.getParameter("categorie"));
        String nomcomposition = request.getParameter("composition");
        Connexion connexion = new Connexion();
        Composition composition = new Composition();
        composition.setNomcomposition(nomcomposition);
        composition.setIdstyle(idstyle);
        composition.setIdcategorie(idcategorie);

        try {
            composition.insertComposition(connexion,composition);
            int idcomposition = composition.get_id_max_composition(connexion);
            if (matieresSelectionnees != null) {
                for (String idMatiere : matieresSelectionnees) {
                    // Traitez chaque valeur sélectionnée comme nécessaire
                    int idMatierePremiere = Integer.parseInt(idMatiere);
                    System.out.println("Matière Première sélectionnée avec l'ID : " + idMatierePremiere);
                    composition.insertMatiereComposition(connexion,idcomposition,idMatierePremiere);
                    // Vous pouvez effectuer d'autres opérations ici avec l'ID de la matière première
                }
            }

            request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
