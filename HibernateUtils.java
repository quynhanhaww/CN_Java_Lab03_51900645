import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = setUp();

    public HibernateUtils() {
        super();
    }
    private static SessionFactory setUp() throws HibernateException {
        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure(StandardServiceRegistryBuilder.DEFAULT_CFG_RESOURCE_NAME)
                .build();
        try {
            SessionFactory newSession = new MetadataSources(standardServiceRegistry)
                    .buildMetadata()
                    .buildSessionFactory();
            return newSession;
        } catch (HibernateException throwables) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            return null;
        }
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;
    }

    public static Session openSession(){
        return getSessionFactory().openSession();
    }

    public static void closeSession() {
        getSessionFactory().close();
    }
}
