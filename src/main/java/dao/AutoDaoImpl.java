package dao;

import config.HibernateUtil;
import entities.Auto;
import entities.Utente;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoDaoImpl implements AutoDao{
    @Override
    public List<Auto> elencoAuto() {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("SELECT a FROM Auto a", Auto.class).list();
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Auto trovaAutoDaTarga(String targa) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return (Auto) session.createQuery("SELECT a FROM Auto a WHERE a.targa=:targa").setParameter("targa", targa).getSingleResult();
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void inserisciAuto(String marca, String modello, int anno, double prezzo, String targa) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Auto a = new Auto();
            a.setMarca(marca);
            a.setModello(modello);
            a.setAnno(anno);
            a.setPrezzo(prezzo);
            a.setTarga(targa);
            session.persist(a);
            session.getTransaction().commit();
            session.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void aggiornaAuto(int id, String marca, String modello, int anno, double prezzo, String targa) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction txn = session.beginTransaction();
            session.createQuery("UPDATE Auto as a SET a.marca=:marca, a.modello=:modello, a.anno=:anno, a.prezzo=:prezzo, a.targa=:targa " +
                            "WHERE a.id=:id").setParameter("marca", marca).setParameter("modello", modello).setParameter("anno", anno).
                            setParameter("prezzo", prezzo).setParameter("targa", targa).setParameter("id", id).executeUpdate();
            txn.commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void eliminaAuto(String targa) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction txn = session.beginTransaction();
            session.createQuery("DELETE Auto as a WHERE a.targa=:targa").setParameter("targa", targa).executeUpdate();
            txn.commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
