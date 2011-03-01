package ch02;


public class StatisticsDisplay implements Observer, DisplayElement {
	
	private float avgTemperature;
	private float minTemperature;
	private float maxTemperature;
	
	private Subject weatherData;
	
	public StatisticsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = "
				+ avgTemperature + "/" + maxTemperature + "/" + minTemperature);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		// TODO Check logic!
		avgTemperature = minTemperature = maxTemperature = temp;
		display();
	}
	
	public void stop() {
		weatherData.removeObserver(this);
	}

}
