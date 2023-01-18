package dao;

import config.HibernateUtil;
import entities.Utente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
}
