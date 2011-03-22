package ch09;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class DinerMenuTestDrive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		Menu pancakeHouseMenu = new PancakeHouseMenu();
		Menu dinerMenu = new DinerMenu();
		Menu cafeMenu = new CafeMenu();
		
		JavaWaitress waitress = new JavaWaitress(pancakeHouseMenu, dinerMenu, cafeMenu);
		*/
		
		ArrayList<Menu> menus = new ArrayList<Menu>();
		menus.add(new PancakeHouseMenu());
		menus.add(new DinerMenu());
		menus.add(new CafeMenu());

		JavaWaitress waitress = new JavaWaitress(menus);
		waitress.printMenu();
	}

}


class JavaWaitress {
	
	/*
	Menu pancakeHouseMenu;
	Menu dinerMenu;
	Menu cafeMenu;
	*/
	
	ArrayList<Menu> menus;
	
	public JavaWaitress(ArrayList<Menu> menus) {
		
		/*
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
		this.cafeMenu = cafeMenu;
		*/
		
		this.menus = menus;
	}
	
	public void printMenu() {
		/*
		ArrayList<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
		for (int i = 0; i < breakfastItems.size(); i++) {
			MenuItem menuItem = breakfastItems.get(i);
			System.out.print(menuItem.getName() + " ");
			System.out.print(menuItem.getDescription() + " ");
			System.out.print(menuItem.getPrice() + " ");
		}
		
		MenuItem[] lunchItems = dinerMenu.getMenuItems();
		for (int i = 0; i < lunchItems.length; i++) {
			MenuItem menuItem = lunchItems[i];
			System.out.print(menuItem.getName() + " ");
			System.out.print(menuItem.getDescription() + " ");
			System.out.print(menuItem.getPrice() + " ");
		}
		*/
		
		Iterator<Menu> iter = menus.iterator();
		while (iter.hasNext()) {
			Menu menu = iter.next();
			System.out.println("메뉴----");
			printMenu(menu.createIterator());
		}

		/*
		Iterator dinerIter = dinerMenu.createIterator();
		Iterator pancakeHouseIter = pancakeHouseMenu.createIterator();
		Iterator cafeIter = cafeMenu.createIterator();
		
		System.out.println("메뉴\n----\n아침 메뉴");
		printMenu(pancakeHouseIter);

		System.out.println("\n점심 메뉴");
		printMenu(dinerIter);
		
		System.out.println("\n저녁 메뉴");
		printMenu(cafeIter);
		*/
		
	}
	
	public void printMenu(Iterator<MenuItem> iter) {
		while (iter.hasNext()) {
			MenuItem menuItem = iter.next();
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription() + "-- ");
		}
	}
	
	public void printBreakfastMenu() {
		
	}
	
	public void printLunchMenu() {
		
	}
	
	public void printVegetarianMenu() {
		
	}
	
	public boolean isItemVegetarian(String name) {
		return true;
	}
}

class MenuItem {
	String name;
	String description;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name, String description, boolean vegetarian, double price) {
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public double getPrice() {
		return price;
	}
}

interface Menu {
	public Iterator<MenuItem> createIterator();
}

class PancakeHouseMenu implements Menu {
	ArrayList<MenuItem> menuItems;
	
	public PancakeHouseMenu() {
		menuItems = new ArrayList<MenuItem>();
		
		addItem("K&B 팬케이크 세트", "스크램블드 에그와 토스트가 곁들여진 팬케이크", true, 2.99);
		addItem("레귤러 팬케이크 세트", "달걀 후라이와 소시지가 곁들여진 팬케이크", false, 2.99);
		addItem("블루베리 팬케이크", "신선한 블루베리와 블루베리 시럽으로 만든 팬케이크", true, 3.49);
		addItem("와플", "와플, 취향에 따라 블루베리나 딸기를 얹을 수 있습니다.", true, 3.59);
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.add(menuItem);
	}
	
	/*
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	*/
	
	public Iterator<MenuItem> createIterator() {
		// return new PancakeHouseMenuIterator(menuItems);
		return menuItems.iterator();
	}
}

class DinerMenu implements Menu {
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	MenuItem[] menuItems;
	
	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
		
		addItem("채식주의자용 BLT", "통밀 위에 (식물성)베이컨, 상추, 토마토를 얹은 메뉴", true, 2.99);
		addItem("BLT", "통밀 위에 베이컨, 상추, 토마토를 얹은 메뉴", false, 2.99);
		addItem("오늘의 스프", "감자 샐러드를 곁들인 오늘의 스프", false, 3.49);
		addItem("핫도그", "샤워크라우트, 갖은 양념, 양파, 치즈가 곁들여진 핫도그", false, 3.09);
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		if (numberOfItems >= MAX_ITEMS) {
			System.err.println("죄송합니다. 메뉴가 꽉 찼습니다. 더 이상 추가할 수 없습니다.");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems++;
		}
	}

	/*
	public MenuItem[] getMenuItems() {
		return menuItems;
	}
	*/
	
	public Iterator<MenuItem> createIterator() {
		return new DinerMenuIterator(menuItems);
	}
}

class CafeMenu implements Menu {
	Hashtable<String, MenuItem> menuItems = new Hashtable<String, MenuItem>();
	
	public CafeMenu() {
		addItem("베지 버거와 에어 프라이", "통밀빵, 상추, 토마토, 감자튀김이 첨가된 베지 버거", true, 3.99);
		addItem("오늘의 스프", "샐러드가 곁들여진 오늘의 스프", false, 3.69);
		addItem("베리또", "통 핀토콩과 살사, 구아카몰이 곁들여진 푸짐한 베리또", true, 4.29);
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.put(menuItem.name, menuItem);
	}
	
	public Iterator<MenuItem> createIterator() {
		return menuItems.values().iterator();
	}
	
	/*
	public Hashtable<String, MenuItem> getItems() {
		return menuItems;
	}
	*/
}

/*
interface Iterator {
	public boolean hasNext();
	public Object next();
}

class PancakeHouseMenuIterator implements Iterator {
	
	ArrayList<MenuItem> items;
	int position = 0;
	
	public PancakeHouseMenuIterator(ArrayList<MenuItem> items) {
		this.items = items;
	}
	
	public boolean hasNext() {
		if (position >= items.size() || items.get(position) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Object next() {
		MenuItem item = items.get(position);
		position++;
		return item;
	}
	
	public void remove() {
		
	}
}
 */

class DinerMenuIterator implements Iterator<MenuItem> {
	
	MenuItem[] items;
	int position = 0;
	
	public DinerMenuIterator(MenuItem[] items) {
		this.items = items;
	}

	public MenuItem next() {
		MenuItem item = items[position];
		position++;
		return item;
	}

	public boolean hasNext() {
		if (position >= items.length || items[position] == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void remove() {
		if (position <= 0) {
			throw new IllegalStateException("next()를 한 번도 호출하지 않은 상태에서는 삭제할 수 없습니다.");
		}
		
		if (items[position-1] != null) {
			for (int i = position-1; i < (items.length - 1); i++) {
				items[i] = items[i+1];
			}
			items[items.length-1] = null;
		}
	}
}
