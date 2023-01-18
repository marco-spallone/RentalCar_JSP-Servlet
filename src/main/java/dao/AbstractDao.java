package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao<E extends Serializable, I extends Serializable>
    implements GenericRepository<E, I>
{
    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<E> entityClass;

    CriteriaBuilder builder;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.entityClass = (Class<E>) ((ParameterizedType)
        this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private CriteriaQuery<E> InitCriteria(){
        builder=this.entityManager.getCriteriaBuilder();
        return builder.createQuery(this.entityClass);
    }

    @Override
    public void Salva(E entity){
        this.entityManager.persist(entity);
        flushAndClear();
    }

    private void flushAndClear(){
        entityManager.flush();
        entityManager.clear();
    }
}
