package policycompass.fcmmanager.controllers;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class AdhocracyAuthentication {
	public String authenticate(String userPath, String userToken)
	{
		String configFile = "config.properties";
	    Properties props = new Properties();
	    
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFile);
		
	    try {
		    props.load(inputStream);
		    String url = props.getProperty("adhocracy_api_base_url").toString();
		    URL url1 = new URL(url);
		    URL user_url = new URL(url1, userPath);
		    HttpURLConnection urlConnection = (HttpURLConnection) user_url.openConnection();
		    urlConnection.addRequestProperty("X-User-Path", userPath);
		    urlConnection.addRequestProperty("X-User-Token", userToken);
		      InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		      urlConnection.disconnect();
		      
		    return userPath + "/" + userToken;
		} catch (FileNotFoundException ex) {
		    return "Error1";
		    // file does not exist
		} catch (IOException ex) {
		    return "Error2";
		    // I/O error
		}
	}
}
