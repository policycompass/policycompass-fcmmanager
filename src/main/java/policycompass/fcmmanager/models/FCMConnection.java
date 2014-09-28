package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Connections")
@XmlType(propOrder = { "ConnectionID", "Title", "ConceptFrom", "ConceptTo", "Weighted", "UserID", "DateAddedtoPC", "DateModified", "ViewsCount" })

@Entity (name="fcmmanager_connections")
public class FCMConnection {

	@Id
	@Column(name = "ConnectionID", unique = true, nullable = false)
	private int ConnectionID;
	@Column(name = "Title", nullable = false)
	private String Title;
	@Column(name = "ConceptFrom", nullable = false)
	private int ConceptFrom;
	@Column(name = "ConceptTo", nullable = false)
	private int ConceptTo;
	@Column(name = "Weighted", nullable = false)
	private double Weighted;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = true)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;

	public int getConnectionID() {
		return ConnectionID;
	}
	public void setConnectionID(int connectionID) {
		ConnectionID = connectionID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getConceptFrom() {
		return ConceptFrom;
	}
	public void setConceptFrom(int conceptFrom) {
		ConceptFrom = conceptFrom;
	}
	public int getConceptTo() {
		return ConceptTo;
	}
	public void setConceptTo(int conceptTo) {
		ConceptTo = conceptTo;
	}
	public double getWeighted() {
		return Weighted;
	}
	public void setWeighted(double weighted) {
		Weighted = weighted;
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
	public int getViewsCount() {
		return ViewsCount;
	}
	public void setViewsCount(int viewsCount) {
		ViewsCount = viewsCount;
	}
}
