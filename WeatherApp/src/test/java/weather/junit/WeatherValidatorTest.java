package weather.junit;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import weather.entity.Weather;
import weather.validator.WeatherValidator;

/*	WeatherValidatorTest
	To Test WeatherValidator Spring Class that validate form entered data */

public class WeatherValidatorTest {
	static Weather weather = new Weather();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("WeatherValidatorTest start");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("WeatherValidatorTest end");
	}
	
	@Test
	public void testZipCodeIsNull() {
		//to check zipcode null 
		weather.setZipCode(null);
		WeatherValidator validator = new WeatherValidator();
		Errors errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("weather.zipcode.empty", errors.getFieldError("zipCode").getCode());
		
		//to check zipcode blank
		weather.setZipCode("");
		validator = new WeatherValidator();
		errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("weather.zipcode.empty", errors.getFieldError("zipCode").getCode());
 
		//to check zipcode with space
		weather.setZipCode(" ");
		validator = new WeatherValidator();
		errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("weather.zipcode.empty", errors.getFieldError("zipCode").getCode());
	}
	
	@Test
	public void testZipCodeIsInvalid() {
		//zipcode lesser than length 5
		weather.setZipCode("1000");
		WeatherValidator validator = new WeatherValidator();
		Errors errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("weather.zipcode.invalid", errors.getFieldError("zipCode").getCode());
		
		//zipcode greater than length 5
		weather.setZipCode("1000000");
		validator = new WeatherValidator();
		errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("weather.zipcode.invalid", errors.getFieldError("zipCode").getCode());
		
		//zipcode not numeric
		weather.setZipCode("abcxyz");
		validator = new WeatherValidator();
		errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("weather.zipcode.invalid", errors.getFieldError("zipCode").getCode());
	}
	
	@Test
	public void testValidZipCode() {
		//zipcode of length 5
		weather.setZipCode("10000");
		WeatherValidator validator = new WeatherValidator();
		Errors errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertFalse(errors.hasErrors());
		
		//zipcode numeric and length 5
		weather.setZipCode("44444");
		validator = new WeatherValidator();
		errors = new BeanPropertyBindingResult(weather, "weather");
		validator.validate(weather, errors);
		Assert.assertFalse(errors.hasErrors());
	}
}
