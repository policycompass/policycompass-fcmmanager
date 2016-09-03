package policycompass.fcmmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name="fcmmanager_models")
public class FCMModel {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
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
	@Column(name = "DateModified", nullable = false)
	private Date DateModified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;
	@Column(name = "UserPath", nullable = true)
	private String UserPath;
	@Column(name = "IsDraft", nullable = true)
	private Boolean IsDraft;
	public FCMModel() {
		this.id = 0;
		this.Title = "";
		this.Description = "";
		this.Keywords = "";
		this.UserID = 0;
		this.ViewsCount = 0;
		this.UserPath="";
		this.IsDraft=false;
	}

	public FCMModel(int fcmid, String title, String description, String keywords, int userid, int viewscount, String UserPath, Boolean IsDraft) {
		this.id = fcmid;
		this.Title = title;
		this.Description = description;
		this.Keywords = keywords;
		this.UserID = userid;
		this.ViewsCount = viewscount;
		this.UserPath=UserPath;
		this.IsDraft=IsDraft;
	}

	public int getId() { return id; }
	public void setId(int fcmid) { this.id = fcmid; }

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

	public String getUserPath() { return UserPath; }
	public void setUserPath(String userpath) { this.UserPath = userpath; }

	public Boolean getIsDraft() { return IsDraft; }
	public void setIsDraft(Boolean isDraft) { this.IsDraft = isDraft; }

	public String toString() { return getId() + ", " + getTitle() + ", " + getDescription(); }
}