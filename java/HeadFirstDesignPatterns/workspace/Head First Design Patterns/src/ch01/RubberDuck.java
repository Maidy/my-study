package ch01;

public class RubberDuck extends Duck {
	
	RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeak();
	}
	
	public void display() {
		System.out.println("I'm rubber duck.");
	}
}
