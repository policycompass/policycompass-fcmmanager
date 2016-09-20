package policycompass.fcmmanager.models;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
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

	@Column(name = "date_created", nullable = false)
	private Date Date_Created;
	@Column(name = "date_modified", nullable = false)
	private Date Date_Modified;
	@Column(name = "ViewsCount", nullable = false)
	private int ViewsCount;
	@Column(name = "UserPath", nullable = true)
	private String UserPath;
	@Column(name = "is_draft", nullable = true)
	private Boolean is_draft;
	@Column(name = "DerivedFromId", nullable = true)
	private Integer DerivedFromId;

	public FCMModel() {
		this.id = 0;
		this.Title = "";
		this.Description = "";
		this.Keywords = "";
		this.UserID = 0;
		this.ViewsCount = 0;
		this.UserPath = "";
		this.is_draft = false;
		this.DerivedFromId = 0;
	}

	public FCMModel(int fcmid, String title, String description, String keywords, int userid, int viewscount, String UserPath, Boolean is_draft, int DerivedFromId) {
		this.id = fcmid;
		this.Title = title;
		this.Description = description;
		this.Keywords = keywords;
		this.UserID = userid;
		this.ViewsCount = viewscount;
		this.UserPath = UserPath;
		this.is_draft = is_draft;
		this.DerivedFromId = DerivedFromId;
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

	public String getdate_created() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(Date_Created);
	}
	public void setdate_created(Date date_created) { this.Date_Created = date_created; }

	public String getdate_modified() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(Date_Modified); }
	public void setdate_modified(Date date_modified) { this.Date_Modified = date_modified; }

	public int getViewsCount() { return ViewsCount; }
	public void setViewsCount(int viewscount) { this.ViewsCount = viewscount; }

	public String getUserPath() { return UserPath; }
	public void setUserPath(String userpath) { this.UserPath = userpath; }

	public Boolean getis_draft() { return is_draft; }
	public void setis_draft(Boolean is_draft) { this.is_draft = is_draft; }

	public int getDerivedFromId() { return DerivedFromId == null ? 0 : DerivedFromId; }
	public void setDerivedFromId(int derivedFromId) { this.DerivedFromId = derivedFromId; }

	public String toString() { return getId() + ", " + getTitle() + ", " + getDescription(); }
}