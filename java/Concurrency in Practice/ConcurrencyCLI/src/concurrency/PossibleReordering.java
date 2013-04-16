package concurrency;

public class PossibleReordering {
	
	static int x = 0, y = 0;
	static int a = 0, b = 0;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread one = new Thread(new Runnable() {
			@Override
			public void run() {
				a = 1;
				x = b;
			}
		});

		Thread two = new Thread(new Runnable() {
			@Override
			public void run() {
				b = 1;
				y = a;
			}
		});
		
		one.start(); two.start();
		one.join(); two.join();
		System.out.println("( " + x + ", " + y + ")");
	}
}
