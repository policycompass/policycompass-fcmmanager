package policycompass.fcmmanager.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection 
{
 
	public Connection getConnection() throws Exception
	{
		try
		{
			String connectionURL = "jdbc:postgresql://" + DbConstants.Host + ":" + DbConstants.Port + "/" + DbConstants.Database;
			Connection connection = null;
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionURL, DbConstants.UserName, DbConstants.Password);
			return connection;
		}
		catch (SQLException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}

