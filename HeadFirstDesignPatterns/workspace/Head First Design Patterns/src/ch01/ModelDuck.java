package ch01;

public class ModelDuck extends Duck {

	ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}
	
	@Override
	public void display() {
		System.out.println("I'm model duck.");

	}

}
