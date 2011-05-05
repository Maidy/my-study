package kr.yuiworld.utf.ch4.abstractTest;

import kr.yuiworld.utf.Book;

public interface DBConnection {
	void connect();
	void close();
	boolean isOpen();
	Book selectBook(String title, String author);
}
