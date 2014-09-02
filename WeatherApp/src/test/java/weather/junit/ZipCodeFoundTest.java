package weather.junit;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import weather.entity.Weather;
import weather.service.JsonParserService;
import weather.service.JsonParserServiceImpl;
import weather.service.WeatherServiceImpl;
import weather.validator.WeatherValidator;

/*	ZipCodeFoundTest
	Main Test class to test weather zipCode exists or not */

public class ZipCodeFoundTest {
	static Weather weather = new Weather();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("ZipCodeFoundTest start");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("ZipCodeFoundTest end");
	}
	
	public static Weather getDetails(String zipCode) throws Exception{
		try {
			weather.setZipCode(zipCode);
			WeatherValidator validator = new WeatherValidator();
			Errors errors = new BeanPropertyBindingResult(weather, "weather");
			validator.validate(weather, errors);
			Assert.assertFalse(errors.hasErrors());
			
			WeatherServiceImpl wService = new WeatherServiceImpl();
			wService.setZipCode(weather.getZipCode());
			String jsonStr = wService.getResponse(wService.getConnection());
			JsonParserService jService = new JsonParserServiceImpl(jsonStr);
			weather = jService.parseJSONText();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weather;
	}
	
	@Test
	public void testdZipCodeNotFound() throws Exception {
			String zipCode1  = "11111";
			//first check its valid zipcode i.e. length 5 and numeric
			weather.setZipCode(zipCode1);
			WeatherValidator validator = new WeatherValidator();
			Errors errors = new BeanPropertyBindingResult(weather, "weather");
			validator.validate(weather, errors);
			Assert.assertFalse(errors.hasErrors());
			//now check zipCode not found
			weather = getDetails(zipCode1);		
			Assert.assertTrue(weather.isQueryNotFound());
			Assert.assertNull(weather.getCity());
			Assert.assertNull(weather.getState());
			Assert.assertEquals(0.0, weather.getTemp_f());
			
			String zipCode2  = "10000";
			//first check its valid zipcode i.e. length 5 and numeric
			weather.setZipCode(zipCode2);
			validator = new WeatherValidator();
			errors = new BeanPropertyBindingResult(weather, "weather");
			validator.validate(weather, errors);
			Assert.assertFalse(errors.hasErrors());
			//now check zipCode not found
			weather = getDetails(zipCode2);		
			Assert.assertTrue(weather.isQueryNotFound());
			Assert.assertNull(weather.getCity());
			Assert.assertNull(weather.getState());
			Assert.assertEquals(0.0, weather.getTemp_f());
	}
	
	
	@Test
	public void testdZipCodeFound() throws Exception {
			String zipCode1  = "55555";
			//first check its valid zipcode i.e. length 5 and numeric
			weather.setZipCode(zipCode1);
			WeatherValidator validator = new WeatherValidator();
			Errors errors = new BeanPropertyBindingResult(weather, "weather");
			validator.validate(weather, errors);
			Assert.assertFalse(errors.hasErrors());
			//now check zipCode exists
			weather = getDetails(zipCode1);		
			Assert.assertFalse(weather.isQueryNotFound());
			Assert.assertNotNull(weather.getCity());
			Assert.assertNotNull(weather.getState());
			Assert.assertNotSame(0.0, weather.getTemp_f());
			
			String zipCode2  = "94117";
			//first check its valid zipcode i.e. length 5 and numeric
			weather.setZipCode(zipCode2);
			validator = new WeatherValidator();
			errors = new BeanPropertyBindingResult(weather, "weather");
			validator.validate(weather, errors);
			Assert.assertFalse(errors.hasErrors());
			//now check zipCode exists
			weather = getDetails(zipCode2);		
			Assert.assertFalse(weather.isQueryNotFound());
			Assert.assertNotNull(weather.getCity());
			Assert.assertNotNull(weather.getState());
			Assert.assertNotSame(0.0, weather.getTemp_f());

	}
	
}
