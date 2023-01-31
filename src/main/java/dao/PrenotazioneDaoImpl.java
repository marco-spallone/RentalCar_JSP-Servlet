package dao;

import config.HibernateUtil;
import entities.Auto;
import entities.Prenotazione;
import entities.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<Prenotazione> prenotazioniDate(Date inizio, Date fine) {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("SELECT a FROM Prenotazione a WHERE (a.dataInizio<=:inizio AND a.dataFine>=:inizio) OR " +
                    "(a.dataInizio<=:fine AND a.dataFine>=:fine) OR (a.dataInizio>=:inizio AND a.dataInizio<=:fine)", Prenotazione.class)
                    .setParameter("inizio", inizio).setParameter("fine", fine)
                    .list();
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Prenotazione> filtra(String campo, String valore) throws ParseException {
        switch (campo){
            case "inizio":
                Date inizio = new SimpleDateFormat("yyyy-MM-dd").parse(valore);
                try(Session session=HibernateUtil.getSessionFactory().openSession()){
                    return session.createQuery("SELECT a FROM Prenotazione a WHERE a.dataInizio = :inizio", Prenotazione.class)
                            .setParameter("inizio", inizio).list();
                } catch(Exception e){
                    System.out.println(e);
                }
                break;
            case "fine":
                Date fine = new SimpleDateFormat("yyyy-MM-dd").parse(valore);
                try(Session session=HibernateUtil.getSessionFactory().openSession()){
                    return session.createQuery("SELECT a FROM Prenotazione a WHERE a.dataFine = :fine", Prenotazione.class)
                            .setParameter("fine", fine).list();
                } catch(Exception e){
                    System.out.println(e);
                }
                break;
            case "targa":
                try(Session session=HibernateUtil.getSessionFactory().openSession()){
                    int valoreInt = Integer.parseInt(valore);
                    return session.createQuery("SELECT a FROM Prenotazione a WHERE a.auto.idAuto = :valoreInt", Prenotazione.class)
                            .setParameter("valoreInt", valoreInt).list();
                } catch(Exception e){
                    System.out.println(e);
                }
                break;
            case "confermata":
                boolean conf=false;
                switch (valore){
                    case "no":
                    case "false":
                        conf=false;
                        break;
                    case "si":
                    case "true":
                        conf=true;
                        break;
                    default:
                        System.out.println("valore errato");
                        break;
                }
                try(Session session=HibernateUtil.getSessionFactory().openSession()){
                    return session.createQuery("SELECT a FROM Prenotazione a WHERE a.confermata = :conf", Prenotazione.class)
                            .setParameter("conf", conf).list();
                } catch(Exception e){
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
    public void inserisciPrenotazione(Prenotazione p) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.saveOrUpdate(p);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void aggiornaPrenotazione(int id, boolean confermata) {
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
