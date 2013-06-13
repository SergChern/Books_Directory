package books.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import books.server.HibernateUtil;

public class BaseDao<E> extends HibernateDaoSupport implements IEntityDao<E> {
    private Class<E> elementClass;
    private Session session = null;

    public BaseDao(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    public List<E> find(String query) {
        return HibernateUtil.getSessionFactory().getCurrentSession().createSQLQuery(query).list();
    }

    public Session getCurSession() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive())
            session.beginTransaction();

        return session;
    }

    @Override
    public E load(Long id) {
        return (E) HibernateUtil.getSessionFactory().getCurrentSession().load(elementClass, id);
    }

    @Override
    public E get(Long id) {
        return (E) HibernateUtil.getSessionFactory().getCurrentSession().get(elementClass, id);
    }

    @Override
    public void save(E el) {
        HibernateUtil.getSessionFactory().getCurrentSession().save(el);
    }

    @Override
    public void saveOrUpdate(E el) {
        HibernateUtil.getSessionFactory().getCurrentSession().saveOrUpdate(el);
    }

    @Override
    public void delete(E el) {
        HibernateUtil.getSessionFactory().getCurrentSession().delete(el);
    }

    @Override
    public List<E> findAll(String obj) {
        return getCurSession().createQuery(obj).list();
    }
}
