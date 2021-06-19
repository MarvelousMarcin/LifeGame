package controllers;

import javax.swing.text.html.ImageView;

public abstract class Creature {

    private int maxHealth;
    private int dmg;
    private ImageView enemyImage;

    public Creature(int health, int dmg){
        this.maxHealth = health;
        this.dmg = dmg;
    }


    public abstract void getDmg(int dmg);

}
