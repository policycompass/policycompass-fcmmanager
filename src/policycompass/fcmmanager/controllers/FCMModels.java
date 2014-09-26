package policycompass.fcmmanager.controllers;

import java.sql.*;
import java.util.ArrayList;

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

	public static FCMModel retrieveHibernate(int id) {
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        Query query = session.createQuery("from fcmmanager_models where FCMModelID= :id");
        query.setLong("id", id);
        FCMModel emp = (FCMModel) query.uniqueResult();
        session.clear();
        sessionFactory.close();			
        
		return emp;
	}	

	public static void createFCMModel(int id) {
		FCMModel model = new FCMModel(id, "Second Model", "Model Description here", "keyword here", 1, 0);

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

}


