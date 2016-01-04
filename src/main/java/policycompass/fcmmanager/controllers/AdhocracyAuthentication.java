package policycompass.fcmmanager.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class AdhocracyAuthentication {
	public boolean authenticate(String userPath, String userToken) throws JSONException
	{
		AdhocracyUser aUser = new AdhocracyUser();
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
		    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.defaultCharset()));
		      StringBuilder sb = new StringBuilder();
		      String line;
		      while ((line = in.readLine()) != null) {
		        sb.append(line);
		      }
		      in.close();
		      urlConnection.disconnect();
		      
		      JSONObject myObj = new JSONObject(sb.toString());
		    aUser.setUserPath(userPath); 
		    aUser.setUserToken(userToken);
		    aUser.setUserURL(myObj.toString());
		    return true;
		} catch (FileNotFoundException ex) {
		    return false;
		    // file does not exist
		} catch (IOException ex) {
		    return false;
		    // I/O error
		}
	}
}
