package kr.yuiworld.utf;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

public class Library {

	private Hashtable<String, Book> books;

    public Library() {
    	books = new Hashtable<String, Book>();
    }

    public void addBook(Book book) {
    	books.put(book.getTitle(), book);
    }

    public Book getBook(String title) {
    	return books.get(title);
    }
    
    public Book getBook(String title, String author) {
    	return books.get(title);
    }
    
    public Vector<Book> getBooks(String author) {
    	Vector<Book> auth_books = new Vector<Book>();
    	for (Enumeration<Book> e = books.elements(); e.hasMoreElements(); ) {
    		Book book = e.nextElement();
    		if (book.getAuthor().equals(author))
    			auth_books.add(book);
    	}
    	return auth_books;
    }
    
    public void removeBook(String title) throws Exception {
    	if (books.remove(title) == null)
    		throw new Exception("Book not found");
    }
    
    public long getNumBooks() {
    	return books.size();
    }
    
    public void empty() {
    	books.clear();
    }
}