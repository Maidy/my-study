package kr.yuiworld.utf.ch4.abstractTest;

import kr.yuiworld.utf.Book;

public class JDBCConnection implements DBConnection {
	
	private String connString;
	private boolean open;
	
	public JDBCConnection(String connect) {
		connString = connect;
		open = false;
	}

	@Override
	public void connect() {
		open = true;
	}

	@Override
	public void close() {
		open = false;
	}

	@Override
	public boolean isOpen() {
		return open;
	}

	@Override
	public Book selectBook(String title, String author) {
		return null;
	}
	
	public String getConnectionString() {
		return connString;
	}

}
