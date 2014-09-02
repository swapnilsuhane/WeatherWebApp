package weather.junit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import weather.entity.Weather;
import weather.service.JsonParserService;
import weather.service.JsonParserServiceImpl;
import weather.service.WeatherServiceImpl;

/*	JsonParserServiceTest
	Test JsonParserService and validate parsed data */

public class JsonParserServiceTest {
	static String zipCode = "10001"; //Murray Hill, New York
	static Weather weather;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("JsonParserServiceTest start");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("JsonParserServiceTest end");
	}

	@Before
	public void setUp() throws Exception {
		WeatherServiceImpl wService = new WeatherServiceImpl();
		wService.setZipCode(zipCode);
		String jsonStr = wService.getResponse(wService.getConnection());
		JsonParserService jService = new JsonParserServiceImpl(jsonStr);
		weather = jService.parseJSONText();
	}


	@Test
	public void testParseJSONTextIsNotNull() {
		Assert.assertNotNull(weather);
	}

	@Test
	public void testParseJSONTextWeatherAttributeNotNull() {
		if (!weather.isQueryNotFound()) {
			Assert.assertNotNull(weather.getCity());
			Assert.assertNotNull(weather.getState());
			Assert.assertNotNull(weather.getTemp_f());
		} else {
			Assert.assertTrue(weather.isQueryNotFound());
		}
	}

	@Test
	public void testParseJSONTextWeatherAttribute() {
		if (!weather.isQueryNotFound()) {
			Assert.assertEquals("Murray Hill, New York", weather.getCity());
			Assert.assertEquals("New York", weather.getState());
			//can not put this test as temperature may change
			//Assert.assertEquals(84.6, weather.getTemp_f(), 1);
		}
	}
}
