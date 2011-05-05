package kr.yuiworld.utf.ch2;

import kr.yuiworld.utf.Book;
import kr.yuiworld.utf.Library;

public class LibraryTest extends UnitTest {
    public void runTest() throws Exception {
		Library library = new Library();
		Book expectedBook = new Book("Dune", "");
	
		library.addBook(expectedBook);
		Book actualBook = library.getBook("Dune");
	
		assertTrue(actualBook.getTitle().equals("Dune"), "got book");
    }
}