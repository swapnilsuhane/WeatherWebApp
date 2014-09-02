package weather.service;

import weather.entity.Weather;

public interface JsonParserService {
	Weather parseJSONText();
}
