package test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class HamcrestTest {

	@Test
	public void testDateString() throws Exception {
		assertThat("Start Date 비교", "2012/11/3", is("2012/11/3"));
		assertThat("fab", both(containsString("a")).and(containsString("b")));
	}
}
