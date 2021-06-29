package controllers;

public class StatsSaver {
    private int moneyGained;
    private int dmgDealt;
    private int expGained;
    private int healthLost;
    private int clickMade;
    private int monstersKilled;

    public int getMoneyGained() {
        return moneyGained;
    }

    public void addMoneyGained(int value) {
        this.moneyGained+= value;
    }

    public int getDmgDealt() {
        return dmgDealt;
    }

    public void addDmgDealt(int value) {
        this.dmgDealt += value;
    }

    public int getExpGained() {
        return expGained;
    }

    public void addExpGained(int value) {
        expGained+= value;
    }

    public int getHealthLost() {
        return healthLost;
    }

    public void addHealthLost(int value) {
        this.healthLost += value;
    }

    public int getClickMade() {
        return clickMade;
    }

    public void addClickMade() {
        clickMade++;
    }

    public int getMonstersKilled() {
        return monstersKilled;
    }

    public void addMonstersKilled() {
        monstersKilled++;
    }

    public StatsSaver() {
        this.moneyGained = 0;
        this.dmgDealt = 0;
        this.expGained = 0;
        this.healthLost = 0;
        this.clickMade = 0;
        this.monstersKilled = 0;
    }
}
