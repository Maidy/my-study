package kr.yuiworld.mavenbook.weather;

import java.io.InputStream;

import junit.framework.TestCase;

import kr.yuiworld.mavenbook.weather.Weather;
import kr.yuiworld.mavenbook.weather.YahooParser;

public class YahooParserTest extends TestCase {
    public YahooParserTest(String name) {
    	super(name);
    }

    public void testParser() throws Exception {
		InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
	
		Weather weather = new YahooParser().parse(nyData);
	
		assertEquals("New York", weather.getCity());
		assertEquals("NY", weather.getRegion());
		assertEquals("US", weather.getCountry());
		assertEquals("63", weather.getTemp());	
		assertEquals("Partly Cloudy", weather.getCondition());
		assertEquals("63", weather.getChill());
		assertEquals("87", weather.getHumidity());
    }
}