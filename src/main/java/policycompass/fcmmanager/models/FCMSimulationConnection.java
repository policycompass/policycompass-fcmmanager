package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class FCMSimulationConnection {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "FCMSimulation_id", nullable = false)
	private int FCMSimulation_id;
	@Column(name = "Connection_id", nullable = false)
	private int Connection_id;
	@Column(name = "Weighted", nullable = false)
	private double Weighted;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = false)
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
	public int getConnectionID() {
		return Connection_id;
	}
	public void setConnectionID(int connectionID) {
		Connection_id = connectionID;
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
}
