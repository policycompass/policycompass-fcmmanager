package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Simulation Results")
@XmlType(propOrder = { "id", "FCMModel_id", "Concept_id", "Iteration_id", "Input", "Output", "UserID", "DateAddedtoPC", "DateModified" })

@Entity (name="fcmmanager_simulationresult")
public class FCMSimulationResult {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "FCMModel_id", nullable = false)
	private int FCMModel_id;
	@Column(name = "Concept_id", nullable = false)
	private int Concept_id;
	@Column(name = "Iteration_id", nullable = false)
	private int Iteration_id;
	@Column(name = "Input", nullable = false)
	private double Input;
	@Column(name = "Output", nullable = false)
	private double Output;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = true)
	private Date DateModified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFCMModel_id() {
		return FCMModel_id;
	}
	public void setFCMModel_id(int FCMModelId) {
		FCMModel_id = FCMModelId;
	}
	public int getConceptID() {
		return Concept_id;
	}
	public void setConceptID(int conceptID) {
		Concept_id = conceptID;
	}
	public int getIteration_id() {
		return Iteration_id;
	}
	public void setIteration_id(int iteration_id) {
		Iteration_id = iteration_id;
	}
	public double getInput() {
		return Input;
	}
	public void setInput(double input) {
		Input = input;
	}
	public double getOutput() {
		return Output;
	}
	public void setOutput(double output) {
		Output = output;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public Date getDateAddedtoPC() {
		return DateAddedtoPC;
	}
	public void setDateAddedtoPC(Date dateAddedtoPC) {
		DateAddedtoPC = dateAddedtoPC;
	}
	public Date getDateModified() {
		return DateModified;
	}
	public void setDateModified(Date dateModified) {
		DateModified = dateModified;
	}
}
