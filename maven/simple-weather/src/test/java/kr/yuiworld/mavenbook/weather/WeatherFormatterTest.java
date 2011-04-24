package kr.yuiworld.mavenbook.weather;

import junit.framework.TestCase;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;

import kr.yuiworld.mavenbook.weather.Weather;
import kr.yuiworld.mavenbook.weather.YahooParser;
import kr.yuiworld.mavenbook.weather.WeatherFormatter;

public class WeatherFormatterTest extends TestCase {
	
	public WeatherFormatterTest(String name) {
		super(name);
	}
	
	public void testFormat() throws Exception {
		
		InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse(nyData);
		String formattedResult = new WeatherFormatter().format(weather);
		
		InputStream expected = getClass().getClassLoader().getResourceAsStream("format-expected.txt");
		assertEquals(IOUtils.toString(expected).trim(), formattedResult.trim());
	}
}
