import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.management.Query;
import java.awt.*;
import java.util.List;

public class PhoneDAO implements Repository<Phone>{

    private SessionFactory sessionFactory;
    private Session session;

    public PhoneDAO() {
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
        } catch (HeadlessException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public boolean add(Phone item) {
        Transaction transaction = session.beginTransaction();
        boolean isSuccessful = session.save(item) != null;
        transaction.commit();
        return isSuccessful;
    }

    @Override
    public Phone get(int id) {
        return session.get(Phone.class, id);
    }

    @Override
    public List<Phone> getAll() {
        return session.createQuery("from Phone", Phone.class).list();
    }

    @Override
    public boolean remove(int id) {
        Transaction transaction = session.beginTransaction();
        Phone phone = session.get(Phone.class, id);
        if (phone != null) {
            session.delete(phone);
            transaction.commit();
            return true;
        }
        transaction.commit();
        return false;
    }

    @Override
    public boolean remove(Phone item) {
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(item);
            transaction.commit();
            return true;
        } catch (HibernateException throwables) {
            transaction.commit();
            return false;
        }
    }

    @Override
    public boolean update(Phone item) {
        Transaction transaction = session.beginTransaction();
        try {
            session.update(item);
            transaction.commit();
            return true;
        } catch (HibernateException throwables) {
            transaction.commit();
            return false;
        }
    }

    public Phone getHighestSellingPrice() {
        return session.createQuery(
                "select max(phone.price) from Phone phone", Phone.class
        ).getSingleResultOrNull();
    }

    public List<Phone> sortedByCountryName() {
        return session.createQuery(
                "from Phone phone order by phone.country asc , phone.price desc"
        ).list();
    }

    public boolean containPriceAbove50Millions() {
        return session.createQuery(
                "from Phone phone where phone.price > 50000000", Phone.class
        ).list().size() > 0;
    }

    public Phone getFirstMeetCriteria() {
        return session.createQuery(
                "from Phone phone " +
                        "where phone.color = \"Pink\" " +
                        "or phone.color = \"pink\" " +
                        "and phone.price > 15000000",
                Phone.class
        ).getSingleResultOrNull();
    }


}
