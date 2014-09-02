package weather.junit;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Ignore;

/*	TemperatureControllerTest
	To Test WeatherApp url call (GET/POST) and getting its running status 
	All the test cases are disabled currently
	Run when the application is Up*/

public class TemperatureControllerTest {
	static HttpURLConnection connection = null;
	static URL url;

	@Ignore
	public static void setUpBeforeClass() throws Exception {
			System.out.println("TemperatureControllerTest start");
			String urlStr = "http://localhost:8080/WeatherApp/temperature";
			url = new URL(urlStr);
	}

	@Ignore
	public static void tearDownAfterClass() throws Exception {
		System.out.println("TemperatureControllerTest end");
	}
	
	//response for POST call
	public static int getResponseCode(String zipCode){
		int responseCode=0;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			String urlParameters = "zipCode="+zipCode;
			 
			// Send post request
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			
			responseCode =  connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseCode;
	}
	
	// Unit Test to check weather webapp is up and running (run when tomcat server is started and application is up)
	@Ignore
	public void testGetWeatherPageUpResponse(){
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			Assert.assertEquals(200, connection.getResponseCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//Unit Test to pass zipCode and get the responseCode
	@Ignore
	public void testGetTemperatureDetailsReponse(){
		int resCode1 = getResponseCode("10001");
		Assert.assertEquals(200, resCode1);
	}

	

}
