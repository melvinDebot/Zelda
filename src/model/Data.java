package model;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {

	public final static int RATIODISPLAY = 40;
	public final static int GAMESCENEWIDHT = 800;
	public final static int GAMESCENEHEIGHT = 800;

	final static int MAPLENGHT = 20;
	final static int MAPWIDTH = 20;
	public final static int NBENEMIS = 10;
	public final static int NBITEMS = 5;
	static public boolean gameStarted = false;


	Element[][] map;
	Personnage player = new Player(5,5,"Link", 'L', 10, 10);
	List<Enemy> enemies = new ArrayList<Enemy>();
	List<Item> items = new ArrayList<Item>();



	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Personnage getPlayer() {
		return player;
	}

	public void setPlayer(Personnage player) {
		this.player = player;
	}

	public Data() {
		map = new Element[MAPLENGHT][MAPWIDTH];
	}

	public Element[][] getMap() {
		return map;
	}

	public void setMap(Element[][] map) {
		this.map = map;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public static int getMapLenght() {
		return MAPLENGHT;
	}

	public static int getMapWidth() {
		return MAPWIDTH;
	}

	public static int getNbenemis() {
		return NBENEMIS;
	}

	public static int getNbitems() {
		return NBITEMS;
	}

}
