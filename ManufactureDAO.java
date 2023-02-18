import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.*;
import java.awt.dnd.InvalidDnDOperationException;
import java.util.List;

public class ManufactureDAO implements Repository<Manufacture>{
    private SessionFactory sessionFactory;
    private Session session;

    public ManufactureDAO() {
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
        } catch (HeadlessException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean add(Manufacture item) {
        Transaction transaction = session.beginTransaction();
        boolean isSuccessful = session.save(item) != null;
        transaction.commit();
        return isSuccessful;
    }

    @Override
    public Manufacture get(int id) {
        return session.get(Manufacture.class, id);
    }

    @Override
    public List<Manufacture> getAll() {
        return session.createQuery("from Manufacture", Manufacture.class).list();
    }

    @Override
    public boolean remove(int id) {
        Transaction transaction = session.beginTransaction();
        Manufacture manufacture = session.get(Manufacture.class, id);
        if (manufacture != null) {
            session.delete(manufacture);
            transaction.commit();
            return true;
        }
        transaction.commit();
        return false;
    }

    @Override
    public boolean remove(Manufacture item) {
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
    public boolean update(Manufacture item) {
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

    public boolean allHasOver100Employees() {
        String query = "from Manufacture";
        for(Manufacture manufacture
                : session.createQuery(query, Manufacture.class).list()) {
            if (manufacture.getEmployee() < 100) {
                return false;
            }
        }
        return true;
    }

    public int getSumEmployees() {
        return Integer.parseInt(
                session.createQuery(
                        "select sum(mf.employee) from Manufacture mf",
                        String.class
                ).getSingleResult()
        );

    }

    public Manufacture getLastMeetCriteria() {
        List<Manufacture> manufactureList = session.createQuery(
                "from Manufacture mf " +
                        "where mf.location = \"us\" " +
                        "or mf.location = \"US\"",
                Manufacture.class
        ).list();
        if (manufactureList.size() > 0)
            return manufactureList.get(manufactureList.size()-1);
        else throw new InvalidDnDOperationException("There is no producer that meets the criteria.");
    }
}
