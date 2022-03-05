package sample;

import model.*;
import utilitaires.Utilitaires;

import java.util.HashMap;

public class Game {

	HashMap<Integer, String> randomMouvToString;
	Data data;

	public Game() {
		randomMouvToString  = new HashMap<Integer, String>();
		randomMouvToString.put(0, "z");
		randomMouvToString.put(1, "s");
		randomMouvToString.put(2, "q");
		randomMouvToString.put(3, "d");



		data = new Data();
		/*Utilitaires.createEnemies(data);
		Utilitaires.createPerso(data);
		Utilitaires.createItems(data);
*/


	}

	public void game() {
		Data data = new Data();
		Utilitaires.fill2DArrayChar(data.getMap());
		Utilitaires.fillMapWithElement(data);
		Utilitaires.createAndPlaceEnemyOnMap(data);
		Utilitaires.createAndPlaceItemOnMap(data);

		boolean gameOn = true;
		while(gameOn) {
			//	Utilitaires.print2DArrayCharToConsole(data.getMap());
			//	Utilitaires.printInfoOnPersonnage(data);
			System.out.println("Mouvement ?");
			//	String mouvPlayer = Utilitaires.saisieString();
			//	handleMouvablePersonnageMouvement(data, mouvPlayer, data.getPlayer());
			Utilitaires.fillMapWithElement(data);
			handleEnemyMouvement(data);
			Utilitaires.fillMapWithEnemy(data);
		}
	}

	public void handleEnemyMouvement(Data data) {
		String mouv = null;
		for (Personnage enemy : data.getEnemies()) {
			if(isEnemy5casesAwayFromPlayer(data, enemy)) {
				if(Math.abs(data.getPlayer().getX()- enemy.getX() )
						<=Math.abs(data.getPlayer().getY() - enemy.getY())) {
					//mouv Y
					if	(data.getPlayer().getY() - enemy.getY()>0) {
						mouv = "d";
					}
					else {
						mouv = "q";
					}
				}
				else {
					if	(data.getPlayer().getX() - enemy.getX()>0) {
						mouv = "s";
					}
					else {
						mouv = "z";
					}
				}

				handleMouvablePersonnageMouvement(data, mouv, enemy);
			}
			//if true -> enemy goes towards player
			//else ->
			else {
				int i = Utilitaires.randomInt(4);
				String s = randomMouvToString.get(i);
				handleMouvablePersonnageMouvement(data, s, enemy);
			}
		}
	}

	public boolean isEnemy5casesAwayFromPlayer(Data data, Personnage enemy) {

		int diff = (Math.abs(data.getPlayer().getX() - enemy.getX())
				+ (Math.abs(data.getPlayer().getY() - enemy.getY())));

		return diff<=5;
	}

	public void handleMouvablePersonnageMouvement(Data data, String mouvPlayer, Personnage mouvableElement) {
		Utilitaires.clearPositionElement(data, mouvableElement);
		Personnage p = mouvableElement;
		switch(mouvPlayer.toLowerCase()) {
			case "q" :
				if(p.getX()-1<=0){

				}
				else if(data.getMap()[p.getX()-1][p.getY()].getCharForMap() == ' ') {
					//	Utilitaires.clearPositionElement(data, mouvableElement);

					p.setX(p.getX() - 1);
				}
				else if(data.getMap()[p.getX()-1][p.getY()].getCharForMap() == 'I' && (p instanceof Player)){
					Item i = (Item) data.getMap()[p.getX()-1][p.getY()];

					DataView.groupGame.getChildren().remove(DataView.hmItems.get(i));
					DataView.hmItems.remove(i);
					Utilitaires.clearPositionElement(data, mouvableElement);

					p.getInventory().add(getPickupItem(data, p.getX()-1, p.getY()));
					p.setX(p.getX()-1);
				}
				else if(data.getMap()[p.getX()-1][p.getY()].getCharForMap() == 'E' && (p instanceof Player)) {
					//Utilitaires.clearPositionElement(data, mouvableElement);

					Element e = getEnemyFromCoordonate(data, p.getX()-1, p.getY());
					data.getEnemies().remove(e);
					p.setX(p.getX()-1);
				}
				else if(data.getMap()[p.getX()-1][p.getY()].getCharForMap() == 'L' && (p instanceof Enemy)) {
					System.out.println("Game over");
				}
				break;
			case "d" :
				//p.setY(p.getY()+1);

				if(p.getX()+1>=data.getMap().length){

				}
				else if(data.getMap()[p.getX()+1][p.getY()].getCharForMap() == ' ') {
					p.setX(p.getX()+1);
				}
				else if(data.getMap()[p.getX()+1][p.getY()].getCharForMap() == 'I' && (p instanceof Player)){
					Item i = (Item) data.getMap()[p.getX()+1][p.getY()];

					DataView.groupGame.getChildren().remove(DataView.hmItems.get(i));
					DataView.hmItems.remove(i);
					Utilitaires.clearPositionElement(data, mouvableElement);

					p.getInventory().add(getPickupItem(data, p.getX()-1, p.getY()));
					p.setX(p.getX()+1);
				}
				else if(data.getMap()[p.getX()+1][p.getY()].getCharForMap() == 'E' && (p instanceof Player)) {
					Element e = getEnemyFromCoordonate(data, p.getX()+1, p.getY());
					data.getEnemies().remove(e);
					p.setX(p.getX()+1);
				}
				else if(data.getMap()[p.getX()+1][p.getY()].getCharForMap() == 'L' && (p instanceof Enemy)) {
					System.out.println("Game over");
				}
				break;
			case "z" :
				//p.setX(p.getX()-1);

				if(p.getY()-1<=0){

				}
				else if(data.getMap()[p.getX()][p.getY()-1].getCharForMap() == ' ') {
					p.setY(p.getY()-1);
				}
				else if(data.getMap()[p.getX()][p.getY()-1].getCharForMap() == 'I' && (p instanceof Player)){
					Item i = (Item) data.getMap()[p.getX()][p.getY()-1];

					DataView.groupGame.getChildren().remove(DataView.hmItems.get(i));
					DataView.hmItems.remove(i);
					Utilitaires.clearPositionElement(data, mouvableElement);

					p.getInventory().add(getPickupItem(data, p.getX()-1, p.getY()));
					p.setY(p.getY()-1);
				}
				else if(data.getMap()[p.getX()][p.getY()-1].getCharForMap() == 'E' && (p instanceof Player)) {
					Element e = getEnemyFromCoordonate(data, p.getX(), p.getY()-1);
					data.getEnemies().remove(e);
					p.setY(p.getY()-1);
				}
				else if(data.getMap()[p.getX()][p.getY()-1].getCharForMap() == 'L' && (p instanceof Enemy)) {
					System.out.println("Game over");
				}
				break;
			case "s" :
				if(p.getY()+1>=data.getMap().length){

				}
				else if(data.getMap()[p.getX()][p.getY()+1].getCharForMap() == ' ') {
					p.setY(p.getY()+1);
				}
				else if(data.getMap()[p.getX()][p.getY()+1].getCharForMap() == 'I' && (p instanceof Player)){
					Item i = (Item) data.getMap()[p.getX()][p.getY()+1];

					DataView.groupGame.getChildren().remove(DataView.hmItems.get(i));
					DataView.hmItems.remove(i);
					Utilitaires.clearPositionElement(data, mouvableElement);


					p.getInventory().add(getPickupItem(data, p.getX()-1, p.getY()));
					p.setY(p.getY()+1);
				}
				else if(data.getMap()[p.getX()][p.getY()+1].getCharForMap() == 'E' && (p instanceof Player)) {
					Element e = getEnemyFromCoordonate(data, p.getX(), p.getY()+1);
					data.getEnemies().remove(e);
					p.setY(p.getY()+1);
				}
				else if(data.getMap()[p.getX()][p.getY()+1].getCharForMap() == 'L' && (p instanceof Enemy)) {
					System.out.println("Game over");
				}
				break;
			default :
				break;
		}

	}


	private Element getEnemyFromCoordonate(Data data, int x, int y) {
		for (Enemy e : data.getEnemies()) {
			if(e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		return null;
	}


	private Item getPickupItem(Data data, int x, int y) {
		for (Item i : data.getItems()) {
			if(i.getX() == x && i.getY() == y) {
				return i;
			}
		}
		return null;
	}

}