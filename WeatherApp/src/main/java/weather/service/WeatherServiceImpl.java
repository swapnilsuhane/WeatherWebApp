package weather.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import weather.entity.Weather;
import weather.entity.WeatherConstants;

/*	WeatherServiceImpl
 * 	This class makes HTTP call and returns Json string from weather API
 * */
public class WeatherServiceImpl extends Weather implements WeatherService{
	
	//method to get URL connection
	public URLConnection getConnection() throws Exception {
		URLConnection connection = null;
		try {
			String urlString = "http://api.wunderground.com/api/" + WeatherConstants.API_KEY + "/conditions/q/" + getZipCode() + ".json";
			System.out.println("API URL: " + urlString);
			URL url = new URL(urlString);
			connection = url.openConnection();
		} catch (Exception e) {
			System.out.println("Error in WeatherService.getConnection "+e);
			throw e;
		}
		return connection;
	}

	//method to get Json string response from weather API
	public String getResponse(URLConnection conn) throws Exception {
		String response = "";
		try {
			InputStream is = (InputStream) conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";

			while ((line = br.readLine()) != null) {
				response += line;
			}
			is.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Error in WeatherService.getResponse "+e);
			response = "Error in response: " + e;
			throw e;
		}
		return response;
	}
}
