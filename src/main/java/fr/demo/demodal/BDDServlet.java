package fr.demo.demodal;

import fr.demo.demodal.bo.Voiture;
import fr.demo.demodal.dal.ConnectionProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/sql")
public class BDDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Etape 1 : récupérer une connection SQL
            Connection connection = ConnectionProvider.getConnection();
            // Etape 2 : écrire la requête SQL
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT marque, modele FROM voiture"
            );
            // Etape 3 : executer la requête SQL
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Voiture> voitures = new ArrayList<>();
            // Etape 4 : Parcourir le tableau de résultat
            while (rs.next()) {
                Voiture v = new Voiture(
                        rs.getString("marque"),
                        rs.getString("modele")
                );
                voitures.add(v);
            }
            // Etape 5 : envoyer la liste a la JSP
            request.setAttribute("mesVoitures", voitures);
            // Etape 6 : Afficher la JSP
            request.getRequestDispatcher("WEB-INF/voitures.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
