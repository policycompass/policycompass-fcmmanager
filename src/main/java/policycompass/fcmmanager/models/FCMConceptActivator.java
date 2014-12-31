package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Concept Activator")
@XmlType(propOrder = { "ActivatorID", "Title", "UserID", "DateAddedtoPC", "DateModified", "ViewsCount" })

@Entity (name="fcmmanager_conceptactivators")
public class FCMConceptActivator {

	@Id
	@Column(name = "ActivatorID", unique = true, nullable = false)
	private int ActivatorID;
	@Column(name = "Title", nullable = false)
	private String Title;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = true)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;

	public int getActivatorID() { return ActivatorID; }
	public void setActivatorID(int activatorID) { this.ActivatorID = activatorID; }

	public String getTitle() { return Title; }
	public void setTitle(String title) { this.Title = title; }
	
	public int getUserID() { return UserID; }
	public void setUserID(int userid) { this.UserID = userid; }

	public Date getDateAddedtoPC() { return DateAddedtoPC; }
	public void setDateAddedtoPC(Date dateaddedtopc) { this.DateAddedtoPC = dateaddedtopc; }

	public Date getDateModified() { return DateModified; }
	public void setDateModified(Date datemodified) { this.DateModified = datemodified; }

	public int getViewsCount() { return ViewsCount; }
	public void setViewsCount(int viewscount) { this.ViewsCount = viewscount; }

	@Override
	public String toString() { return getActivatorID() + ", " + getTitle(); }
}
