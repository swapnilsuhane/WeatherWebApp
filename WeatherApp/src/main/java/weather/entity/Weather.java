package weather.entity;

public class Weather {
	private String zipCode;
	private double temp_f;
	private String city;
	private String state;
	private boolean queryNotFound = false;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public double getTemp_f() {
		return temp_f;
	}

	public void setTemp_f(double temp_f) {
		this.temp_f = temp_f;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isQueryNotFound() {
		return queryNotFound;
	}

	public void setQueryNotFound(boolean queryNotFound) {
		this.queryNotFound = queryNotFound;
	}

}
