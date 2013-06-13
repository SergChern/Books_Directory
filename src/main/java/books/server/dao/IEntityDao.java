package books.server.dao;

import java.util.List;

public interface IEntityDao<E> {

    public List<E> find(String query);

    public E load(Long id);

    public E get(Long id);

    public void save(E el);

    public void saveOrUpdate(E el);

    public void delete(E el);

    public List<E> findAll(String obj);
}
