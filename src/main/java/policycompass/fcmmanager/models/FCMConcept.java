package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Concepts")
@XmlType(propOrder = { "id", "FCMModel_id", "Title", "Description", "Scale", "UserID", "DateAddedtoPC", "DateModified", "ViewsCount" })

@Entity (name="fcmmanager_concepts")
public class FCMConcept {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "FCMModel_id", nullable = false)
	private int FCMModel_id;
	@Column(name = "Title", nullable = false)
	private String Title;
	@Column(name = "Description", nullable = false)
	private String Description;
	@Column(name = "Scale", nullable = false)
	private int Scale;
	@Column(name = "PositionX", nullable = false)
	private int PositionX;
	@Column(name = "PositionY", nullable = false)
	private int PositionY;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = false)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;

	public int getId() { return id; }
	public void setId(int cid) { this.id = cid; }

	public int getFCMModelID() { return FCMModel_id; }
	public void setFCMModelID(int fcmid) { this.FCMModel_id = fcmid; }

	public String getTitle() { return Title; }
	public void setTitle(String title) { this.Title = title; }

	public String getDescription() { return Description; }
	public void setDescription(String description) { this.Description = description; }

	public int getScale() { return Scale; }
	public void setScale(int scale) { this.Scale = scale; }

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
