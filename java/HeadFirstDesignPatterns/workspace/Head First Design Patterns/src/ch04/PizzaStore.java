package ch04;

public class PizzaStore {

	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore store = new PizzaStore(factory);
		store.orderPizza("veggie");
	}

	private SimplePizzaFactory factory;

	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}

	public Pizza orderPizza(String type) {
		Pizza pizza = factory.createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
}

class Pizza {
	public void prepare() {
		System.out.println("Pizza preparing");
	}
	public void bake() {
		System.out.println("Pizza baking");
	}
	public void cut() {
		System.out.println("Pizza cutting");
	}
	public void box() {
		System.out.println("Pizza boxing");
	}
}

class CheesePizza extends Pizza {
	public void prepare() {
		System.out.println("CheesePizza preparing");
	}
	public void bake() {
		System.out.println("CheesePizza baking");
	}
	public void cut() {
		System.out.println("CheesePizza cutting");
	}
	public void box() {
		System.out.println("CheesePizza boxing");
	}
}

class VeggiePizza extends Pizza {
	public void prepare() {
		System.out.println("VeggiePizza preparing");
	}
	public void bake() {
		System.out.println("VeggiePizza baking");
	}
	public void cut() {
		System.out.println("VeggiePizza cutting");
	}
	public void box() {
		System.out.println("VeggiePizza boxing");
	}
}

class ClamPizza extends Pizza {
	public void prepare() {
		System.out.println("ClamPizza preparing");
	}
	public void bake() {
		System.out.println("ClamPizza baking");
	}
	public void cut() {
		System.out.println("ClamPizza cutting");
	}
	public void box() {
		System.out.println("ClamPizza boxing");
	}
}

class PepperoniPizza extends Pizza {
	public void prepare() {
		System.out.println("PepperoniPizza preparing");
	}
	public void bake() {
		System.out.println("PepperoniPizza baking");
	}
	public void cut() {
		System.out.println("PepperoniPizza cutting");
	}
	public void box() {
		System.out.println("PepperoniPizza boxing");
	}
}

class SimplePizzaFactory {
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		
		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		} else if (type.equals("clam")) {
			pizza = new ClamPizza();
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza();
		}
		
		return pizza;
	}
}
