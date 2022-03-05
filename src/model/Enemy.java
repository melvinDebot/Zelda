package model;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Enemy extends Personnage{

	public Text printPdv;

	public Enemy(int x, int y, String name, char charForMap, int pdv, int pdd) {
		super(x, y, name, charForMap, pdv, pdd);
		printPdv = new Text(""+pdv);
		printPdv.setX(x * Data.RATIODISPLAY);
		printPdv.setY(y * Data.RATIODISPLAY - 40);
		printPdv.setFill(Color.RED);
	}


}