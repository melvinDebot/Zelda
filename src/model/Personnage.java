package model;

import java.util.ArrayList;

public class Personnage extends Element{

    private ArrayList<Item> inventory;
    private int pdv;
    private int pdd;


    public Personnage(int x, int y, String name, char charForMap, int pdv, int pdd) {
        super(x, y, name, charForMap);
        this.pdv = pdv;
        this.pdd = pdd;
        inventory = new ArrayList<Item>();
    }


    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public int getPdd() {
        return pdd;
    }

    public void setPdd(int pdd) {
        this.pdd = pdd;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

}
