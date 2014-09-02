package weather.service;

import java.net.URLConnection;

public interface WeatherService {
	
	URLConnection getConnection() throws Exception;
	String getResponse(URLConnection conn) throws Exception;
	
}
