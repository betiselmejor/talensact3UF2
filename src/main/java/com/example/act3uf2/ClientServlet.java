package com.example.act3uf2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="create user",urlPatterns = "/formusuarios")
public class ClientServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.getCurrentSession();
        sesion.getTransaction().begin();

        ClientDao cld= new ClientDao(sesion);
        CompteDao cmd = new CompteDao(sesion);





        String nomCuenta= request.getParameter("cuenta");
        int ingresI= Integer.parseInt(request.getParameter("ingresI"));

        String name = request.getParameter("nom");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");




        Clients cli = new Clients(name,dni,pais,email);


        Comptes compte=new Comptes(nomCuenta,ingresI,cli);






            /*cliente y cuenta ya estan en la base de datos*/
        if (cld.checkUser(cli)==true && cmd.checkCompte(compte)==true){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("<H1> CLIENTE Y CUENTA YA ESTABAN EN LA BASE DE DATOS</H1>");
            out.println ("</BODY>");
            out.println ("</HTML>");
        }else if (cld.checkUser(cli)==false && cmd.checkCompte(compte)==false){
            /*ni cliente ni cuenta estan en la base de datos*/
            cld.saveUser(cli);
            cmd.saveCompte(compte);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("<H1> NUEVO CLIENTE</H1>");
            out.println ("<p>"+ name +"</p>");
            out.println ("<p>" + dni + "</p>");
            out.println ("<p>" + email + "</p>");
            out.println ("<p>" + pais + "</p>");
            out.println ("<H1> NUEVA CUENTA</H1>");
            out.println ("<p>"+ nomCuenta +"</p>");
            out.println ("<p>"+ ingresI +"</p>");
            out.println ("</BODY>");
            out.println ("</HTML>");
        }else if (cld.checkUser(cli)==true && cmd.checkCompte(compte)==false){
            /*si existe el cliente pero no la cuenta*/
//            cmd.saveCompte(compte);
           Clients client2= cld.returnClientByDni(dni);
//            cmd.saveCompte(compte);
            cmd.insertCuenta(client2, compte.getIban(), (int) compte.getSaldo());

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("<H1> CLIENTE YA ESTABA EN LA BASE DE DATOS</H1>");
            out.println ("<H2> Cuenta agregada a cliente</H2>");
            out.println ("<p>"+ nomCuenta +"</p>");
            out.println ("<p>"+ ingresI +"</p>");
            out.println ("</BODY>");
            out.println ("</HTML>");
        }


        sesion.getTransaction().commit();
    }




    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {


    }
}
