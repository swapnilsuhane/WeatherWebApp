package weather.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import weather.entity.Weather;

/*
 * JsonParserServiceImpl
 * This class parses the Json string and creates Weather object
*/
public class JsonParserServiceImpl implements JsonParserService{
	private String jsonString="";
	Weather w = new Weather();

	public JsonParserServiceImpl(String jsonStr) {
		this.jsonString = jsonStr;
	}
	
	public Weather parseJSONText(){
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(this.jsonString);

			// get Strings from the JSON object
			if (((JSONObject) jsonObject.get("response")).containsKey("error")){
				String queryNotFound = (String) ((JSONObject) ((JSONObject) jsonObject.get("response")).get("error")).get("type");
				if (queryNotFound.equals("querynotfound"))
					w.setQueryNotFound(true);
			}
			
			//json path hardcodes, can be put in property file
			if (jsonObject.containsKey("current_observation")){
				double tempF = (Double) ((JSONObject) jsonObject.get("current_observation")).get("temp_f");
				String city = (String) ((JSONObject) ((JSONObject) jsonObject.get("current_observation")).get("observation_location")).get("city");
				String state = (String) ((JSONObject) ((JSONObject) jsonObject.get("current_observation")).get("observation_location")).get("state");
				
				System.out.println("Details: [Temperature_F] "+ tempF + " [City] " + city + " [State] " + state);
				w.setCity(city);
				w.setState(state);
				w.setTemp_f(tempF);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return w;
	}
	
}