package controllers;

import javafx.scene.image.Image;

public class Boss {
    private int bossHp;
    private int bossDmg;
    private int currentHp;
    private Image bossImage;

    public Boss(int bossHp, int bossDmg, Image bossImage){
        this.bossHp = bossHp;
        this.bossDmg = bossDmg;
        this.bossImage = bossImage;
    }

    public int getBossHp() {
        return bossHp;
    }

    public void setBossHp(int bossHp) {
        this.bossHp = bossHp;
    }

    public int getBossDmg() {
        return bossDmg;
    }

    public void setBoostDmg(int boostDmg) {
        this.bossDmg = boostDmg;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public Image getBossImage() {
        return bossImage;
    }

    public void setBossImage(Image bossImage) {
        this.bossImage = bossImage;
    }
}
