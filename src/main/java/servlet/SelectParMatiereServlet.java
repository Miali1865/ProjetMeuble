package servlet;

import connexion.Connexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.MatierePremiere;
import model.Volume_Composition;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SelectParMatiereServlet", value = "/SelectParMatiereServlet")
public class SelectParMatiereServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connexion connexion = new Connexion();
            MatierePremiere matierePremiere = new MatierePremiere();
            List<MatierePremiere> listematierepremiere = matierePremiere.get_list_matierePremiere(connexion);
            request.getSession().setAttribute("listematierepremiere", listematierepremiere);
        }catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("selectbymatiere.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idmatierepremiere = Integer.parseInt(request.getParameter("idmatierepremiere"));
        try {
            Connexion connexion = new Connexion();
            MatierePremiere matierePremiere = new MatierePremiere();
            List<MatierePremiere> listematierepremiere = matierePremiere.get_list_matierePremiere(connexion);
            request.getSession().setAttribute("listematierepremiere", listematierepremiere);

            Volume_Composition volume_composition = new Volume_Composition();
            List<Volume_Composition> listevolumes = volume_composition.get_list_matiere_produit(connexion,idmatierepremiere);
            request.getSession().setAttribute("listevolumes", listevolumes);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("selectbymatiere.jsp").forward(request, response);
    }
}
