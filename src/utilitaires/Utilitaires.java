package utilitaires;


import model.*;

import java.util.Random;
import java.util.Scanner;

public class Utilitaires {

	/*public static void print2DArrayCharToConsole(Element[][] tad2D) {
		for (Element[] cs : tad2D) {
			for (Element cs2 : cs) {
				System.out.print(cs2 + "  ");
			}
			System.out.println("");
		}
	}*/

	public static Element[][] fill2DArrayChar(Element[][] tab2D) {
		for (int i = 0; i<tab2D.length; i++) {
			for (int j = 0; j<tab2D[i].length; j++) {
				if(i==0 || j==0 || i==tab2D.length-1 || j==tab2D[i].length-1)
					tab2D[i][j]= new ElementDecor(i, j, "Bordure", '0');
				else {
					tab2D[i][j]= new ElementDecor(i, j, "Vide", ' ');
				}
			}
		}
		return tab2D;
	}

	public static void fillMapWithElement(Data data) {
		data.getMap()[data.getPlayer().getX()][data.getPlayer().getY()] =
				data.getPlayer();
	}

	/*public static String saisieString() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}*/

	public static void clearPositionElement(Data data, Personnage mouvableElement) {
		data.getMap()[mouvableElement.getX()][mouvableElement.getY()] =
				new ElementDecor(mouvableElement.getX(), mouvableElement.getY(), "Vide", ' ' ) ;
	}

	public static void createAndPlaceEnemyOnMap(Data data) {
		for(int i = 1; i<=Data.NBENEMIS; i++) {
			int[] positionToCreation = positionRandomOnMap();
			data.getEnemies().add(new Enemy(positionToCreation[0]+1, positionToCreation[1]+1, "Enemy", 'E' , 10, 10));
		}
		fillMapWithEnemy(data);
	}

	public static void createEnemies(Data data){
		for(int i = 1; i<=Data.NBENEMIS; i++) {
			int[] positionToCreation = positionRandomOnMap();
			data.getEnemies().add(new Enemy(positionToCreation[0]+1, positionToCreation[1]+1, "Enemy", 'E', 10, 10));
		}
	}

	public static void createPerso(Data data){
		data.setPlayer(new Player(5,5,"Link", 'L', 10, 10));
	}

	public static void createItems(Data data){
		for(int i = 1; i<=Data.NBITEMS; i++) {
			int[] positionToCreation = positionRandomOnMap();
			data.getItems().add(new Item(positionToCreation[0]+1, positionToCreation[1]+1, "Item", 'I'));
		}
	}


	public static void fillMapWithEnemy(Data data) {
		for (Enemy e : data.getEnemies()) {
			data.getMap()[e.getX()][e.getY()] = e;
		}
	}

	public static void fillMapWithItem(Data data) {
		for (Item i : data.getItems()) {
			data.getMap()[i.getX()][i.getY()] = i;
		}
	}
	public static int[] positionRandomOnMap() {
		int[] randomPostion = new int[2];
		randomPostion[0] = randomInt(Data.getMapLenght()-2);
		randomPostion[1] = randomInt(Data.getMapWidth()-2);
		return randomPostion;
	}

	public static int randomInt(int maxBound) {
		Random r = new Random();
		return r.nextInt(maxBound);
	}

	public static void createAndPlaceItemOnMap(Data data) {
		for(int i = 1; i<=Data.NBITEMS; i++) {
			int[] positionToCreation = positionRandomOnMap();
			data.getItems().add(new Item(positionToCreation[0]+1, positionToCreation[1]+1, "Item", 'I'));
		}
		fillMapWithItem(data);
	}

}