package kr.yuiworld.mavenbook.weather;

import java.io.InputStream;
import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static void main(String[] args) throws Exception {
	
		// Configure Log4J
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));
		
		String zipcode = "60202";
		try {
			zipcode = args[0];
		} catch (Exception e) { }
		
		(new Main(zipcode)).start();
	}
	
	private String zip;
	
	public Main(String zip) {
		this.zip = zip;
	}
	
	public void start() throws Exception {
		InputStream dataIn = new YahooRetriever().retrieve(zip);
		Weather weather = new YahooParser().parse(dataIn);
		System.out.print(new WeatherFormatter().format(weather));
	}
}

