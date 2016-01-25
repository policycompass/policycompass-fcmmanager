package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name="fcmmanager_conceptindividuals")

public class FCMConceptIndividual {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "Concept_id", nullable = false)
	private int Concept_id;
	@Column(name = "Individual_id", nullable = false)
	private int Individual_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConceptID() {
		return Concept_id;
	}

	public void setConceptID(int conceptID) {
		Concept_id = conceptID;
	}

	public int getIndividualID() {
		return Individual_id;
	}

	public void setIndividualID(int individualID) {
		Individual_id = individualID;
	}

}
