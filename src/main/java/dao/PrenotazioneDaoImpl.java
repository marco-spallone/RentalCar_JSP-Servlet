package dao;

import config.HibernateUtil;
import entities.Auto;
import entities.Prenotazione;
import entities.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Set;


public class PrenotazioneDaoImpl implements PrenotazioneDao{
    @Override
    public List<Prenotazione> elencoPrenotazioni() {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("SELECT a FROM Prenotazione a", Prenotazione.class).list();
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Prenotazione> prenotazioniPerUtente(int id) {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("SELECT a FROM Prenotazione a WHERE a.utente.id = :id", Prenotazione.class).setParameter("id", id).list();
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Prenotazione trovaPrenotazioneDaId(int id) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return (Prenotazione) session.createQuery("SELECT a FROM Prenotazione a WHERE a.id = :id").setParameter("id", id).getSingleResult();
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void inserisciPrenotazione(Prenotazione p) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(p);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void aggiornaStatoPrenotazione(int id, boolean confermata) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction txn = session.beginTransaction();
            session.createQuery("UPDATE Prenotazione SET confermata=:confermata WHERE idPrenotazione=:id").setParameter("confermata", confermata)
                    .setParameter("id", id).executeUpdate();
            txn.commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void eliminaPrenotazione(int id) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction txn = session.beginTransaction();
            session.createQuery("DELETE Prenotazione p WHERE p.idPrenotazione=:id").setParameter("id", id).executeUpdate();
            txn.commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
