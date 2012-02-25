package ch03;

public class StarbuzzCoffee {

    public static void main(String args[]) {
//		Beverage beverage = new Espresso(Beverage.GRANDE);
//		System.out.println(beverage.getDescription() + " $" + beverage.cost());
//	
//		Beverage beverage2 = new DarkRoast(Beverage.TALL);
//		beverage2 = new Mocha(beverage2);
//		beverage2 = new Mocha(beverage2);
//		beverage2 = new Whip(beverage2);
//		System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
//	
//		Beverage beverage3 = new DarkRoast(Beverage.VENTI);
//		beverage3 = new Soy(beverage3);
//		beverage3 = new Mocha(beverage3);
//		beverage3 = new Whip(beverage3);
//		System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
		
		Beverage coffee = new DarkRoast(Beverage.VENTI);
		System.out.println(coffee.getDescription() + " $" + coffee.cost());
		coffee = new Soy(coffee);
		System.out.println(coffee.getDescription() + " $" + coffee.cost());
		coffee = new Mocha(coffee);
		System.out.println(coffee.getDescription() + " $" + coffee.cost());
		// 3+3+30
		
    }
}
