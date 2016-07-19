package policycompass.fcmmanager.models;

import java.util.List;

public class FCMRelatedModels {
	private List<FCMModel> ModelList;
	
	public FCMRelatedModels() {
	}

	public List<FCMModel> getModelList() { return ModelList; }
	public void setModelList(List<FCMModel> modelList) { this.ModelList = modelList; }
}
