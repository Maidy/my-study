package ch01;

public class MiniDuckSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Duck mallard = new MallardDuck();
		Duck rubber = new RubberDuck();
		
		mallard.display();
		rubber.display();
		
		mallard.swim();
		rubber.swim();
		
		mallard.performQuack();
		rubber.performQuack();
		
		mallard.performFly();
		rubber.performFly();
		
		System.out.println("\n====Model Duck Presentation====");
		Duck model = new ModelDuck();
		model.display();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
	}
}
