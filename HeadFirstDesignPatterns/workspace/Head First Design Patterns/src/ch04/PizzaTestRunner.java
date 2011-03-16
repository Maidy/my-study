package ch04;

import java.util.ArrayList;

public class PizzaTestRunner {
	public static void main(String[] args) {
		PizzaStore2 store1 = new NYPizzaStore();
		store1.orderPizza("veggie");
		
		PizzaStore2 store2 = new ChicagoPizzaStore();
		store2.orderPizza("clam");
	}
}

abstract class PizzaStore2 {
	
	public Pizza2 orderPizza(String type) {
		Pizza2 pizza = createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
	abstract Pizza2 createPizza(String type);
}

class NYPizzaStore extends PizzaStore2 {
	
	Pizza2 createPizza(String type) {
		Pizza2 pizza = null;

		if (type.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new NYPepperoniPizza();
		} else if (type.equals("clam")) {
			pizza = new NYClamPizza();
		} else if (type.equals("veggie")) {
			pizza = new NYVeggiePizza();
		}
		
		return pizza;
	}
}


class ChicagoPizzaStore extends PizzaStore2 {
	
	Pizza2 createPizza(String type) {
		Pizza2 pizza = null;

		if (type.equals("cheese")) {
			pizza = new ChicagoCheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new ChicagoPepperoniPizza();
		} else if (type.equals("clam")) {
			pizza = new ChicagoClamPizza();
		} else if (type.equals("veggie")) {
			pizza = new ChicagoVeggiePizza();
		}
		
		return pizza;
	}
}

/*
class CaliforniaPizzaStore extends PizzaStore2 {
	
	Pizza2 createPizza(String type) {
		Pizza2 pizza = null;

		if (type.equals("cheese")) {
			pizza = new CaliforniaCheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new CaliforniaPepperoniPizza();
		} else if (type.equals("clam")) {
			pizza = new CaliforniaClamPizza();
		} else if (type.equals("veggie")) {
			pizza = new CaliforniaVeggiePizza();
		}
		
		return pizza;
	}
}
*/

abstract class Pizza2 {
	String name;
	String dough;
	String sauce;
	ArrayList<String> toppings = new ArrayList<String>();
	
	public void prepare() {
		System.out.println("Pizza preparing " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings: ");
		for (int i=0; i < toppings.size(); i++) {
			System.out.println("   " + toppings.get(i));
		}
	}
	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}
	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}
	public void box() {
		System.out.println("Place pizza in official PizzaStore2 box");
	}
	
	public String getName() {
		return name;
	}
}

class NYCheesePizza extends Pizza2 {
	public NYCheesePizza() {
		name = "My Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}
	
}

class NYPepperoniPizza extends Pizza2 {
	public NYPepperoniPizza() {
		name = "My Style Sauce and Pepperoni Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}
	
}

class NYClamPizza extends Pizza2 {
	public NYClamPizza() {
		name = "NYClamPizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}
}

class NYVeggiePizza extends Pizza2 {
	public NYVeggiePizza() {
		name = "NYVeggiePizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}
}


class ChicagoCheesePizza extends Pizza2 {
	public ChicagoCheesePizza() {
		name = "My Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}
}

class ChicagoPepperoniPizza extends Pizza2 {
	public ChicagoPepperoniPizza() {
		name = "My Style Sauce and Pepperoni Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}
}

class ChicagoClamPizza extends Pizza2 {
	public ChicagoClamPizza() {
		name = "ChicagoClamPizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}
}

class ChicagoVeggiePizza extends Pizza2 {
	public ChicagoVeggiePizza() {
		name = "ChicagoVeggiePizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		
		toppings.add("Grated Reggiano Cheese");
	}	
}

