package edu.carleton.leight;


public class EnemyImage {

    private int size;
    private String color;

    public EnemyImage(int size, String color) {
        this.size = size;
        this.color = color;
    }

    public int enemySize() {return this.size;}
    public String enemyColor() {return this.color;}
}
