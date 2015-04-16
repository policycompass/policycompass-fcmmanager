package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class FCMSimulationConnection {

	@Id
	@Column(name = "ConnectionID", unique = true, nullable = false)
	private int ConnectionID;
	@Column(name = "id", nullable = false)
	private int id;
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
	@Column(name = "DateModified", nullable = false)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;

	public int getConnectionID() {
		return ConnectionID;
	}
	public void setConnectionID(int connectionID) {
		ConnectionID = connectionID;
	}

	public int getId() { return id; }
	public void setId(int fcmid) { this.id = fcmid; }

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
