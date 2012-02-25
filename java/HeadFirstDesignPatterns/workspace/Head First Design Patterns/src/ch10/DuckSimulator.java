package ch10;

public class DuckSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DuckSimulator simulator = new DuckSimulator();
		AbstractDuckFactory factory = new CountingDuckFactory();
		simulator.simulate(factory);
	}
	
	void simulate(AbstractDuckFactory factory) {
		
		Quackable redhead = factory.createRedheadDuck();
		Quackable duckcall = factory.createDuckCall();
		Quackable rubber = factory.createRubberDuck();
		Quackable goose = new GooseAdapter(new Goose());
		System.out.println("Duck Simulator: With Composite - Flocks");
		
		Flock flockOfDucks = new Flock();
		flockOfDucks.add(redhead);
		flockOfDucks.add(duckcall);
		flockOfDucks.add(rubber);
		flockOfDucks.add(goose);
		
		Quackable mallardOne = factory.createMallardDuck();
		Quackable mallardTwo = factory.createMallardDuck();
		Quackable mallardThree = factory.createMallardDuck();
		Quackable mallardFour = factory.createMallardDuck();
		
		Flock flockOfMallard = new Flock();
		flockOfMallard.add(mallardOne);
		flockOfMallard.add(mallardTwo);
		flockOfMallard.add(mallardThree);
		flockOfMallard.add(mallardFour);
		
		flockOfDucks.add(flockOfMallard);
		
		System.out.println("\nDuckSimulator: with Observer");
		Quackologist quackologist = new Quackologist();
		flockOfDucks.registerOberserver(quackologist);
		
		simulate(flockOfDucks);

		System.out.println("\nThe ducks quacked " + QuackCounter.getQuacks() + " times");
	}
	
	void simulate(Quackable duck) {
		duck.quack();
	}
}
