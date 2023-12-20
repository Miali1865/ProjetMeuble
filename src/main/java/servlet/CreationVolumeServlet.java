package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Composition;
import model.MatierePremiere;
import model.Taille;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreationVolumeServlet", value = "/CreationVolumeServlet")
public class CreationVolumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connexion connexion = new Connexion();
        Taille taille = new Taille();
        Composition composition = new Composition();
        MatierePremiere matierePremiere = new MatierePremiere();
        try {
            List<Composition> listcompositions = composition.get_list_composition(connexion);
            request.getSession().setAttribute("listcompositions", listcompositions);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("creation_volume.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("idcomposition") != null) {
            System.out.println("Metyyyy");
            //request.getRequestDispatcher("initialisation_volume.jsp").forward(request, response);
            response.sendRedirect("initialisation_volume.jsp?idcomposition="+request.getParameter("idcomposition"));
        }
    }
}
