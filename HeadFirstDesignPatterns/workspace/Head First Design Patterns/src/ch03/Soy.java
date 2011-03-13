package ch03;

public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
    	this.beverage = beverage;
    }
    
    public int getSize() {
    	return beverage.getSize();
    }

    public String getDescription() {
    	return beverage.getDescription() + ".Soy";
    }

    public double cost() {
    	double cost = beverage.cost();
    	int size = getSize();
    	if (size == Beverage.TALL) {
    		cost += 1.00;
    	} else if (size == Beverage.GRANDE) {
    		cost += 2.00;
    	} else {
    		cost += 3.00;
    	}
    	return cost;
    }
}
