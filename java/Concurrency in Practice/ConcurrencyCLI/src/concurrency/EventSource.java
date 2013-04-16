package concurrency;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventSource {

	private EventListener listener;
	
	public void registerListener(EventListener listener) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.listener = listener;
		Method[] methods = listener.getClass().getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName() + " / "+ method.invoke(listener, (Object) null));
		}
	}
	
	public void hello () {
		
	}

}
