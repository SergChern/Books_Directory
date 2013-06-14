package books.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import books.server.Author;
import books.server.Book;
import books.server.HibernateUtil;
import books.server.Snake;
import books.server.dao.AuthorDao;
import books.server.dao.BookDao;
import books.server.dao.IEntityDao;

@Service
public class ZZZService {
    private BookDao bookDao;
    private AuthorDao authorDao;
    private List snakes = new ArrayList();
    Session session = null;
    Query query = null;

    public ZZZService() {
    }

    public void setBookDao(IEntityDao bookDao) {
        this.bookDao = (BookDao) bookDao;
    }

    public void setAuthorDao(IEntityDao authorDao) {
        this.authorDao = (AuthorDao) authorDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public List getBooks() throws IllegalArgumentException {
        Criteria q = HibernateUtil.getCurSession().createCriteria(Book.class);
        return q.list();
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public List getAuthors() throws IllegalArgumentException {
        Criteria q = HibernateUtil.getCurSession().createCriteria(Author.class);
        return q.list();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Author getAuthor(Long id) throws IllegalArgumentException {
        return authorDao.load(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Book getBook(Long id) throws IllegalArgumentException {
        return bookDao.load(id);
    }

    public List<Snake> getSnake(String leksem) {
        if (snakes.size() == 1)
            snakes.add("hello");
        int i = snakes.indexOf(leksem);
        if (i == -1)
            snakes.add(leksem);
        else
            while (i + 1 < snakes.size())
                snakes.remove(i + 1);

        List<Snake> snakeList = new ArrayList();
        for (i = 0; i < snakes.size(); i++) {
            snakeList.add(new Snake((String) snakes.get(i)));
        }
        return snakeList;
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void saveOrUpdate(Object entity) {
        session = HibernateUtil.getCurrentSession();
        try {
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e; // or display error message
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Object entity) {
        session = HibernateUtil.getCurrentSession();
        try {
            session.delete(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e; // or display error message
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public List getBooks(Long idAuthor) throws IllegalArgumentException {
        session = HibernateUtil.getCurrentSession();
        String sql = session.getNamedQuery("getBooksAuthor").getQueryString();
        query = session.createSQLQuery(sql).addEntity(Book.class);
        return query.setLong("id", idAuthor).list();
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public List findBooks(String seekName) throws IllegalArgumentException {
        seekName = "%" + seekName + "%";
        session = HibernateUtil.getCurSession();
        String sql = session.getNamedQuery("findBooks").getQueryString();
        query = session.createSQLQuery(sql).addEntity(Book.class);
        return query.setString("name", seekName).list();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List getAuthorAll(Long id) throws IllegalArgumentException {
        if (id == null)
            id = new Long(0);

        session = HibernateUtil.getCurrentSession();
        String sql = session.getNamedQuery("getAuthorAll").getQueryString();
        query = session.createSQLQuery(sql).addEntity(Author.class);
        return query.setLong("id", id).list();
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void deleteAuthor(Long idAuthor, Long idBook) throws IllegalArgumentException {
        session = HibernateUtil.getCurrentSession();
        String sql = null;

        try {
            sql = session.getNamedQuery("deleteAuthor1").getQueryString();
            query = session.createSQLQuery(sql);
            query.setLong("id", idAuthor);
            query.executeUpdate();

            if (idBook != null) {
                sql = session.getNamedQuery("deleteAuthor2").getQueryString();
                query = session.createSQLQuery(sql);
                query.setLong("id", idBook);
                query.executeUpdate();
            }

            sql = session.getNamedQuery("deleteAuthor3").getQueryString();
            query = session.createSQLQuery(sql);
            query.setLong("id", idAuthor);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e; // or display error message
        }
    }
}
