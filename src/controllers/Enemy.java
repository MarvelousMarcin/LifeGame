package controllers;


import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy{

    private int maxHealth;
    private int currentHealth;
    private int dmg;
    private final Image ePic;
    private final int exp;
    private int loot;

    public Enemy(int dmg, int health, Image ePic, int exp, int loot){
        this.maxHealth = health;
        this.dmg = dmg;
        this.ePic = ePic;
        this.exp = exp;
        this.loot = loot;
        currentHealth = maxHealth;
    }

    public int getExp(){
        return exp;
    }

    public void getDmg(int dmg) {
        this.currentHealth -= dmg;
    }

    public int getHealth(){
        return currentHealth;
    }

    public ProgressBar getHealthBar(){
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress((float)currentHealth/maxHealth);
        return progressBar;
    }

    public int getDmg() {
        return dmg;
    }

    public Image getEnemyPic(){
        return ePic;
    }
}
