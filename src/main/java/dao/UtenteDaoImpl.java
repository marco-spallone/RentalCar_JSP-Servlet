package dao;

import config.HibernateUtil;
import entities.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public List<Utente> trovaCustomers() {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("SELECT a FROM Utente a WHERE a.tipo=false", Utente.class).list();
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
    public List<Utente> filtra(String campo, String valore) {
        switch (campo){
            case "nome":
                try(Session session=HibernateUtil.getSessionFactory().openSession()){
                    return session.createQuery("SELECT a FROM Utente a WHERE a.nome=:valore", Utente.class).setParameter("valore", valore).list();
                } catch (Exception e){
                    System.out.println(e);
                }
                break;
            case "cognome":
                try(Session session=HibernateUtil.getSessionFactory().openSession()){
                    return session.createQuery("SELECT a FROM Utente a WHERE a.cognome=:valore", Utente.class).setParameter("valore", valore).list();
                } catch (Exception e){
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Error");
                break;
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
    public Utente login(String username, String password) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("SELECT a FROM Utente a WHERE a.username = :username AND a.password = :password", Utente.class)
                    .setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
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
