package dao;

import java.io.Serializable;

public interface GenericRepository<E extends Serializable, I extends Serializable> {

    void Salva(E entity);
}
