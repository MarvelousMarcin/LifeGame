package controllers;

public class Upgrade {
    private final int cost;
    private final int gain;

    public int getCost() {
        return cost;
    }

    public int getGain() {
        return gain;
    }

    public Upgrade(int cost, int gain){
        this.gain = gain;
        this.cost = cost;
    }
}
