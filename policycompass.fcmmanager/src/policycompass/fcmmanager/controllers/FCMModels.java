package policycompass.fcmmanager.controllers;

import java.sql.*;
import java.util.ArrayList;

import policycompass.fcmmanager.models.*;;

public class FCMModels {

	public static FCMModel retrieve(int id) {
		FCMModel result = null;
		String Query=null;
		try {
			
			DBConnection con = new DBConnection();
			Connection connection = con.getConnection();
			
			Query = "SELECT * FROM fcmmanager_models where \"FCMModelID\"=" + id;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(Query);
			if (resultSet.next()) {
				result = new FCMModel(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4),
						resultSet.getInt(5), resultSet.getInt(8));
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
//			logger.info(e.getMessage());
		}
		return result;
	}	

	 public static ArrayList<FCMModel> getAllModelsList()throws Exception {
		 ArrayList<FCMModel> ModelList = new ArrayList<FCMModel>();
		 try {
				DBConnection database= new DBConnection();
				Connection connection = database.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM fcmmanager_models");
				while (resultSet.next()) {
					ModelList.add(new FCMModel(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getString(4),
							resultSet.getInt(5), resultSet.getInt(8)));
				}
				resultSet.close();
				statement.close();
				connection.close();
		 
		 } catch (Exception e) {
			 throw e;
		 }
		 return ModelList;
	}
}


