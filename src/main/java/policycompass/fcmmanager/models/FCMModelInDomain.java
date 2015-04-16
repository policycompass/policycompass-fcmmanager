package policycompass.fcmmanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "FCM Model In Domain")
@XmlType(propOrder = { "id", "fcmmodel_id", "domain_id" })

@Entity (name="fcmmanager_modelindomain")
public class FCMModelInDomain {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "FCMModel_id", nullable = false)
	private int FCMModel_id;
	@Column(name = "Domain_id", nullable = false)
	private int Domain_id;

	public int getId() { return id; }
	public void setId(int cid) { this.id = cid; }

	public int getFCMModelID() { return FCMModel_id; }
	public void setFCMModelID(int fcmid) { this.FCMModel_id = fcmid; }

	public int getDomainID() { return Domain_id; }
	public void setDomainID(int did) { this.Domain_id = did; }

}
