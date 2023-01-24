package dao;

import config.HibernateUtil;
import entities.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class UtenteDaoImpl implements UtenteDao {
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
            return session.createQuery("SELECT a FROM Utente a WHERE a.id = :id", Utente.class).setParameter("id", id).getSingleResult();
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void inserisciOAggiornaUtente(Utente u) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.saveOrUpdate(u);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void eliminaUtente(int id) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction txn = session.beginTransaction();
            session.createQuery("DELETE Utente u WHERE u.id=:id").setParameter("id", id).executeUpdate();
            txn.commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
