package controllers;

import javafx.scene.image.Image;

public class BossList {
    private final int playerLevel;

    public BossList(int playerLevel){
        this.playerLevel = playerLevel;
    }

    public Boss getBoss(){
        switch (playerLevel) {
            case 5 -> {
                Image image = new Image("/img/alienn.png");
                return new Boss(2000, 50, image);
            }
            case 10 -> {
                Image image = new Image("/img/zucker.png");
                return new Boss(10000, 100, image);
            }
            case 15 -> {
                Image image = new Image("/img/musk.png");
                return new Boss(20000, 200, image);
            }
            case 20 -> {
                Image image = new Image("/img/jobs.png");
                return new Boss(50000, 300, image);
            }
            case 25 -> {
                Image image = new Image("/img/gates.png");
                return new Boss(200000, 500, image);
            }
            default -> {
                return null;
            }
        }
    }
}
