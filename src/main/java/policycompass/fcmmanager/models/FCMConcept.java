package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Concepts")
@XmlType(propOrder = { "FCMModelID", "ConceptID", "Title", "Description", "Activator", "Input", "Output", "FixedOutput", "UserID", "DateAddedtoPC", "DateModified", "ViewsCount" })

@Entity (name="fcmmanager_concepts")
public class FCMConcept {

	@Id
	@Column(name = "ConceptID", unique = true, nullable = false)
	private int ConceptID;
	@Column(name = "FCMModelID", nullable = false)
	private int FCMModelID;
	@Column(name = "Title", nullable = false)
	private String Title;
	@Column(name = "Description", nullable = false)
	private String Description;
	@Column(name = "Activator", nullable = false)
	private String Activator;
	@Column(name = "Input", nullable = false)
	private double Input;
	@Column(name = "Output", nullable = false)
	private double Output;
	@Column(name = "FixedOutput", nullable = false)
	private double FixedOutput;
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

	public int getFCMModelID() { return FCMModelID; }
	public void setFCMModelID(int id) { this.FCMModelID = id; }

	public String getTitle() { return Title; }
	public void setTitle(String title) { this.Title = title; }

	public String getDescription() { return Description; }
	public void setDescription(String description) { this.Description = description; }

	public String getActivator() { return Activator; }
	public void setActivator(String activator) { this.Activator = activator; }

	public double getInput() { return Input; }
	public void setInput(double input) { this.Input = input; }

	public double getOutput() { return Output; }
	public void setOutput(double output) { this.Output = output; }

	public double getFixedOutput() { return FixedOutput; }
	public void setFixedOutput(double fixedoutput) { this.FixedOutput = fixedoutput; }

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
