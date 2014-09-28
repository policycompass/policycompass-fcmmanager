package policycompass.fcmmanager.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import policycompass.fcmmanager.models.*;;

public class FCMModels {

	public static FCMModel retrieve(int id) {
		FCMModel result = null;
		String Query=null;
		try {
			
			DBConnection con = new DBConnection();
			Connection connection = con.getConnection();
			
			Query = "SELECT * FROM fcmmanager_models where \"FCMModelID\"=" + id;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(Query);
			if (resultSet.next()) {
				result = new FCMModel(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4),
						resultSet.getInt(5), resultSet.getInt(8));
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
//			logger.info(e.getMessage());
		}
		return result;
	}	

	 public static ArrayList<FCMModel> getAllModelsList()throws Exception {
		 ArrayList<FCMModel> ModelList = new ArrayList<FCMModel>();
		 try {
				DBConnection database= new DBConnection();
				Connection connection = database.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM fcmmanager_models");
				while (resultSet.next()) {
					ModelList.add(new FCMModel(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getString(4),
							resultSet.getInt(5), resultSet.getInt(8)));
				}
				resultSet.close();
				statement.close();
				connection.close();
		 
		 } catch (Exception e) {
			 throw e;
		 }
		 return ModelList;
	}

	public static FCMModel retrieveFCMModel(int id) {
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        Query query = session.createQuery("from fcmmanager_models where fcmmodelid= :id");
        query.setInteger("id", id);
        FCMModel model = (FCMModel) query.uniqueResult();
        session.clear();
        sessionFactory.close();			
        
		return model;
	}	

	public static List<FCMModel> retrieveFCMModelList() {
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        Query query = session.createQuery("from fcmmanager_models");
        @SuppressWarnings("unchecked")
        List<FCMModel> model = query.list();
        session.clear();
        sessionFactory.close();			
        
		return model;
	}	

	public static void createFCMModel(FCMModel model) {

		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        sessionFactory.close();			
	}	

	public static FCMModel LoadData() {
		
		FCMModel model = new FCMModel();
		FCMConcept concept1 = new FCMConcept();
		FCMConcept concept2 = new FCMConcept();
		FCMConcept concept3 = new FCMConcept();
		FCMConcept concept4 = new FCMConcept();
		FCMConnection connection1 = new FCMConnection();
		FCMConnection connection2 = new FCMConnection();
		FCMConnection connection3 = new FCMConnection();
		FCMConnection connection4 = new FCMConnection();
		
		Date date1=null;
		String s = "01012014";
        SimpleDateFormat sd = new SimpleDateFormat("MMddyyyy");
        try {
            date1 = sd.parse(s);
    	} catch (Exception e) {
        	  //do anything you want to handle the exception
    	}
        
		model.setFCMModelID(1);
		model.setTitle("First Model");
		model.setDescription("Model Description here");
		model.setKeywords("Model Keywords here");
		model.setUserID(1);
		model.setDateAddedtoPC(date1);
		model.setDateModified(null);
		model.setViewsCount(0);

		concept1.setFCMModelID(1);
		concept1.setConceptID(1);
		concept1.setTitle("Concept-1");
		concept1.setDescription("Concept Description here");
		concept1.setActivator("Activator here");
		concept1.setInput(0.0);
		concept1.setOutput(0.0);
		concept1.setFixedOutput(0.0);
		concept1.setUserID(1);
		concept1.setDateAddedtoPC(date1);
		concept1.setDateModified(null);
		concept1.setViewsCount(0);

		concept2.setFCMModelID(1);
		concept2.setConceptID(2);
		concept2.setTitle("Concept-2");
		concept2.setDescription("Concept Description here");
		concept2.setActivator("Activator here");
		concept2.setInput(0.0);
		concept2.setOutput(0.0);
		concept2.setFixedOutput(0.0);
		concept2.setUserID(1);
		concept2.setDateAddedtoPC(date1);
		concept2.setDateModified(null);
		concept2.setViewsCount(0);

		concept3.setFCMModelID(1);
		concept3.setConceptID(3);
		concept3.setTitle("Concept-3");
		concept3.setDescription("Concept Description here");
		concept3.setActivator("Activator here");
		concept3.setInput(0.0);
		concept3.setOutput(0.0);
		concept3.setFixedOutput(0.0);
		concept3.setUserID(1);
		concept3.setDateAddedtoPC(date1);
		concept3.setDateModified(null);
		concept3.setViewsCount(0);

		concept4.setFCMModelID(1);
		concept4.setConceptID(4);
		concept4.setTitle("Concept-4");
		concept4.setDescription("Concept Description here");
		concept4.setActivator("Activator here");
		concept4.setInput(0.0);
		concept4.setOutput(0.0);
		concept4.setFixedOutput(0.0);
		concept4.setUserID(1);
		concept4.setDateAddedtoPC(date1);
		concept4.setDateModified(null);
		concept4.setViewsCount(0);

		connection1.setConnectionID(1);
		connection1.setTitle("Connection-1");
		connection1.setConceptFrom(1);
		connection1.setConceptTo(2);
		connection1.setWeighted(0.0);
		connection1.setUserID(1);
		connection1.setDateAddedtoPC(date1);
		connection1.setDateModified(null);
		connection1.setViewsCount(0);

		connection2.setConnectionID(2);
		connection2.setTitle("Connection-2");
		connection2.setConceptFrom(2);
		connection2.setConceptTo(3);
		connection2.setWeighted(0.0);
		connection2.setUserID(1);
		connection2.setDateAddedtoPC(date1);
		connection2.setDateModified(null);
		connection2.setViewsCount(0);

		connection3.setConnectionID(3);
		connection3.setTitle("Connection-3");
		connection3.setConceptFrom(3);
		connection3.setConceptTo(4);
		connection3.setWeighted(0.0);
		connection3.setUserID(1);
		connection3.setDateAddedtoPC(date1);
		connection3.setDateModified(null);
		connection3.setViewsCount(0);

		connection4.setConnectionID(4);
		connection4.setTitle("Connection-4");
		connection4.setConceptFrom(4);
		connection4.setConceptTo(1);
		connection4.setWeighted(0.0);
		connection4.setUserID(1);
		connection4.setDateAddedtoPC(date1);
		connection4.setDateModified(null);
		connection4.setViewsCount(0);

		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        session.save(model);
        session.save(concept1);
        session.save(concept2);
        session.save(concept3);
        session.save(concept4);
        session.save(connection1);
        session.save(connection2);
        session.save(connection3);
        session.save(connection4);
        session.getTransaction().commit();
        sessionFactory.close();
        return model;
	}	
}


