package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Taille;

import java.io.IOException;

@WebServlet(name = "AjoutTailleServlet", value = "/AjoutTailleServlet")
public class AjoutTailleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ajout_taille.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taille = request.getParameter("taille");
        Connexion connexion = new Connexion();
        Taille taille1 = new Taille();
        taille1.setTaille(taille);

        try {
            taille1.insertTaille(connexion, taille1);
        }catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("ajout_taille.jsp").forward(request, response);
    }
}
