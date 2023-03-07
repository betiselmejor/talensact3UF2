package com.example.act3uf2;

import com.mysql.cj.xdevapi.Client;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CompteDao {
    Session session;

    public CompteDao(Session session) {
        this.session = session;
    }

    public void saveCompte(Comptes compte){

        if (checkCompte(compte)==true){

        }else {
//            SessionFactory sf = HibernateUtil.getSessionFactory();
//            Session sesion1 = sf.getCurrentSession();
//            session.getTransaction().begin();
            session.persist(compte);
//            session.getTransaction().commit();
        }



    }
    public boolean checkCompte(Comptes compte){

        boolean tor=false;
        String iban = compte.getIban();
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session sesion1 = sf.getCurrentSession();
//        session.getTransaction().begin();
        Query q = session.createQuery("FROM Comptes c WHERE c.iban LIKE :iban");
        q.setParameter("iban", iban);

        if (!q.getResultList().isEmpty()) {
            tor = true;
        } else {
            tor = false;
        }

//        session.getTransaction().commit();
        return tor;
    }

    public List<Comptes> returnComptesByClient(Clients client){
        List<Comptes> comptes= new ArrayList<>();

        long id = client.getIdClient();
        System.out.println(id);
        Query q = session.createQuery("FROM Comptes c WHERE c.client LIKE :id ");
        q.setParameter("id", client);

        comptes = q.getResultList();


        return comptes;
    }

    public Comptes returnCompteByIban(String iban){
        Comptes toReturn= new Comptes();

        Query q = session.createQuery("FROM Comptes c WHERE c.iban LIKE :iban");
        q.setParameter("iban", iban);

        toReturn= (Comptes) q.uniqueResult();

        return  toReturn;

    }

    public void insertCuenta (Clients clients, String iban, int saldo){

        Comptes comptes = new Comptes();
        comptes.setIban(iban);
        comptes.setClient(clients);
        comptes.setSaldo(saldo);

        session.saveOrUpdate(comptes);

    }

}
