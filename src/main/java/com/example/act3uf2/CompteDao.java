package com.example.act3uf2;

import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CompteDao {

    public void saveCompte(Comptes compte){

        if (checkCompte(compte)==true){

        }else {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session sesion1 = sf.getCurrentSession();
            sesion1.getTransaction().begin();
            sesion1.persist(compte);
            sesion1.getTransaction().commit();
        }



    }
    public boolean checkCompte(Comptes compte){

        boolean tor=false;
        String iban = compte.getIban();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion1 = sf.getCurrentSession();
        sesion1.getTransaction().begin();
        Query q = sesion1.createQuery("FROM Comptes c WHERE c.iban LIKE :iban");
        q.setParameter("iban", iban);

        if (!q.getResultList().isEmpty()) {
            tor = true;
        } else {
            tor = false;
        }

        sesion1.getTransaction().commit();
        return tor;
    }

}
//public class CompteDao {
//
//    public void saveCompte(Comptes compte) throws PersistenceException {
//        try (SessionFactory sf = HibernateUtil.getSessionFactory();
//             Session session = sf.getCurrentSession()) {
//            Transaction tx = session.beginTransaction();
//
//            // Validación de campos obligatorios
//            if (compte.getIban() == null || compte.getIban().isEmpty()) {
//                throw new IllegalArgumentException("El campo IBAN es obligatorio");
//            }
//
//            if (!checkCompte(compte)) {
//                session.persist(compte);
//                tx.commit();
//            } else {
//                throw new PersistenceException("La cuenta ya existe en la base de datos");
//            }
//        } catch (HibernateException ex) {
//            throw new PersistenceException("Error al guardar la cuenta en la base de datos", ex);
//        }
//    }
//
//    public boolean checkCompte(Comptes compte) throws PersistenceException {
//        try (SessionFactory sf = HibernateUtil.getSessionFactory();
//             Session session = sf.getCurrentSession()) {
//            Transaction tx = session.beginTransaction();
//
//            String iban = compte.getIban();
//            Query<Comptes> query = session.createQuery("FROM Comptes c WHERE c.iban = :iban", Comptes.class);
//            query.setParameter("iban", iban);
//
//            boolean result = !query.getResultList().isEmpty();
//
//            tx.commit();
//            return result;
//        } catch (HibernateException ex) {
//            throw new PersistenceException("Error al comprobar si la cuenta existe en la base de datos", ex);
//        }
//    }
//}