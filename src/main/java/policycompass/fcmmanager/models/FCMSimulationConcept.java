package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class FCMSimulationConcept {

	@Id
	@Column(name = "ConceptID", unique = true, nullable = false)
	private int ConceptID;
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "Title", nullable = false)
	private String Title;
	@Column(name = "Description", nullable = false)
	private String Description;
	@Column(name = "ActivatorID", nullable = false)
	private int ActivatorID;
	@Column(name = "Input", nullable = false)
	private double Input;
	@Column(name = "Output", nullable = false)
	private double Output;
	@Column(name = "FixedOutput", nullable = false)
	private boolean FixedOutput;
	@Column(name = "MetricID", nullable = false)
	private int MetricID;
	@Column(name = "PositionX", nullable = false)
	private int PositionX;
	@Column(name = "PositionY", nullable = false)
	private int PositionY;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = true)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;

	public int getConceptID() { return ConceptID; }
	public void setConceptID(int id) { this.ConceptID = id; }

	public int getFCMModelID() { return id; }
	public void setFCMModelID(int fcmid) { this.id = fcmid; }

	public String getTitle() { return Title; }
	public void setTitle(String title) { this.Title = title; }

	public String getDescription() { return Description; }
	public void setDescription(String description) { this.Description = description; }

	public int getActivatorID() { return ActivatorID; }
	public void setActivatorID(int activatorID) { this.ActivatorID = activatorID; }
	
	public double getInput() { return Input; }
	public void setInput(double input) { this.Input = input; }

	public double getOutput() { return Output; }
	public void setOutput(double output) { this.Output = output; }

	public boolean getFixedOutput() { return FixedOutput; }
	public void setFixedOutput(boolean fixedOutput) { FixedOutput = fixedOutput; }
	
	public int getMetricID() { return MetricID; }
	public void setMetricID(int metricID) {this.MetricID = metricID; }

	public int getPositionX() { return PositionX; }
	public void setPositionX(int positionX) { PositionX = positionX; }

	public int getPositionY() { return PositionY; }
	public void setPositionY(int positionY) { PositionY = positionY; }
	
	public int getUserID() { return UserID; }
	public void setUserID(int userid) { this.UserID = userid; }

	public Date getDateAddedtoPC() { return DateAddedtoPC; }
	public void setDateAddedtoPC(Date dateaddedtopc) { this.DateAddedtoPC = dateaddedtopc; }

	public Date getDateModified() { return DateModified; }
	public void setDateModified(Date datemodified) { this.DateModified = datemodified; }

	public int getViewsCount() { return ViewsCount; }
	public void setViewsCount(int viewscount) { this.ViewsCount = viewscount; }

	@Override
	public String toString() { return getFCMModelID() + ", " + getTitle() + ", " + getDescription(); }
}
