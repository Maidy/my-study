package ch07;

public class DuckTestDrive {
	public static void main(String[] args) {
		
		MallardDuck duck = new MallardDuck();
		WildTurkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);
		
		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();
		
		System.out.println("\nThe Duck says...");
		testDuck(duck);
		
		System.out.println("\nThe TurkeyAdapter says...");
		testDuck(turkeyAdapter);
	}
	
	static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}

interface Duck {
	public void quack();
	public void fly();
}

class MallardDuck implements Duck {
	public void quack() {
		System.out.println("Quack");
	}
	public void fly() {
		System.out.println("I'm flying");
	}
}

interface Turkey {
	public void gobble();
	public void fly();
}

class WildTurkey implements Turkey {
	public void gobble() {
		System.out.println("Gobble gobble");
	}
	public void fly() {
		System.out.println("I'm flying a short distance");
	}
}

class TurkeyAdapter implements Duck {
	
	private Turkey turkey;
	
	TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	
	public void quack() {
		turkey.gobble();
	}
	
	public void fly() {
		for (int i=0; i < 5; i++) {
			this.turkey.fly();
		}
	}
}
