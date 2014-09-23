package policycompass.fcmmanager.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Models")
@XmlType(propOrder = { "fcmmodelID", "title", "description", "keywords", "userID", "viewsCount" })

public class FCMModel {

	private int FCMModelID, UserID, ViewsCount;
	private String Title, Description, Keywords;
	
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

	public int getViewsCount() { return ViewsCount; }
	public void setViewsCount(int viewscount) { this.ViewsCount = viewscount; }

	@Override
	public String toString() { return getFCMModelID() + ", " + getTitle() + ", " + getDescription(); }
}
