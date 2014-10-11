package policycompass.fcmmanager.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@XmlRootElement(name = "FCM Model")
//@XmlType(propOrder = { "fcmmodelid", "title", "description", "keywords", "userID", "DateAddedtoPC", "DateModified", "viewsCount" })
public class FCMModelDetail {

	FCMModel model;
	List<FCMConcept> concepts;
	List<FCMConnection> connections;
	
	public FCMModel getModel() {
		return model;
	}

	public void setModel(FCMModel model) {
		this.model = model;
	}

	public List<FCMConcept> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<FCMConcept> concepts) {
		this.concepts = concepts;
	}

	public List<FCMConnection> getConnections() {
		return connections;
	}

	public void setConnections(List<FCMConnection> connections) {
		this.connections = connections;
	}

	public FCMModelDetail(int modelID) {
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
        query.setInteger("id", modelID);
        model = (FCMModel) query.uniqueResult();
        session.clear();
        sessionFactory.close();			

		SessionFactory sessionFactoryConcept;
	    ServiceRegistry serviceRegistryConcept;

		Configuration configurationConcept = new Configuration();
		configurationConcept.configure();
        serviceRegistryConcept = new StandardServiceRegistryBuilder().applySettings(
        		configurationConcept.getProperties()).build();
        sessionFactoryConcept = configurationConcept.buildSessionFactory(serviceRegistryConcept);
        Session sessionConcept = sessionFactoryConcept.getCurrentSession();
         
        sessionConcept.beginTransaction();
        Query queryConcept = sessionConcept.createQuery("from fcmmanager_concepts where fcmmodelid= :id");
        queryConcept.setInteger("id", modelID);
        @SuppressWarnings("unchecked")
        List<FCMConcept> concept = queryConcept.list();
        concepts = concept;
        sessionConcept.clear();
        sessionFactoryConcept.close();			

        List<Integer> ConceptIDs = new ArrayList<Integer>();
        for (FCMConcept con : concept)
        {
        	ConceptIDs.add(con.getConceptID());
        }

        SessionFactory sessionFactoryConnection;
	    ServiceRegistry serviceRegistryConnection;

		Configuration configurationConnection = new Configuration();
		configurationConnection.configure();
        serviceRegistryConnection = new StandardServiceRegistryBuilder().applySettings(
                        configurationConnection.getProperties()).build();
        sessionFactoryConnection = configurationConnection.buildSessionFactory(serviceRegistryConnection);
        Session sessionConnection = sessionFactoryConnection.getCurrentSession();
         
        sessionConnection.beginTransaction();
        Query queryConnection = sessionConnection.createQuery("from fcmmanager_connections where fcmmodelid= :id");
        queryConnection.setParameter("id", modelID);
        @SuppressWarnings("unchecked")
        List<FCMConnection> connection = queryConnection.list();
        connections = connection;
        sessionConnection.clear();
        sessionFactoryConnection.close();			
	}
	
}
