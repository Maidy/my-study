package ch09.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class MenuTestDrive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MenuComponent pancakeHouseMenu = new Menu("팬케이크 하우스 메뉴", "아침 메뉴");
		MenuComponent dinerMenu = new Menu("객체마을 식당 메뉴", "점심 메뉴");
		MenuComponent cafeMenu = new Menu("카페 메뉴", "저녁 메뉴");
		MenuComponent dessertMenu = new Menu("디저트 메뉴", "디저트를 즐겨 보세요!");
		
		MenuComponent allMenu = new Menu("전체 메뉴", "전체 메뉴");
		
		allMenu.add(pancakeHouseMenu);
		allMenu.add(dinerMenu);
		allMenu.add(cafeMenu);
		
		pancakeHouseMenu.add(new MenuItem("K&B 팬케이크 세트", "스크램블드 에그와 토스트가 곁들여진 팬케이크", true, 2.99));
		pancakeHouseMenu.add(new MenuItem("레귤러 팬케이크 세트", "달걀 후라이와 소시지가 곁들여진 팬케이크", false, 2.99));
		pancakeHouseMenu.add(new MenuItem("블루베리 팬케이크", "신선한 블루베리와 블루베리 시럽으로 만든 팬케이크", true, 3.49));
		pancakeHouseMenu.add(new MenuItem("와플", "와플, 취향에 따라 블루베리나 딸기를 얹을 수 있습니다.", true, 3.59));
		
		dinerMenu.add(new MenuItem("채식주의자용 BLT", "통밀 위에 (식물성)베이컨, 상추, 토마토를 얹은 메뉴", true, 2.99));
		dinerMenu.add(new MenuItem("BLT", "통밀 위에 베이컨, 상추, 토마토를 얹은 메뉴", false, 2.99));
		dinerMenu.add(new MenuItem("오늘의 스프", "감자 샐러드를 곁들인 오늘의 스프", false, 3.49));
		dinerMenu.add(new MenuItem("핫도그", "샤워크라우트, 갖은 양념, 양파, 치즈가 곁들여진 핫도그", false, 3.09));
		dinerMenu.add(new MenuItem("파스타", "마리나라 소스 스파게티. 효모빵도 드립니다.", true, 3.89));
		dinerMenu.add(dessertMenu);
		
		dessertMenu.add(new MenuItem("애플 파이", "바삭바삭한 크러스트에 바닐라 아이스크림에 얹혀 있는 애플 파이", true, 1.59));
		dessertMenu.add(new MenuItem("치츠케이크", "초콜릿 그레이엄 크러스트 위에 부드러운 뉴욕 치즈케이크", true, 1.99));
		dessertMenu.add(new MenuItem("소르베", "라스베리에 라임의 절묘한 조화", true, 1.89));
		
		cafeMenu.add(new MenuItem("베지 버거와 에어 프라이", "통밀빵, 상추, 토마토, 감자튀김이 첨가된 베지 버거", true, 3.99));
		cafeMenu.add(new MenuItem("오늘의 스프", "샐러드가 곁들여진 오늘의 스프", false, 3.69));
		cafeMenu.add(new MenuItem("베리또", "통 핀토콩과 살사, 구아카몰이 곁들여진 푸짐한 베리또", true, 4.29));
		
		Waitress waitress = new Waitress(allMenu);
		waitress.printMenu();
		
		System.out.println("\n=========================\nVegetarian Menus\n=========================");
		waitress.printVegetarianMenu();
	}

}

class Waitress {
	MenuComponent allMenus;
	
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void printMenu() {
		allMenus.print();
	}
	
	public void printVegetarianMenu() {
		Iterator<MenuComponent> iterator = allMenus.createIterator();
		while (iterator.hasNext()) {
			MenuComponent menuComponent = iterator.next();
			try {
				if (menuComponent.isVegetarian()) {
					menuComponent.print();
				}
			} catch (UnsupportedOperationException e) {
				
			}
		}
	}
}

abstract class MenuComponent {
	public String getName() {
		throw new UnsupportedOperationException();
	}
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}
	public void print() {
		throw new UnsupportedOperationException();
	}
	public void add(MenuComponent comp) {
		throw new UnsupportedOperationException();
	}
	public void remove(MenuComponent comp) {
		throw new UnsupportedOperationException();
	}
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	public abstract Iterator<MenuComponent> createIterator();
}


class MenuItem extends MenuComponent {
	String name;
	String description;
	double price;
	boolean vegetarian;
	
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
	public double getPrice() {
		return price;
	}
	public boolean isVegetarian() {
		return vegetarian;
	}
	public void print() {
		System.out.print("  " + getName());
		if (isVegetarian()) {
			System.out.print("(v)");
		}
		System.out.println(",  " + getPrice());
		System.out.println("    -- " + getDescription());
	}
	
	public Iterator<MenuComponent> createIterator() {
		return new NullIterator();
	}
}

class Menu extends MenuComponent {
	String name;
	String description;
	ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>(); 
	
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(",  " + getDescription());
		System.out.println("----------------------");
		
		Iterator<MenuComponent> iter = menuComponents.iterator();
		while (iter.hasNext()) {
			MenuComponent comp = iter.next();
			comp.print();
		}
		
	}
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}
	public MenuComponent getChild(int i) {
		return menuComponents.get(i);
	}
	
	public Iterator<MenuComponent> createIterator() {
		return new CompositeIterator(menuComponents.iterator());
	}
}


class CompositeIterator implements Iterator<MenuComponent> {
	Stack<Iterator<MenuComponent>> stack = new Stack<Iterator<MenuComponent>>();
	
	public CompositeIterator(Iterator<MenuComponent> iterator) {
		stack.push(iterator);
	}
	
	@Override
	public boolean hasNext() {
		
		if (stack.empty()) {
			return false;
		}
		
		Iterator<MenuComponent> iterator = stack.peek();
		if (iterator.hasNext()) {
			return true;
		} else {
			stack.pop();
			return hasNext();
		}
	}

	@Override
	public MenuComponent next() {
		
		if (hasNext()) {
			Iterator<MenuComponent> iterator = stack.peek();
			MenuComponent component = iterator.next();
			if (component instanceof Menu) {
				stack.push(component.createIterator());
				return next();
			}
			return component;
		}
		return null;

	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}

class NullIterator implements Iterator<MenuComponent> {

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public MenuComponent next() {
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();		
	}
	
}
