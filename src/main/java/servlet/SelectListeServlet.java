package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SelectListeServlet", value = "/SelectListeServlet")
public class SelectListeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connexion connexion = new Connexion();
        Style style = new Style();
        Categorie categorie = new Categorie();
        try {
            // Mise à jour des données avant de rediriger
            List<Style> listestyle = style.get_list_style(connexion);
            request.getSession().setAttribute("listestyle", listestyle);
            List<Categorie> listecategorie = categorie.get_list_categorie(connexion);
            request.getSession().setAttribute("listecategorie", listecategorie);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("select_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idstyle = Integer.parseInt(request.getParameter("style"));
        Connexion connexion = new Connexion();
        Composition composition = new Composition();
        try {
            List<Composition> listeproduit = composition.get_list_style_composition(connexion,idstyle);
            request.getSession().setAttribute("listeproduit", listeproduit);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("select_list.jsp").forward(request, response);
    }
}
