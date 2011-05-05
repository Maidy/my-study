package kr.yuiworld.utf.ch4.perf;

import junit.framework.TestCase;
import java.util.Vector;
import kr.yuiworld.utf.Library;
import kr.yuiworld.utf.Book;

public class LibraryPerfTest extends TestCase {
	private Library library;
	
	public void setUp() {
		library = new Library();
		for (long i = 0; i < 100000; i++) {
			String title = "book" + i;
			String author = "author" + i;
			library.addBook(new Book(title, author));
		}
	}
	
	public void testGetBooksPerf() {
		double maxTime = 100;
		long startTime = System.currentTimeMillis();
		library.getBooks("author99999");
		long endTime = System.currentTimeMillis();
		
		long time = endTime - startTime;
		assertTrue("max time over", time < maxTime);
	}
}
