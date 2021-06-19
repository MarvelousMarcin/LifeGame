package controllers;

import javafx.scene.control.ProgressBar;

public class Player {

    private int level = 0;
    private int health;
    private int money;
    private int dmg;
    private int playerExp;
    private int nextLevelExpNeeded = 1000;

    public Player() {
        level = 1;
        playerExp = 0;
        health = 100;
        money = 1000;
        dmg = 10;
    }

    public void levelUp(){
        level += 1;
        playerExp = playerExp-nextLevelExpNeeded;
        nextLevelExpNeeded += 100;
    }

    public boolean checkIfNextLevel(){
        return playerExp >= nextLevelExpNeeded;
    }

    public ProgressBar levelBar(){
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress((float)playerExp/nextLevelExpNeeded);
        return progressBar;
    }

    public void addExp(int exp){
        playerExp += exp;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public int getHealth(){
        return health;
    }

    public int getMoney(){
        return money;
    }

    public int getDmg(){
        return dmg;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setMoney(int money){
        this.health = money;
    }

    public int getLevel(){
        return level;
    }

}
