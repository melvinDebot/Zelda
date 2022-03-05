package model;

public abstract class Element {

	private int x, y;
	private String name;
	private char charForMap;

	public Element() {

	}
	public Element(int x, int y, String name, char charForMap) {
		this.x = x;
		this.y = y;
		this.setName(name);
		this.setCharForMap(charForMap);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getCharForMap() {
		return charForMap;
	}

	public void setCharForMap(char charForMap) {
		this.charForMap = charForMap;
	}



}