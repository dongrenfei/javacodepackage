package head.first.ch02;

public class WeatherStation {

	public static void main(String[] args) {
		WeatherData wdata = new WeatherData();
		CurrentConditionsDisplay cdisplay = new CurrentConditionsDisplay(wdata);
		wdata.setMeasurements(80, 65, 30.4f);
		
		wdata.setMeasurements(82, 70, 29.2f);
		wdata.removeObserver(cdisplay);
		wdata.setMeasurements(78, 90, 29.2f);
	}

}
