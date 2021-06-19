package controllers;

public class Player {

    private int level;
    private int health;
    private int money;
    private int dmg;

    public Player(){
        level = 1;
        health = 100;
        money = 1000;
        dmg = 10;
    }

    public int attackValue(){
        return dmg;
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
}
