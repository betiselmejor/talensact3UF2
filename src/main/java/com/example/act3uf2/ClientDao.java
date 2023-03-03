package com.example.act3uf2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientDao {

    public void saveUser(Clients client) {

        if (checkUser(client)==true){

        }else {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session sesion1 = sf.getCurrentSession();
            sesion1.getTransaction().begin();
            sesion1.persist(client);
            sesion1.getTransaction().commit();
        }

    }

    public boolean checkUser(Clients client){

        boolean tor=false;
        String dni = client.getDNI();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion1 = sf.getCurrentSession();
        sesion1.getTransaction().begin();
        Query q = sesion1.createQuery("FROM Clients c WHERE c.DNI LIKE :dni");
        q.setParameter("dni", dni);

        if (!q.getResultList().isEmpty()) {
            tor = true;
        } else {
            tor = false;
        }

        sesion1.getTransaction().commit();
        return tor;
    }
////////////////////////////////////////////////////////////////////////////////////////////

    public List< Clients > getAllClients() {

        Transaction transaction = null;
        List < Clients > listOfUser = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get a user object

            listOfUser = session.createQuery(" from Clients").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
}
