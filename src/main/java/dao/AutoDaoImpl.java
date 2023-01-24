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
    public void inserisciOAggiornaAuto(Auto a) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.saveOrUpdate(a);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void eliminaAuto(int id) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction txn = session.beginTransaction();
            session.createQuery("DELETE Auto as a WHERE a.id=:id").setParameter("id", id).executeUpdate();
            txn.commit();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
