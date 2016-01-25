package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name="fcmmanager_connections")
public class FCMConnection {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "FCMModel_id", nullable = false)
	private int FCMModel_id;
	@Column(name = "ConceptFrom", nullable = false)
	private int ConceptFrom;
	@Column(name = "ConceptTo", nullable = false)
	private int ConceptTo;
	@Column(name = "Weight", nullable = true)
	private String Weight;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = false)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;

	public int getId() {
		return id;
	}
	public void setId(int connectionID) {
		id = connectionID;
	}

	public int getFCMModelID() { return FCMModel_id; }
	public void setFCMModelID(int fcmid) { this.FCMModel_id = fcmid; }

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
	public String getWeight() {
		return Weight;
	}
	public void setWeight(String weight) {
		Weight = weight;
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
