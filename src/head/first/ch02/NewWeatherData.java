package head.first.ch02;

import java.util.Observable;

public class NewWeatherData extends Observable {
	private float temp;
	private float humidity;
	private float pressure;
	
	public NewWeatherData() {
		
	}
	
	public void measurementsChanged() {
		setChanged();
		notifyObservers();
	}
	
	public void setMeasurements(float temprature, float humidity, float pressure) {
		this.temp = temprature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
	
	public float getTemprature() {
		return temp;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}
	
}
