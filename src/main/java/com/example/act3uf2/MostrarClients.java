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
import java.util.List;

@WebServlet(name="mostrarClients", urlPatterns = "/buscarusuarios")
public class MostrarClients extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.getCurrentSession();
        sesion.getTransaction().begin();

        ClientDao cld= new ClientDao(sesion);
        CompteDao cmd = new CompteDao(sesion);

        String dni = request.getParameter("dnibuscar");



        if (cld.checkUserByDni(dni)==true){
            Clients client = cld.returnClientByDni(dni);
            List<Comptes> comptesClient= cmd.returnComptesByClient(client);
            PrintWriter out = response.getWriter();
            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("<H1> Client </H1>");
            out.println ("<p>" +client.getDNI() +"</p>");
            out.println ("<p>" +client.getNom() +"</p>");
            out.println ("<p>" +client.getPais() +"</p>");
            out.println ("<p>" +client.getIdClient() +"</p>");
            out.println ("<H1> Comptes </H1>");
            for (int i = 0; i < comptesClient.size(); i++) {
                Comptes compte = comptesClient.get(i);

                out.println("<table>" +
                        "<tr>" +
                        "<th> Iban </th>"+
                        "<td>"+ compte.getIban() +"</td>"+
                        "</tr>" +
                        "<tr>" +
                        "<th> Saldo </th>"+
                        "<td>"+ compte.getSaldo() +"</td>"+
                        "</tr>" +
                        "</table");

            }

            out.println ("</BODY>");
            out.println ("</HTML>");
        }else{

            PrintWriter out = response.getWriter();
            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("<H2>client no existeix</H2>");
            out.println ("</BODY>");
            out.println ("</HTML>");
        }

        sesion.getTransaction().commit();
    }




    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {


    }
}
