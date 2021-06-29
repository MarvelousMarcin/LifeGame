package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Shop {

    @FXML
    private AnchorPane shopPane;

    @FXML
    private Circle dmgCir1;

    @FXML
    private Circle dmgCir2;

    @FXML
    private Circle dmgCir3;

    @FXML
    private Circle dmgCir4;

    @FXML
    private Circle dmgCir5;

    @FXML
    private Circle dmgCir6;

    @FXML
    private Circle healthUpCir1;

    @FXML
    private Circle healthUpCir2;

    @FXML
    private Circle healthUpCir3;

    @FXML
    private Circle healthUpCir4;

    @FXML
    private Circle healthUpCir5;

    @FXML
    private Circle healthUpCir6;

    @FXML
    private Circle lootCir1;

    @FXML
    private Circle lootCir2;

    @FXML
    private Circle lootCir3;

    @FXML
    private Circle lootCir4;

    @FXML
    private Circle lootCir5;

    @FXML
    private Circle lootCir6;

    @FXML
    private Button dmgUpgBut;

    @FXML
    private Circle expCir1;

    @FXML
    private Circle expCir2;

    @FXML
    private Circle expCir3;

    @FXML
    private Circle expCir4;

    @FXML
    private Circle expCir5;

    @FXML
    private Circle expCir6;

    @FXML
    private Button healthUpgBut;

    @FXML
    private Button lootUpgBut;

    @FXML
    private Button expUpgBut;

    private final HashMap<Integer, Upgrade> levels = new HashMap<>();
    private Player player;
    private Menu menu;
    private int dmgLevel = 1;
    private int healthLevel = 1;
    private int lootLevel = 1;
    private int expLevel = 1;

    public AnchorPane getShopPane(){
        return shopPane;
    }

    public void initialize(){
        createUpgradeList();

        EventHandler<ActionEvent> upgButAction = actionEvent -> {
            if(dmgLevel <= 6 && player.getMoney() >= levels.get(dmgLevel).getCost()){
                paintCir(getCircleDmg(dmgLevel));
                player.takeMoney(levels.get(dmgLevel).getCost());
                player.addDmg(levels.get(dmgLevel).getGain());
                dmgLevel++;
                menu.updateUserStats(player);
            }
        };
        dmgUpgBut.addEventHandler(ActionEvent.ACTION, upgButAction);

        EventHandler<ActionEvent> healthButAction = actionEvent -> {
            if(healthLevel <= 6 && player.getMoney() >= levels.get(healthLevel).getCost()){
                paintCir(getCircleHealth(healthLevel));
                player.takeMoney(levels.get(healthLevel).getCost());
                player.addHealth(levels.get(healthLevel).getGain());
                healthLevel++;
                menu.updateUserStats(player);
            }
        };
        healthUpgBut.addEventHandler(ActionEvent.ACTION, healthButAction);

        EventHandler<ActionEvent> lootButAction = actionEvent -> {
            if(lootLevel <= 6 && player.getMoney() >= levels.get(lootLevel).getCost()){
                paintCir(getCircleLoot(lootLevel));
                player.takeMoney(levels.get(lootLevel).getCost());
                Game.setLootEnemy(Game.getLootEnemy()+levels.get(lootLevel).getGain());
                System.out.println(Game.getLootEnemy());

                lootLevel++;
                menu.updateUserStats(player);
            }
        };

        lootUpgBut.addEventHandler(ActionEvent.ACTION, lootButAction);

        EventHandler<ActionEvent> expButAction = actionEvent -> {
            if(expLevel <= 6 && player.getMoney() >= levels.get(expLevel).getCost()){
                paintCir(getCircleExp(expLevel));
                player.takeMoney(levels.get(expLevel).getCost());
                Game.setExpEnemy(Game.getExpEnemy()+levels.get(expLevel).getGain());
                System.out.println(Game.getExpEnemy());
                expLevel++;
                menu.updateUserStats(player);
            }
        };
        expUpgBut.addEventHandler(ActionEvent.ACTION, expButAction);

    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }


    public void createUpgradeList(){
        levels.put(1, new Upgrade(3000,10));
        levels.put(2, new Upgrade(6000,30));
        levels.put(3, new Upgrade(12000,60));
        levels.put(4, new Upgrade(15000,80));
        levels.put(5, new Upgrade(20000,100));
        levels.put(6, new Upgrade(25000,150));
    }

    public Circle getCircleDmg(int level){
        return getCircle(level, dmgCir1, dmgCir2, dmgCir3, dmgCir4, dmgCir5, dmgCir6);
    }

    public Circle getCircleHealth(int level){
        return getCircle(level, healthUpCir1, healthUpCir2, healthUpCir3, healthUpCir4, healthUpCir5, healthUpCir6);
    }

    public Circle getCircleLoot(int level){
        return getCircle(level, lootCir1, lootCir2, lootCir3, lootCir4, lootCir5, lootCir6);
    }

    public Circle getCircleExp(int level){
        return getCircle(level, expCir1, expCir2, expCir3, expCir4, expCir5, expCir6);
    }

    private Circle getCircle(int level, Circle healthUpCir1, Circle healthUpCir2, Circle healthUpCir3, Circle healthUpCir4, Circle healthUpCir5, Circle healthUpCir6) {
        switch (level) {
            case 1 -> {
                return healthUpCir1;
            }
            case 2 -> {
                return healthUpCir2;
            }
            case 3 -> {
                return healthUpCir3;
            }
            case 4 -> {
                return healthUpCir4;
            }
            case 5 -> {
                return healthUpCir5;
            }
            case 6 -> {
                return healthUpCir6;
            }
        }
        return null;
    }

    private void paintCir(Circle cir){
        cir.setFill(Color.valueOf("#6DECB9"));
        FadeTransition fd = new FadeTransition();
        fd.setNode(cir);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.setCycleCount(1);
        fd.setDuration(Duration.millis(900));
        fd.play();

    }



}
