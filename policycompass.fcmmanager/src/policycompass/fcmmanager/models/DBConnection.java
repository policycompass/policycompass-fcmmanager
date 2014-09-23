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
			String connectionURL = "jdbc:postgresql://localhost:5432/pcompass";
			Connection connection = null;
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionURL, "pcompass", "ALLAHisG8");
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

