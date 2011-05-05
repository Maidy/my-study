package kr.yuiworld.utf.ch2;

import kr.yuiworld.utf.Book;

public class BookTest extends UnitTest {
    public void runTest() throws Exception {
		Book book = new Book("Dune", "");
		assertTrue(book.getTitle().equals("Dune"), "checking title");
    }
}