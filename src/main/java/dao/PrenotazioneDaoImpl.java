package dao;

import config.HibernateUtil;
import entities.Auto;
import entities.Prenotazione;
import entities.Utente;
import org.hibernate.Session;

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
    public Prenotazione trovaPrenotazioneDaId(int id) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return (Prenotazione) session.createQuery("SELECT a FROM Prenotazione a WHERE a.id = :id").setParameter("id", id).getSingleResult();
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void inserisciPrenotazione(Date inizio, Date fine, Utente u, Auto a) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Prenotazione p = new Prenotazione();
            p.setDataInizio(inizio);
            p.setDataFine(fine);
            p.setUtente(u);
            p.setAuto(a);
            session.persist(p);
            session.getTransaction().commit();
            session.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
