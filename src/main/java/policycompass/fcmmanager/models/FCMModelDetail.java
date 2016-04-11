package policycompass.fcmmanager.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import policycompass.fcmmanager.hibernate.HibernateUtil;

public class FCMModelDetail {

	FCMModel model;
	List<FCMModelInDomain> domains;
	List<FCMConcept> concepts;
	List<FCMConnection> connections;
	List<FCMConceptIndividual> conceptindividuals;

	public FCMModel getModel() {
		return model;
	}

	public void setModel(FCMModel model) {
		this.model = model;
	}

	public List<FCMModelInDomain> getDomains() {
		return domains;
	}

	public void setDomains(List<FCMModelInDomain> domain) {
		this.domains = domain;
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

	public List<FCMConceptIndividual> getConceptindividuals() {
		return conceptindividuals;
	}

	public void setConceptindividuals(List<FCMConceptIndividual> conceptindividuals) {
		this.conceptindividuals = conceptindividuals;
	}
/*
	public FCMModelDetail(int modelID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from fcmmanager_models where id= :id");
		query.setInteger("id", modelID);
		model = (FCMModel) query.uniqueResult();

		Query queryDomain = session.createQuery("from fcmmanager_modelindomain where fcmmodel_id= :id");
		queryDomain.setInteger("id", modelID);
		@SuppressWarnings("unchecked")
		List<FCMModelInDomain> domain = queryDomain.list();
		domains = domain;

		Query queryConcept = session.createQuery("from fcmmanager_concepts where fcmmodel_id= :id");
		queryConcept.setInteger("id", modelID);
		@SuppressWarnings("unchecked")
		List<FCMConcept> concept = queryConcept.list();
		concepts = concept;

		List<Integer> ConceptIDs = new ArrayList<Integer>();
		for (FCMConcept con : concept) {
			ConceptIDs.add(con.getId());
		}

		Query queryConceptIndividual = session
				.createQuery("from fcmmanager_conceptindividuals where concept_id in ( :mId)");
		queryConceptIndividual.setParameterList("mId", ConceptIDs);
		@SuppressWarnings("unchecked")
		List<FCMConceptIndividual> conceptIndividual = queryConceptIndividual.list();
		conceptindividuals = conceptIndividual;

		Query queryConnection = session.createQuery("from fcmmanager_connections where fcmmodel_id= :id");
		queryConnection.setParameter("id", modelID);
		@SuppressWarnings("unchecked")
		List<FCMConnection> connection = queryConnection.list();
		connections = connection;
		session.clear();
		session.close();
	}
        */

	public FCMModelDetail(String userPath, int modelID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//Query query = session.createQuery("from fcmmanager_models where id= :id and userPath=:userPath");
		Query query = session.createQuery("from fcmmanager_models where id= :id");
		query.setInteger("id", modelID);
		//query.setString("userPath", userPath);
		model = (FCMModel) query.uniqueResult();
		if(model!=null){
			Query queryDomain = session.createQuery("from fcmmanager_modelindomain where fcmmodel_id= :id");
			queryDomain.setInteger("id", modelID);
			@SuppressWarnings("unchecked")
			List<FCMModelInDomain> domain = queryDomain.list();
			domains = domain;

			Query queryConcept = session.createQuery("from fcmmanager_concepts where fcmmodel_id= :id");
			queryConcept.setInteger("id", modelID);
			@SuppressWarnings("unchecked")
			List<FCMConcept> concept = queryConcept.list();
			concepts = concept;

			List<Integer> ConceptIDs = new ArrayList<Integer>();
			for (FCMConcept con : concept) {
				ConceptIDs.add(con.getId());
			}

			Query queryConceptIndividual = session
					.createQuery("from fcmmanager_conceptindividuals where concept_id in ( :mId)");
			queryConceptIndividual.setParameterList("mId", ConceptIDs);
			@SuppressWarnings("unchecked")
			List<FCMConceptIndividual> conceptIndividual = queryConceptIndividual.list();
			conceptindividuals = conceptIndividual;

			Query queryConnection = session.createQuery("from fcmmanager_connections where fcmmodel_id= :id");
			queryConnection.setParameter("id", modelID);
			@SuppressWarnings("unchecked")

			List<FCMConnection> connection = queryConnection.list();
			connections = connection;
		}
		session.clear();
		session.close();
	}
}