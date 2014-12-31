package policycompass.fcmmanager.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;

import policycompass.fcmmanager.models.*;;

public class FCMModels {

	public static FCMModelDetail retrieveFCMModel(int id) {
        FCMModelDetail model = new FCMModelDetail(id);
      
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

	public static FCMModelDetail createFCMModel(JSONObject jsonModel) {

		FCMModel model = new FCMModel();
		List<FCMConcept> concept = new ArrayList<FCMConcept>();
		List<FCMConnection> connection = new ArrayList<FCMConnection>();
		
		Date date1=new Date();
        
		int modelID = getFCMModelID();
        int conceptID = getConceptID();
        int connectionID = getConnectionID();
        
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

	    try {
		    model.setFCMModelID(modelID);
			model.setTitle(jsonModel.getJSONObject("data").get("ModelTitle").toString());
			model.setDescription(jsonModel.getJSONObject("data").get("ModelDesc").toString());
			model.setKeywords(jsonModel.getJSONObject("data").get("ModelKeywords").toString());
			model.setUserID(Integer.parseInt(jsonModel.getJSONObject("data").get("userID").toString()));
			model.setDateAddedtoPC(date1);
			model.setViewsCount(0);
			
			JSONArray concepts = jsonModel.getJSONObject("data").getJSONArray("concepts");
			for(int i=0;i<concepts.length();i++){
				FCMConcept con = new FCMConcept();
				JSONObject ob= concepts.getJSONObject(i);
				
				con.setFCMModelID(modelID);
				con.setConceptID(conceptID+Integer.parseInt(ob.getString("Id").substring(1)));
				con.setTitle(ob.getString("title"));
				con.setDescription(ob.getString("description"));
//				con.setInput(Double.parseDouble(ob.getString("input")));
				con.setInput(0.0);
				con.setOutput(0.0);
//				con.setFixedOutput(Double.parseDouble(ob.getString("fixedoutput")));
				con.setFixedOutput(false);
				con.setActivatorID(Integer.parseInt(ob.getString("activetor")));
				con.setDateAddedtoPC(date1);
				con.setUserID(Integer.parseInt(jsonModel.getJSONObject("data").get("userID").toString()));
				con.setViewsCount(0);
				concept.add(con);
			}			

			JSONArray connections = jsonModel.getJSONObject("data").getJSONArray("connections");
			for(int i=0;i<connections.length();i++){
				FCMConnection con = new FCMConnection();
				JSONObject ob= connections.getJSONObject(i);
				
				con.setFCMModelID(modelID);
				con.setConnectionID(connectionID+Integer.parseInt(ob.getString("Id").substring(1)));
				con.setTitle("Conection");
				con.setConceptFrom(conceptID+Integer.parseInt(ob.getJSONObject("source").getString("Id").substring(1)));
				con.setConceptTo(conceptID+Integer.parseInt(ob.getJSONObject("destination").getString("Id").substring(1)));
				con.setWeighted(Double.parseDouble(ob.getString("weight")));
				con.setDateAddedtoPC(date1);
				con.setUserID(Integer.parseInt(jsonModel.getJSONObject("data").get("userID").toString()));
				con.setViewsCount(0);
				connection.add(con);
			}			
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        session.save(model);
		for (int i=0;i<concept.size();i++)
		{
	        session.save(concept.get(i));
		}
		for (int i=0;i<connection.size();i++)
		{
	        session.save(connection.get(i));
		}
        session.getTransaction().commit();
        sessionFactory.close();			
	
        return(retrieveFCMModel(modelID));
	}	

	public static void deleteFCMModel(int id) {
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
        Query qModel = session.createQuery("from fcmmanager_models where fcmmodelid= :id");
        qModel.setInteger("id", id);
        FCMModel model = (FCMModel) qModel.uniqueResult();
        
        Query qConcept = session.createQuery("from fcmmanager_concepts where fcmmodelid= :id");
        qConcept.setInteger("id", id);
        @SuppressWarnings("unchecked")
        List<FCMConcept> concept = qConcept.list();
        
        Query qConnection = session.createQuery("from fcmmanager_connections where fcmmodelid= :id");
        qConnection.setInteger("id", id);
        @SuppressWarnings("unchecked")
        List<FCMConnection> connection = qConnection.list();

        session.delete(model);
		for (int i=0;i<concept.size();i++)
		{
	        session.delete(concept.get(i));
		}
		for (int i=0;i<connection.size();i++)
		{
	        session.delete(connection.get(i));
		}
        session.getTransaction().commit();
        sessionFactory.close();			
	}
	
	public static int getFCMModelID() {
		int modelID=0;
		
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

	    Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Criteria criteria = session
	    .createCriteria(FCMModel.class)
	    .setProjection(Projections.max("FCMModelID"));
        if (criteria.uniqueResult()==null)
        {
        	modelID=0;
        } else {
            modelID = (Integer)criteria.uniqueResult();
        }
        	
        session.close();
        sessionFactory.close();			
        
		return (modelID+1);
	}
	
	public static int getConceptID() {
		int conceptID=0;
		
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

	    Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Criteria criteria = session
	    .createCriteria(FCMConcept.class)
	    .setProjection(Projections.max("ConceptID"));
        if (criteria.uniqueResult()==null)
        {
        	conceptID=0;
        } else {
            conceptID = (Integer)criteria.uniqueResult();
        }
        session.close();
        sessionFactory.close();			
        
		return (conceptID+1);
	}
	
	public static int getConnectionID() {
		int connectionID=0;
		
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

	    Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Criteria criteria = session
	    .createCriteria(FCMConnection.class)
	    .setProjection(Projections.max("ConnectionID"));
        if (criteria.uniqueResult()==null)
        {
        	connectionID=0;
        } else {
            connectionID = (Integer)criteria.uniqueResult();
        }
        session.close();
        sessionFactory.close();			
        
		return (connectionID+1);
	}
	
	public static FCMModel LoadData() {
		FCMConceptActivator[] activator = new FCMConceptActivator[8];
		FCMModel[] model = new FCMModel[2];
		FCMConcept[] concept = new FCMConcept[29];
		FCMConnection[] connection = new FCMConnection[40];
		Date date1=new Date();
        
		for (int i=0;i<8;i++)
		{
			activator[i] = new FCMConceptActivator();
		}
		activator[0].setTitle("Cauchy Activator");
		activator[1].setTitle("Gaussian Activator");
		activator[2].setTitle("Hyperbolic Tangent Activator");
		activator[3].setTitle("Interval Activator");
		activator[4].setTitle("Linear Activator");
		activator[5].setTitle("Nary Activator");
		activator[6].setTitle("Sigmoid Activator");
		activator[7].setTitle("Signum Activator");
		for (int i=0;i<8;i++)
		{
			activator[i].setActivatorID(i+1);
			activator[i].setUserID(1);
			activator[i].setDateAddedtoPC(date1);
			activator[i].setDateModified(null);
			activator[i].setViewsCount(0);
		}
		
		for (int i=0;i<2;i++)
		{
			 model[i] = new FCMModel();
		}
		for (int i=0;i<29;i++)
		{
			concept[i] = new FCMConcept();
		}
		for (int i=0;i<40;i++)
		{
			connection[i] = new FCMConnection();
		}

		int modelID = getFCMModelID();
        int conceptID = getConceptID();
        int connectionID = getConnectionID();
        
		model[0].setTitle("Cambridgeshire County Council Field Trial");
		model[0].setDescription("This is an example fuzzy cognitive map (FCM) of the policy model for the proposed CCC field trial.");
		model[0].setKeywords("Community Learning, Skill, Deprivation, Unemployment, Current Availability of Learning Provision, Historical Data, Local Knowledge of Stakeholders, Facilities");

		model[1].setTitle("Leningrad Region Field Trial");
		model[1].setDescription("This is an example fuzzy cognitive map (FCM) of the policy model for the proposed Leningrad Region field trial.");
		model[1].setKeywords("");

		for (int i=0;i<2;i++)
		{
			model[i].setFCMModelID(modelID+i);
			model[i].setUserID(1);
			model[i].setDateAddedtoPC(date1);
			model[i].setDateModified(null);
			model[i].setViewsCount(0);
		}
		
		concept[0].setTitle("Proposal 1");
		concept[1].setTitle("Proposal 2");
		concept[2].setTitle("Proposal n");
		concept[3].setTitle("Number of programme specialized for IT beginer");
		concept[4].setTitle("Ratio of disadvantage learners");
		concept[5].setTitle("Number of programme to social security beneficiary");
		concept[6].setTitle("Personalised learning");
		concept[7].setTitle("Digital literacy");
		concept[8].setTitle("Social renewal");
		concept[9].setTitle("Financial literacy");
		concept[10].setTitle("Strong community");
		concept[11].setTitle("Social inclusion");
		concept[12].setTitle("Health and well being");
		concept[13].setTitle("Aspiration");
		concept[14].setTitle("Employment skills");
		concept[15].setTitle("Vulnerable group");
		concept[16].setTitle("Quality of life");
		concept[17].setTitle("Initiative 1 (e-workflow introduction)");
		concept[18].setTitle("Initiative 2 (number of e-services)");
		concept[19].setTitle("Initiative 3 (Penetration rate of broadband nets)");
		concept[20].setTitle("Initiative 4 (citizen’s e-skills improvement)");
		concept[21].setTitle("Government spending");
		concept[22].setTitle("Speed of public services delivery");
		concept[23].setTitle("Level of public services accessibility");
		concept[24].setTitle("Spending on economic development");
		concept[25].setTitle("Expenditure on social protection");
		concept[26].setTitle("Level of citizens satisfaction with the activities of the authorities");
		concept[27].setTitle("Regional GDP");
		concept[28].setTitle("Quality of life");

		for (int i=0;i<29;i++)
		{
			if (i<17) {
				concept[i].setFCMModelID(modelID);
			} else {
				concept[i].setFCMModelID(modelID+1);
			}
			concept[i].setConceptID(conceptID+i);
			concept[i].setDescription("");
			concept[i].setActivatorID(1);
			concept[i].setInput(0.0);
			concept[i].setOutput(0.0);
			concept[i].setFixedOutput(false);
			concept[i].setMetricID(1);
			concept[i].setPositionX((i+1)*100);
			concept[i].setPositionY((i+1)*100);
			concept[i].setUserID(1);
			concept[i].setDateAddedtoPC(date1);
			concept[i].setDateModified(null);
			concept[i].setViewsCount(0);
		}
		
		connection[0].setConceptFrom(conceptID-1+1);
		connection[0].setConceptTo(conceptID-1+4);
		connection[0].setWeighted(0.4);

		connection[1].setConceptFrom(conceptID-1+1);
		connection[1].setConceptTo(conceptID-1+5);
		connection[1].setWeighted(0.8);

		connection[2].setConceptFrom(conceptID-1+2);
		connection[2].setConceptTo(conceptID-1+5);
		connection[2].setWeighted(0.2);

		connection[3].setConceptFrom(conceptID-1+3);
		connection[3].setConceptTo(conceptID-1+11);
		connection[3].setWeighted(0.8);

		connection[4].setConceptFrom(conceptID-1+4);
		connection[4].setConceptTo(conceptID-1+8);
		connection[4].setWeighted(0.8);

		connection[5].setConceptFrom(conceptID-1+5);
		connection[5].setConceptTo(conceptID-1+10);
		connection[5].setWeighted(0.5);

		connection[6].setConceptFrom(conceptID-1+6);
		connection[6].setConceptTo(conceptID-1+10);
		connection[6].setWeighted(0.7);

		connection[7].setConceptFrom(conceptID-1+7);
		connection[7].setConceptTo(conceptID-1+14);
		connection[7].setWeighted(0.5);

		connection[8].setConceptFrom(conceptID-1+7);
		connection[8].setConceptTo(conceptID-1+15);
		connection[8].setWeighted(0.9);

		connection[9].setConceptFrom(conceptID-1+8);
		connection[9].setConceptTo(conceptID-1+9);
		connection[9].setWeighted(0.5);

		connection[10].setConceptFrom(conceptID-1+8);
		connection[10].setConceptTo(conceptID-1+16);
		connection[10].setWeighted(0.4);

		connection[11].setConceptFrom(conceptID-1+9);
		connection[11].setConceptTo(conceptID-1+17);
		connection[11].setWeighted(0.8);

		connection[12].setConceptFrom(conceptID-1+10);
		connection[12].setConceptTo(conceptID-1+9);
		connection[12].setWeighted(0.5);

		connection[13].setConceptFrom(conceptID-1+10);
		connection[13].setConceptTo(conceptID-1+16);
		connection[13].setWeighted(0.5);

		connection[14].setConceptFrom(conceptID-1+11);
		connection[14].setConceptTo(conceptID-1+16);
		connection[14].setWeighted(0.5);

		connection[15].setConceptFrom(conceptID-1+11);
		connection[15].setConceptTo(conceptID-1+17);
		connection[15].setWeighted(0.5);

		connection[16].setConceptFrom(conceptID-1+12);
		connection[16].setConceptTo(conceptID-1+11);
		connection[16].setWeighted(0.5);

		connection[17].setConceptFrom(conceptID-1+12);
		connection[17].setConceptTo(conceptID-1+17);
		connection[17].setWeighted(0.5);

		connection[18].setConceptFrom(conceptID-1+13);
		connection[18].setConceptTo(conceptID-1+17);
		connection[18].setWeighted(0.5);

		connection[19].setConceptFrom(conceptID-1+11);
		connection[19].setConceptTo(conceptID-1+9);
		connection[19].setWeighted(0.5);

		connection[20].setConceptFrom(conceptID-1+15);
		connection[20].setConceptTo(conceptID-1+14);
		connection[20].setWeighted(0.7);

		connection[21].setConceptFrom(conceptID-1+18);
		connection[21].setConceptTo(conceptID-1+22);
		connection[21].setWeighted(-0.2);

		connection[22].setConceptFrom(conceptID-1+18);
		connection[22].setConceptTo(conceptID-1+23);
		connection[22].setWeighted(0.8);

		connection[23].setConceptFrom(conceptID-1+19);
		connection[23].setConceptTo(conceptID-1+22);
		connection[23].setWeighted(-0.3);

		connection[24].setConceptFrom(conceptID-1+19);
		connection[24].setConceptTo(conceptID-1+23);
		connection[24].setWeighted(0.4);

		connection[25].setConceptFrom(conceptID-1+19);
		connection[25].setConceptTo(conceptID-1+24);
		connection[25].setWeighted(0.7);

		connection[26].setConceptFrom(conceptID-1+20);
		connection[26].setConceptTo(conceptID-1+22);
		connection[26].setWeighted(0.1);

		connection[27].setConceptFrom(conceptID-1+20);
		connection[27].setConceptTo(conceptID-1+23);
		connection[27].setWeighted(0.2);

		connection[28].setConceptFrom(conceptID-1+20);
		connection[28].setConceptTo(conceptID-1+24);
		connection[28].setWeighted(0.4);

		connection[29].setConceptFrom(conceptID-1+21);
		connection[29].setConceptTo(conceptID-1+24);
		connection[29].setWeighted(0.6);

		connection[30].setConceptFrom(conceptID-1+22);
		connection[30].setConceptTo(conceptID-1+25);
		connection[30].setWeighted(0.1);

		connection[31].setConceptFrom(conceptID-1+22);
		connection[31].setConceptTo(conceptID-1+26);
		connection[31].setWeighted(0.1);

		connection[32].setConceptFrom(conceptID-1+23);
		connection[32].setConceptTo(conceptID-1+27);
		connection[32].setWeighted(0.3);

		connection[33].setConceptFrom(conceptID-1+24);
		connection[33].setConceptTo(conceptID-1+27);
		connection[33].setWeighted(0.4);

		connection[34].setConceptFrom(conceptID-1+25);
		connection[34].setConceptTo(conceptID-1+28);
		connection[34].setWeighted(0.4);

		connection[35].setConceptFrom(conceptID-1+26);
		connection[35].setConceptTo(conceptID-1+29);
		connection[35].setWeighted(0.5);

		connection[36].setConceptFrom(conceptID-1+27);
		connection[36].setConceptTo(conceptID-1+29);
		connection[36].setWeighted(0.4);

		connection[37].setConceptFrom(conceptID-1+28);
		connection[37].setConceptTo(conceptID-1+26);
		connection[37].setWeighted(0.6);

		connection[38].setConceptFrom(conceptID-1+28);
		connection[38].setConceptTo(conceptID-1+27);
		connection[38].setWeighted(0.3);

		connection[39].setConceptFrom(conceptID-1+28);
		connection[39].setConceptTo(conceptID-1+29);
		connection[39].setWeighted(0.6);

		for (int i=0;i<40;i++)
		{
			if (i<21) {
				connection[i].setFCMModelID(modelID);
			} else {
				connection[i].setFCMModelID(modelID+1);
			}
			connection[i].setTitle("Connection-" + (connectionID+i));
			connection[i].setConnectionID(connectionID+i);
			connection[i].setUserID(1);
			connection[i].setDateAddedtoPC(date1);
			connection[i].setDateModified(null);
			connection[i].setViewsCount(0);
		}
         
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
         
        session.beginTransaction();
		for (int i=0;i<8;i++)
		{
			session.save(activator[i]);
		}
		for (int i=0;i<2;i++)
		{
	        session.save(model[i]);
		}
		for (int i=0;i<29;i++)
		{
	        session.save(concept[i]);
		}
		for (int i=0;i<40;i++)
		{
	        session.save(connection[i]);
		}
        session.getTransaction().commit();
        sessionFactory.close();
        return model[0];
	}		
}


