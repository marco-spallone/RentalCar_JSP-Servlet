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
            return (Utente) session.createQuery("SELECT a FROM Utente a WHERE a.id = :id").setParameter("id", id).getSingleResult();
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void inserisciUtente(String nome, String cognome, boolean tipo) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Utente u = new Utente();
            u.setNome(nome);
            u.setCognome(cognome);
            u.setTipo(tipo);
            session.persist(u);
            session.getTransaction().commit();
            session.close();
        } catch(Exception e){
            System.out.println(e);
        }
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
