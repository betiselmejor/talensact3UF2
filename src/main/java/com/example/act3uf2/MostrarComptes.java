package com.example.act3uf2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="mostrarComptes", urlPatterns = "/buscarcuentas")
public class MostrarComptes extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.getCurrentSession();
        sesion.getTransaction().begin();

        ClientDao cld= new ClientDao(sesion);
        CompteDao cmd = new CompteDao(sesion);

        String iban = request.getParameter("ibanbuscar");


        Comptes comptes = new Comptes();
        comptes.setIban(iban);

        if (cmd.checkCompte(comptes)==true){
        Comptes comptes1=    cmd.returnCompteByIban(comptes.getIban());
            PrintWriter out = response.getWriter();
            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("<H2>Compte</H2>");
            out.println(
                    "<table>" +
                    "<tr>" +
                    "<th> Iban </th>"+
                    "<td>"+ comptes1.getIban() +"</td>"+
                    "</tr>" +
                    "<tr>" +
                    "<th> Saldo </th>"+
                    "<td>"+ comptes1.getSaldo() +"</td>"+
                    "</tr>" +
                    "</table>");

            out.println ("<H2>Client </H2>");
            out.println(
                    "<table>" +
                    "<tr>" +
                    "<th> Nom </th>"+
                    "<td>"+ comptes1.getClient().getNom() +"</td>"+
                    "</tr>" +
                    "<tr>" +
                    "<th> Dni </th>"+
                    "<td>"+ comptes1.getClient().getDNI() +"</td>"+
                    "</tr>" +
                    "<tr>" +
                    "<th> Dni </th>"+
                    "<td>"+ comptes1.getClient().getPais() +"</td>"+
                    "</tr>" +
                    "</table>");
            out.println ("</BODY>");
            out.println ("</HTML>");
        }else if (cmd.checkCompte(comptes)==false){
            PrintWriter out = response.getWriter();
            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("<H2>no muy bien</H2>");
            out.println ("</BODY>");
            out.println ("</HTML>");
        }

    }




    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {


    }
}
