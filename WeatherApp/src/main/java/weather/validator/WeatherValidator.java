package weather.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import weather.entity.Weather;

/*	WeatherValidator
 * 	Spring Validator class for form validation*/

public class WeatherValidator extends Weather implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return Weather.class.equals(clazz);
	}

	public void validate(Object obj, Errors error) {
		Weather weather = (Weather) obj;
	
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "zipCode", "weather.zipcode.empty");
		
		if (  weather.getZipCode() != null && weather.getZipCode().trim().length() != 0) {
			if (!isNumeric(weather.getZipCode()) || (weather.getZipCode().length() != 5)){
				error.rejectValue("zipCode", "weather.zipcode.invalid");
			}	
		}
	}
	
	public boolean isNumeric(String code){
		try{
			//to check any math operation if its a numeric
			Integer.parseInt(code);
			return true;
		} catch(NumberFormatException nfe){
			return false;
		}
	}
	
}
