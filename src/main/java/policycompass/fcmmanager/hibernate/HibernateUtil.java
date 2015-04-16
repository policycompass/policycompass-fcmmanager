package policycompass.fcmmanager.hibernate;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static  
    {
    	try 
    	{
    		if (sessionFactory == null)
    		{
	            Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
	            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	            serviceRegistryBuilder.applySettings(configuration.getProperties());
	            ServiceRegistry serviceRegistry =  serviceRegistryBuilder.build();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    		}
    	} catch (Throwable th) {
            System.err.println("Initial SessionFactory creation failed." + th);
            throw new ExceptionInInitializerError(th);
    	}
    }

    public static SessionFactory getSessionFactory() { return sessionFactory; }

    public static void closeSessionFactory() {
    	if (sessionFactory != null) {
    		try {
    			getSessionFactory().close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
}
