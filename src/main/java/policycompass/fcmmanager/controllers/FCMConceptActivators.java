package policycompass.fcmmanager.controllers;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import policycompass.fcmmanager.models.FCMConceptActivator;;

public class FCMConceptActivators {

	public static List<FCMConceptActivator> retrieveFCMActivatorList() {
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        Query query = session.createQuery("from fcmmanager_conceptactivators");
        @SuppressWarnings("unchecked")
        List<FCMConceptActivator> activator = query.list();
        session.clear();
        sessionFactory.close();			
        
		return activator;
	}	

}
