package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Categorie;
import model.Style;

import java.io.IOException;

@WebServlet(name = "AjoutCategorieServlet", value = "/AjoutCategorieServlet")
public class AjoutCategorieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ajout_categorie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categorie = request.getParameter("categorie");
        Connexion connexion = new Connexion();
        Categorie categorie1 = new Categorie();
        categorie1.setCategorie(categorie);

        try {
            categorie1.insertCategorie(connexion,categorie1);
        }catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
