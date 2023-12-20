package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Style;

import java.io.IOException;

@WebServlet(name = "AjoutStyleServlet", value = "/AjoutStyleServlet")
public class AjoutStyleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ajout_style.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String style = request.getParameter("style");
        Connexion connexion = new Connexion();
        Style style1 = new Style();
        style1.setStyle(style);
        try {
            style1.insertStyle(connexion,style1);
        }catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
