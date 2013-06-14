package books.server;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static User user = null;

    private static SessionFactory buildSessionFactory() {
        /*
         * Turning off the hibernate logging - now it shows only warnings.
         */
        Logger logger = Logger.getLogger("org.hibernate");
        logger.setLevel(Level.WARNING);

        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        Session session = getSessionFactory().getCurrentSession();
        return session;
    }

    public static Session getCurSession() {
        Session session = getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive())
            session.beginTransaction();

        return session;
    }

    public static boolean isName(HttpServletRequest request, String summitName) {
        Enumeration fields = request.getParameterNames();

        if (fields.hasMoreElements()) {
            while (fields.hasMoreElements()) {
                String field = (String) fields.nextElement();
                if (field.equals(summitName))
                    return true;
            }
        }
        return false;
    }

    public static String getValueName(HttpServletRequest request, String summitName) {
        String valueName = null;
        Enumeration fields = request.getParameterNames();

        if (fields.hasMoreElements()) {
            while (fields.hasMoreElements()) {
                String field = (String) fields.nextElement();
                if (field.equals(summitName))
                    return request.getParameter(field);
            }
        }
        return null;
    }

    public static String getUsername() {
        if (user == null)
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

}
