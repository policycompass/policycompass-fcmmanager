package policycompass.fcmmanager.controllers;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import policycompass.fcmmanager.hibernate.HibernateUtil;
import policycompass.fcmmanager.models.FCMConceptActivator;;

public class FCMConceptActivators {

	public static List<FCMConceptActivator> retrieveFCMActivatorList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
         
        session.beginTransaction();
        Query query = session.createQuery("from fcmmanager_conceptactivators");
        @SuppressWarnings("unchecked")
        List<FCMConceptActivator> activator = query.list();
        session.clear();
	session.close();
        
		return activator;
	}	

}
