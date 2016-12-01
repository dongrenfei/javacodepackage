package head.first.ch02;

import java.util.Observable;
import java.util.Observer;

public class NewCurrentConditionsDisplay implements Observer, DisplayElement {
	Observable observalbe;
	private float temp;
	private float humidity;
	
	public NewCurrentConditionsDisplay(Observable observable) {
		this.observalbe = observable;
		observable.addObserver(this);
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		if(obs instanceof NewWeatherData) {
			NewWeatherData wdata = (NewWeatherData) obs;
			this.temp = wdata.getTemprature();
			this.humidity = wdata.getHumidity();
			display();
		}
	}
	
	public void display() {
		System.out.println("Current conditions: "+temp+"F degrees and "+humidity+"% humidity.");
	}

}
