package dao;

import config.HibernateUtil;
import entities.Utente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class UtentiDaoImpl implements UtentiDao {
    @Override
    public List<Utente> trovaUtenti() {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("SELECT a FROM Utente a", Utente.class).list();
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Utente trovaUtenteDaId(int id) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return (Utente) session.createQuery("SELECT a FROM Utente a WHERE a.id = :id").setParameter("id", id).getSingleResult();
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    @Transactional
    public void aggiornaUtente(int id, String nome, String cognome, boolean tipo) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
         Transaction txn = session.beginTransaction();
         session.createQuery("UPDATE Utente as u SET nome=:nome,cognome=:cognome,tipo=:tipo WHERE u.id=:id").setParameter("tipo", tipo)
                 .setParameter("nome", nome).setParameter("cognome", cognome).setParameter("id", id).executeUpdate();
         txn.commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
