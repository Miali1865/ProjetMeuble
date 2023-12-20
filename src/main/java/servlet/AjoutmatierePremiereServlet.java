package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Categorie;
import model.MatierePremiere;

import java.io.IOException;

@WebServlet(name = "AjoutmatierePremiereServlet", value = "/AjoutmatierePremiereServlet")
public class AjoutmatierePremiereServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ajout_matierePremiere.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matierePremiere = request.getParameter("matierePremiere");
        Connexion connexion = new Connexion();
        MatierePremiere matierePremiere1 = new MatierePremiere();
        matierePremiere1.setMatierePremiere(matierePremiere);

        try {
            matierePremiere1.insertmatierePremiere(connexion,matierePremiere1);
        }catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
