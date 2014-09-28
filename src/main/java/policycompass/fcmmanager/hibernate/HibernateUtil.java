package policycompass.fcmmanager.hibernate;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static ServiceRegistry serviceRegistry;

    static {

            try {

                    Configuration configuration = new Configuration();
                    configuration.configure();
                    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                                    configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Throwable th) {

                    System.err.println("Enitial SessionFactory creation failed" + th);

                    throw new ExceptionInInitializerError(th);

            }

    }

    public static SessionFactory getSessionFactory() {

            return sessionFactory;

    }

    public static void closeSessionFactory() {
    	if (sessionFactory != null) {
    		try {
    			StandardServiceRegistryBuilder.destroy(serviceRegistry);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
}
