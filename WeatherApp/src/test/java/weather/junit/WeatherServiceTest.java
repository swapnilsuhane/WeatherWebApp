package weather.junit;

import java.io.IOException;
import java.net.URLConnection;

import junit.framework.Assert;
import junit.framework.TestResult;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import weather.entity.Weather;
import weather.service.WeatherService;
import weather.service.WeatherServiceImpl;

/*	WeatherServiceTest
	To Test WeatherService class call/response */

public class WeatherServiceTest extends TestResult{
	static WeatherService wService;
	static URLConnection connection;
	static String zipCode = "10001";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		wService = new WeatherServiceImpl();
		System.out.println("WeatherServiceTest start");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("WeatherServiceTest end");
	}

	@Before
	public void setUp() throws Exception {
		((Weather) wService).setZipCode(zipCode);
		connection = wService.getConnection();
	}

	@Test
	public void testGetConnectionNotNull() {
		Assert.assertNotNull(connection);
	}
	
	@Test
	public void testGetConnectionContentType() {
		Assert.assertEquals("application/json; charset=UTF-8", connection.getContentType());
	}
	
	@Test
	public void testGetConnectionContentLength() {
		Assert.assertTrue(connection.getContentLength() > 1);
	}
	
	@Test
	public void testGetConnectionStream() throws IOException {
		Assert.assertEquals("HttpInputStream", connection.getInputStream().getClass().getSimpleName());
	}

	//Unit Test to test Weather Service Response
	@Test
	public void testGetResponseNotNull() throws Exception {
		Assert.assertNotNull(wService.getResponse(connection));
	}

	@Test
	public void testGetResponseIsString() throws Exception {
		Assert.assertEquals("String", wService.getResponse(connection).getClass().getSimpleName());
	}

	@Test
	public void testGetResponseValid() throws Exception {
		Assert.assertEquals(true, wService.getResponse(connection).contains("response"));
	}
}
