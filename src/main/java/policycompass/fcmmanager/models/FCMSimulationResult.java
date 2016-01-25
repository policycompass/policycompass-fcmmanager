package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class FCMSimulationResult {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "FCMSimulation_id", nullable = false)
	private int FCMSimulation_id;
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

	public int getFCMSimulation_id() {
		return FCMSimulation_id;
	}

	public void setFCMSimulation_id(int fCMSimulation_id) {
		FCMSimulation_id = fCMSimulation_id;
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
