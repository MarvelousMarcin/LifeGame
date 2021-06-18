package controllers;

public class Player {

    private int level;
    private int health;
    private int money;
    private int dmgDealt;
    private int monstersKilled;

    public Player(){
        level = 0;
        health = 100;
        money = 1000;
        dmgDealt = 0;
        monstersKilled = 0;
    }

    public int getHealth(){
        return health;
    }

    public int getMoney(){
        return money;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setMoney(int money){
        this.health = money;
    }
}
