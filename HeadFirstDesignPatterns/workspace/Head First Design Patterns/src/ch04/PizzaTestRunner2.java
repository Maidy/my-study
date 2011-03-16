package ch04;

public class PizzaTestRunner2 {

	public static void main(String[] args) {
		return;
	}
}

interface PizzaIngredientFactory {
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
}

class NYPizzaIngredientFactory implements PizzaIngredientFactory {
	public Dough createDough() {
		Dough dough = new ThinCrustDough();
		return dough;
	}
	public Sauce createSauce() {
		Sauce sauce = new MarinaraSauce();
		return sauce;
	}
	public Cheese createCheese() {
		Cheese cheese = new ReggianoCheese();
		return cheese;
	}
	public Veggies[] createVeggies() {
		Veggies[] veggies = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
	public Pepperoni createPepperoni() {
		Pepperoni pepperoni  = new SlicedPepperoni();
		return pepperoni;
	}
	public Clams createClam() {
		Clams clams = new FreshClams();
		return clams;
	}
}

class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
	public Dough createDough() {
		Dough dough = new ThickCrustDough();
		return dough;
	}
	public Sauce createSauce() {
		Sauce sauce = new PlumTomatoSauce();
		return sauce;
	}
	public Cheese createCheese() {
		Cheese cheese = new MozzarellaCheese();
		return cheese;
	}
	public Veggies[] createVeggies() {
		Veggies[] veggies = { };
		return veggies;
	}
	public Pepperoni createPepperoni() {
		Pepperoni pepperoni  = new SlicedPepperoni();
		return pepperoni;
	}
	public Clams createClam() {
		Clams clams = new FrozenClams();
		return clams;
	}
}

class CaliforniaPizzaIngredientFactory implements PizzaIngredientFactory {
	public Dough createDough() {
		Dough dough = new VeryThinCrust();
		return dough;
	}
	public Sauce createSauce() {
		Sauce sauce = new BruschettaSauce();
		return sauce;
	}
	public Cheese createCheese() {
		Cheese cheese = new GoatCheese();
		return cheese;
	}
	public Veggies[] createVeggies() {
		Veggies[] veggies = { };
		return veggies;
	}
	public Pepperoni createPepperoni() {
		Pepperoni pepperoni  = new SlicedPepperoni();
		return pepperoni;
	}
	public Clams createClam() {
		Clams clams = new Camari();
		return clams;
	}
}

// Ingredients
abstract class Dough {
	
}

class ThinCrustDough extends Dough {
	
}

class ThickCrustDough extends Dough {
	
}

class VeryThinCrust extends Dough {
	
}

abstract class Sauce {
	
}

class MarinaraSauce extends Sauce {
	
}

class PlumTomatoSauce extends Sauce {
	
}

class BruschettaSauce extends Sauce {
	
}

abstract class Cheese {
	
}

class ReggianoCheese extends Cheese {
	
}

class MozzarellaCheese extends Cheese {
	
}

class GoatCheese extends Cheese {
	
}

abstract class Veggies {
	
}

class Garlic extends Veggies {
	
}

class Onion extends Veggies {
	
}

class Mushroom extends Veggies {
	
}

class RedPepper extends Veggies {
	
}

abstract class Pepperoni{
	
}

class SlicedPepperoni extends Pepperoni {
	
}

abstract class Clams {
	
}

class FreshClams extends Clams {
	
}

class FrozenClams extends Clams {
	
}

class Camari extends Clams {
	
}

abstract class Pizza3 {
	String name;
	Dough dough;
	Sauce sauce;
	Veggies veggies[];
	Cheese cheese;
	Clams clam;
	Pepperoni pepperoni;
	
	abstract void prepare();
	
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}

class CheesePizza3 extends Pizza3 {
	
	PizzaIngredientFactory ingredientFactory;
	
	public CheesePizza3(PizzaIngredientFactory ingredientFactory) {
		name = "Cheese Pizza";
		this.ingredientFactory = ingredientFactory;
	}
	
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
	}
}

class ClamPizza3 extends Pizza3 {
	
	PizzaIngredientFactory ingredientFactory;
	
	public ClamPizza3(PizzaIngredientFactory ingredientFactory) {
		name = "Clam Pizza";
		this.ingredientFactory = ingredientFactory;
	}
	
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		clam = ingredientFactory.createClam();
	}
}

class PepperoniPizza3 extends Pizza3 {
	
	PizzaIngredientFactory ingredientFactory;
	
	public PepperoniPizza3(PizzaIngredientFactory ingredientFactory) {
		name = "Pepperoni Pizza";
		this.ingredientFactory = ingredientFactory;
	}
	
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		pepperoni = ingredientFactory.createPepperoni();
	}
}

class VeggiePizza3 extends Pizza3 {
	
	PizzaIngredientFactory ingredientFactory;
	
	public VeggiePizza3(PizzaIngredientFactory ingredientFactory) {
		name = "Veggies Pizza";
		this.ingredientFactory = ingredientFactory;
	}
	
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		veggies = ingredientFactory.createVeggies();
	}
}

// Pizza store
abstract class PizzaStore3 {
	
	public Pizza3 orderPizza(String type) {
		Pizza3 pizza = createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
	abstract Pizza3 createPizza(String type);
}

class NYPizzaStore3 extends PizzaStore3 {
	
	Pizza3 createPizza(String type) {
		Pizza3 pizza = null;
		PizzaIngredientFactory factory = new NYPizzaIngredientFactory();
		
		if (type.equals("cheese")) {
			pizza = new CheesePizza3(factory);
			pizza.setName("New York style Cheese Pizza");
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza3(factory);
			pizza.setName("New York style Pepperoni Pizza");
		} else if (type.equals("clam")) {
			pizza = new ClamPizza3(factory);
			pizza.setName("New York style Clam Pizza");
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza3(factory);
			pizza.setName("New York style Veggie Pizza");
		}
		
		return pizza;
	}
}

class ChicagoPizzaStore3 extends PizzaStore3 {
	
	Pizza3 createPizza(String type) {
		Pizza3 pizza = null;
		PizzaIngredientFactory factory = new ChicagoPizzaIngredientFactory();
		
		if (type.equals("cheese")) {
			pizza = new CheesePizza3(factory);
			pizza.setName("Chicago style Cheese Pizza");
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza3(factory);
			pizza.setName("Chicago style Pepperoni Pizza");
		} else if (type.equals("clam")) {
			pizza = new ClamPizza3(factory);
			pizza.setName("Chicago style Clam Pizza");
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza3(factory);
			pizza.setName("Chicago style Veggie Pizza");
		}
		
		return pizza;
	}
}
