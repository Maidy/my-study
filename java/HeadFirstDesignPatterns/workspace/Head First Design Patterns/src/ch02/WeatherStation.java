package ch02;

public class WeatherStation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("============ Push ============");
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionDisplay currentDisplay = new CurrentConditionDisplay(weatherData);
		StatisticsDisplay statsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
		
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
		
		System.out.println("\n============ Pull - Java Observer ============");
		
		WeatherData2 weatherData2 = new WeatherData2();
		
		CurrentConditionDisplay2 currentDisplay2 = new CurrentConditionDisplay2(weatherData2);
		HeatIndexDisplay2 heatIndexDisplay2 = new HeatIndexDisplay2(weatherData2);
		
		// TODO implement StatisticsDisplay2 class 
		// StatisticsDisplay statsDisplay = new StatisticsDisplay(weatherData);
		
		// TODO implement ForecastDisplay2 class
		// ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
				
		weatherData2.setMeasurements(80, 65, 30.4f);
		weatherData2.setMeasurements(82, 70, 29.2f);
		weatherData2.setMeasurements(78, 90, 29.2f);
		
	}

}
