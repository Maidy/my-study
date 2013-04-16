package concurrency;

import java.awt.Event;
import java.lang.reflect.InvocationTargetException;

public class ThisEscape {
	
	private int a = 0;
	
	public int getA(){
		return a;
	}
	
	public ThisEscape(EventSource source) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		source.registerListener(
				new EventListener() {
					public void  onEvent(Event e) {
						doSomething(e);
					}
				});
	}
	
	public void doSomething(Event e) {
		
	}
	
	public void hello() {
		
	}

	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		EventSource eventSource = new EventSource();
		ThisEscape escape = new ThisEscape(eventSource);
		
	}

}
