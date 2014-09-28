package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Models")
@XmlType(propOrder = { "fcmmodelid", "title", "description", "keywords", "userID", "DateAddedtoPC", "DateModified", "viewsCount" })

@Entity (name="fcmmanager_models")
public class FCMModel {

	@Id
	@Column(name = "FCMModelID", unique = true, nullable = false)
	private int FCMModelID;
	@Column(name = "Title", nullable = false)
	private String Title;
	@Column(name = "Description", nullable = false)
	private String Description;
	@Column(name = "Keywords", nullable = false)
	private String Keywords;
	@Column(name = "UserID", nullable = false)
	private int UserID;
	@Column(name = "DateAddedtoPC", nullable = false)
	private Date DateAddedtoPC;
	@Column(name = "DateModified", nullable = true)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;
	
	public FCMModel() {
		this.FCMModelID = 0;
		this.Title = "";
		this.Description = "";
		this.Keywords = "";
		this.UserID = 0;
		this.ViewsCount = 0;
	}

	public FCMModel(int id, String title, String description, String keywords, int userid, int viewscount) {
		this.FCMModelID = id;
		this.Title = title;
		this.Description = description;
		this.Keywords = keywords;
		this.UserID = userid;
		this.ViewsCount = viewscount;
	}

	public int getFCMModelID() { return FCMModelID; }
	public void setFCMModelID(int id) { this.FCMModelID = id; }

	public String getTitle() { return Title; }
	public void setTitle(String title) { this.Title = title; }

	public String getDescription() { return Description; }
	public void setDescription(String description) { this.Description = description; }

	public String getKeywords() { return Keywords; }
	public void setKeywords(String keywords) { this.Keywords = keywords; }

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
