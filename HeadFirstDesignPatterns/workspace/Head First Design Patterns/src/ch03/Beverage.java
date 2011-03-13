package ch03;

public abstract class Beverage {
    String description = "unknown";
    int size;
    
    static public int TALL = 0;
    static public int GRANDE = 1;
    static public int VENTI = 2;
    
    public String getDescription() {
    	return description;
    }

    public int getSize() {
    	return this.size;
    }
    
    public abstract double cost();
}
