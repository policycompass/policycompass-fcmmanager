package policycompass.fcmmanager.models;

import java.util.ArrayList;
import java.util.List;

public class FCMSimulationDetail {

	FCMModel model;
	List<FCMSimulationResult> result;

	public FCMModel getModel() {
		return model;
	}
	public void setModel(FCMModel model) {
		this.model = model;
	}
	public List<FCMSimulationResult> getResult() {
		return result;
	}
	public void setResult(List<FCMSimulationResult> result) {
		this.result = result;
	}
}
