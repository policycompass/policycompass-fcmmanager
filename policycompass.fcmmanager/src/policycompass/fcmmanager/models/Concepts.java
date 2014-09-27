package policycompass.fcmmanager.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Concepts")
@XmlType(propOrder = { "FCMModelID", "ConceptID", "Title", "Description", "Activator", "Input", "Outpit", "FixedOutput", "UserID", "ViewsCount" })

@Entity
public class Concepts {

	private int FCMModelID, ConceptID, UserID, ViewsCount;
	private String Title, Description, Activator;
	private double Input, Output, FixedOutput;

	public int getFCMModelID() { return FCMModelID; }
	public void setFCMModelID(int id) { this.FCMModelID = id; }

	@Id
	public int getConceptID() { return ConceptID; }
	public void setConceptID(int id) { this.ConceptID = id; }

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

	public int getViewsCount() { return ViewsCount; }
	public void setViewsCount(int viewscount) { this.ViewsCount = viewscount; }

	@Override
	public String toString() { return getFCMModelID() + ", " + getTitle() + ", " + getDescription(); }
}
