package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Composition;
import model.Taille;
import model.Volume_Composition;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "InitialisationVolumeServlet", value = "/InitialisationVolumeServlet")
public class InitialisationVolumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("creation_volume.jsp").forward(request, response);
        if(request.getParameter("idcomposition") != null) {
            System.out.println("Metyyyy");
            int idcomposition = Integer.parseInt(request.getParameter("idcomposition"));
            try {
                Composition composition = new Composition();
                Connexion connexion = new Connexion();
                Taille taille = new Taille();

                List<Taille> listetaille = taille.get_list_taille(connexion);
                request.getSession().setAttribute("listetaille", listetaille);
                List<Composition> listecompositions = composition.get_list_matiere_composition(connexion,idcomposition);
                request.getSession().setAttribute("listecompositions", listecompositions);
            } catch (Exception e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            request.getRequestDispatcher("initialisation_volume.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connexion connexion = new Connexion();
            Volume_Composition volComposition = new Volume_Composition();
            // Récupérer les données de taille
            int idtaille = Integer.parseInt(request.getParameter("idtaille"));

            // Récupérer les données de composition
            String[] idmatiere_compositions = request.getParameterValues("idmatiere_composition");
            String[] quantitees = request.getParameterValues("quantite");

            // Vérifier si les tableaux ne sont pas nuls et ont la même longueur
            if (idmatiere_compositions != null && quantitees != null && idmatiere_compositions.length == quantitees.length) {
                // Traiter les données
                for (int i = 0; i < idmatiere_compositions.length; i++) {
                    int idmatiere_composition = Integer.parseInt(idmatiere_compositions[i]);

                    // Vérifier si la quantité n'est pas une chaîne vide avant de la convertir en nombre
                    double quantitee = 0.0; // Valeur par défaut si la conversion échoue
                    if (quantitees[i] != null && !quantitees[i].isEmpty()) {
                        quantitee = Double.parseDouble(quantitees[i]);
                    }

                    volComposition.setIdmatiere_composition(idmatiere_composition);
                    volComposition.setIdtaille(idtaille);
                    volComposition.setQuantite(quantitee);
                    volComposition.insertVolumeComposition(connexion,volComposition);
                    System.out.println("idtaille: " + idtaille + " idmatiere_composition: " + idmatiere_composition + " quantitee: " + quantitee);

                    // Ajouter le code pour mettre à jour vos données en base de données ou effectuer d'autres opérations
                }
            }

            // Rediriger vers la page index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Gérer l'exception (par exemple, afficher un message d'erreur à l'utilisateur)
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les autres exceptions
        }
    }

}
